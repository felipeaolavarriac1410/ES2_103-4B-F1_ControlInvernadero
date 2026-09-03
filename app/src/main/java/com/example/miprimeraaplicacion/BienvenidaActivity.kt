package com.example.miprimeraaplicacion

// Importa Bundle para el ciclo de vida de la Activity
import android.os.Bundle
// Importa TextView para manipular etiquetas de texto en pantalla
import android.widget.TextView
// Importa la función para soporte edge-to-edge
import androidx.activity.enableEdgeToEdge
// Importa la clase base AppCompatActivity
import androidx.appcompat.app.AppCompatActivity
// Importa ViewCompat para listeners de ventanas del sistema
import androidx.core.view.ViewCompat
// Importa WindowInsetsCompat para obtener dimensiones de barras de estado/navegación
import androidx.core.view.WindowInsetsCompat

// Declaración de la clase para la segunda pantalla (Bienvenida)
class BienvenidaActivity : AppCompatActivity() {

    // Método que se ejecuta al crearse esta pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama a la inicialización de la superclase
        super.onCreate(savedInstanceState)
        // Habilita diseño de pantalla completa detrás de las barras del sistema
        enableEdgeToEdge()
        // Carga y muestra el archivo XML correspondiente (activity_bienvenida.xml)
        setContentView(R.layout.activity_bienvenida)
        // Ajusta el padding para evitar que el contenido quede oculto bajo las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtiene dimensiones de las barras del sistema
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Aplica el relleno perimetral a la vista raíz
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Devuelve los insets procesados
            insets
        }

        // Lee el dato tipo String que fue enviado desde MainActivity con la clave "usuario"
        val usuario = intent.getStringExtra("usuario")
        // Busca y obtiene la referencia del TextView txtBienvenida definido en el XML
        val txtBienvenida = findViewById<TextView>(R.id.txtBienvenida)
        // Modifica el texto en pantalla concatenando el saludo con el valor recibido
        txtBienvenida.text = "Bienvenido, $usuario"
    }
}
