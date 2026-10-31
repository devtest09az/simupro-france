package com.solaris.manager.viewmodel

import androidx.lifecycle.ViewModel
import com.solaris.manager.data.EcomDossiersData
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.ProjectPhase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EcomHubUiState(
    val currentPhase: ProjectPhase = ProjectPhase.CONCEPTION,
    val dossiers: List<EcomDossier> = EcomDossiersData.dossiers,
    val selectedDossierId: String? = null
)

class EcomHubViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EcomHubUiState())
    val uiState: StateFlow<EcomHubUiState> = _uiState.asStateFlow()

    fun selectPhase(phase: ProjectPhase) {
        _uiState.value = _uiState.value.copy(currentPhase = phase)
    }

    fun selectDossier(dossierId: String?) {
        val nextId = if (_uiState.value.selectedDossierId == dossierId) null else dossierId
        _uiState.value = _uiState.value.copy(selectedDossierId = nextId)
    }

    fun toggleAction(dossierId: String, actionId: String) {
        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) {
                val updatedActions = dossier.productionActions.map { action ->
                    if (action.id == actionId) action.copy(isDone = !action.isDone) else action
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
