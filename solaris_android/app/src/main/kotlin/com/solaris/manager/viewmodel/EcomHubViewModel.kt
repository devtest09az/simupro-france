package com.solaris.manager.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.solaris.manager.data.EcomDossiersData
import com.solaris.manager.model.ClientLeadItem
import com.solaris.manager.model.EcomDossier
import com.solaris.manager.model.ProjectPhase
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EcomHubUiState(
    val currentPhase: ProjectPhase = ProjectPhase.CONCEPTION,
    val dossiers: List<EcomDossier> = emptyList(),
    val selectedDossierId: String? = "dossier_solaire",
    val activeNotesByDossier: Map<String, String> = emptyMap(),
    val noteSavedFeedback: String? = null,
    val clientLeads: List<ClientLeadItem> = emptyList()
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
            val restoredVerifications = dossier.associateVerifications.map { v ->
                val isVerified = prefs.getBoolean("verif_${dossier.id}_${v.id}", v.isVerified)
                v.copy(isVerified = isVerified)
            }
            val restoredPhases = dossier.operationalPhases.map { step ->
                val isDone = prefs.getBoolean("phase_${dossier.id}_${step.stepNumber}", step.isCompleted)
                step.copy(isCompleted = isDone)
            }
            val restoredHumanPrerequisites = dossier.humanPrerequisites.map { item ->
                val isDone = prefs.getBoolean("human_${dossier.id}_${item.id}", item.isCompleted)
                item.copy(isCompleted = isDone)
            }
            val completedCount = restoredActions.count { it.isDone }
            val percent = if (restoredActions.isNotEmpty()) ((completedCount.toDouble() / restoredActions.size) * 100).toInt() else 0
            dossier.copy(
                partnerNotes = persistedNotes,
                productionActions = restoredActions,
                associateVerifications = restoredVerifications,
                operationalPhases = restoredPhases,
                humanPrerequisites = restoredHumanPrerequisites,
                progressPercent = percent
            )
        }

        val notesMap = initialDossiers.associate { it.id to it.partnerNotes }

        val initialLeads = loadClientLeads()

        return EcomHubUiState(
            currentPhase = ProjectPhase.CONCEPTION,
            dossiers = initialDossiers,
            selectedDossierId = "dossier_solaire",
            activeNotesByDossier = notesMap,
            noteSavedFeedback = null,
            clientLeads = initialLeads
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

    fun toggleVerification(dossierId: String, verificationId: String) {
        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) {
                val updatedVerifs = dossier.associateVerifications.map { v ->
                    if (v.id == verificationId) {
                        val next = !v.isVerified
                        prefs.edit().putBoolean("verif_${dossier.id}_${v.id}", next).apply()
                        v.copy(isVerified = next)
                    } else {
                        v
                    }
                }
                dossier.copy(associateVerifications = updatedVerifs)
            } else {
                dossier
            }
        }
        _uiState.value = _uiState.value.copy(dossiers = updatedDossiers)
    }


    fun togglePhaseStep(dossierId: String, stepNumber: String) {
        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) {
                val updatedSteps = dossier.operationalPhases.map { step ->
                    if (step.stepNumber == stepNumber) {
                        val next = !step.isCompleted
                        prefs.edit().putBoolean("phase_${dossier.id}_${step.stepNumber}", next).apply()
                        step.copy(isCompleted = next)
                    } else {
                        step
                    }
                }
                dossier.copy(operationalPhases = updatedSteps)
            } else {
                dossier
            }
        }
        _uiState.value = _uiState.value.copy(dossiers = updatedDossiers)
    }

    fun toggleHumanPrerequisite(dossierId: String, prerequisiteId: String) {
        val updatedDossiers = _uiState.value.dossiers.map { dossier ->
            if (dossier.id == dossierId) {
                val updatedItems = dossier.humanPrerequisites.map { item ->
                    if (item.id == prerequisiteId) {
                        val next = !item.isCompleted
                        prefs.edit().putBoolean("human_${dossier.id}_${item.id}", next).apply()
                        item.copy(isCompleted = next)
                    } else {
                        item
                    }
                }
                dossier.copy(humanPrerequisites = updatedItems)
            } else {
                dossier
            }
        }
        _uiState.value = _uiState.value.copy(dossiers = updatedDossiers)
    }


    private fun loadClientLeads(): List<ClientLeadItem> {
        val jsonStr = prefs.getString("client_leads_json", null)
        if (jsonStr.isNullOrBlank()) {
            return listOf(
                ClientLeadItem(
                    id = "lead_1",
                    clientName = "Marc V.",
                    phone = "06 45 89 12 30",
                    city = "Toulouse (31)",
                    packType = "Pack Solo 440W (349 €)",
                    status = "Devis / Proforma envoyé",
                    dateCreated = "31/10/2026",
                    notes = "Intéressé par l'annonce Leboncoin. Veut équiper son abri de jardin orienté plein Sud."
                ),
                ClientLeadItem(
                    id = "lead_2",
                    clientName = "Stéphane B.",
                    phone = "06 78 12 90 44",
                    city = "Nantes (44)",
                    packType = "Pack Duo 880W (649 €)",
                    status = "Nouveau contact",
                    dateCreated = "31/10/2026",
                    notes = "Demande de rappel téléphonique pour valider l'absence de Consuel sur prise 220V."
                )
            )
        }
        return try {
            val array = JSONArray(jsonStr)
            val list = mutableListOf<ClientLeadItem>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                list.add(
                    ClientLeadItem(
                        id = obj.getString("id"),
                        clientName = obj.getString("clientName"),
                        phone = obj.getString("phone"),
                        city = obj.getString("city"),
                        packType = obj.getString("packType"),
                        status = obj.getString("status"),
                        dateCreated = obj.getString("dateCreated"),
                        notes = obj.optString("notes", "")
                    )
                )
            }
            list
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun persistLeads(leads: List<ClientLeadItem>) {
        val array = JSONArray()
        for (lead in leads) {
            val obj = JSONObject()
            obj.put("id", lead.id)
            obj.put("clientName", lead.clientName)
            obj.put("phone", lead.phone)
            obj.put("city", lead.city)
            obj.put("packType", lead.packType)
            obj.put("status", lead.status)
            obj.put("dateCreated", lead.dateCreated)
            obj.put("notes", lead.notes)
            array.put(obj)
        }
        prefs.edit().putString("client_leads_json", array.toString()).apply()
    }

    fun addClientLead(clientName: String, phone: String, city: String, packType: String, notes: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE)
        val newLead = ClientLeadItem(
            id = "lead_${System.currentTimeMillis()}",
            clientName = clientName,
            phone = phone,
            city = city,
            packType = packType,
            status = "Nouveau contact",
            dateCreated = dateFormat.format(Date()),
            notes = notes
        )
        val updated = listOf(newLead) + _uiState.value.clientLeads
        persistLeads(updated)
        _uiState.value = _uiState.value.copy(clientLeads = updated)
    }

    fun updateLeadStatus(leadId: String, newStatus: String) {
        val updated = _uiState.value.clientLeads.map { lead ->
            if (lead.id == leadId) lead.copy(status = newStatus) else lead
        }
        persistLeads(updated)
        _uiState.value = _uiState.value.copy(clientLeads = updated)
    }

    fun deleteClientLead(leadId: String) {
        val updated = _uiState.value.clientLeads.filter { it.id != leadId }
        persistLeads(updated)
        _uiState.value = _uiState.value.copy(clientLeads = updated)
    }
}
