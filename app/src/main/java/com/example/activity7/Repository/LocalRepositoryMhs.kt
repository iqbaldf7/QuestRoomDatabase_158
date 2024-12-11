package com.example.activity7.Repository

import com.example.activity7.data.dao.MahasiswaDao
import com.example.activity7.data.entity.Mahasiswa

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
): RepositoryMhs {
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
       mahasiswaDao.insertMahasiswa(mahasiswa)
    }

}