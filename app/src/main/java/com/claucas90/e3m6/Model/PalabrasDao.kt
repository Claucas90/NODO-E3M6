package com.claucas90.e3m6.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface PalabrasDao {

    @Query("SELECT * FROM TABLE_PALABRAS ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Palabras>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(datos: Palabras)

    @Query("DELETE FROM TABLE_PALABRAS")
    suspend fun deleteAll()

    @Query("DELETE FROM TABLE_PALABRAS where id=:id")
    suspend fun deleteUno(id:Int)

    @Query("UPDATE TABLE_PALABRAS SET palabra =:nuevaPalabra WHERE id=:idPalabra")
    suspend fun updatePalabra(idPalabra:Int,nuevaPalabra:String)
}