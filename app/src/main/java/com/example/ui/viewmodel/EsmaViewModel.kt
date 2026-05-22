package com.example.ui.viewmodel

import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import com.example.receiver.DailyReminderReceiver
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale

class EsmaViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val repository = EsmaRepository(db.nameStateDao())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<EsmaCategory?>(null)
    val selectedCategory: StateFlow<EsmaCategory?> = _selectedCategory.asStateFlow()

    private val _onlyFavorites = MutableStateFlow(false)
    val onlyFavorites: StateFlow<Boolean> = _onlyFavorites.asStateFlow()

    private val _selectedName = MutableStateFlow<EsmaNameModel?>(null)
    val selectedName: StateFlow<EsmaNameModel?> = _selectedName.asStateFlow()

    // Combined Flow containing lists filtered by search, category, and favorite toggles
    val namesList: StateFlow<List<EsmaNameModel>> = combine(
        repository.allNamesFlow,
        _searchQuery,
        _selectedCategory,
        _onlyFavorites
    ) { names, query, category, onlyFavs ->
        names.filter { name ->
            val matchesQuery = query.isEmpty() ||
                    name.details.name.contains(query, ignoreCase = true) ||
                    name.details.pronunciation.contains(query, ignoreCase = true) ||
                    name.details.meaning.contains(query, ignoreCase = true) ||
                    name.details.arabic.contains(query)
            
            val matchesCategory = category == null || name.details.category == category
            val matchesFavorites = !onlyFavs || name.isFavorite

            matchesQuery && matchesCategory && matchesFavorites
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // TTS management
    private var tts: TextToSpeech? = null
    private val _isPlayingAudio = MutableStateFlow<Int?>(null) // Contains id of playing esma, null if none
    val isPlayingAudio: StateFlow<Int?> = _isPlayingAudio.asStateFlow()

    private val sharedPrefs = application.getSharedPreferences("esma_settings", Context.MODE_PRIVATE)
    private val _reminderEnabled = MutableStateFlow(sharedPrefs.getBoolean("reminder_enabled", true))
    val reminderEnabled: StateFlow<Boolean> = _reminderEnabled.asStateFlow()

    init {
        // Initialize TTS
        tts = TextToSpeech(application) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale("tr", "TR")
            }
        }
        
        // Ensure automated reminder is set up
        if (_reminderEnabled.value) {
            DailyReminderReceiver.scheduleDailyReminder(application)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: EsmaCategory?) {
        _selectedCategory.value = category
    }

    fun toggleOnlyFavorites() {
        _onlyFavorites.value = !_onlyFavorites.value
    }

    fun selectName(name: EsmaNameModel?) {
        _selectedName.value = name
    }

    fun selectNameById(id: Int) {
        viewModelScope.launch {
            repository.allNamesFlow.firstOrNull()?.find { it.details.id == id }?.let {
                _selectedName.value = it
            }
        }
    }

    fun toggleFavorite(name: EsmaNameModel) {
        viewModelScope.launch {
            repository.toggleFavorite(name.details.id, !name.isFavorite, name.note)
            if (_selectedName.value?.details?.id == name.details.id) {
                _selectedName.value = _selectedName.value?.copy(isFavorite = !name.isFavorite)
            }
        }
    }

    fun saveNote(nameId: Int, note: String, currentFav: Boolean) {
        viewModelScope.launch {
            repository.saveNote(nameId, note, currentFav)
            if (_selectedName.value?.details?.id == nameId) {
                _selectedName.value = _selectedName.value?.copy(note = note)
            }
        }
    }

    fun playPronunciation(name: EsmaNameModel) {
        val speakText = "${name.details.name}. Telaffuzu: ${name.details.pronunciation}. Anlamı: ${name.details.meaning}"
        _isPlayingAudio.value = name.details.id
        
        tts?.setOnUtteranceProgressListener(object : android.speech.tts.UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {}
            override fun onDone(utteranceId: String?) {
                _isPlayingAudio.value = null
            }
            override fun onError(utteranceId: String?) {
                _isPlayingAudio.value = null
            }
        })

        val params = android.os.Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, name.details.id.toString())
        }
        tts?.speak(speakText, TextToSpeech.QUEUE_FLUSH, params, name.details.id.toString())
    }

    fun toggleReminder(active: Boolean) {
        _reminderEnabled.value = active
        sharedPrefs.edit().putBoolean("reminder_enabled", active).apply()
        val context = getApplication<Application>()
        if (active) {
            DailyReminderReceiver.scheduleDailyReminder(context)
        } else {
            // Cancel alarm
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? android.app.AlarmManager
            if (alarmManager != null) {
                val intent = android.content.Intent(context, DailyReminderReceiver::class.java).apply {
                    action = "com.example.ACTION_DAILY_REMINDER"
                }
                val pendingIntent = android.app.PendingIntent.getBroadcast(
                    context,
                    101,
                    intent,
                    android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
                )
                alarmManager.cancel(pendingIntent)
            }
        }
    }

    fun triggerImmediateReminder() {
        DailyReminderReceiver.showNotification(getApplication())
    }

    override fun onCleared() {
        super.onCleared()
        tts?.stop()
        tts?.shutdown()
    }
}
