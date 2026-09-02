package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emotion_records")
data class EmotionRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val emotionNames: List<String>,
    val intensity: Int, // 1 to 5
    val contextNote: String,
    val stateTitle: String,
    val stateSubtitle: String,
    val nervousSystemBranch: String,
    val valence: Float,
    val arousal: Float,
    val summary: String,
    val recommendationTitles: List<String>,
    val recommendationInstructions: List<String>
)
