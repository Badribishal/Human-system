package com.example.model

data class SystemDiagnosis(
    val stateTitle: String,
    val stateSubtitle: String,
    val valence: Float, // -1.0 to 1.0
    val arousal: Float, // 0.0 to 1.0
    val primaryNervousSystemBranch: String,
    val summary: String,
    val emotionalDynamic: String,
    val somaticInsight: String,
    val recommendations: List<ActionRecommendation>
)

data class ActionRecommendation(
    val title: String,
    val category: String, // Somatic, Cognitive, Micro-Action, Boundary
    val instruction: String,
    val whyItWorks: String,
    val iconName: String
)

object SystemCalculator {

    fun calculateState(
        selectedEmotions: List<Emotion>,
        intensity: Int = 3, // 1 to 5
        contextNote: String = ""
    ): SystemDiagnosis {
        if (selectedEmotions.isEmpty()) {
            return SystemDiagnosis(
                stateTitle = "Baseline Equilibrium",
                stateSubtitle = "System awaiting emotional input",
                valence = 0.0f,
                arousal = 0.3f,
                primaryNervousSystemBranch = "Neutral Baseline",
                summary = "Your human system is currently in an unmeasured baseline state. Check in with your body and select what you are feeling to compute guidance.",
                emotionalDynamic = "No active emotional charge registered.",
                somaticInsight = "Notice your breath and posture as you prepare to scan your internal state.",
                recommendations = listOf(
                    ActionRecommendation(
                        title = "Somatic Check-In",
                        category = "Somatic",
                        instruction = "Close your eyes, place a hand over your chest, and scan from head to toe for where energy or tension is held.",
                        whyItWorks = "Directs conscious interoception to identify subtle feelings.",
                        iconName = "touch_app"
                    ),
                    ActionRecommendation(
                        title = "Select Your Feelings",
                        category = "Micro-Action",
                        instruction = "Browse the 121+ feelings in the Record tab to log your current internal weather.",
                        whyItWorks = "Naming your emotions (affect labeling) reduces amygdala reactivity.",
                        iconName = "add_reaction"
                    )
                )
            )
        }

        val avgValence = selectedEmotions.map { it.valence }.average().toFloat()
        val avgArousal = selectedEmotions.map { it.arousal }.average().toFloat()

        val positiveCount = selectedEmotions.count { it.valence > 0.1f }
        val negativeCount = selectedEmotions.count { it.valence < -0.1f }
        val isMixed = positiveCount > 0 && negativeCount > 0

        // Determine Primary System State
        val (stateTitle, stateSubtitle, branch, dynamic) = when {
            isMixed -> {
                Quad(
                    "Complex Ambivalence",
                    "Dual-polarity emotional charge active",
                    "Mixed Autonomic Regulation",
                    "Your system is holding conflicting emotional signals simultaneously (${selectedEmotions.take(3).joinToString { it.name }}). This dual activation requires holding space for complexity without forcing premature resolution."
                )
            }
            avgValence >= 0.3f && avgArousal >= 0.65f -> {
                Quad(
                    "Dopaminergic Expansion",
                    "High-vitality motivated flow state",
                    "Ventral Vagal + Sympathetic Surge",
                    "Your human system is operating in a high-vitality, expansive state with strong forward momentum. Dopamine and endorphin signaling are elevated, fueling inspiration and action."
                )
            }
            avgValence >= 0.2f && avgArousal < 0.65f -> {
                Quad(
                    "Ventral Vagal Coherence",
                    "Safe, connected & grounded equilibrium",
                    "Ventral Vagal (Parasympathetic)",
                    "Your nervous system is in a regulated, restorative state. Heart rate variability is optimal, allowing open social connection, clear perception, and internal harmony."
                )
            }
            avgValence < -0.2f && avgArousal >= 0.6f -> {
                Quad(
                    "Sympathetic Hyper-Activation",
                    "Defensive alert / Fight-Flight response",
                    "Sympathetic Nervous System",
                    "Your system is experiencing an acute mobilization response. Heart rate, adrenaline, and vigilance are heightened in response to perceived friction, pressure, or threat."
                )
            }
            avgValence < -0.2f && avgArousal < 0.35f -> {
                Quad(
                    "Dorsal Vagal Depletion",
                    "Conservation / Hypo-arousal & fatigue",
                    "Dorsal Vagal (Shutdown / Energy Conservation)",
                    "Your system has shifted into conservation mode. When stress or grief exceeds capacity, the body down-regulates metabolic expenditure, resulting in heaviness, fatigue, or withdrawal."
                )
            }
            else -> {
                Quad(
                    "Dynamic Internal Processing",
                    "Active emotional integration underway",
                    "Central Autonomic Balance",
                    "Your system is processing active sensory and emotional inputs with moderate arousal. Emotions are transitioning and integrating."
                )
            }
        }

        // Somatic Insight synthesis
        val somaticNotes = selectedEmotions.map { it.somaticSignal }.distinct().take(3).joinToString("; ")
        val somaticInsight = "Common physiological expressions: $somaticNotes."

        // Summary of what is happening
        val emotionNames = selectedEmotions.joinToString(", ") { it.name }
        val intensityDescription = when (intensity) {
            1 -> "subtle, background intensity"
            2 -> "mild, noticeable intensity"
            3 -> "moderate, clear intensity"
            4 -> "strong, pronounced intensity"
            else -> "acute, powerful intensity"
        }
        val summary = "You are currently experiencing $emotionNames at $intensityDescription. " +
                "Your overall system valence is ${if (avgValence >= 0) "+" else ""}${(avgValence * 100).toInt()}% " +
                "with an energy/arousal activation of ${(avgArousal * 100).toInt()}%."

        // Compute Action Recommendations
        val recs = mutableListOf<ActionRecommendation>()

        // 1. Somatic Regulation
        when {
            avgArousal >= 0.65f && avgValence < 0 -> {
                recs.add(
                    ActionRecommendation(
                        title = "Physiological Sigh & Grounding",
                        category = "Somatic Regulation",
                        instruction = "Take two quick inhales through the nose, followed by one long, slow exhale through the mouth. Repeat for 5 cycles while pressing your feet firmly into the floor.",
                        whyItWorks = "Double-inhale pops open collapsed alveoli in the lungs, and the extended exhale triggers the parasympathetic brake to drop heart rate immediately.",
                        iconName = "air"
                    )
                )
            }
            avgArousal < 0.35f && avgValence < 0 -> {
                recs.add(
                    ActionRecommendation(
                        title = "Gentle Somatic Reactivation",
                        category = "Somatic Regulation",
                        instruction = "Drink a large glass of room-temperature water, wrap yourself in warmth, and do gentle wrist rolls or sway side-to-side without rushing.",
                        whyItWorks = "Soft, rhythmic micro-movements signal the brainstem that the body is safe to emerge from shutdown.",
                        iconName = "spa"
                    )
                )
            }
            avgValence >= 0.3f -> {
                recs.add(
                    ActionRecommendation(
                        title = "Embodied Savoring",
                        category = "Somatic Regulation",
                        instruction = "Close your eyes for 30 seconds and locate the physical warmth in your chest. Deeply absorb the sensation before switching tasks.",
                        whyItWorks = "Neuroplastic savoring requires 20-30 seconds of conscious attention to encode positive states into long-term trait resilience.",
                        iconName = "favorite"
                    )
                )
            }
            else -> {
                recs.add(
                    ActionRecommendation(
                        title = "Diaphragmatic Equal Breathing",
                        category = "Somatic Regulation",
                        instruction = "Inhale for 4 seconds into your belly, hold for 2 seconds, exhale smoothly for 6 seconds.",
                        whyItWorks = "Balances oxygen-carbon dioxide ratio and stabilizes autonomic baseline.",
                        iconName = "self_improvement"
                    )
                )
            }
        }

        // 2. Cognitive Reframe / Mental Guidance
        when {
            isMixed -> {
                recs.add(
                    ActionRecommendation(
                        title = "Accept Duality Without Forcing Choice",
                        category = "Cognitive Reframe",
                        instruction = "Tell yourself: 'I can feel both ${selectedEmotions.first().name} and ${selectedEmotions.last().name} at the same time. Both signals carry valid data.'",
                        whyItWorks = "Dialectical awareness stops internal cognitive dissonance from escalating into self-judgment.",
                        iconName = "psychology"
                    )
                )
            }
            avgValence < -0.3f -> {
                recs.add(
                    ActionRecommendation(
                        title = "Affect Labeling & De-identification",
                        category = "Cognitive Reframe",
                        instruction = "Mentally rephrase from 'I am anxious/sad' to 'My human system is currently hosting the feeling of ${selectedEmotions.first().name}.'",
                        whyItWorks = "Creates psychological distance (diffusion) so you observe the emotion as transient weather rather than your identity.",
                        iconName = "lightbulb"
                    )
                )
            }
            else -> {
                recs.add(
                    ActionRecommendation(
                        title = "Align With Core Values",
                        category = "Cognitive Reframe",
                        instruction = "Reflect on what condition, person, or creative spark made this state possible, and how you can honor it today.",
                        whyItWorks = "Reinforces constructive neural pathways and purposeful life alignment.",
                        iconName = "star"
                    )
                )
            }
        }

        // 3. Behavioral Micro-Action from top emotion
        val topEmotion = selectedEmotions.maxByOrNull { kotlin.math.abs(it.valence) + it.arousal } ?: selectedEmotions.first()
        recs.add(
            ActionRecommendation(
                title = "Targeted Action: ${topEmotion.name}",
                category = "Micro-Action",
                instruction = topEmotion.regulationHint,
                whyItWorks = "Directly satisfies the biological wisdom of ${topEmotion.name}: ${topEmotion.biologicalPurpose}.",
                iconName = "check_circle"
            )
        )

        // 4. System Boundary / Recovery
        if (avgArousal > 0.7f || avgValence < -0.5f) {
            recs.add(
                ActionRecommendation(
                    title = "System Protection & Pace Regulation",
                    category = "Boundary & Pacing",
                    instruction = "Postpone any major emotional confrontations or non-urgent decisions for at least 2 hours until system activation settles below 50%.",
                    whyItWorks = "Prefrontal cortex executive control is degraded during acute autonomic mobilization.",
                    iconName = "shield"
                )
            )
        } else {
            recs.add(
                ActionRecommendation(
                    title = "Expressive Flow or Rest",
                    category = "System Care",
                    instruction = if (avgArousal > 0.5f) "Channel this alertness into a single focused task or journal entry." else "Allow yourself unhurried rest without guilt.",
                    whyItWorks = "Harmonizes behavioral output with your current internal energy reserves.",
                    iconName = "bedtime"
                )
            )
        }

        return SystemDiagnosis(
            stateTitle = stateTitle,
            stateSubtitle = stateSubtitle,
            valence = avgValence,
            arousal = avgArousal,
            primaryNervousSystemBranch = branch,
            summary = summary,
            emotionalDynamic = dynamic,
            somaticInsight = somaticInsight,
            recommendations = recs
        )
    }

    private data class Quad(
        val title: String,
        val subtitle: String,
        val branch: String,
        val dynamic: String
    )
}
