package com.example.data

import com.example.model.Emotion
import com.example.model.EmotionCatalog
import com.example.model.EmotionCategory
import com.example.model.SystemCalculator
import com.example.model.SystemDiagnosis
import kotlinx.coroutines.flow.Flow

class HumanSystemRepository(
    private val recordDao: EmotionRecordDao,
    private val entryDao: EmotionEntryDao? = null,
    private val recommendationDao: RecommendationDao? = null
) {

    val allRecords: Flow<List<EmotionRecord>> = recordDao.getAllRecords()
    val latestRecord: Flow<EmotionRecord?> = recordDao.getLatestRecord()

    fun getAllAvailableEmotions(): List<Emotion> = EmotionCatalog.allEmotions

    fun filterEmotions(category: EmotionCategory?, searchQuery: String): List<Emotion> {
        val list = EmotionCatalog.allEmotions
        val filteredByCategory = if (category != null) {
            list.filter { it.category == category }
        } else {
            list
        }
        return if (searchQuery.isBlank()) {
            filteredByCategory
        } else {
            val query = searchQuery.trim().lowercase()
            filteredByCategory.filter {
                it.name.lowercase().contains(query) ||
                        it.category.displayName.lowercase().contains(query) ||
                        it.biologicalPurpose.lowercase().contains(query) ||
                        it.somaticSignal.lowercase().contains(query) ||
                        it.regulationHint.lowercase().contains(query)
            }
        }
    }

    suspend fun importRecords(records: List<EmotionRecord>): Int {
        var count = 0
        for (record in records) {
            val id = recordDao.insertRecord(record)
            entryDao?.let { dao ->
                val entry = EmotionEntry(
                    id = id,
                    emotionIds = record.emotionNames.map { it.lowercase().replace(" ", "_") },
                    emotionNames = record.emotionNames,
                    intensity = record.intensity,
                    contextNote = record.contextNote,
                    stateTitle = record.stateTitle,
                    stateSubtitle = record.stateSubtitle,
                    nervousSystemBranch = record.nervousSystemBranch,
                    valence = record.valence,
                    arousal = record.arousal,
                    summary = record.summary
                )
                val recommendations = record.recommendationTitles.mapIndexed { idx, title ->
                    Recommendation(
                        entryId = id,
                        title = title,
                        category = "Regulation",
                        instruction = record.recommendationInstructions.getOrElse(idx) { "" },
                        whyItWorks = "Autonomic nervous system stabilization",
                        iconName = "spa"
                    )
                }
                dao.insertEntryWithRecommendations(entry, recommendations)
            }
            count++
        }
        return count
    }

    suspend fun saveEmotionRecord(
        selectedEmotions: List<Emotion>,
        intensity: Int,
        contextNote: String,
        diagnosis: SystemDiagnosis
    ): Long {
        val record = EmotionRecord(
            emotionNames = selectedEmotions.map { it.name },
            intensity = intensity,
            contextNote = contextNote.trim(),
            stateTitle = diagnosis.stateTitle,
            stateSubtitle = diagnosis.stateSubtitle,
            nervousSystemBranch = diagnosis.primaryNervousSystemBranch,
            valence = diagnosis.valence,
            arousal = diagnosis.arousal,
            summary = diagnosis.summary,
            recommendationTitles = diagnosis.recommendations.map { it.title },
            recommendationInstructions = diagnosis.recommendations.map { it.instruction }
        )
        val id = recordDao.insertRecord(record)

        // Also save to EmotionEntry & Recommendation tables for normalized schema
        entryDao?.let { dao ->
            val entry = EmotionEntry(
                id = id,
                emotionIds = selectedEmotions.map { it.id },
                emotionNames = selectedEmotions.map { it.name },
                intensity = intensity,
                contextNote = contextNote.trim(),
                stateTitle = diagnosis.stateTitle,
                stateSubtitle = diagnosis.stateSubtitle,
                nervousSystemBranch = diagnosis.primaryNervousSystemBranch,
                valence = diagnosis.valence,
                arousal = diagnosis.arousal,
                summary = diagnosis.summary
            )
            val recommendations = diagnosis.recommendations.map { rec ->
                Recommendation(
                    entryId = id,
                    title = rec.title,
                    category = rec.category,
                    instruction = rec.instruction,
                    whyItWorks = rec.whyItWorks,
                    iconName = rec.iconName
                )
            }
            dao.insertEntryWithRecommendations(entry, recommendations)
        }

        return id
    }

    suspend fun deleteRecord(record: EmotionRecord) {
        recordDao.deleteRecord(record)
        entryDao?.getEntryById(record.id)?.let { entry ->
            entryDao.deleteEntry(entry)
        }
    }

    suspend fun deleteRecordById(id: Long) {
        recordDao.deleteById(id)
        entryDao?.getEntryById(id)?.let { entry ->
            entryDao.deleteEntry(entry)
        }
    }

    suspend fun clearAllRecords() {
        recordDao.clearAll()
        entryDao?.deleteAllEntries()
        recommendationDao?.deleteAll()
    }
}
