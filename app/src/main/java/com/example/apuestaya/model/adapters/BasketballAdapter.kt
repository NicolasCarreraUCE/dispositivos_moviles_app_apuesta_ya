package com.example.apuestaya.model.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.R
import com.example.apuestaya.model.entities.api.basketball.Response
import com.example.apuestaya.ui.activities.ApuestaActivity
import com.squareup.picasso.Picasso

class BasketballAdapter(val user: String, val partidosList: List<Response>, val context: Context) : RecyclerView.Adapter<BasketballAdapter.BasketballViewHolder>() {

    inner class BasketballViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var imagenPrimerEquipo: ImageView
        lateinit var imagenSegundoEquipo: ImageView
        lateinit var nombrePrimerEquipo: TextView
        lateinit var nombreSegundoEquipo: TextView
        lateinit var marcadorPrimerEquipo: TextView
        lateinit var marcadorSegundoEquipo: TextView
        lateinit var btnApostar: Button

        init {
            imagenPrimerEquipo = itemView.findViewById(R.id.imageViewPrimerEquipo)
            imagenSegundoEquipo = itemView.findViewById(R.id.imageViewSegundoEquipo)
            nombrePrimerEquipo = itemView.findViewById(R.id.nombrePrimerEquipo)
            nombreSegundoEquipo = itemView.findViewById(R.id.nombreSegundoEquipo)
            marcadorPrimerEquipo = itemView.findViewById(R.id.marcadorPrimerEquipo)
            marcadorSegundoEquipo = itemView.findViewById(R.id.marcadorSegundoEquipo)
            btnApostar = itemView.findViewById(R.id.apostar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketballViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return BasketballViewHolder(layoutInflater.inflate(R.layout.activity_lista_partidos, parent, false))
    }

    override fun onBindViewHolder(holder: BasketballAdapter.BasketballViewHolder, position: Int) {
        Picasso.get().load(partidosList[position].teams.home.logo).into(holder.imagenPrimerEquipo)
        Picasso.get().load(partidosList[position].teams.away.logo).into(holder.imagenSegundoEquipo)
        holder.nombrePrimerEquipo.text = partidosList[position].teams.home.name
        holder.nombreSegundoEquipo.text = partidosList[position].teams.away.name
        holder.marcadorPrimerEquipo.text = partidosList[position].scores.home.total.toString()
        holder.marcadorSegundoEquipo.text = partidosList[position].scores.away.total.toString()
        holder.btnApostar.setOnClickListener {
            val intent = Intent(context, ApuestaActivity::class.java)

            intent.putExtra("usuario",user)
            intent.putExtra("DEPORTE","Basketball")

            intent.putExtra("HOME_LOGO", partidosList[position].teams.home.logo)
            intent.putExtra("AWAY_LOGO", partidosList[position].teams.away.logo)
            intent.putExtra("HOME_NAME", partidosList[position].teams.home.name)
            intent.putExtra("AWAY_NAME", partidosList[position].teams.away.name)
            intent.putExtra("GOALS_HOME", partidosList[position].scores.home.total)
            intent.putExtra("GOALS_AWAY", partidosList[position].scores.away.total)
            intent.putExtra("HORA", partidosList[position].time)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = partidosList.size
}