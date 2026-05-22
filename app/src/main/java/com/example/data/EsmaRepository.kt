package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class EsmaNameModel(
    val details: EsmaName,
    val isFavorite: Boolean = false,
    val note: String = ""
)

class EsmaRepository(private val nameStateDao: NameStateDao) {

    // Return all names merged with their local user state (favorites, notes)
    val allNamesFlow: Flow<List<EsmaNameModel>> = nameStateDao.getAllNameStates().map { states ->
        val stateMap = states.associateBy { it.nameId }
        EsmaDataProvider.ALL_NAMES.map { esmaName ->
            val state = stateMap[esmaName.id]
            EsmaNameModel(
                details = esmaName,
                isFavorite = state?.isFavorite ?: false,
                note = state?.personalNote ?: ""
            )
        }
    }

    // Toggle favorite state while preserving notes
    suspend fun toggleFavorite(nameId: Int, isFav: Boolean, currentNote: String) {
        val newState = NameState(
            nameId = nameId,
            isFavorite = isFav,
            personalNote = currentNote,
            updatedAt = System.currentTimeMillis()
        )
        nameStateDao.insertOrUpdateState(newState)
    }

    // Save note for a name while preserving favorite state
    suspend fun saveNote(nameId: Int, note: String, currentFav: Boolean) {
        val newState = NameState(
            nameId = nameId,
            isFavorite = currentFav,
            personalNote = note,
            updatedAt = System.currentTimeMillis()
        )
        nameStateDao.insertOrUpdateState(newState)
    }
}
