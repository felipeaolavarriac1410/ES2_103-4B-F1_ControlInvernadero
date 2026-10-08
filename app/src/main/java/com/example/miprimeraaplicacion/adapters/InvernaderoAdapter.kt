package com.example.miprimeraaplicacion.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miprimeraaplicacion.R
import com.example.miprimeraaplicacion.models.Invernadero

class InvernaderoAdapter(
    private val lista: List<Invernadero>,
    private val onEditClick: (Invernadero) -> Unit,
    private val onDeleteClick: (Invernadero) -> Unit
) : RecyclerView.Adapter<InvernaderoAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSector: TextView = itemView.findViewById(R.id.tvSectorFila)
        val tvHumedad: TextView = itemView.findViewById(R.id.tvHumedadFila)
        val tvTemperatura: TextView = itemView.findViewById(R.id.tvTemperaturaFila)
        val tvEstadoVentilador: TextView = itemView.findViewById(R.id.tvEstadoVentiladorFila)
        val btnEditar: ImageButton = itemView.findViewById(R.id.btnEditarFila)
        val btnEliminar: ImageButton = itemView.findViewById(R.id.btnEliminarFila)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_invernadero, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        // Formato de etiquetas exigido por el negocio en el punto 8 de la pauta
        holder.tvSector.text = "Sector: ${item.sector}"
        holder.tvHumedad.text = "Humedad: ${item.humedad}%"
        holder.tvTemperatura.text = "Temp: ${item.temperatura}°C"
        holder.tvEstadoVentilador.text = "Ventilador: ${item.estadoVentilador}"

        holder.btnEditar.setOnClickListener { onEditClick(item) }
        holder.btnEliminar.setOnClickListener { onDeleteClick(item) }
    }

    override fun getItemCount(): Int = lista.size
}