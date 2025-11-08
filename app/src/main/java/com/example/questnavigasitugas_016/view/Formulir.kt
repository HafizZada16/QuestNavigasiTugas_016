package com.example.questnavigasitugas_016.view

import android.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.questnavigasitugas_016.viewmodel.ParticipantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulirScreen(navController: NavController, viewModel: ParticipantViewModel) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<String?>(null) }
    var maritalStatus by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

}