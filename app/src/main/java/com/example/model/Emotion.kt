package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterDrama
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonOff
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class EmotionCategory(
    val displayName: String,
    val description: String,
    val emoji: String,
    val lightColor: Long,
    val darkColor: Long,
    val containerLightColor: Long,
    val containerDarkColor: Long
) {
    JOY_CONTENTMENT(
        "Happy & Joyful",
        "Pleasant, uplifting, and harmonious emotions",
        "☀️",
        0xFFD48B28,
        0xFFF5BE6B,
        0xFFFFF3D6,
        0xFF4D3208
    ),
    PEACE_CALM(
        "Calm & Peaceful",
        "Centered, relaxed, and stabilizing feelings",
        "🌿",
        0xFF588157,
        0xFF95C594,
        0xFFE5F4E4,
        0xFF1F3A1E
    ),
    LOVE_CONNECTION(
        "Love & Connection",
        "Warmth, intimacy, and interpersonal bond",
        "💖",
        0xFFC0526B,
        0xFFEE8DA2,
        0xFFFFE3E8,
        0xFF4A1320
    ),
    VITALITY_DRIVE(
        "Vitality & Drive",
        "Energized, motivated, and expansive states",
        "⚡",
        0xFFD96B27,
        0xFFFA9E68,
        0xFFFFE7DA,
        0xFF522105
    ),
    FEAR_ANXIETY(
        "Fear & Anxiety",
        "Alertness, apprehension, and threat response",
        "🛡️",
        0xFF5C6B73,
        0xFFA5B3BA,
        0xFFE5ECF0,
        0xFF212B30
    ),
    ANGER_FRUSTRATION(
        "Angry & Frustrated",
        "Boundary defense, resistance, and irritation",
        "🔥",
        0xFFC84B31,
        0xFFF18571,
        0xFFFFE2DC,
        0xFF4D1409
    ),
    SADNESS_GRIEF(
        "Sad & Grieving",
        "Loss, mourning, letting go, and reflection",
        "💧",
        0xFF4682B4,
        0xFF8CBCE5,
        0xFFE0EFFC,
        0xFF112E48
    ),
    DISGUST_AVERSION(
        "Disgust & Aversion",
        "Rejection of toxic, offensive, or unhealthy stimuli",
        "🚫",
        0xFF6B705C,
        0xFFA5AA96,
        0xFFE7E9E1,
        0xFF272B1F
    ),
    AWE_CURIOSITY(
        "Surprise & Wonder",
        "Awe, astonishment, intellectual intrigue, and open-mindedness",
        "✨",
        0xFF8A5A9E,
        0xFFC59DDA,
        0xFFF4E5FB,
        0xFF371746
    ),
    OVERWHELM_FATIGUE(
        "Overwhelm & Fatigue",
        "System overload, depletion, and burnout",
        "🔋",
        0xFF7F7F7F,
        0xFFBDBDBD,
        0xFFEEEEEE,
        0xFF2E2E2E
    ),
    VULNERABILITY_SHAME(
        "Vulnerability & Shame",
        "Exposure, self-doubt, and fragility",
        "🪞",
        0xFF9E6B7A,
        0xFFD7A7B4,
        0xFFFBECEF,
        0xFF3E1E28
    ),
    GUILT_REGRET(
        "Guilt & Regret",
        "Remorse, self-reproach, moral conscience, and amends",
        "⚖️",
        0xFF8D6E63,
        0xFFBCAAA4,
        0xFFEFEBE9,
        0xFF3E2723
    ),
    JEALOUSY_ENVY(
        "Jealousy & Envy",
        "Comparison, covetousness, insecurity, and rivalry",
        "👁️",
        0xFF4A7C59,
        0xFF81C784,
        0xFFE8F5E9,
        0xFF1B5E20
    ),
    LONELINESS_ISOLATION(
        "Loneliness & Isolation",
        "Disconnection, alienation, and longing for belonging",
        "🪹",
        0xFF455A64,
        0xFF90A4AE,
        0xFFECEFF1,
        0xFF263238
    ),
    NUMBNESS_APATHY(
        "Numbness & Apathy",
        "Emotional blunting, dissociation, indifference, and paralysis",
        "🧊",
        0xFF607D8B,
        0xFFB0BEC5,
        0xFFECEFF1,
        0xFF263238
    ),
    CONFUSION_AMBIVALENCE(
        "Confusion & Ambivalence",
        "Uncertainty, mixed signals, and seeking clarity",
        "🌀",
        0xFF5F758E,
        0xFFA0B5CC,
        0xFFE4ECF4,
        0xFF1D2C3B
    ),
    GROUNDING_RESILIENCE(
        "Grounding & Strength",
        "Steadfastness, courage, and balance",
        "🏔️",
        0xFF606C38,
        0xFFA8B87B,
        0xFFEAF0DA,
        0xFF242C10
    );

    val colorHex: Long get() = lightColor

    fun getCategoryIcon(): ImageVector {
        return when (this) {
            JOY_CONTENTMENT -> Icons.Default.EmojiEmotions
            PEACE_CALM -> Icons.Default.Spa
            LOVE_CONNECTION -> Icons.Default.Favorite
            VITALITY_DRIVE -> Icons.Default.Bolt
            FEAR_ANXIETY -> Icons.Default.Shield
            ANGER_FRUSTRATION -> Icons.Default.LocalFireDepartment
            SADNESS_GRIEF -> Icons.Default.WaterDrop
            DISGUST_AVERSION -> Icons.Default.Block
            AWE_CURIOSITY -> Icons.Default.AutoAwesome
            OVERWHELM_FATIGUE -> Icons.Default.FilterDrama
            VULNERABILITY_SHAME -> Icons.Default.Lock
            GUILT_REGRET -> Icons.Default.Balance
            JEALOUSY_ENVY -> Icons.Default.RemoveRedEye
            LONELINESS_ISOLATION -> Icons.Default.PersonOff
            NUMBNESS_APATHY -> Icons.Default.AcUnit
            CONFUSION_AMBIVALENCE -> Icons.Default.QuestionMark
            GROUNDING_RESILIENCE -> Icons.Default.SelfImprovement
        }
    }
}

data class Emotion(
    val id: String,
    val name: String,
    val category: EmotionCategory,
    val valence: Float, // -1.0 (very negative) to +1.0 (very positive)
    val arousal: Float, // 0.0 (very calm/low energy) to 1.0 (high energy/activation)
    val somaticSignal: String,
    val biologicalPurpose: String,
    val regulationHint: String
)
