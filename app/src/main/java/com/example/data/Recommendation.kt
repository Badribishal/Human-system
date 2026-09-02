package com.example.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recommendations",
    foreignKeys = [
        ForeignKey(
            entity = EmotionEntry::class,
            parentColumns = ["id"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["entryId"])]
)
data class Recommendation(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entryId: Long = 0,
    val title: String,
    val category: String, // Somatic, Cognitive, Micro-Action, Boundary
    val instruction: String,
    val whyItWorks: String,
    val iconName: String
)
