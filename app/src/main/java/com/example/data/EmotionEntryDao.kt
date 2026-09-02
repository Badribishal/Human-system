package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

data class EmotionEntryWithRecommendations(
    @Embedded val entry: EmotionEntry,
    @Relation(
        parentColumn = "id",
        entityColumn = "entryId"
    )
    val recommendations: List<Recommendation>
)

@Dao
interface EmotionEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: EmotionEntry): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendations(recommendations: List<Recommendation>)

    @Transaction
    suspend fun insertEntryWithRecommendations(
        entry: EmotionEntry,
        recommendations: List<Recommendation>
    ): Long {
        val entryId = insertEntry(entry)
        val linkedRecs = recommendations.map { it.copy(entryId = entryId) }
        insertRecommendations(linkedRecs)
        return entryId
    }

    @Query("SELECT * FROM emotion_entries ORDER BY timestamp DESC")
    fun getAllEntries(): Flow<List<EmotionEntry>>

    @Transaction
    @Query("SELECT * FROM emotion_entries ORDER BY timestamp DESC")
    fun getAllEntriesWithRecommendations(): Flow<List<EmotionEntryWithRecommendations>>

    @Query("SELECT * FROM emotion_entries ORDER BY timestamp DESC LIMIT 1")
    fun getLatestEntry(): Flow<EmotionEntry?>

    @Query("SELECT * FROM emotion_entries WHERE id = :id")
    suspend fun getEntryById(id: Long): EmotionEntry?

    @Delete
    suspend fun deleteEntry(entry: EmotionEntry)

    @Query("DELETE FROM emotion_entries")
    suspend fun deleteAllEntries()
}
