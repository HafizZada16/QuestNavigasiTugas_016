package com.example.questnavigasitugas_016.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.questnavigasitugas_016.R

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.welcome_title),
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(48.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("CARD-LST", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8A2BE2))
            Text("MOBILE APP", fontSize = 16.sp, color = Color.Gray)
            Text("2025", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8A2BE2))
        }
        Spacer(modifier = Modifier.weight(1f))

        Text(text = stringResource(id = R.string.developer_name))
        Text(text = stringResource(id = R.string.developer_nim))

        Spacer(modifier = Modifier.height(24.dp))
    }
}