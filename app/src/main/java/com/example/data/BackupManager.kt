package com.example.data

import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object BackupManager {

    fun exportToJson(records: List<EmotionRecord>): String {
        val root = JSONObject()
        root.put("app", "HumanSystem")
        root.put("version", 2)
        root.put("exportDate", SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()))
        root.put("totalRecords", records.size)

        val array = JSONArray()
        for (record in records) {
            val obj = JSONObject()
            obj.put("id", record.id)
            obj.put("timestamp", record.timestamp)
            obj.put("intensity", record.intensity)
            obj.put("contextNote", record.contextNote)
            obj.put("stateTitle", record.stateTitle)
            obj.put("stateSubtitle", record.stateSubtitle)
            obj.put("nervousSystemBranch", record.nervousSystemBranch)
            obj.put("valence", record.valence.toDouble())
            obj.put("arousal", record.arousal.toDouble())
            obj.put("summary", record.summary)

            val emotionNames = JSONArray()
            record.emotionNames.forEach { emotionNames.put(it) }
            obj.put("emotionNames", emotionNames)

            val recTitles = JSONArray()
            record.recommendationTitles.forEach { recTitles.put(it) }
            obj.put("recommendationTitles", recTitles)

            val recInstructions = JSONArray()
            record.recommendationInstructions.forEach { recInstructions.put(it) }
            obj.put("recommendationInstructions", recInstructions)

            array.put(obj)
        }
        root.put("records", array)

        return root.toString(2)
    }

    fun parseJson(jsonString: String): List<EmotionRecord> {
        val records = mutableListOf<EmotionRecord>()
        val root = JSONObject(jsonString)
        val array = root.getJSONArray("records")

        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val timestamp = obj.optLong("timestamp", System.currentTimeMillis())
            val intensity = obj.optInt("intensity", 3)
            val contextNote = obj.optString("contextNote", "")
            val stateTitle = obj.optString("stateTitle", "Emotional State")
            val stateSubtitle = obj.optString("stateSubtitle", "")
            val nervousSystem = obj.optString("nervousSystemBranch", obj.optString("primaryNervousSystemBranch", "Ventral Vagal"))
            val valence = obj.optDouble("valence", 0.0).toFloat()
            val arousal = obj.optDouble("arousal", 0.5).toFloat()
            val summary = obj.optString("summary", "")

            val emotionNames = mutableListOf<String>()
            val namesArr = obj.optJSONArray("emotionNames")
            if (namesArr != null) {
                for (j in 0 until namesArr.length()) {
                    emotionNames.add(namesArr.getString(j))
                }
            }

            val recTitles = mutableListOf<String>()
            val recTitlesArr = obj.optJSONArray("recommendationTitles")
            if (recTitlesArr != null) {
                for (j in 0 until recTitlesArr.length()) {
                    recTitles.add(recTitlesArr.getString(j))
                }
            }

            val recInstructions = mutableListOf<String>()
            val recInstArr = obj.optJSONArray("recommendationInstructions")
            if (recInstArr != null) {
                for (j in 0 until recInstArr.length()) {
                    recInstructions.add(recInstArr.getString(j))
                }
            }

            records.add(
                EmotionRecord(
                    id = 0, // Auto-generate new IDs on import
                    timestamp = timestamp,
                    emotionNames = emotionNames,
                    intensity = intensity,
                    contextNote = contextNote,
                    stateTitle = stateTitle,
                    stateSubtitle = stateSubtitle,
                    nervousSystemBranch = nervousSystem,
                    valence = valence,
                    arousal = arousal,
                    summary = summary,
                    recommendationTitles = recTitles,
                    recommendationInstructions = recInstructions
                )
            )
        }
        return records
    }
}
