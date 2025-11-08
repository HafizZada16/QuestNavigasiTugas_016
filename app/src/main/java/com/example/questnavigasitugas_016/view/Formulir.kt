package com.example.questnavigasitugas_016.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.questnavigasitugas_016.R
import com.example.questnavigasitugas_016.data.FormData
import com.example.questnavigasitugas_016.viewmodel.ParticipantViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

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
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                stringResource(id = R.string.full_name_label),
                style = MaterialTheme.typography.labelMedium
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(stringResource(id = R.string.full_name_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.gender_label), style = MaterialTheme.typography.labelMedium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == stringResource(id = R.string.gender_male),
                    onClick = { gender = stringResource(id = R.string.gender_male)}
                )
                Text(stringResource(id = R.string.gender_male))
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = gender == stringResource(id = R.string.gender_female),
                    onClick = { gender = stringResource(id = R.string.gender_female) }
                )
                Text(stringResource(id = R.string.gender_female))
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.marital_status_label), style = MaterialTheme.typography.labelMedium)
            MaritalStatusDropdown(
                selectedValue = maritalStatus,
                onValueChange = { maritalStatus = it }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(stringResource(R.string.address_label), style = MaterialTheme.typography.labelMedium)
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                placeholder = { Text(stringResource(id = R.string.address_placeholder)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                ) {
                    Text(stringResource(id = R.string.back_button))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = handleSubmit,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8A2BE2))
                ) {
                    Text(stringResource(id = R.string.submit_button), color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun MaritalStatusDropdown(selectedValue: String, onValueChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("Kawin", "Lajang", "Cerai")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (selectedValue.isEmpty()) stringResource(R.string.marital_status_placeholder) else selectedValue,
                color = if (selectedValue.isEmpty()) Color.Gray else MaterialTheme.colorScheme.onSurface
            )
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ValidationPopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.validation_error_title)) },
        text = { Text(stringResource(id = R.string.validation_error_message)) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(id = R.string.ok_button))
            }
        }
    )
}

@Composable
private fun SuccessPopup(participant: FormData, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.success_popup_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("Nama: ${participant.nama}")
                    Text("Jenis Kelamin: ${participant.gender}")
                    Text("Status: ${participant.status}")
                    Text("Alamat: ${participant.alamat}")
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(stringResource(id = R.string.ok_button))
                }
            }
        }
    }
}

