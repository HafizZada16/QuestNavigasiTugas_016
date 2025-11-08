package com.example.questnavigasitugas_016.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.questnavigasitugas_016.R
import com.example.questnavigasitugas_016.data.FormData
import com.example.questnavigasitugas_016.viewmodel.ParticipantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulirScreen(navController: NavController, viewModel: ParticipantViewModel) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<String?>(null) }
    var maritalStatus by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var showValidationError by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf<FormData?>(null) }

    val handleSubmit = {
        if (name.isBlank() || gender == null || maritalStatus.isBlank() || address.isBlank()) {
            showValidationError = true
        } else {
            val newParticipant = FormData(
                nama = name,
                gender = gender!!,
                status = maritalStatus,
                alamat = address
            )
            viewModel.addParticipant(newParticipant)
            showSuccessDialog = newParticipant
        }
    }
    if (showValidationError) {
        val participant = null
        ValidationPopup(onDismiss = { showValidationError = false })
    }
    showSuccessDialog?.let { participant ->
        SuccessPopup(participant = participant, onDismiss = {
            showSuccessDialog = null
            navController.popBackStack()
        })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.registration_form_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
        }
    ){}
}

