package com.example.questnavigasitugas_016.viewmodel

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
}