package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.Emotion
import com.example.model.EmotionCatalog
import com.example.model.EmotionCategory
import com.example.ui.components.ActionRecommendationCard
import com.example.ui.components.EmotionChip
import com.example.ui.components.EmotionDetailDialog
import com.example.ui.components.SystemStatusCard
import com.example.ui.viewmodel.HumanSystemViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecordEmotionsScreen(
    viewModel: HumanSystemViewModel,
    onRecordSaved: () -> Unit,
    modifier: Modifier = Modifier
) {
    var activeSubTab by remember { mutableIntStateOf(0) } // 0: Log Feelings, 1: Emotion Library

    val selectedEmotions by viewModel.selectedEmotions.collectAsStateWithLifecycle()
    val intensity by viewModel.intensity.collectAsStateWithLifecycle()
    val contextNote by viewModel.contextNote.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val currentDiagnosis by viewModel.currentDiagnosis.collectAsStateWithLifecycle()
    val filteredEmotions by viewModel.filteredEmotions.collectAsStateWithLifecycle()
    val viewingEmotionDetail by viewModel.viewingEmotionDetail.collectAsStateWithLifecycle()

    if (activeSubTab == 1) {
        EmotionLibraryScreen(
            viewModel = viewModel,
            onNavigateToRecord = { activeSubTab = 0 },
            modifier = modifier
        )
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .testTag("record_emotions_screen"),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Screen Header with Top Quick Save / Entry Button
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Record Feelings",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = (-0.5).sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Catalog of ${EmotionCatalog.totalCount} distinct emotional states across 13 categories",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Entry button on top (icon-only with count badge)
                    if (selectedEmotions.isNotEmpty()) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = MaterialTheme.colorScheme.onTertiary
                                ) {
                                    Text("${selectedEmotions.size}")
                                }
                            }
                        ) {
                            FilledIconButton(
                                onClick = {
                                    viewModel.saveCalculation {
                                        onRecordSaved()
                                    }
                                },
                                shape = RoundedCornerShape(14.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ),
                                modifier = Modifier
                                    .size(46.dp)
                                    .testTag("top_save_entry_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = "Save System Calculation (${selectedEmotions.size} feelings)",
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    } else {
                        FilledTonalIconButton(
                            onClick = { },
                            enabled = false,
                            shape = RoundedCornerShape(14.dp),
                            modifier = Modifier
                                .size(46.dp)
                                .testTag("top_save_entry_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Save,
                                contentDescription = "Save Disabled (Select feelings first)",
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }
            }

            // Sub-Tab Switcher: Record Feelings vs Emotion Library Guide
            item {
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { activeSubTab = 0 }
                                .testTag("subtab_log_feelings"),
                            color = if (activeSubTab == 0) MaterialTheme.colorScheme.surface else Color.Transparent,
                            shadowElevation = if (activeSubTab == 0) 2.dp else 0.dp,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = if (activeSubTab == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Log Feelings",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = if (activeSubTab == 0) FontWeight.Bold else FontWeight.Medium
                                    ),
                                    color = if (activeSubTab == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { activeSubTab = 1 }
                                .testTag("subtab_emotion_library"),
                            color = if (activeSubTab == 1) MaterialTheme.colorScheme.surface else Color.Transparent,
                            shadowElevation = if (activeSubTab == 1) 2.dp else 0.dp,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = if (activeSubTab == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Emotion Library",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = if (activeSubTab == 1) FontWeight.Bold else FontWeight.Medium
                                    ),
                                    color = if (activeSubTab == 1) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }

            // Quick Search Bar at top of Record area
            item {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { viewModel.setSearchQuery(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("emotion_search_input"),
                        placeholder = { Text("Search 520+ feelings (e.g. ecstatic, grounded, anxious, awe...)") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear search",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (searchQuery.isBlank()) "${filteredEmotions.size} feelings ready to select" else "${filteredEmotions.size} matches found for \"$searchQuery\"",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        if (searchQuery.isNotEmpty() || selectedCategory != null) {
                            TextButton(
                                onClick = {
                                    viewModel.setSearchQuery("")
                                    viewModel.setSelectedCategory(null)
                                },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text("Reset Filters", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }

            // Colorful Category Filter Bar
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Categories:",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedCategory == null,
                            onClick = { viewModel.setSelectedCategory(null) },
                            label = { Text("All (${EmotionCatalog.totalCount})") },
                            shape = RoundedCornerShape(12.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            modifier = Modifier.testTag("category_filter_all")
                        )

                        EmotionCategory.values().forEach { category ->
                            val categoryCount = EmotionCatalog.allEmotions.count { it.category == category }
                            val catColor = Color(category.colorHex)
                            val isSelected = selectedCategory == category

                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    viewModel.setSelectedCategory(
                                        if (selectedCategory == category) null else category
                                    )
                                },
                                label = { Text("${category.emoji} ${category.displayName} ($categoryCount)") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = category.getCategoryIcon(),
                                        contentDescription = category.displayName,
                                        tint = if (isSelected) Color.White else catColor,
                                        modifier = Modifier.size(16.dp)
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = catColor,
                                    selectedLabelColor = Color.White,
                                    containerColor = catColor.copy(alpha = 0.12f),
                                    labelColor = MaterialTheme.colorScheme.onSurface
                                ),
                                border = BorderStroke(1.dp, if (isSelected) catColor else catColor.copy(alpha = 0.35f)),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.testTag("category_filter_${category.name}")
                            )
                        }
                    }
                }
            }

            // Selected Emotions Bar Dock
            if (selectedEmotions.isNotEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("selected_emotions_dock"),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        ),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Surface(
                                        shape = CircleShape,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(22.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = "${selectedEmotions.size}",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onPrimary,
                                                    fontSize = 11.sp
                                                )
                                            )
                                        }
                                    }
                                    Text(
                                        text = "Selected Feelings (${selectedEmotions.size})",
                                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                        color = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }

                                TextButton(
                                    onClick = { viewModel.clearSelectedEmotions() },
                                    modifier = Modifier.testTag("clear_selected_button")
                                ) {
                                    Text("Clear all", style = MaterialTheme.typography.labelMedium)
                                }
                            }

                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                selectedEmotions.forEach { emotion ->
                                    val catColor = Color(emotion.category.colorHex)
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.surface,
                                        border = BorderStroke(1.dp, catColor.copy(alpha = 0.5f))
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(start = 10.dp, end = 4.dp, top = 4.dp, bottom = 4.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text(
                                                text = emotion.name,
                                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            IconButton(
                                                onClick = { viewModel.toggleEmotionSelection(emotion) },
                                                modifier = Modifier.size(20.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Close,
                                                    contentDescription = "Remove ${emotion.name}",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.size(14.dp)
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

            // Emotions Grouped By Category
            item {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    if (filteredEmotions.isEmpty()) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "No matching emotions found",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Try adjusting your search query or selected category filter.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    } else {
                        // Group the filtered emotions by their actual categories
                        val categoriesInFiltered = EmotionCategory.values().filter { cat ->
                            filteredEmotions.any { it.category == cat }
                        }

                        categoriesInFiltered.forEach { category ->
                            val categoryEmotions = filteredEmotions.filter { it.category == category }
                            val catColor = Color(category.colorHex)

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("category_section_${category.name}"),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface
                                ),
                                border = BorderStroke(1.dp, catColor.copy(alpha = 0.3f))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    // Category Header Banner
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Surface(
                                                shape = CircleShape,
                                                color = catColor,
                                                modifier = Modifier.size(28.dp)
                                            ) {
                                                Box(contentAlignment = Alignment.Center) {
                                                    Icon(
                                                        imageVector = category.getCategoryIcon(),
                                                        contentDescription = null,
                                                        tint = Color.White,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                }
                                            }

                                            Column {
                                                Text(
                                                    text = "${category.emoji} ${category.displayName}",
                                                    style = MaterialTheme.typography.titleMedium.copy(
                                                        fontWeight = FontWeight.Bold
                                                    ),
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Text(
                                                    text = category.description,
                                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp),
                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                )
                                            }
                                        }

                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = catColor.copy(alpha = 0.15f)
                                        ) {
                                            Text(
                                                text = "${categoryEmotions.size}",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = catColor
                                                ),
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }

                                    // Category Emotions Flow
                                    FlowRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        categoryEmotions.forEach { emotion ->
                                            val isSelected = selectedEmotions.any { it.id == emotion.id }
                                            EmotionChip(
                                                emotion = emotion,
                                                isSelected = isSelected,
                                                onClick = { viewModel.toggleEmotionSelection(emotion) },
                                                onInfoClick = { viewModel.setViewingEmotionDetail(emotion) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Intensity Selector
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Tune,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = "Overall Sensation Intensity",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            val intensityLabel = when (intensity) {
                                1 -> "Level 1: Subtle"
                                2 -> "Level 2: Mild"
                                3 -> "Level 3: Moderate"
                                4 -> "Level 4: Strong"
                                else -> "Level 5: Acute"
                            }
                            Text(
                                text = intensityLabel,
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Slider(
                            value = intensity.toFloat(),
                            onValueChange = { viewModel.setIntensity(it.toInt()) },
                            valueRange = 1f..5f,
                            steps = 3,
                            modifier = Modifier.testTag("intensity_slider")
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("1 (Subtle)", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), color = MaterialTheme.colorScheme.outline)
                            Text("3 (Moderate)", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), color = MaterialTheme.colorScheme.outline)
                            Text("5 (Acute)", style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp), color = MaterialTheme.colorScheme.outline)
                        }
                    }
                }
            }

            // Optional Context / Body Notes
            item {
                OutlinedTextField(
                    value = contextNote,
                    onValueChange = { viewModel.setContextNote(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("context_notes_input"),
                    label = { Text("Context & Body Sensations (Optional)") },
                    placeholder = { Text("Where do you feel this in your body? Any triggering event?") },
                    shape = RoundedCornerShape(16.dp),
                    maxLines = 3
                )
            }

            // Live Calculated System Diagnosis (if emotions selected)
            if (selectedEmotions.isNotEmpty()) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Calculated State Analysis:",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        SystemStatusCard(diagnosis = currentDiagnosis)
                    }
                }

                // Live Recommended Actions Preview
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Calculated Guidance (What Should I Do):",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        currentDiagnosis.recommendations.forEachIndexed { index, rec ->
                            ActionRecommendationCard(
                                recommendation = rec,
                                stepIndex = index + 1
                            )
                        }
                    }
                }
            }

            // Primary "Calculate & Record State" Icon Button at bottom
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FilledIconButton(
                        onClick = {
                            viewModel.saveCalculation {
                                onRecordSaved()
                            }
                        },
                        modifier = Modifier
                            .size(56.dp)
                            .testTag("save_calculation_button"),
                        shape = RoundedCornerShape(16.dp),
                        enabled = selectedEmotions.isNotEmpty(),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = if (selectedEmotions.isEmpty()) "Save System Calculation (Disabled - Select emotions first)" else "Calculate & Save to System Log",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }

        // Detail Dialog if an emotion is being inspected
        viewingEmotionDetail?.let { emotion ->
            val isSelected = selectedEmotions.any { it.id == emotion.id }
            EmotionDetailDialog(
                emotion = emotion,
                isSelected = isSelected,
                onToggleSelect = { viewModel.toggleEmotionSelection(emotion) },
                onDismiss = { viewModel.setViewingEmotionDetail(null) }
            )
        }
    }
}
