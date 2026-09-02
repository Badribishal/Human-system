package com.example.model

import com.example.data.EmotionRecord
import java.util.Calendar

object AchievementCalculator {

    fun computeMastery(records: List<EmotionRecord>): MasteryProfile {
        val totalEmotionsInCatalog = EmotionCatalog.totalCount
        val uniqueEmotionsExplored = mutableSetOf<String>()
        val categoryCounts = mutableMapOf<EmotionCategory, Int>()
        var multiEmotionCount = 0
        var notesCount = 0
        var highIntensityRegulatedCount = 0
        var ventralCount = 0
        var sympatheticCount = 0
        var dorsalCount = 0
        var dawnCount = 0     // 5 AM - 9 AM
        var middayCount = 0   // 11 AM - 2 PM
        var duskCount = 0     // 5 PM - 8 PM
        var midnightCount = 0 // 10 PM - 4 AM

        for (record in records) {
            uniqueEmotionsExplored.addAll(record.emotionNames)
            if (record.emotionNames.size >= 2) multiEmotionCount++
            if (record.contextNote.isNotBlank()) notesCount++
            if (record.intensity >= 4) highIntensityRegulatedCount++

            when {
                record.valence > 0.15f -> ventralCount++
                record.arousal > 0.65f && record.valence < 0f -> sympatheticCount++
                record.arousal < 0.35f && record.valence < -0.1f -> dorsalCount++
            }

            val cal = Calendar.getInstance().apply { timeInMillis = record.timestamp }
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            when (hour) {
                in 5..8 -> dawnCount++
                in 11..13 -> middayCount++
                in 17..20 -> duskCount++
                in 22..23, in 0..4 -> midnightCount++
            }

            for (name in record.emotionNames) {
                (EmotionCatalog.findByName(name) ?: EmotionCatalog.findById(name))?.let { em ->
                    categoryCounts[em.category] = (categoryCounts[em.category] ?: 0) + 1
                }
            }
        }

        val totalRecords = records.size
        val uniqueCount = uniqueEmotionsExplored.size
        val streakDays = calculateStreak(records)

        val dominantCategoryName = categoryCounts.maxByOrNull { it.value }?.key?.displayName ?: "Exploration"
        val regulatedCount = ventralCount + highIntensityRegulatedCount
        val regulatedStatePercent = if (totalRecords > 0) ((regulatedCount.toFloat() / totalRecords) * 100).toInt().coerceIn(0, 100) else 100

        val achievements = mutableListOf<Achievement>()

        // 1. Lexicon Explorer Achievements
        val lexiconTiers = listOf(
            Triple("lex_1", "First Spark", "Log your first distinct human emotion") to Pair(1, AchievementRarity.COMMON),
            Triple("lex_10", "Emotional Cartographer", "Identify and feel 10 distinct emotions") to Pair(10, AchievementRarity.COMMON),
            Triple("lex_25", "Granular Discernment", "Identify and feel 25 distinct emotions") to Pair(25, AchievementRarity.RARE),
            Triple("lex_50", "Vocabulary of the Soul", "Identify and feel 50 distinct emotions") to Pair(50, AchievementRarity.RARE),
            Triple("lex_100", "Centurion of Sentience", "Identify and explore 100 distinct emotions") to Pair(100, AchievementRarity.EPIC),
            Triple("lex_200", "Deep Spectrum Explorer", "Identify and explore 200 distinct emotions") to Pair(200, AchievementRarity.EPIC),
            Triple("lex_350", "Affective Master", "Identify and explore 350 distinct emotions") to Pair(350, AchievementRarity.LEGENDARY),
            Triple("lex_500", "Universal Human Polymath", "Explore 500+ emotions across the entire catalog") to Pair(500, AchievementRarity.TRANSCENDENT)
        )

        for ((meta, tier) in lexiconTiers) {
            val (id, title, desc) = meta
            val (target, rarity) = tier
            val pts = rarity.pointMultiplier
            val progress = uniqueCount.coerceAtMost(target)
            val unlocked = uniqueCount >= target
            achievements.add(
                Achievement(id, title, desc, AchievementGroup.LEXICON, rarity, pts, progress, target, unlocked)
            )
        }

        // 2. Consistency & Log Count Milestones
        val logTiers = listOf(
            Triple("log_1", "The Awakening", "Record your very first emotional state") to Pair(1, AchievementRarity.COMMON),
            Triple("log_5", "Mindful Rhythm", "Record 5 emotional states") to Pair(5, AchievementRarity.COMMON),
            Triple("log_25", "Somatic Discipline", "Record 25 emotional states") to Pair(25, AchievementRarity.RARE),
            Triple("log_50", "Dedicated Witness", "Record 50 emotional states") to Pair(50, AchievementRarity.RARE),
            Triple("log_100", "Century of Clarity", "Record 100 emotional states") to Pair(100, AchievementRarity.EPIC),
            Triple("log_250", "Sovereign Habit", "Record 250 emotional states") to Pair(250, AchievementRarity.EPIC),
            Triple("log_500", "Master Chronicler", "Record 500 emotional states") to Pair(500, AchievementRarity.LEGENDARY),
            Triple("log_1000", "Grand Oracle of Self", "Record 1000 emotional states") to Pair(1000, AchievementRarity.TRANSCENDENT)
        )

        for ((meta, tier) in logTiers) {
            val (id, title, desc) = meta
            val (target, rarity) = tier
            val pts = rarity.pointMultiplier
            val progress = totalRecords.coerceAtMost(target)
            val unlocked = totalRecords >= target
            achievements.add(
                Achievement(id, title, desc, AchievementGroup.MILESTONES, rarity, pts, progress, target, unlocked)
            )
        }

        // 3. Streak Milestones
        val streakTiers = listOf(
            Triple("strk_3", "Steady Pulse", "Maintain a 3-day continuous logging streak") to Pair(3, AchievementRarity.COMMON),
            Triple("strk_7", "Seven Days of Truth", "Maintain a 7-day continuous logging streak") to Pair(7, AchievementRarity.RARE),
            Triple("strk_14", "Fortnight Fortress", "Maintain a 14-day continuous logging streak") to Pair(14, AchievementRarity.RARE),
            Triple("strk_30", "Lunar Equilibrium", "Maintain a 30-day continuous logging streak") to Pair(30, AchievementRarity.EPIC),
            Triple("strk_60", "Season of Presence", "Maintain a 60-day continuous logging streak") to Pair(60, AchievementRarity.EPIC),
            Triple("strk_100", "Centurion of Consistency", "Maintain a 100-day logging streak") to Pair(100, AchievementRarity.LEGENDARY),
            Triple("strk_365", "Solar Mastery", "Maintain an unbroken 365-day journey of self-awareness") to Pair(365, AchievementRarity.TRANSCENDENT)
        )

        for ((meta, tier) in streakTiers) {
            val (id, title, desc) = meta
            val (target, rarity) = tier
            val pts = rarity.pointMultiplier
            val progress = streakDays.coerceAtMost(target)
            val unlocked = streakDays >= target
            achievements.add(
                Achievement(id, title, desc, AchievementGroup.STREAK, rarity, pts, progress, target, unlocked)
            )
        }

        // 4. Circadian Cadence / Diurnal Logging
        achievements.add(
            Achievement(
                id = "diur_dawn",
                title = "Dawn Attunement",
                description = "Record your emotional state in early morning (5 AM - 9 AM) 5 times",
                group = AchievementGroup.DIURNAL,
                rarity = AchievementRarity.RARE,
                points = 150,
                currentProgress = dawnCount.coerceAtMost(5),
                maxProgress = 5,
                isUnlocked = dawnCount >= 5
            )
        )
        achievements.add(
            Achievement(
                id = "diur_midday",
                title = "Midday Anchor",
                description = "Perform a somatic reset during peak daytime (11 AM - 2 PM) 5 times",
                group = AchievementGroup.DIURNAL,
                rarity = AchievementRarity.COMMON,
                points = 50,
                currentProgress = middayCount.coerceAtMost(5),
                maxProgress = 5,
                isUnlocked = middayCount >= 5
            )
        )
        achievements.add(
            Achievement(
                id = "diur_twilight",
                title = "Twilight Contemplation",
                description = "Reflect during evening transition hours (5 PM - 8 PM) 5 times",
                group = AchievementGroup.DIURNAL,
                rarity = AchievementRarity.COMMON,
                points = 50,
                currentProgress = duskCount.coerceAtMost(5),
                maxProgress = 5,
                isUnlocked = duskCount >= 5
            )
        )
        achievements.add(
            Achievement(
                id = "diur_midnight",
                title = "Midnight Stillness",
                description = "Honor nocturnal contemplation (10 PM - 4 AM) 5 times",
                group = AchievementGroup.DIURNAL,
                rarity = AchievementRarity.RARE,
                points = 150,
                currentProgress = midnightCount.coerceAtMost(5),
                maxProgress = 5,
                isUnlocked = midnightCount >= 5
            )
        )

        // 5. Category Specialists (All 13 Feeling Groups)
        for (category in EmotionCategory.values()) {
            val catCount = categoryCounts[category] ?: 0
            val target = 15
            val unlocked = catCount >= target
            val rarity = AchievementRarity.RARE
            achievements.add(
                Achievement(
                    id = "cat_${category.name.lowercase()}",
                    title = "${category.emoji} ${category.displayName} Adept",
                    description = "Explore at least 15 distinct experiences in ${category.displayName}",
                    group = AchievementGroup.CATEGORY_MASTERY,
                    rarity = rarity,
                    points = rarity.pointMultiplier,
                    currentProgress = catCount.coerceAtMost(target),
                    maxProgress = target,
                    isUnlocked = unlocked
                )
            )
        }

        // 6. Autonomic Nervous System Harmony
        achievements.add(
            Achievement(
                id = "poly_ventral",
                title = "Ventral Vagal Pioneer",
                description = "Anchor in safety, social connection, and positive valence 10 times",
                group = AchievementGroup.NERVOUS_SYSTEM,
                rarity = AchievementRarity.RARE,
                points = 150,
                currentProgress = ventralCount.coerceAtMost(10),
                maxProgress = 10,
                isUnlocked = ventralCount >= 10
            )
        )
        achievements.add(
            Achievement(
                id = "poly_symp",
                title = "Sympathetic Alchemist",
                description = "Successfully diagnose and regulate high-arousal fight-or-flight energy 10 times",
                group = AchievementGroup.NERVOUS_SYSTEM,
                rarity = AchievementRarity.EPIC,
                points = 350,
                currentProgress = sympatheticCount.coerceAtMost(10),
                maxProgress = 10,
                isUnlocked = sympatheticCount >= 10
            )
        )
        achievements.add(
            Achievement(
                id = "poly_dorsal",
                title = "Dorsal Vagal Resurrector",
                description = "Compassionately navigate and recover from dorsal shutdown/freeze 10 times",
                group = AchievementGroup.NERVOUS_SYSTEM,
                rarity = AchievementRarity.EPIC,
                points = 350,
                currentProgress = dorsalCount.coerceAtMost(10),
                maxProgress = 10,
                isUnlocked = dorsalCount >= 10
            )
        )

        // 7. Emotional Granularity
        achievements.add(
            Achievement(
                id = "gran_blend",
                title = "Complex Synthesizer",
                description = "Log complex multifaceted states with 3+ simultaneous emotions 10 times",
                group = AchievementGroup.GRANULARITY,
                rarity = AchievementRarity.RARE,
                points = 150,
                currentProgress = multiEmotionCount.coerceAtMost(10),
                maxProgress = 10,
                isUnlocked = multiEmotionCount >= 10
            )
        )
        achievements.add(
            Achievement(
                id = "gran_journal",
                title = "Introspective Scribe",
                description = "Write somatic and contextual reflection notes on 20 entries",
                group = AchievementGroup.GRANULARITY,
                rarity = AchievementRarity.RARE,
                points = 150,
                currentProgress = notesCount.coerceAtMost(20),
                maxProgress = 20,
                isUnlocked = notesCount >= 20
            )
        )
        achievements.add(
            Achievement(
                id = "gran_tempest",
                title = "Eye of the Storm",
                description = "Maintain mindful awareness during level 4+ peak intensity states 10 times",
                group = AchievementGroup.GRANULARITY,
                rarity = AchievementRarity.EPIC,
                points = 350,
                currentProgress = highIntensityRegulatedCount.coerceAtMost(10),
                maxProgress = 10,
                isUnlocked = highIntensityRegulatedCount >= 10
            )
        )

        val unlockedCount = achievements.count { it.isUnlocked }
        val totalCount = achievements.size
        val totalEarnedPoints = achievements.filter { it.isUnlocked }.sumOf { it.points }
        val maxPossiblePoints = achievements.sumOf { it.points }

        val level = (totalEarnedPoints / 250) + 1
        val levelTitle = getLevelTitle(level)
        val rankTier = getRankTier(level)
        val currentLevelBase = (level - 1) * 250

        // Domain proficiencies calculation (0-100%)
        val lexicalScore = ((uniqueCount.toFloat() / 150f) * 100).toInt().coerceIn(0, 100)
        val regScore = ((((ventralCount + sympatheticCount + dorsalCount).toFloat()) / 30f) * 100).toInt().coerceIn(0, 100)
        val somaticScore = (((highIntensityRegulatedCount.toFloat()) / 15f) * 100).toInt().coerceIn(0, 100)
        val rhythmScore = ((((streakDays * 5 + totalRecords * 2).toFloat()) / 100f) * 100).toInt().coerceIn(0, 100)
        val granScore = ((((multiEmotionCount * 3 + notesCount * 2).toFloat()) / 40f) * 100).toInt().coerceIn(0, 100)

        val domains = listOf(
            DomainProficiency(
                domain = MasteryDomain.LEXICAL,
                proficiencyPercent = lexicalScore,
                currentXp = uniqueCount,
                targetXp = 150,
                rankTitle = getDomainRank(lexicalScore, "Cartographer"),
                tipToAdvance = "Explore new unlogged words across the catalog"
            ),
            DomainProficiency(
                domain = MasteryDomain.REGULATION,
                proficiencyPercent = regScore,
                currentXp = ventralCount + sympatheticCount + dorsalCount,
                targetXp = 30,
                rankTitle = getDomainRank(regScore, "Alchemist"),
                tipToAdvance = "Check in when feeling sympathetic or dorsal activation"
            ),
            DomainProficiency(
                domain = MasteryDomain.SOMATIC,
                proficiencyPercent = somaticScore,
                currentXp = highIntensityRegulatedCount,
                targetXp = 15,
                rankTitle = getDomainRank(somaticScore, "Anchor"),
                tipToAdvance = "Log level 4+ intensity feelings with body anchors"
            ),
            DomainProficiency(
                domain = MasteryDomain.RHYTHM,
                proficiencyPercent = rhythmScore,
                currentXp = streakDays,
                targetXp = 30,
                rankTitle = getDomainRank(rhythmScore, "Sentinel"),
                tipToAdvance = "Maintain daily check-in cadence without breaks"
            ),
            DomainProficiency(
                domain = MasteryDomain.GRANULARITY,
                proficiencyPercent = granScore,
                currentXp = multiEmotionCount + notesCount,
                targetXp = 40,
                rankTitle = getDomainRank(granScore, "Scribe"),
                tipToAdvance = "Combine 2+ feeling nuances and add contextual notes"
            )
        )

        // Generate level milestones roadmap
        val levelMilestones = listOf(
            LevelMilestone(1, "Awakened Seeker", "Tier I: Initiation", 0, "Unlock Basic State Diagnostics", level >= 1),
            LevelMilestone(3, "Attuned Witness", "Tier I: Initiation", 500, "Unlock Somatic Guidance Cards", level >= 3),
            LevelMilestone(5, "Conscious Explorer", "Tier II: Apprentice", 1000, "Unlock Multi-Emotion Blend Radar", level >= 5),
            LevelMilestone(7, "Mindful Cartographer", "Tier II: Apprentice", 1500, "Unlock Deep Catalog Nuance Badges", level >= 7),
            LevelMilestone(10, "Deep Somatic Practitioner", "Tier III: Adept", 2250, "Unlock Autonomic Vagal Modulator", level >= 10),
            LevelMilestone(15, "Affective Adept", "Tier III: Adept", 3500, "Unlock Circadian Cadence Analytics", level >= 15),
            LevelMilestone(20, "Master Alchemist of Emotion", "Tier IV: Master", 4750, "Unlock Archetype Synthesis", level >= 20),
            LevelMilestone(30, "Grandmaster of Human System", "Tier V: Grandmaster", 7250, "Unlock Transcendent Polymath Emblem", level >= 30),
            LevelMilestone(50, "Transcendent Sage", "Tier VI: Transcendent", 12250, "Universal Affective Sovereignty", level >= 50)
        )

        return MasteryProfile(
            totalPoints = totalEarnedPoints,
            maxPossiblePoints = maxPossiblePoints,
            currentLevel = level,
            levelTitle = levelTitle,
            rankTier = rankTier,
            currentLevelXp = totalEarnedPoints - currentLevelBase,
            nextLevelXp = 250,
            unlockedCount = unlockedCount,
            totalCount = totalCount,
            exploredEmotionsCount = uniqueCount,
            totalEmotionsCount = totalEmotionsInCatalog,
            currentStreak = streakDays,
            totalCheckIns = totalRecords,
            regulatedStatePercent = regulatedStatePercent,
            dominantCategory = dominantCategoryName,
            domains = domains,
            levelMilestones = levelMilestones,
            achievements = achievements
        )
    }

