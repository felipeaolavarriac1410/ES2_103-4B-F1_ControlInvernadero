package com.example.miprimeraaplicacion

// Importa la clase Intent para permitir la navegación y comunicación entre Activities (pantallas)
import android.content.Intent
// Importa Bundle para manejar el estado guardado de la Activity en su ciclo de vida
import android.os.Bundle
// Importa View para recibir la vista que disparó un evento de clic
import android.view.View
// Importa el componente visual CheckBox
import android.widget.CheckBox
// Importa el componente visual EditText para campos de entrada de texto
import android.widget.EditText
// Importa Toast para mostrar notificaciones flotantes temporales en pantalla
import android.widget.Toast
// Importa la función para habilitar el diseño de borde a borde (edge-to-edge)
import androidx.activity.enableEdgeToEdge
// Importa la clase base para actividades con soporte de compatibilidad hacia atrás
import androidx.appcompat.app.AppCompatActivity
// Importa utilidades para manejar vistas y compatibilidad de diseño
import androidx.core.view.ViewCompat
// Importa el manejador de márgenes del sistema (barras de estado y navegación)
import androidx.core.view.WindowInsetsCompat

// Declaración de la clase principal de la pantalla de Login que hereda de AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Método que se ejecuta al crearse la pantalla (primer paso del ciclo de vida)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llama a la implementación original de la clase padre para inicializar la actividad
        super.onCreate(savedInstanceState)
        // Habilita el modo de pantalla completa que se extiende detrás de las barras del sistema
        enableEdgeToEdge()
        // Asocia y "dibuja" el diseño XML (activity_main.xml) en esta Activity
        setContentView(R.layout.activity_main)
        // Ajusta los márgenes (padding) de la vista raíz para no solaparse con las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Obtiene las dimensiones de las barras del sistema (status bar y navigation bar)
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Aplica padding a la vista con el tamaño exacto de las barras del sistema
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            // Retorna los insets procesados
            insets
        }
    }

    // Método público ejecutado automáticamente al hacer clic en el botón Ingresar (vía android:onClick)
    fun onIngresarClick(view: View) {
        // Busca y obtiene la referencia del campo de texto de usuario por su ID
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        // Busca y obtiene la referencia del campo de texto de contraseña por su ID
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        // Busca y obtiene la referencia del checkbox "Recordarme" por su ID
        val chkRecordarme = findViewById<CheckBox>(R.id.chkRecordarme)

        // Extrae el texto ingresado en el campo usuario y lo convierte a String
        val usuario = edtUsuario.text.toString()
        // Extrae el texto ingresado en el campo contraseña y lo convierte a String
        val password = edtPassword.text.toString()
        // Obtiene el estado booleano del CheckBox (true si está marcado, false si no)
        val recordar = chkRecordarme.isChecked

        // Valida si alguno de los dos campos obligatorios está vacío
        if (usuario.isEmpty() || password.isEmpty()) {
            // Muestra un mensaje flotante breve advirtiendo al usuario que complete ambos campos
            Toast.makeText(this, "Completa usuario y contraseña", Toast.LENGTH_SHORT).show()
        } else {
            // Crea un Intent explícito indicando la pantalla actual (this) y la pantalla destino (BienvenidaActivity)
            val intent = Intent(this, BienvenidaActivity::class.java)
            // Empaqueta el nombre de usuario como un dato extra en el Intent con la clave "usuario"
            intent.putExtra("usuario", usuario)
            // Inicia la navegación y lanza la nueva Activity
            startActivity(intent)
        }
    }

    // Método público ejecutado automáticamente al hacer clic en el botón Limpiar (vía android:onClick)
    fun onLimpiarClick(view: View) {
        // Busca y obtiene la referencia del campo de texto de usuario por su ID
        val edtUsuario = findViewById<EditText>(R.id.edtUsuario)
        // Busca y obtiene la referencia del campo de texto de contraseña por su ID
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        // Busca y obtiene la referencia del checkbox "Recordarme" por su ID
        val chkRecordarme = findViewById<CheckBox>(R.id.chkRecordarme)

        // Limpia el texto del campo usuario dejándolo vacío
        edtUsuario.setText("")
        // Limpia el texto del campo contraseña dejándolo vacío
        edtPassword.setText("")
        // Desmarca la casilla de verificación de recordarme
        chkRecordarme.isChecked = false
    }
}