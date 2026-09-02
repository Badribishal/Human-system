package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotion_entries")
data class EmotionEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val emotionIds: List<String>,
    val emotionNames: List<String>,
    val intensity: Int, // 1 to 5
    val contextNote: String,
    val stateTitle: String,
    val stateSubtitle: String,
    val nervousSystemBranch: String,
    val valence: Float,
    val arousal: Float,
    val summary: String
)
