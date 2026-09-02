package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecommendationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recommendation: Recommendation): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recommendations: List<Recommendation>)

    @Query("SELECT * FROM recommendations WHERE entryId = :entryId")
    fun getRecommendationsForEntry(entryId: Long): Flow<List<Recommendation>>

    @Query("DELETE FROM recommendations WHERE entryId = :entryId")
    suspend fun deleteRecommendationsForEntry(entryId: Long)

    @Query("DELETE FROM recommendations")
    suspend fun deleteAll()
}
