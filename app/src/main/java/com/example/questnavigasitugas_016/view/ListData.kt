package com.example.questnavigasitugas_016.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.questnavigasitugas_016.R
import com.example.questnavigasitugas_016.data.FormData
import com.example.questnavigasitugas_016.viewmodel.ParticipantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListDataScreen(navController: NavController, viewModel: ParticipantViewModel) {
    val participants by viewModel.participants.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.participant_list_title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(participants) { participant ->
                ParticipantCard(participant = participant)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ParticipantCard(participant: FormData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.full_name_label), style = MaterialTheme.typography.labelSmall)
                Text(participant.nama, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.marital_status_label), style = MaterialTheme.typography.labelSmall)
                Text(participant.status, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.gender_label), style = MaterialTheme.typography.labelSmall)
                Text(participant.gender, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.address_label), style = MaterialTheme.typography.labelSmall)
                Text(participant.alamat, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                if (navController.currentDestination?.route != "list_data") {
                    navController.navigate("list_data") {
                        popUpTo("list_data") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(id = R.string.home_button))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = { navController.navigate("formulir") },
            modifier = Modifier.weight(1f)
        ) {
            Text(stringResource(id = R.string.form_button))
        }
    }
}