package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.ui.graphics.vector.ImageVector

enum class AchievementRarity(val displayName: String, val colorHex: Long, val pointMultiplier: Int) {
    COMMON("Novice", 0xFF64748B, 50),
    RARE("Adept", 0xFF0284C7, 150),
    EPIC("Master", 0xFF7C3AED, 350),
    LEGENDARY("Grandmaster", 0xFFD97706, 750),
    TRANSCENDENT("Transcendent", 0xFFEAB308, 1500)
}

enum class AchievementGroup(val title: String, val icon: ImageVector) {
    LEXICON("Emotion Lexicon", Icons.Default.DarkMode),
    NERVOUS_SYSTEM("Autonomic Regulation", Icons.Default.Spa),
    STREAK("Temporal Rhythm", Icons.Default.LocalFireDepartment),
    CATEGORY_MASTERY("Feeling Groups", Icons.Default.Category),
    GRANULARITY("Emotional Granularity", Icons.Default.AutoAwesome),
    DIURNAL("Circadian Cadence", Icons.Default.WbSunny),
    MILESTONES("System Milestones", Icons.Default.EmojiEvents)
}

enum class MasteryDomain(
    val title: String,
    val subtitle: String,
    val archetype: String,
    val icon: ImageVector,
    val colorHex: Long
) {
    LEXICAL("Lexicon Depth", "Breadth of affective discernment", "The Cartographer", Icons.Default.DarkMode, 0xFF38BDF8),
    REGULATION("Autonomic Balance", "Vagal tone & state modulation", "The Alchemist", Icons.Default.Spa, 0xFF34D399),
    SOMATIC("Somatic Grounding", "Body attunement & intensity resilience", "The Anchor", Icons.Default.SelfImprovement, 0xFFA78BFA),
    RHYTHM("Temporal Rhythm", "Habit consistency & diurnal attunement", "The Sentinel", Icons.Default.LocalFireDepartment, 0xFFFB923C),
    GRANULARITY("Granular Nuance", "Complex blends & contextual reflection", "The Scribe", Icons.Default.AutoAwesome, 0xFFF472B6)
}

data class DomainProficiency(
    val domain: MasteryDomain,
    val proficiencyPercent: Int,
    val currentXp: Int,
    val targetXp: Int,
    val rankTitle: String,
    val tipToAdvance: String
)

data class LevelMilestone(
    val level: Int,
    val title: String,
    val rankTier: String,
    val xpRequired: Int,
    val unlockPerk: String,
    val isReached: Boolean
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val group: AchievementGroup,
    val rarity: AchievementRarity,
    val points: Int,
    val currentProgress: Int,
    val maxProgress: Int,
    val isUnlocked: Boolean,
    val unlockedAt: Long? = null,
    val iconName: String = "trophy"
) {
    val progressPercent: Float
        get() = if (maxProgress <= 0) 1f else (currentProgress.toFloat() / maxProgress).coerceIn(0f, 1f)
}

data class MasteryProfile(
    val totalPoints: Int,
    val maxPossiblePoints: Int,
    val currentLevel: Int,
    val levelTitle: String,
    val rankTier: String,
    val currentLevelXp: Int,
    val nextLevelXp: Int,
    val unlockedCount: Int,
    val totalCount: Int,
    val exploredEmotionsCount: Int,
    val totalEmotionsCount: Int,
    val currentStreak: Int,
    val totalCheckIns: Int,
    val regulatedStatePercent: Int,
    val dominantCategory: String,
    val domains: List<DomainProficiency>,
    val levelMilestones: List<LevelMilestone>,
    val achievements: List<Achievement>
) {
    val levelProgress: Float
        get() {
            return if (nextLevelXp <= 0) 1f else (currentLevelXp.toFloat() / nextLevelXp).coerceIn(0f, 1f)
        }
}