    private fun getDomainRank(percent: Int, base: String): String = when {
        percent >= 90 -> "Master $base (Tier V)"
        percent >= 70 -> "Senior $base (Tier IV)"
        percent >= 50 -> "Adept $base (Tier III)"
        percent >= 25 -> "Practicing $base (Tier II)"
        else -> "Initiate $base (Tier I)"
    }

    private fun calculateStreak(records: List<EmotionRecord>): Int {
        if (records.isEmpty()) return 0
        val days = records.map {
            val cal = Calendar.getInstance()
            cal.timeInMillis = it.timestamp
            "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.DAY_OF_YEAR)}"
        }.distinct().sortedDescending()

        if (days.isEmpty()) return 0

        val todayCal = Calendar.getInstance()
        val todayStr = "${todayCal.get(Calendar.YEAR)}-${todayCal.get(Calendar.DAY_OF_YEAR)}"
        todayCal.add(Calendar.DAY_OF_YEAR, -1)
        val yesterdayStr = "${todayCal.get(Calendar.YEAR)}-${todayCal.get(Calendar.DAY_OF_YEAR)}"

        if (days[0] != todayStr && days[0] != yesterdayStr) {
            return 0
        }

        var streak = 1
        for (i in 0 until days.size - 1) {
            val parts1 = days[i].split("-").map { it.toInt() }
            val parts2 = days[i + 1].split("-").map { it.toInt() }
            if (parts1[0] == parts2[0] && parts1[1] - parts2[1] == 1) {
                streak++
            } else {
                break
            }
        }
        return streak
    }

    private fun getRankTier(level: Int): String = when {
        level >= 50 -> "Tier VI: Transcendent"
        level >= 30 -> "Tier V: Grandmaster"
        level >= 20 -> "Tier IV: Master"
        level >= 10 -> "Tier III: Adept"
        level >= 5 -> "Tier II: Apprentice"
        else -> "Tier I: Initiation"
    }

    private fun getLevelTitle(level: Int): String = when {
        level >= 50 -> "Transcendent Polymath"
        level >= 40 -> "Sovereign Sage of Sentience"
        level >= 30 -> "Grandmaster of the Human System"
        level >= 20 -> "Master Alchemist of Emotion"
        level >= 15 -> "Affective Adept"
        level >= 10 -> "Deep Somatic Practitioner"
        level >= 7 -> "Mindful Cartographer"
        level >= 5 -> "Conscious Explorer"
        level >= 3 -> "Attuned Witness"
        level >= 2 -> "Awakened Seeker"
        else -> "Emotional Novice"
    }
}

