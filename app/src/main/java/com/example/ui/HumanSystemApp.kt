package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.ConfirmationDialog
import com.example.ui.screens.RecordEmotionsScreen
import com.example.ui.screens.SystemHomeScreen
import com.example.ui.theme.AppThemeMode
import com.example.ui.theme.HumanSystemTheme
import com.example.ui.viewmodel.HumanSystemViewModel
import kotlinx.coroutines.launch

import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.AlertDialog
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import com.example.ui.screens.AchievementsScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HumanSystemApp(
    viewModel: HumanSystemViewModel = viewModel()
) {
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager.current

    // HorizontalPager state for swipeable tabs (0: System, 1: Record, 2: Achievements)
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    var showMenu by remember { mutableStateOf(false) }
    var showClearConfirm by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    var showExportDialog by remember { mutableStateOf(false) }
    var showImportDialog by remember { mutableStateOf(false) }
    var exportContent by remember { mutableStateOf("") }
    var importInputText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.userMessage.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    HumanSystemTheme(themeMode = themeMode) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primaryContainer,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.DarkMode,
                                        contentDescription = "Human System",
                                        tint = Color(0xFFFFD166),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Human System",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = (-0.5).sp
                                )
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = { showMenu = !showMenu },
                            modifier = Modifier.testTag("more_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More Options"
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Export Backup File") },
                                onClick = {
                                    showMenu = false
                                    exportContent = viewModel.exportBackupJson()
                                    showExportDialog = true
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.FileDownload, contentDescription = "Export")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Import Backup File") },
                                onClick = {
                                    showMenu = false
                                    importInputText = ""
                                    showImportDialog = true
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.FileUpload, contentDescription = "Import")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Appearance Theme") },
                                onClick = {
                                    showMenu = false
                                    showThemeDialog = true
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.SettingsBrightness, contentDescription = null)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Clear All History") },
                                onClick = {
                                    showMenu = false
                                    showClearConfirm = true
                                },
                                leadingIcon = {
                                    Icon(Icons.Default.DeleteSweep, contentDescription = null)
                                }
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
            },
            bottomBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 28.dp, top = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surface,
                        tonalElevation = 8.dp,
                        shadowElevation = 12.dp,
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                        ),
                        modifier = Modifier.testTag("main_navigation_bar")
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            FloatingPillTab(
                                selected = pagerState.currentPage == 0,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(0)
                                    }
                                },
                                icon = if (pagerState.currentPage == 0) Icons.Default.DarkMode else Icons.Outlined.DarkMode,
                                label = "System",
                                testTag = "tab_system"
                            )

                            FloatingPillTab(
                                selected = pagerState.currentPage == 1,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(1)
                                    }
                                },
                                icon = if (pagerState.currentPage == 1) Icons.Default.AddCircle else Icons.Outlined.AddCircleOutline,
                                label = "Record",
                                testTag = "tab_record"
                            )

                            FloatingPillTab(
                                selected = pagerState.currentPage == 2,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(2)
                                    }
                                },
                                icon = if (pagerState.currentPage == 2) Icons.Default.EmojiEvents else Icons.Outlined.EmojiEvents,
                                label = "Mastery",
                                testTag = "tab_achievements"
                            )
                        }
                    }
                }
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            // Horizontal Pager enabling horizontal swipe between 3 tabs
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("horizontal_tabs_pager")
            ) { page ->
                when (page) {
                    0 -> SystemHomeScreen(
                        viewModel = viewModel,
                        onNavigateToRecord = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                    1 -> RecordEmotionsScreen(
                        viewModel = viewModel,
                        onRecordSaved = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        }
                    )
                    2 -> AchievementsScreen(
                        viewModel = viewModel
                    )
                }
            }
        }

        // Export Backup Dialog
        if (showExportDialog) {
            AlertDialog(
                onDismissRequest = { showExportDialog = false },
                icon = {
                    Icon(
                        imageVector = Icons.Default.FileDownload,
                        contentDescription = "Export Backup",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                title = {
                    Text(
                        text = "Export Local History Backup",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Here is your raw offline backup text (JSON format). You can copy this text to store in a notes app or local text file.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        OutlinedTextField(
                            value = exportContent,
                            onValueChange = {},
                            readOnly = true,
                            maxLines = 8,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            clipboardManager.setText(AnnotatedString(exportContent))
                            showExportDialog = false
                        }
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy to Clipboard")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showExportDialog = false }) {
                        Text("Close")
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.testTag("export_backup_dialog")
            )
        }

        // Import Backup Dialog
        if (showImportDialog) {
            AlertDialog(
                onDismissRequest = { showImportDialog = false },
                icon = {
                    Icon(
                        imageVector = Icons.Default.FileUpload,
                        contentDescription = "Import Backup",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                },
                title = {
                    Text(
                        text = "Import History Backup",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Paste your exported backup text (JSON) below to restore your past check-in logs into this device.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        OutlinedTextField(
                            value = importInputText,
                            onValueChange = { importInputText = it },
                            placeholder = { Text("Paste JSON backup content here...") },
                            maxLines = 8,
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("import_backup_input"),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (importInputText.isNotBlank()) {
                                viewModel.importBackupJson(importInputText) { success, _ ->
                                    if (success) showImportDialog = false
                                }
                            }
                        },
                        enabled = importInputText.isNotBlank(),
                        modifier = Modifier.testTag("confirm_import_button")
                    ) {
                        Text("Restore Backup")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showImportDialog = false }) {
                        Text("Cancel")
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.testTag("import_backup_dialog")
            )
        }

        // Theme Selector Dialog
        if (showThemeDialog) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showThemeDialog = false },
                title = {
                    Text(
                        text = "Choose App Theme",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                },
                text = {
                    Column {
                        AppThemeOptionRow(
                            title = "System Default",
                            subtitle = "Follows Android system appearance",
                            icon = Icons.Default.SettingsBrightness,
                            isSelected = themeMode == AppThemeMode.SYSTEM,
                            onClick = {
                                viewModel.setThemeMode(AppThemeMode.SYSTEM)
                                showThemeDialog = false
                            }
                        )
                        AppThemeOptionRow(
                            title = "Light Theme",
                            subtitle = "Clean, high-contrast daytime layout",
                            icon = Icons.Default.LightMode,
                            isSelected = themeMode == AppThemeMode.LIGHT,
                            onClick = {
                                viewModel.setThemeMode(AppThemeMode.LIGHT)
                                showThemeDialog = false
                            }
                        )
                        AppThemeOptionRow(
                            title = "Dark Theme",
                            subtitle = "Deep indigo eye-safe night layout",
                            icon = Icons.Default.DarkMode,
                            isSelected = themeMode == AppThemeMode.DARK,
                            onClick = {
                                viewModel.setThemeMode(AppThemeMode.DARK)
                                showThemeDialog = false
                            }
                        )
                    }
                },
                confirmButton = {
                    androidx.compose.material3.TextButton(onClick = { showThemeDialog = false }) {
                        Text("Dismiss")
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.testTag("theme_dialog")
            )
        }

        // Clear All Confirmation Dialog
        if (showClearConfirm) {
            ConfirmationDialog(
                title = "Clear All Human System Logs?",
                message = "This will permanently remove all feeling entries and calculated states from your local device database. This action cannot be reversed.",
                confirmButtonText = "Clear All",
                isDestructive = true,
                onConfirm = {
                    viewModel.clearAllHistory()
                    showClearConfirm = false
                },
                onDismiss = { showClearConfirm = false }
            )
        }
    }
}

@Composable
fun AppThemeOptionRow(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f) else Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .testTag("theme_option_${title.lowercase().replace(" ", "_")}")
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun FloatingPillTab(
    selected: Boolean,
    onClick: () -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    testTag: String
) {
    val animatedBgColor by androidx.compose.animation.animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
        label = "pillTabBg"
    )
    val animatedContentColor by androidx.compose.animation.animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "pillTabContent"
    )

    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = animatedBgColor,
        modifier = Modifier.testTag(testTag)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = animatedContentColor,
                modifier = Modifier.size(19.dp)
            )
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    letterSpacing = 0.1.sp
                ),
                maxLines = 1,
                softWrap = false,
                color = animatedContentColor
            )
        }
    }
}
