package com.example.apuestaya.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.R
import com.example.apuestaya.model.entities.api.enfrentamientos.Response
import com.squareup.picasso.Picasso

class PartidosAdapter(val partidosList : List<Response>) : RecyclerView.Adapter<PartidosAdapter.PartidosViewHolder>() {
    inner class PartidosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var imagenPrimerEquipo: ImageView
        lateinit var imagenSegundoEquipo: ImageView
        lateinit var nombrePrimerEquipo: TextView
        lateinit var nombreSegundoEquipo:TextView
        lateinit var marcadorPrimerEquipo:TextView
        lateinit var marcadorSegundoEquipo:TextView

        init {
            imagenPrimerEquipo = itemView.findViewById(R.id.imageViewPrimerEquipo)
            imagenSegundoEquipo = itemView.findViewById(R.id.imageViewSegundoEquipo)
            nombrePrimerEquipo = itemView.findViewById(R.id.nombrePrimerEquipo)
            nombreSegundoEquipo = itemView.findViewById(R.id.nombreSegundoEquipo)
            marcadorPrimerEquipo = itemView.findViewById(R.id.marcadorPrimerEquipo)
            marcadorSegundoEquipo = itemView.findViewById(R.id.marcadorSegundoEquipo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PartidosViewHolder(layoutInflater.inflate(R.layout.activity_lista_partidos, parent, false))
    }

    override fun onBindViewHolder(holder: PartidosViewHolder, position: Int) {
        Picasso.get().load(partidosList[position].teams.home.logo).into(holder.imagenPrimerEquipo)
        Picasso.get().load(partidosList[position].teams.away.logo).into(holder.imagenSegundoEquipo)
        holder.nombrePrimerEquipo.text = partidosList[position].teams.home.name
        holder.nombreSegundoEquipo.text = partidosList[position].teams.away.name
        holder.marcadorPrimerEquipo.text = partidosList[position].goals.home.toString()
        holder.marcadorSegundoEquipo.text = partidosList[position].goals.away.toString()
    }

    override fun getItemCount(): Int = partidosList.size
}