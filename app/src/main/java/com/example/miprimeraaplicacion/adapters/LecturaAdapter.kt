package com.example.miprimeraaplicacion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.models.Lectura

// Modificamos el constructor para recibir una función (lambda) que se ejecutará cuando se haga clic en eliminar
class LecturaAdapter(
    private var listaLecturas: List<Lectura> = listOf(),
    private val onEliminarClick: (Lectura) -> Unit
) : RecyclerView.Adapter<LecturaAdapter.LecturaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lectura, parent, false)
        return LecturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: LecturaViewHolder, position: Int) {
        val lectura = listaLecturas[position]
        // Le pasamos al bind la lectura y la función que debe ejecutarse al presionar el botón de eliminar
        holder.bind(lectura, onEliminarClick)
    }

    override fun getItemCount(): Int {
        return listaLecturas.size
    }

    fun actualizarLista(nuevaLista: List<Lectura>) {
        listaLecturas = nuevaLista
        notifyDataSetChanged()
    }

    class LecturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        private val txtValor: TextView = itemView.findViewById(R.id.txtValor)
        // Agregamos la referencia al botón de eliminar que creamos en el XML
        private val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminar)

        // Actualizamos el bind para recibir la función de eliminación
        fun bind(lectura: Lectura, onEliminarClick: (Lectura) -> Unit) {
            txtDescripcion.text = lectura.descripcion
            txtValor.text = "Valor: ${lectura.valor}"

            // Configuramos qué pasa cuando alguien toca el tarro de basura
            btnEliminar.setOnClickListener {
                onEliminarClick(lectura) // Ejecutamos la función que nos pasaron, entregándole la lectura actual
            }
        }
    }
}
