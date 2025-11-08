package com.example.questnavigasitugas_016.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.questnavigasitugas_016.data.FormData

class ParticipantViewModel : ViewModel(){
    private val _participants = MutableLiveData<List<FormData>>(
        listOf(
            FormData(
                nama = "Asroni Sukirman",
                gender = "Laki - Laki",
                status = "Cerai",
                alamat = "Sleman"
            ),
            FormData(
                nama = "Aprilia Kurnianti",
                gender = "Perempuan",
                status = "Lajang",
                alamat = "Bantul"
            ),
            FormData(
                nama = "Haris Setyawan",
                gender = "Laki - Laki",
                status = "Kawin",
                alamat = "Jogja"
            ),
            FormData(
                nama = "Hafiz",
                gender = "Laki - Laki",
                status = "Lajang",
                alamat = "Pacitan"
            )
        )
    )
    val participants: LiveData<List<FormData>> = _participants

    fun addParticipant(participant: FormData) {
        val currentList = _participants.value ?: emptyList()
        _participants.value = currentList + participant
    }
}