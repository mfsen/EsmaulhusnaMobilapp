package com.example

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.*
import com.example.ui.components.EsmaDecorativeCanvas
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.EsmaViewModel
import com.example.util.PdfExporter
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.pager.PagerState

class MainActivity : ComponentActivity() {
    private var activeViewModel: EsmaViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initialNameId = intent?.getIntExtra("NAME_ID", -1) ?: -1

        setContent {
            var darkThemeMode by rememberSaveable { mutableStateOf<Boolean?>(null) }
            val resolvedDarkTheme = darkThemeMode ?: isSystemInDarkTheme()

            MyApplicationTheme(darkTheme = resolvedDarkTheme) {
                val viewModel: EsmaViewModel = viewModel()
                activeViewModel = viewModel

                // Route to clicked notification name if exists
                LaunchedEffect(initialNameId) {
                    if (initialNameId != -1) {
                        viewModel.selectNameById(initialNameId)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EsmaAppDashboard(
                        viewModel = viewModel,
                        isDark = resolvedDarkTheme,
                        onToggleTheme = {
                            darkThemeMode = !resolvedDarkTheme
                        }
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val nameId = intent.getIntExtra("NAME_ID", -1)
        if (nameId != -1) {
            activeViewModel?.selectNameById(nameId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EsmaAppDashboard(
    viewModel: EsmaViewModel,
    isDark: Boolean,
    onToggleTheme: () -> Unit
) {
    val context = LocalContext.current
    val names by viewModel.namesList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val onlyFavorites by viewModel.onlyFavorites.collectAsState()
    val selectedName by viewModel.selectedName.collectAsState()
    val isPlayingAudioId by viewModel.isPlayingAudio.collectAsState()
    val reminderEnabled by viewModel.reminderEnabled.collectAsState()

    // Request notification permission for Android 13+ Safely
    var hasNotificationPermission by remember {
        mutableStateOf(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            } else {
                true
            }
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        hasNotificationPermission = isGranted
        if (isGranted) {
            viewModel.toggleReminder(true)
            Toast.makeText(context, "Bildirim hatırlatıcı aktif edildi.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.toggleReminder(false)
            Toast.makeText(context, "Bildirim izni reddedildi.", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Esma-ül Hüsna",
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        letterSpacing = 1.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageIcon(Icons.Filled.MenuBook),
                        contentDescription = "Kitap",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                },
                actions = {
                    // Quick Action: Export Notes PDF
                    IconButton(
                        onClick = { PdfExporter.exportNotesToPdf(context, names) },
                        modifier = Modifier
                            .testTag("pdf_export_top_button")
                            .padding(end = 4.dp),
                        colors = IconButtonDefaults.iconButtonColors()
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.PictureAsPdf,
                            contentDescription = "PDF Not Çıktısı Al",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    // Theme Toggle
                    IconButton(
                        onClick = onToggleTheme,
                        modifier = Modifier.testTag("theme_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                            contentDescription = "Tema Değiştir",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                // Header Spiritual Banner
                SpiritualBannerCard()

                Spacer(modifier = Modifier.height(12.dp))

                // Search Bar with test tags
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_field_input"),
                    placeholder = { Text("İsim veya anlam ara... (Örn: Rahman)") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Arama İkonu")
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Temizle")
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Advanced Horizontal Scrollable Category Filter Chips
                HorizontalCategoryFilter(
                    selectedCategory = selectedCategory,
                    onSelectCategory = { viewModel.selectCategory(it) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Personal Filters & Settings Card
                PersonalFiltersBar(
                    onlyFavorites = onlyFavorites,
                    onToggleFavorites = { viewModel.toggleOnlyFavorites() },
                    reminderEnabled = reminderEnabled,
                    onToggleReminder = { enabled ->
                        if (enabled && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && !hasNotificationPermission) {
                            permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                        } else {
                            viewModel.toggleReminder(enabled)
                            Toast.makeText(
                                context,
                                if (enabled) "Günlük hatırlatıcı kuruldu." else "Hatırlatıcı kapatıldı.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    onTriggerTestReminder = {
                        viewModel.triggerImmediateReminder()
                        Toast.makeText(context, "Saniyeler içinde örnek bir zikir tefekkür bildirimi ulaştırılacaktır.", Toast.LENGTH_LONG).show()
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Main Esma Names list with proper Compose testTags
                if (names.isEmpty()) {
                    EmptyHistoryView(onlyFavorites)
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("names_lazy_list")
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(names, key = { it.details.id }) { item ->
                            EsmaItemCard(
                                item = item,
                                isPlaying = isPlayingAudioId == item.details.id,
                                onCardClick = { viewModel.selectName(item) },
                                onToggleFavClick = { viewModel.toggleFavorite(item) },
                                onSpeakerClick = { viewModel.playPronunciation(item) }
                            )
                        }
                    }
                }
            }

            // Beautiful slide-in full-screen focal Card for the selected Name Details as a Swipeable Reel!
            AnimatedVisibility(
                visible = selectedName != null,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                modifier = Modifier.fillMaxSize()
            ) {
                if (names.isNotEmpty() && selectedName != null) {
                    val initialIndex = remember {
                        names.indexOfFirst { it.details.id == selectedName?.details?.id }.coerceAtLeast(0)
                    }
                    val pagerState = rememberPagerState(initialPage = initialIndex) { names.size }

                    // Sync pager current page with ViewModel selectedName
                    LaunchedEffect(pagerState.currentPage) {
                        if (pagerState.currentPage in names.indices) {
                            val activeEsma = names[pagerState.currentPage]
                            if (selectedName?.details?.id != activeEsma.details.id) {
                                viewModel.selectName(activeEsma)
                            }
                        }
                    }

                    // Also sync selectedName change (from outer list) with pagerState scroll
                    LaunchedEffect(selectedName) {
                        selectedName?.let { sel ->
                            val index = names.indexOfFirst { it.details.id == sel.details.id }
                            if (index >= 0 && index != pagerState.currentPage && !pagerState.isScrollInProgress) {
                                pagerState.scrollToPage(index)
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable { viewModel.selectName(null) },
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.92f)
                                .clickable(enabled = false) {} // Prevent click-through pass
                                .testTag("esma_detailed_form"),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background
                            ),
                            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
                        ) {
                            VerticalPager(
                                state = pagerState,
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) { page ->
                                val esmaItem = names.getOrNull(page)
                                if (esmaItem != null) {
                                    EsmaDetailContent(
                                        item = esmaItem,
                                        isPlaying = isPlayingAudioId == esmaItem.details.id,
                                        onClose = { viewModel.selectName(null) },
                                        onToggleFav = { viewModel.toggleFavorite(esmaItem) },
                                        onPlayAudio = { viewModel.playPronunciation(esmaItem) },
                                        onSaveNote = { noteText ->
                                            viewModel.saveNote(esmaItem.details.id, noteText, esmaItem.isFavorite)
                                            Toast.makeText(context, "Tefekkür notunuz veri tabanına kaydedildi.", Toast.LENGTH_SHORT).show()
                                        },
                                        currentPage = page,
                                        totalPages = names.size
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SpiritualBannerCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(32.dp)),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x66161B22) // Elegant slate deep container
        ),
        border = BorderStroke(1.dp, Color(0xFF1E293B)) // border-slate-800 look
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Elegant category identifier / state header
            Text(
                text = "GÜNÜN TEFEKKÜRÜ",
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.secondary, // Softemerald representation
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            // Large Arabic calligraphy displaying the primary attribute
            Text(
                text = "الرَّحْمَنُ",
                fontWeight = FontWeight.Normal,
                fontSize = 44.sp,
                color = MaterialTheme.colorScheme.primary, // GoldAmber
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Latinized pronunciation
            Text(
                text = "er-Rahmân",
                fontWeight = FontWeight.Light,
                fontSize = 22.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Full meaning statement formatted nicely with quotation marks
            Text(
                text = "\"Dünyada bütün mahlükata merhamet eden, şefkat gösteren, hayır ve iyilikte bulunan.\"",
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center,
                color = Color(0xFF94A3B8) // Slate 400 subtext
            )
            
            Spacer(modifier = Modifier.height(6.dp))
            
            // Subtitle Verse / Source
            Text(
                text = "A'râf Suresi, 180. Ayet",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun HorizontalCategoryFilter(
    selectedCategory: EsmaCategory?,
    onSelectCategory: (EsmaCategory?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // "Tümü" filter chip
        FilterChip(
            selected = selectedCategory == null,
            onClick = { onSelectCategory(null) },
            label = { Text("Tümü") },
            leadingIcon = {
                Icon(Icons.Outlined.AllInclusive, contentDescription = "Tümü", modifier = Modifier.size(16.dp))
            },
            shape = RoundedCornerShape(18.dp)
        )

        // Categories map
        EsmaCategory.values().forEach { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = { onSelectCategory(category) },
                label = { Text(category.displayName) },
                leadingIcon = {
                    val icon = when (category) {
                        EsmaCategory.RAHMET -> Icons.Outlined.Favorite
                        EsmaCategory.KUDRET -> Icons.Outlined.Security
                        EsmaCategory.ILIM -> Icons.Outlined.Psychology
                        EsmaCategory.YARATILIS -> Icons.Outlined.SelfImprovement
                        EsmaCategory.TENZIH -> Icons.Outlined.AutoAwesome
                    }
                    Icon(icon, contentDescription = null, modifier = Modifier.size(16.dp))
                },
                shape = RoundedCornerShape(18.dp)
            )
        }
    }
}

@Composable
fun PersonalFiltersBar(
    onlyFavorites: Boolean,
    onToggleFavorites: () -> Unit,
    reminderEnabled: Boolean,
    onToggleReminder: (Boolean) -> Unit,
    onTriggerTestReminder: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Favorite Toggle Button
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onToggleFavorites() }
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (onlyFavorites) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favori Filtresi",
                        tint = if (onlyFavorites) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Kişisel Favorilerim",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Daily Reminder Toggle
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Günlük Hatırlatıcı",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Switch(
                        checked = reminderEnabled,
                        onCheckedChange = { onToggleReminder(it) },
                        modifier = Modifier
                            .scale(0.8f)
                            .testTag("reminder_switch")
                    )
                }
            }

            // Test Reminder Button
            if (reminderEnabled) {
                Button(
                    onClick = onTriggerTestReminder,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .testTag("test_reminder_trigger"),
                    contentPadding = PaddingValues(vertical = 4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                        contentColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        Icons.Default.NotificationsActive,
                        contentDescription = "Zikir Öner",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Notifikasyonla Hemen Bir Esma Öner",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Composable
fun EsmaItemCard(
    item: EsmaNameModel,
    isPlaying: Boolean,
    onCardClick: () -> Unit,
    onToggleFavClick: () -> Unit,
    onSpeakerClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .testTag("esma_card_${item.details.id}"),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (isPlaying) MaterialTheme.colorScheme.secondary else Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circle index layout
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.details.id.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Text names details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.details.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (item.note.isNotBlank()) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            Icons.Filled.NoteAlt,
                            contentDescription = "Not kaydedilmiş",
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
                Text(
                    text = item.details.meaning,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Arabic calligraphy display
            Text(
                text = item.details.arabic,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            // Dynamic volume speaker controls
            IconButton(
                onClick = onSpeakerClick,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Filled.VolumeUp else Icons.Filled.VolumeMute,
                    contentDescription = "Sesli Telaffuz Dinle",
                    tint = if (isPlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    modifier = Modifier.size(20.dp)
                )
            }

            // Bookmark star controls
            IconButton(
                onClick = onToggleFavClick,
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (item.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                    contentDescription = "Favorilere Ekle",
                    tint = if (item.isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}

@Composable
fun EsmaDetailContent(
    item: EsmaNameModel,
    isPlaying: Boolean,
    onClose: () -> Unit,
    onToggleFav: () -> Unit,
    onPlayAudio: () -> Unit,
    onSaveNote: (String) -> Unit,
    currentPage: Int,
    totalPages: Int
) {
    val context = LocalContext.current
    var noteText by remember(item.details.id) { mutableStateOf(item.note) }

    // Breathing pulse scale animation for audios
    val infiniteTransition = rememberInfiniteTransition()
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.18f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        // Drag handle bar
        Box(
            modifier = Modifier
                .size(40.dp, 4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(MaterialTheme.colorScheme.outlineVariant)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Reel Metadata & Swiper Indicator
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Esma Tefekkürü (${currentPage + 1} / $totalPages)",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp,
                color = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = "Reel Gibi Dikey Kaydırın",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Detail Header Actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Geri Dön")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Quick Social Media Share Button
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "Esma-ül Hüsna: ${item.details.name}\n" +
                                        "Arapça Yazılışı: ${item.details.arabic}\n" +
                                        "Anlamı: ${item.details.meaning}\n\n" +
                                        "Tefekkür Boyutu: ${item.details.description}\n\n" +
                                        (if (noteText.isNotBlank()) "Kişisel Notum: $noteText\n\n" else "") +
                                        "Esma-ül Hüsna uygulaması ile paylaşıldı."
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Paylaş"))
                    }
                ) {
                    Icon(Icons.Default.Share, contentDescription = "Sosyal Medyada Paylaş")
                }

                // Bookmark Star
                IconButton(onClick = onToggleFav) {
                    Icon(
                        imageVector = if (item.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                        contentDescription = "Favorilere Kaydet",
                        tint = if (item.isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Calligraphy Showcase animated vector canvas representing classic Islamic star
        EsmaDecorativeCanvas(
            item = item,
            isPulsing = isPlaying,
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Core Title Info
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.details.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(10.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = item.details.category.displayName,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }

        // Fetch deep spiritual theological metadata
        val spiritualDetails = remember(item.details.id) {
            EsmaSpiritualDetails.getDetails(item.details)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Meaning Quote Callout Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f)
            ),
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Meal",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Şerhli Açıklayıcı Meali",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = item.details.meaning,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Section Title for Spiritual Exploration
        Text(
            text = "Dini & Tefekkür Boyutları",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Expandable Accordions for Quran, Hadith, Reflection & Secret with explicit rich citations
        TheologicalExpandableCard(
            title = "Kur'an-ı Kerim Tecellileri",
            icon = Icons.Outlined.MenuBook,
            content = spiritualDetails.quranReference,
            badgeText = "Ayet-i Kerime",
            sourceText = "T.C. Diyanet İşleri Meali, Kur'an Yolu Tefsiri ve Elmalılı Hamdi Yazır Şerhi"
        )

        TheologicalExpandableCard(
            title = "Hadis-i Şerif İncileri",
            icon = Icons.Outlined.AutoAwesome,
            content = spiritualDetails.hadithReference,
            badgeText = "Hadis-i Şerif",
            badgeColor = MaterialTheme.colorScheme.tertiary,
            sourceText = "Kütüb-i Sitte Muhaddisleri (Sahih-i Buhârî, Sahih-i Müslim ve Tirmizî Derlemeleri)"
        )

        TheologicalExpandableCard(
            title = "Tefekkür & Yaşam Pratiği",
            icon = Icons.Outlined.Psychology,
            content = spiritualDetails.reflectionGuide,
            badgeText = "Ahlaklanma",
            sourceText = "İmam Gazâlî Hazretleri - el-Maksadü'l-Esna fi Şerhi Esmaillahi'l-Hüsna"
        )

        TheologicalExpandableCard(
            title = "Mana Sırrı & Hususi Niyaz",
            icon = Icons.Outlined.Lock,
            content = spiritualDetails.divineSecret,
            badgeText = "Sır & Dua",
            badgeColor = MaterialTheme.colorScheme.primary,
            sourceText = "Fahreddin er-Râzî Hazretleri - Levâmiu'l-Beyyinât fi'l-Esma ve's-Sıfat"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Audio player action button with pulsing effect
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.08f))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .scale(if (isPlaying) pulseScale else 1f)
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            if (isPlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Filled.VolumeUp else Icons.Filled.PlayArrow,
                        contentDescription = "Oynat",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Sesli Telaffuz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (isPlaying) "Şu anda sesli okunuyor..." else "İsmin telaffuzunu ve mealini dinleyin",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Button(
                onClick = onPlayAudio,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPlaying) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = if (isPlaying) "Durdur" else "Dinle",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Custom Note taking section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.EditNote,
                        contentDescription = "Not Not Ekle",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Kişisel Tefekkür Notlarım",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .testTag("note_input_field"),
                    placeholder = { Text("Boş zamanlarımda zikir çekerken veya bu isim üzerine derin düşüncelere daldığımda buraya notlarımı alabilirim...") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    ),
                    maxLines = 5,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { onSaveNote(noteText) },
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(38.dp)
                        .testTag("save_note_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        "Notumu Kaydet",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Reel Drag Up / Switch Guide Indicator
        if (currentPage < totalPages - 1) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Sonraki isme kaydır",
                    tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.6f),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Sonraki Esma için yukarı çekin",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f),
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Text(
                text = "Son Esma-ül Hüsna'ya ulaştınız.",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 16.dp)
            )
        }
    }
}

@Composable
fun EmptyHistoryView(onlyFavorites: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = if (onlyFavorites) Icons.Outlined.StarBorder else Icons.Outlined.MenuBook,
                contentDescription = "Bulunamadı",
                modifier = Modifier.size(72.dp),
                tint = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (onlyFavorites) "Kayıtlı Favori İsminiz Bulunmuyor" else "Sonuç Bulunamadı",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (onlyFavorites) "Esma kartlarındaki yıldız simgelerine basarak bu bölüme ekleyebilir ve not alabilirsiniz." else "Lütfen arama kelimenizi veya filtre seçimlerinizi kontrol edin.",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

// Inline fallback for compose menuBook icons
fun imageIcon(p0: androidx.compose.ui.graphics.vector.ImageVector) = p0

@Composable
fun TheologicalExpandableCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: String,
    badgeText: String? = null,
    badgeColor: Color = MaterialTheme.colorScheme.secondary,
    sourceText: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (expanded) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.22f) 
                             else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (expanded) MaterialTheme.colorScheme.primary.copy(alpha = 0.35f) 
                    else Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (badgeText != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(badgeColor.copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = badgeText,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = badgeColor
                            )
                        }
                    }
                }
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) "Küçült" else "Genişlet",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(20.dp)
                )
            }
            
            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Divider(
                        modifier = Modifier.padding(bottom = 12.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f)
                    )
                    Text(
                        text = content,
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Justify
                    )
                    if (sourceText != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "Şerhli Kaynak: $sourceText",
                                fontSize = 11.sp,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.82f)
                            )
                        }
                    }
                }
            }
        }
    }
}
