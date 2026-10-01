package com.example.miprimeraaplicacion.views

// Importaciones necesarias para interactuar con Android y Firebase
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.adapters.LecturaAdapter
import com.example.miprimeraaplicacion.models.Lectura
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Declara la clase ListaActivity que hereda de AppCompatActivity (representa una pantalla de Android)
class ListaActivity : AppCompatActivity() {

    // Variable para manejar la base de datos Firestore, se inicializará más adelante (lateinit)
    private lateinit var db: FirebaseFirestore
    // Variable para manejar el adaptador que conectará los datos con la lista visual
    private lateinit var adapter: LecturaAdapter
    // Variable para referenciar el componente visual RecyclerView definido en el XML
    private lateinit var rvLecturas: RecyclerView

    // Método que se ejecuta automáticamente cuando se crea la pantalla (Activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama al método padre para realizar las configuraciones base del sistema Android
        super.onCreate(savedInstanceState)
        // Asigna el archivo de diseño XML activity_lista.xml como la interfaz gráfica de esta pantalla
        setContentView(R.layout.activity_lista)

        // Inicializa la instancia de la base de datos Firestore
        db = Firebase.firestore

        // Inicializa el adaptador de lecturas creando una instancia nueva sin datos (lista vacía por defecto)
        adapter = LecturaAdapter()
        
        // Busca y enlaza el componente RecyclerView del diseño XML usando su ID (rvLecturas)
        rvLecturas = findViewById(R.id.rvLecturas)
        // Configura el RecyclerView con un LinearLayoutManager para que muestre los elementos en una lista vertical estándar
        rvLecturas.layoutManager = LinearLayoutManager(this)
        // Le asigna nuestro adaptador (adapter) al RecyclerView para que sepa cómo dibujar los datos
        rvLecturas.adapter = adapter

        // Llama al método personalizado que se encarga de escuchar los cambios en la base de datos en tiempo real
        escucharLectura()

        // Busca el FAB en el diseño XML
        val fabAgregar = findViewById<FloatingActionButton>(R.id.fabAgregarLectura)
        // Le asigna un escuchador para que haga algo cuando sea presionado
        fabAgregar.setOnClickListener {
            // Crea un Intent para ir a la nueva pantalla CrearLecturaActivity
            val intent = Intent(this, CrearLecturaActivity::class.java)
            // Lanza la nueva pantalla
            startActivity(intent)
        }
    }

    // Método personalizado para conectarse a Firestore y escuchar la colección de lecturas
    private fun escucharLectura() {
        // Accede a la colección llamada "lecturas" en Firestore y añade un listener (escuchador) de eventos en tiempo real
        db.collection("lecturas").addSnapshotListener { snapshot, error ->
            // Si ocurre un error al intentar leer los datos de Firebase, entra en esta condición
            if (error != null) {
                // Imprime un mensaje de advertencia (Warning) en la consola de depuración (Logcat) con el detalle del error
                Log.w("ListaActivity", "Error al escuchar las lecturas.", error)
                // Detiene la ejecución de este bloque de código porque hubo un fallo, evitando que la app colapse
                return@addSnapshotListener
            }

            // Si el snapshot (la instantánea o "foto" de los datos) no es nulo, significa que recibimos información exitosamente
            if (snapshot != null) {
                // Convierte automáticamente todos los documentos de Firestore en una lista de objetos de nuestra clase 'Lectura'
                val listaLecturas = snapshot.toObjects(Lectura::class.java)
                
                // Imprime en la consola (Debug) la cantidad total de lecturas que se recuperaron
                Log.d("ListaActivity", "Lecturas recuperadas: ${listaLecturas.size}")
                
                // Llama al método 'actualizarLista' de nuestro adaptador pasándole la nueva lista de datos 
                // Esto hará que la lista en pantalla se refresque automáticamente con la información más reciente
                adapter.actualizarLista(listaLecturas)
            }
        }
    }
}
