package com.example.miprimeraaplicacion.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimeraaplicacion.R
import com.google.firebase.auth.FirebaseAuth

class BienvenidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bienvenida)

        val mainView = findViewById<View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val tvMensaje = findViewById<TextView>(R.id.tvMensajeBienvenida)
        val usuarioEmail = intent.getStringExtra("usuario") ?: FirebaseAuth.getInstance().currentUser?.email ?: "Usuario"

        tvMensaje?.text = "¡Bienvenido, $usuarioEmail!"

        val btnFormulario = findViewById<Button>(R.id.btnIrAFormulario)
        val btnLista = findViewById<Button>(R.id.btnIrALista)
        val btnPreferencias = findViewById<Button>(R.id.btnIrAPreferencias)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        btnFormulario?.setOnClickListener {
            startActivity(Intent(this, FormularioLecturaActivity::class.java))
        }

        btnLista?.setOnClickListener {
            startActivity(Intent(this, ListaActivity::class.java))
        }

        btnPreferencias?.setOnClickListener {
            startActivity(Intent(this, PreferenciasActivity::class.java))
        }

        btnCerrarSesion?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}