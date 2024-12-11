package com.example.activity7.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activity7.Repository.LocalRepositoryMhs
import com.example.activity7.Repository.RepositoryMhs
import com.example.activity7.data.entity.Mahasiswa
import kotlinx.coroutines.launch

class MahasiswaViewModel(private val repositoryMhs: RepositoryMhs ) : ViewModel(){
    var uiState by  mutableStateOf(MhsUiState())

    fun updateState(mahasiswaEvent: MahasiswaEvent) {
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
    private  fun  validateFields(): Boolean {
        val event = uiState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak bole kosong",
            nama = if (event.nama.isNotEmpty()) null else "NAMA tidak bole kosong",
            jemisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis kelamin tidak bole kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak bole kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "Kelas tidak bole kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "Angkatan tidak bole kosong",

            )
        uiState = uiState.copy(isEntryValid = errorState)
        return  errorState.isValid()
    }
    fun saveData() {
        val currentEvent = uiState.mahasiswaEvent
        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = " Date berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()

                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = " Date gagal disimpan"
                    )
                }
            }
            fun resetSnackBarMessage() {
                uiState = uiState.copy(snackBarMessage = null)
            }
        }
    }
    data class  MhsUiState(
        val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
        val isEntryValid: FormErrorState = FormErrorState(),
        val snackBarMessage: String? = null,
    )
}

data class MhsUiState(
    val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,

)
data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jemisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
    val angkatan: String? = null,

){
    fun isValid(): Boolean{
        return nim== null && nama== null&& jemisKelamin== null&&
                alamat== null&& kelas == null&& angkatan ==null
    }
}

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = "",
)

//Menyimpan input kedalam form entity
fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenisKelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

