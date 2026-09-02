package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        EmotionRecord::class,
        EmotionEntry::class,
        Recommendation::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class HumanSystemDatabase : RoomDatabase() {
    abstract fun emotionRecordDao(): EmotionRecordDao
    abstract fun emotionEntryDao(): EmotionEntryDao
    abstract fun recommendationDao(): RecommendationDao

    companion object {
        @Volatile
        private var INSTANCE: HumanSystemDatabase? = null

        fun getDatabase(context: Context): HumanSystemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HumanSystemDatabase::class.java,
                    "human_system_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
