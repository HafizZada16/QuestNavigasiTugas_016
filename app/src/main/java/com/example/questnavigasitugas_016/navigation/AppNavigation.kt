package com.example.questnavigasitugas_016.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questnavigasitugas_016.view.FormulirScreen
import com.example.questnavigasitugas_016.view.ListDataScreen
import com.example.questnavigasitugas_016.view.WelcomeScreen
import com.example.questnavigasitugas_016.viewmodel.ParticipantViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val participantViewModel: ParticipantViewModel = viewModel()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }
        composable("list_data") {
            ListDataScreen(navController = navController, viewModel = participantViewModel)
        }
        composable("formulir") {
            FormulirScreen(navController = navController, viewModel = participantViewModel)
        }
    }
}