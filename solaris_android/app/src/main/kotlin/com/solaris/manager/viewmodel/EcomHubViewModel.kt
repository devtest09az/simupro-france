package com.solaris.manager.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.solaris.manager.data.EcomDossiersData
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.ProjectPhase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EcomHubUiState(
    val currentPhase: ProjectPhase = ProjectPhase.CONCEPTION,
    val dossiers: List<EcomDossier> = emptyList(),
    val selectedDossierId: String? = "dossier_solaire",
    val activeNotesByDossier: Map<String, String> = emptyMap(),
    val noteSavedFeedback: String? = null
)

class EcomHubViewModel(application: Application) : AndroidViewModel(application) {
    private val prefs = application.getSharedPreferences("ecom_hub_storage", Context.MODE_PRIVATE)

    private val _uiState = MutableStateFlow(loadInitialState())
    val uiState: StateFlow<EcomHubUiState> = _uiState.asStateFlow()

    private fun loadInitialState(): EcomHubUiState {
        val initialDossiers = EcomDossiersData.dossiers.map { dossier ->
            val persistedNotes = prefs.getString("notes_${dossier.id}", dossier.partnerNotes) ?: dossier.partnerNotes
            val restoredActions = dossier.productionActions.map { action ->
                val isDone = prefs.getBoolean("action_${dossier.id}_${action.id}", action.isDone)
                action.copy(isDone = isDone)
            }
            val completedCount = restoredActions.count { it.isDone }
            val percent = if (restoredActions.isNotEmpty()) ((completedCount.toDouble() / restoredActions.size) * 100).toInt() else 0
            dossier.copy(
                partnerNotes = persistedNotes,
                productionActions = restoredActions,
                progressPercent = percent
            )
        }

        val notesMap = initialDossiers.associate { it.id to it.partnerNotes }

        return EcomHubUiState(
            currentPhase = ProjectPhase.CONCEPTION,
            dossiers = initialDossiers,
            selectedDossierId = "dossier_solaire",
            activeNotesByDossier = notesMap,
            noteSavedFeedback = null
        )
    }

    fun selectPhase(phase: ProjectPhase) {
        _uiState.value = _uiState.value.copy(currentPhase = phase)
    }

    fun selectDossier(dossierId: String?) {
        val nextId = if (_uiState.value.selectedDossierId == dossierId) null else dossierId
        _uiState.value = _uiState.value.copy(selectedDossierId = nextId)
    }

    fun onNoteChange(dossierId: String, text: String) {
        val updatedMap = _uiState.value.activeNotesByDossier.toMutableMap()
        updatedMap[dossierId] = text
        _uiState.value = _uiState.value.copy(activeNotesByDossier = updatedMap)
    }

    fun saveNotes(dossierId: String) {
        val noteContent = _uiState.value.activeNotesByDossier[dossierId] ?: ""
        prefs.edit().putString("notes_$dossierId", noteContent).apply()

        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) dossier.copy(partnerNotes = noteContent) else dossier
        }
        val dossierTitle = updatedDossiers.firstOrNull { it.id == dossierId }?.title ?: ""
        _uiState.value = _uiState.value.copy(
            dossiers = updatedDossiers,
            noteSavedFeedback = "Note sauvegardée pour : $dossierTitle"
        )
    }

    fun clearFeedback() {
        _uiState.value = _uiState.value.copy(noteSavedFeedback = null)
    }

    fun toggleAction(dossierId: String, actionId: String) {
        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) {
                val updatedActions = dossier.productionActions.map { action ->
                    if (action.id == actionId) {
                        val newDone = !action.isDone
                        prefs.edit().putBoolean("action_${dossier.id}_${action.id}", newDone).apply()
                        action.copy(isDone = newDone)
                    } else {
                        action
                    }
                }
                val completedCount = updatedActions.count { it.isDone }
                val newPercent = ((completedCount.toDouble() / updatedActions.size) * 100).toInt()
                dossier.copy(productionActions = updatedActions, progressPercent = newPercent)
            } else {
                dossier
            }
        }
        _uiState.value = _uiState.value.copy(dossiers = updatedDossiers)
    }
}
