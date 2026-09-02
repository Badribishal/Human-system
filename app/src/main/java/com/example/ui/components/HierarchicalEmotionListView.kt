package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.UnfoldLess
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Emotion
import com.example.model.EmotionCategory

/**
 * High-performance hierarchical expandable list view for organizing emotions by category.
 * Leverages Jetpack Compose memoization (remember & derivedStateOf) and LazyColumn to ensure
 * buttery smooth 60fps scrolling and instant responsive accordion expansion across 600+ emotions.
 */
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HierarchicalEmotionListView(
    emotions: List<Emotion>,
    selectedEmotions: List<Emotion>,
    onEmotionToggle: (Emotion) -> Unit,
    onEmotionInspect: (Emotion) -> Unit,
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    selectedCategoryFilter: EmotionCategory? = null,
    onClearCategory: ((EmotionCategory) -> Unit)? = null,
    isLibraryMode: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
) {
    val isDark = isSystemInDarkTheme()

    // 1. Memoize grouping of emotions by category so grouping only recomputes when emotions change
    val groupedEmotions = remember(emotions) {
        emotions.groupBy { it.category }
    }

    // 2. Memoize category list
    val categories = remember(groupedEmotions) {
        groupedEmotions.keys.toList()
    }

    // 3. Fast O(1) set lookup for selected emotion IDs
    val selectedEmotionIds = remember(selectedEmotions) {
        selectedEmotions.map { it.id }.toSet()
    }

    // 4. Memoize selected counts mapped by category
    val selectedCountByCategory = remember(selectedEmotions, groupedEmotions) {
        groupedEmotions.mapValues { (_, emotionList) ->
            emotionList.count { it.id in selectedEmotionIds }
        }
    }

    // 5. Manage expansion state with remember
    var expandedCategories by remember {
        mutableStateOf<Set<EmotionCategory>>(
            // Initially expand all if filtered to a single category or actively searching
            if (selectedCategoryFilter != null || searchQuery.isNotBlank()) {
                categories.toSet()
            } else {
                setOf()
            }
        )
    }

    // Auto-expand categories when search query is active
    LaunchedEffect(searchQuery, selectedCategoryFilter) {
        if (searchQuery.isNotBlank() || selectedCategoryFilter != null) {
            expandedCategories = categories.toSet()
        }
    }

    // 6. Memoize derived states using derivedStateOf for reactive UI synchronization
    val allCategoriesExpanded by remember(categories, expandedCategories) {
        derivedStateOf {
            categories.isNotEmpty() && categories.all { it in expandedCategories }
        }
    }

    val totalEmotionsCount by remember(emotions) {
        derivedStateOf { emotions.size }
    }

    val totalSelectedCount by remember(selectedEmotions) {
        derivedStateOf { selectedEmotions.size }
    }

    Column(modifier = modifier.fillMaxWidth()) {
        // Top Toolbar: Expand All / Collapse All & Category Status
        if (categories.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "${categories.size} Categories",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold
                    )
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.padding(start = 2.dp)
                    ) {
                        Text(
                            text = "$totalEmotionsCount feelings",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    if (totalSelectedCount > 0 && !isLibraryMode) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = "$totalSelectedCount selected",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }
                }

                TextButton(
                    onClick = {
                        expandedCategories = if (allCategoriesExpanded) {
                            emptySet()
                        } else {
                            categories.toSet()
                        }
                    },
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    modifier = Modifier.testTag("toggle_expand_all_button")
                ) {
                    Icon(
                        imageVector = if (allCategoriesExpanded) Icons.Default.UnfoldLess else Icons.Default.UnfoldMore,
                        contentDescription = if (allCategoriesExpanded) "Collapse All" else "Expand All",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (allCategoriesExpanded) "Collapse All" else "Expand All",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        if (categories.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No emotions found matching your filter.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // LazyColumn for smooth virtualization
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(
                    items = categories,
                    key = { category -> "category_${category.name}" }
                ) { category ->
                    val categoryEmotions = groupedEmotions[category] ?: emptyList()
                    val isExpanded = category in expandedCategories
                    val selectedInCat = selectedCountByCategory[category] ?: 0

                    val chevronRotation by animateFloatAsState(
                        targetValue = if (isExpanded) 180f else 0f,
                        animationSpec = tween(durationMillis = 200),
                        label = "chevron_rotation"
                    )

                    val categoryColor = if (isDark) Color(category.darkColor) else Color(category.lightColor)
                    val containerColor = if (isDark) Color(category.containerDarkColor) else Color(category.containerLightColor)

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("category_card_${category.name.lowercase()}"),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.elevatedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = if (isExpanded) 2.dp else 1.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            // Expandable Category Header
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable {
                                        expandedCategories = if (isExpanded) {
                                            expandedCategories - category
                                        } else {
                                            expandedCategories + category
                                        }
                                    }
                                    .padding(horizontal = 14.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    // Category color indicator pill
                                    Box(
                                        modifier = Modifier
                                            .size(width = 4.dp, height = 32.dp)
                                            .clip(RoundedCornerShape(2.dp))
                                            .background(categoryColor)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))

                                    // Category icon / emoji badge
                                    Box(
                                        modifier = Modifier
                                            .size(38.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(containerColor),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = category.emoji,
                                            fontSize = 18.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = category.displayName,
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.SemiBold,
                                                color = MaterialTheme.colorScheme.onSurface,
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                        Text(
                                            text = category.description,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    // Clear category selection button
                                    if (selectedInCat > 0 && onClearCategory != null && !isLibraryMode) {
                                        IconButton(
                                            onClick = { onClearCategory(category) },
                                            modifier = Modifier.size(28.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Clear selected in ${category.displayName}",
                                                tint = MaterialTheme.colorScheme.error,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }

                                    // Count Badge
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = if (selectedInCat > 0) categoryColor else MaterialTheme.colorScheme.surfaceVariant,
                                        modifier = Modifier.padding(end = 2.dp)
                                    ) {
                                        Text(
                                            text = if (selectedInCat > 0) "$selectedInCat/${categoryEmotions.size}" else "${categoryEmotions.size}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (selectedInCat > 0) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                        )
                                    }

                                    // Chevron Arrow
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = if (isExpanded) "Collapse" else "Expand",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier
                                            .size(22.dp)
                                            .rotate(chevronRotation)
                                    )
                                }
                            }

                            // Expandable content containing emotion items
                            AnimatedVisibility(
                                visible = isExpanded,
                                enter = expandVertically(animationSpec = tween(180)) + fadeIn(animationSpec = tween(180)),
                                exit = shrinkVertically(animationSpec = tween(150)) + fadeOut(animationSpec = tween(150))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(containerColor.copy(alpha = if (isDark) 0.15f else 0.35f))
                                        .padding(horizontal = 14.dp, vertical = 12.dp)
                                ) {
                                    if (isLibraryMode) {
                                        // Library mode: detailed rows with somatic quick peek
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            categoryEmotions.forEach { emotion ->
                                                val isSelected = emotion.id in selectedEmotionIds
                                                LibraryEmotionRow(
                                                    emotion = emotion,
                                                    isSelected = isSelected,
                                                    categoryColor = categoryColor,
                                                    onSelect = { onEmotionToggle(emotion) },
                                                    onInspect = { onEmotionInspect(emotion) }
                                                )
                                            }
                                        }
                                    } else {
                                        // Record mode: interactive chip grid
                                        FlowRow(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            verticalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            categoryEmotions.forEach { emotion ->
                                                val isSelected = emotion.id in selectedEmotionIds
                                                EmotionChip(
                                                    emotion = emotion,
                                                    isSelected = isSelected,
                                                    categoryColor = categoryColor,
                                                    onToggle = { onEmotionToggle(emotion) },
                                                    onInspect = { onEmotionInspect(emotion) }
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
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmotionChip(
    emotion: Emotion,
    isSelected: Boolean,
    categoryColor: Color,
    onToggle: () -> Unit,
    onInspect: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onToggle,
        label = {
            Text(
                text = emotion.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        },
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected",
                    modifier = Modifier.size(16.dp)
                )
            }
        } else null,
        trailingIcon = {
            IconButton(
                onClick = onInspect,
                modifier = Modifier
                    .size(24.dp)
                    .testTag("inspect_emotion_${emotion.id}")
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Details for ${emotion.name}",
                    tint = if (isSelected) categoryColor else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    modifier = Modifier.size(14.dp)
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = categoryColor.copy(alpha = 0.18f),
            selectedLabelColor = MaterialTheme.colorScheme.onSurface,
            selectedLeadingIconColor = categoryColor
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            selectedBorderColor = categoryColor,
            borderWidth = if (isSelected) 1.5.dp else 1.dp
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.testTag("emotion_chip_${emotion.id}")
    )
}

@Composable
private fun LibraryEmotionRow(
    emotion: Emotion,
    isSelected: Boolean,
    categoryColor: Color,
    onSelect: () -> Unit,
    onInspect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { onInspect() }
            .testTag("library_row_${emotion.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = if (isSelected) BorderStroke(1.5.dp, categoryColor) else BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = emotion.name,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    // Valence indicator pill
                    val isPositive = emotion.valence > 0.05f
                    val isNegative = emotion.valence < -0.05f
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = when {
                            isPositive -> Color(0xFF4CAF50).copy(alpha = 0.15f)
                            isNegative -> Color(0xFFE53935).copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.surfaceVariant
                        }
                    ) {
                        Text(
                            text = when {
                                isPositive -> "+${(emotion.valence * 100).toInt()}%"
                                isNegative -> "${(emotion.valence * 100).toInt()}%"
                                else -> "Neutral"
                            },
                            style = MaterialTheme.typography.labelSmall,
                            color = when {
                                isPositive -> Color(0xFF2E7D32)
                                isNegative -> Color(0xFFC62828)
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            },
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = emotion.somaticSignal,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Quick Select / Toggle Button
                IconButton(
                    onClick = onSelect,
                    modifier = Modifier.size(36.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) categoryColor else MaterialTheme.colorScheme.surfaceVariant
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = if (isSelected) "Deselect" else "Select",
                            tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

                // Inspect Button
                IconButton(
                    onClick = onInspect,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "View Guide for ${emotion.name}",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
