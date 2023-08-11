package com.claucas90.e3m6.Model.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.claucas90.e3m6.Model.Palabras
import com.claucas90.e3m6.Model.PalabrasDao

class PalabrasRepository(private val PalabrasDao: PalabrasDao) {

    val allDatos: LiveData<List<Palabras>> = PalabrasDao.getAllDatos()

    @WorkerThread
    suspend fun insert(palabra: Palabras) {
        PalabrasDao.insert(palabra)
    }
    @WorkerThread
    suspend fun deleteAll() {
        PalabrasDao.deleteAll()
    }
    @WorkerThread
    suspend fun deleteUno(id:Int) {
        PalabrasDao.deleteUno(id)
    }

    @WorkerThread
    suspend fun updatePalabra(idPalabra:Int,nuevaPalabra:String) {
        PalabrasDao.updatePalabra(idPalabra,nuevaPalabra)
    }
}