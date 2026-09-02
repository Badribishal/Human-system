package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmotionRecordDao {
    @Query("SELECT * FROM emotion_records ORDER BY timestamp DESC")
    fun getAllRecords(): Flow<List<EmotionRecord>>

    @Query("SELECT * FROM emotion_records ORDER BY timestamp DESC LIMIT 1")
    fun getLatestRecord(): Flow<EmotionRecord?>

    @Query("SELECT * FROM emotion_records WHERE id = :id")
    fun getRecordById(id: Long): Flow<EmotionRecord?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: EmotionRecord): Long

    @Delete
    suspend fun deleteRecord(record: EmotionRecord)

    @Query("DELETE FROM emotion_records WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM emotion_records")
    suspend fun clearAll()
}
