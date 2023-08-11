package com.claucas90.e3m6.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.claucas90.e3m6.R
import com.claucas90.e3m6.databinding.ActivityMainBinding
import com.claucas90.e3m6.Model.Palabras
import com.claucas90.e3m6.Model.PalabrasRoomDatabase
import com.claucas90.e3m6.Model.Repository.PalabrasRepository
import com.claucas90.e3m6.View.ViewModel.PalabrasViewModel
import com.claucas90.e3m6.View.ViewModel.PalabrasViewModelFactory

class MainActivity : AppCompatActivity(), BlankFragment.ListaButtonClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var palabrasViewModel: PalabrasViewModel
    lateinit var data: List<Palabras>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = PalabrasRoomDatabase.getDatabase(applicationContext)
        val palabrasDao = database.PalabrasDao()
        val palabrasRepository = PalabrasRepository(palabrasDao)
        val palabrasViewModelFactory = PalabrasViewModelFactory(palabrasRepository)
        palabrasViewModel = ViewModelProvider(this, palabrasViewModelFactory).get(PalabrasViewModel::class.java)

        palabrasViewModel.allDatos.observe(this, Observer { datosList ->
            data = datosList
        })

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BlankFragment>(androidx.fragment.R.id.fragment_container_view_tag)
        }
    }

    override fun onListaButtonClick() {
        if (::data.isInitialized && data.isNotEmpty()) {
            val listaFragment = ListaFragment()
            val bundle = Bundle()
            listaFragment.arguments = bundle
            supportFragmentManager.commit {
                replace(androidx.fragment.R.id.fragment_container_view_tag, listaFragment)
                addToBackStack(null)
            }
        } else {
            Toast.makeText(this, "La lista está vacía", Toast.LENGTH_SHORT).show()
        }
    }

    override fun insertar() {
        val palabraEditText = findViewById<EditText>(R.id.textPalabra)
        val palabra = palabraEditText.text.toString()
        val datos = Palabras(null, palabra)
        palabrasViewModel.insert(datos)
        Toast.makeText(this, "Agregado correctamente", Toast.LENGTH_SHORT).show()
    }

    fun eliminarUno() {
        if (::data.isInitialized && data.isNotEmpty()) {
            val primerValor = data.first()
            val valorAEliminar = primerValor.id
            palabrasViewModel.eliminarUno(valorAEliminar!!)
            data = data.filterNot { it.id == valorAEliminar }
            actualizarVista()
        }
    }

    fun eliminar() {
        palabrasViewModel.deleteAll()
        data = emptyList()
        actualizarVista()
    }

    private fun actualizarVista() {
        val listaFragment = ListaFragment()
        supportFragmentManager.beginTransaction()
            .replace(androidx.fragment.R.id.fragment_container_view_tag, listaFragment)
            .commit()
    }
}
