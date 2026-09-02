package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.EmotionRecord
import com.example.data.HumanSystemDatabase
import com.example.data.HumanSystemRepository
import com.example.model.Emotion
import com.example.model.EmotionCatalog
import com.example.model.EmotionCategory
import com.example.model.SystemCalculator
import com.example.model.SystemDiagnosis
import com.example.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

import com.example.data.BackupManager
import com.example.model.AchievementCalculator
import com.example.model.MasteryProfile
import kotlinx.coroutines.flow.map

import kotlinx.coroutines.flow.combine

class HumanSystemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HumanSystemRepository

    init {
        val database = HumanSystemDatabase.getDatabase(application)
        repository = HumanSystemRepository(
            recordDao = database.emotionRecordDao(),
            entryDao = database.emotionEntryDao(),
            recommendationDao = database.recommendationDao()
        )
    }

    // App Theme State
    private val _themeMode = MutableStateFlow(AppThemeMode.SYSTEM)
    val themeMode: StateFlow<AppThemeMode> = _themeMode.asStateFlow()

    // Record Tab State
    private val _selectedEmotions = MutableStateFlow<List<Emotion>>(emptyList())
    val selectedEmotions: StateFlow<List<Emotion>> = _selectedEmotions.asStateFlow()

    private val _intensity = MutableStateFlow(3)
    val intensity: StateFlow<Int> = _intensity.asStateFlow()

    private val _contextNote = MutableStateFlow("")
    val contextNote: StateFlow<String> = _contextNote.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow<EmotionCategory?>(null)
    val selectedCategory: StateFlow<EmotionCategory?> = _selectedCategory.asStateFlow()

    // Emotion Library Tab State
    private val _librarySearchQuery = MutableStateFlow("")
    val librarySearchQuery: StateFlow<String> = _librarySearchQuery.asStateFlow()

    private val _librarySelectedCategory = MutableStateFlow<EmotionCategory?>(null)
    val librarySelectedCategory: StateFlow<EmotionCategory?> = _librarySelectedCategory.asStateFlow()

    private val _viewingEmotionDetail = MutableStateFlow<Emotion?>(null)
    val viewingEmotionDetail: StateFlow<Emotion?> = _viewingEmotionDetail.asStateFlow()

    // Filtered emotions for Record screen
    val filteredEmotions: StateFlow<List<Emotion>> = combine(
        _selectedCategory,
        _searchQuery
    ) { cat, query ->
        repository.filterEmotions(cat, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EmotionCatalog.allEmotions
    )

    // Filtered emotions for Library screen
    val libraryEmotions: StateFlow<List<Emotion>> = combine(
        _librarySelectedCategory,
        _librarySearchQuery
    ) { cat, query ->
        repository.filterEmotions(cat, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = EmotionCatalog.allEmotions
    )

    // Calculated Diagnosis
    private val _currentDiagnosis = MutableStateFlow(SystemCalculator.calculateState(emptyList()))
    val currentDiagnosis: StateFlow<SystemDiagnosis> = _currentDiagnosis.asStateFlow()

    // Selected record for inspecting details in dialog
    private val _inspectingRecord = MutableStateFlow<EmotionRecord?>(null)
    val inspectingRecord: StateFlow<EmotionRecord?> = _inspectingRecord.asStateFlow()

    // Snackbars & Feedback
    private val _userMessage = MutableSharedFlow<String>()
    val userMessage: SharedFlow<String> = _userMessage.asSharedFlow()

    // Local DB Flow
    val historyRecords: StateFlow<List<EmotionRecord>> = repository.allRecords
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val latestRecord: StateFlow<EmotionRecord?> = repository.latestRecord
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    // Gamification & Achievements Profile
    val masteryProfile: StateFlow<MasteryProfile> = repository.allRecords
        .map { records -> AchievementCalculator.computeMastery(records) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AchievementCalculator.computeMastery(emptyList())
        )

    fun setThemeMode(mode: AppThemeMode) {
        _themeMode.value = mode
    }

    fun toggleEmotionSelection(emotion: Emotion) {
        val current = _selectedEmotions.value.toMutableList()
        if (current.any { it.id == emotion.id }) {
            current.removeAll { it.id == emotion.id }
        } else {
            current.add(emotion)
        }
        _selectedEmotions.value = current
        recalculate()
    }

    fun clearSelectedEmotions() {
        _selectedEmotions.value = emptyList()
        _contextNote.value = ""
        _intensity.value = 3
        recalculate()
    }

    fun setIntensity(level: Int) {
        _intensity.value = level.coerceIn(1, 5)
        recalculate()
    }

    fun setContextNote(note: String) {
        _contextNote.value = note
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setSelectedCategory(category: EmotionCategory?) {
        _selectedCategory.value = category
    }

    fun setLibrarySearchQuery(query: String) {
        _librarySearchQuery.value = query
    }

    fun setLibrarySelectedCategory(category: EmotionCategory?) {
        _librarySelectedCategory.value = category
    }

    fun setViewingEmotionDetail(emotion: Emotion?) {
        _viewingEmotionDetail.value = emotion
    }

    fun getFilteredEmotions(): List<Emotion> {
        return repository.filterEmotions(_selectedCategory.value, _searchQuery.value)
    }

    fun setInspectingRecord(record: EmotionRecord?) {
        _inspectingRecord.value = record
    }

    private fun recalculate() {
        _currentDiagnosis.value = SystemCalculator.calculateState(
            selectedEmotions = _selectedEmotions.value,
            intensity = _intensity.value,
            contextNote = _contextNote.value
        )
    }

    fun saveCalculation(onSuccess: (() -> Unit)? = null) {
        val emotions = _selectedEmotions.value
        if (emotions.isEmpty()) {
            viewModelScope.launch {
                _userMessage.emit("Please select at least one emotion before recording.")
            }
            return
        }

        val diagnosis = _currentDiagnosis.value
        viewModelScope.launch {
            repository.saveEmotionRecord(
                selectedEmotions = emotions,
                intensity = _intensity.value,
                contextNote = _contextNote.value,
                diagnosis = diagnosis
            )
            _userMessage.emit("Emotional check-in saved to Human System.")
            clearSelectedEmotions()
            onSuccess?.invoke()
        }
    }

    fun deleteRecord(record: EmotionRecord) {
        viewModelScope.launch {
            repository.deleteRecord(record)
            _userMessage.emit("Entry removed from system log.")
        }
    }

    fun clearAllHistory() {
        viewModelScope.launch {
            repository.clearAllRecords()
            _userMessage.emit("All system records cleared.")
        }
    }

    fun exportBackupJson(): String {
        val records = historyRecords.value
        return BackupManager.exportToJson(records)
    }

    fun importBackupJson(jsonString: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val parsed = BackupManager.parseJson(jsonString)
                if (parsed.isEmpty()) {
                    onResult(false, "No valid emotion records found in backup file.")
                    return@launch
                }
                val importedCount = repository.importRecords(parsed)
                _userMessage.emit("Successfully imported $importedCount records from backup.")
                onResult(true, "Restored $importedCount records.")
            } catch (e: Exception) {
                onResult(false, "Import failed: ${e.localizedMessage ?: "Invalid file format"}")
            }
        }
    }
}
