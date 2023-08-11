package com.claucas90.e3m6.View.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.claucas90.e3m6.Model.Palabras
import com.claucas90.e3m6.Model.Repository.PalabrasRepository
import kotlinx.coroutines.launch


class PalabrasViewModel (private val repository: PalabrasRepository) : ViewModel()  {

    // devuelve todos los datos de la bd
    val allDatos: LiveData<List<Palabras>> = repository.allDatos

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(palabra: Palabras) = viewModelScope.launch {
        repository.insert(palabra)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun eliminarUno(id:Int) = viewModelScope.launch {
        repository.deleteUno(id)
    }

}

class PalabrasViewModelFactory(private val repository: PalabrasRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PalabrasViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PalabrasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}