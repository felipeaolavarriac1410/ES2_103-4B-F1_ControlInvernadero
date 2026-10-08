package com.example.miprimeraaplicacion.views

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miprimeraaplicacion.R

class PreferenciasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferencias)

        val mainView = findViewById<android.view.View>(R.id.main)
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        val switchNotif = findViewById<Switch>(R.id.switchNotificaciones)
        val switchModo = findViewById<Switch>(R.id.switchModoOscuro)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarPreferencias)
        val btnVolver = findViewById<Button>(R.id.btnVolverPreferencias)

        // Cargar preferencias guardadas con SharedPreferences
        val sharedPref = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        switchNotif?.isChecked = sharedPref.getBoolean("alertas", true)
        switchModo?.isChecked = sharedPref.getBoolean("modoOscuro", false)

        btnGuardar?.setOnClickListener {
            val editor = sharedPref.edit()
            editor.putBoolean("alertas", switchNotif.isChecked)
            editor.putBoolean("modoOscuro", switchModo.isChecked)
            editor.apply()

            Toast.makeText(this, "Preferencias guardadas", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnVolver?.setOnClickListener {
            finish()
        }
    }
}