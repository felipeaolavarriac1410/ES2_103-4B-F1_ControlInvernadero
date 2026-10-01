package com.example.miprimeraaplicacion.views

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.models.Lectura
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearLecturaActivity : AppCompatActivity() {

    // Variable para la base de datos Firestore
    private lateinit var db: FirebaseFirestore

    // Variables para los campos de texto
    private lateinit var edtDescripcion: EditText
    private lateinit var edtValor: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_lectura)

        // Inicializa Firestore
        db = Firebase.firestore

        // Conecta las variables con los componentes visuales del XML
        edtDescripcion = findViewById(R.id.edtDescripcion)
        edtValor = findViewById(R.id.edtValor)
    }

    // Método que se ejecuta al presionar el botón "Guardar"
    fun onGuardarLecturaClick(view: View) {
        // Obtiene el texto escrito por el usuario
        val descripcion = edtDescripcion.text.toString()
        val valorString = edtValor.text.toString()

        // Validación simple: comprueba que ninguno de los campos esté vacío
        if (descripcion.isEmpty() || valorString.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return // Detiene la ejecución si falta información
        }

        // Convierte el valor String a Double. 
        // Usa toDoubleOrNull() para evitar errores si el usuario ingresó algo que no es un número válido.
        val valorDouble = valorString.toDoubleOrNull()
        if (valorDouble == null) {
            Toast.makeText(this, "El valor ingresado no es válido", Toast.LENGTH_SHORT).show()
            return
        }

        // Crea un nuevo objeto de la clase Lectura
        // No enviamos el 'id', Firestore lo creará por nosotros automáticamente
        val nuevaLectura = Lectura(descripcion = descripcion, valor = valorDouble)

        // Agrega el nuevo objeto a la colección "lecturas" en Firestore
        db.collection("lecturas")
            .add(nuevaLectura) // .add() inserta un nuevo documento generando un ID automático
            .addOnSuccessListener { documento ->
                // Si la inserción es exitosa, se ejecuta esto:
                Toast.makeText(this, "Lectura guardada exitosamente", Toast.LENGTH_SHORT).show()
                // Cierra esta pantalla (Activity) y devuelve al usuario a la lista anterior
                finish()
            }
            .addOnFailureListener { e ->
                // Si ocurre un error al intentar guardar, se ejecuta esto:
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}
