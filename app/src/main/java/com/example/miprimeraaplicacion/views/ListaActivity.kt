package com.example.miprimeraaplicacion.views

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.adapters.InvernaderoAdapter
import com.example.miprimeraaplicacion.models.Invernadero
import com.google.firebase.firestore.FirebaseFirestore

class ListaActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var rvLecturas: RecyclerView
    private lateinit var adapter: InvernaderoAdapter
    private val listaInvernaderos = mutableListOf<Invernadero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista)

        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        db = FirebaseFirestore.getInstance()
        rvLecturas = findViewById(R.id.rvLecturas)
        val btnVolver = findViewById<Button>(R.id.btnVolverLista)

        rvLecturas.layoutManager = LinearLayoutManager(this)

        // Se pasan las funciones lambdas para los clics de editar y eliminar requeridos por tu InvernaderoAdapter
        adapter = InvernaderoAdapter(
            listaInvernaderos,
            onEditClick = { invernadero ->
                Toast.makeText(this, "Editar: ${invernadero.sector}", Toast.LENGTH_SHORT).show()
            },
            onDeleteClick = { invernadero ->
                eliminarLectura(invernadero)
            }
        )
        rvLecturas.adapter = adapter

        cargarDatosDesdeFirestore()

        btnVolver?.setOnClickListener {
            finish()
        }
    }

    private fun cargarDatosDesdeFirestore() {
        db.collection("lecturas")
            .get()
            .addOnSuccessListener { result ->
                listaInvernaderos.clear()
                for (document in result) {
                    val sector = document.getString("ubicacion") ?: document.getString("sector") ?: "Sin sector"
                    val temp = document.getDouble("temperatura") ?: 0.0
                    val hum = document.getDouble("humedad") ?: 0.0
                    val id = document.id
                    val estadoVentilador = if (temp > 25.0) "Encendido" else "Apagado"

                    listaInvernaderos.add(
                        Invernadero(
                            id = id,
                            sector = sector,
                            temperatura = temp,
                            humedad = hum,
                            estadoVentilador = estadoVentilador
                        )
                    )
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar datos: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }

    private fun eliminarLectura(invernadero: Invernadero) {
        db.collection("lecturas").document(invernadero.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Lectura eliminada", Toast.LENGTH_SHORT).show()
                cargarDatosDesdeFirestore()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al eliminar: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
    }
}