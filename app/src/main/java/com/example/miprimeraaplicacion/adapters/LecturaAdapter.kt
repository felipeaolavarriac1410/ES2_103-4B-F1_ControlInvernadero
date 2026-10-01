package com.example.miprimeraaplicacion.adapters

// Importa las clases necesarias para el funcionamiento del RecyclerView y las vistas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.models.Lectura

// Declara la clase LecturaAdapter que hereda de RecyclerView.Adapter
// Recibe como parámetro inicial una lista de objetos Lectura, que por defecto está vacía
class LecturaAdapter(private var listaLecturas: List<Lectura> = listOf()) :
    RecyclerView.Adapter<LecturaAdapter.LecturaViewHolder>() {

    // Método llamado por el RecyclerView para crear cada nueva vista (item) de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturaViewHolder {
        // Infla (convierte de XML a objeto visual) el diseño item_lectura.xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lectura, parent, false)
        // Retorna un nuevo ViewHolder que contiene la vista recién inflada
        return LecturaViewHolder(view)
    }

    // Método llamado por el RecyclerView para llenar de datos cada vista (item)
    override fun onBindViewHolder(holder: LecturaViewHolder, position: Int) {
        // Obtiene el objeto Lectura correspondiente a la posición actual en la lista
        val lectura = listaLecturas[position]
        // Llama al método del ViewHolder para asignar los datos a los componentes visuales de la pantalla
        holder.bind(lectura)
    }

    // Método que le indica al RecyclerView cuántos elementos hay en la lista en total
    override fun getItemCount(): Int {
        // Retorna el tamaño (cantidad de elementos) de la lista de lecturas
        return listaLecturas.size
    }

    // Método personalizado para actualizar la lista completa de datos desde la Activity
    fun actualizarLista(nuevaLista: List<Lectura>) {
        // Asigna la nueva lista reemplazando la anterior
        listaLecturas = nuevaLista
        // Notifica al RecyclerView que los datos han cambiado para que vuelva a dibujar la lista en pantalla
        notifyDataSetChanged()
    }

    // Clase interna que representa cada elemento visual dentro del RecyclerView (el contenedor o ViewHolder)
    class LecturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Encuentra y guarda una referencia al TextView de la descripción buscándolo por su ID
        private val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        // Encuentra y guarda una referencia al TextView del valor buscándolo por su ID
        private val txtValor: TextView = itemView.findViewById(R.id.txtValor)

        // Método personalizado para asignar los datos de un objeto Lectura a los TextViews
        fun bind(lectura: Lectura) {
            // Asigna el texto de la descripción al TextView correspondiente
            txtDescripcion.text = lectura.descripcion
            // Convierte el valor Double a String y lo asigna al TextView correspondiente agregándole el texto "Valor: "
            txtValor.text = "Valor: ${lectura.valor}"
        }
    }
}
