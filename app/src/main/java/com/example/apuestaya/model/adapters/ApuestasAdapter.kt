package com.example.apuestaya.model.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.R
import com.example.apuestaya.model.entities.api.user.Apuestas

class ApuestasAdapter(var context:Context, var dataList:MutableList<Apuestas>) : RecyclerView.Adapter<ApuestasAdapter.ViewHolder>() {



    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {


        lateinit var labelId:TextView
        lateinit var labelDeporte:TextView
        lateinit var labelEquipo:TextView
        lateinit var labelEstadoPartido:TextView
        lateinit var labelEstadoApuesta:TextView
        lateinit var labePuntos:TextView

        init {
            //labelId=view.findViewById(R.id.idId)
            labelDeporte=view.findViewById(R.id.idDeporte)
            labelEquipo=view.findViewById(R.id.idEquipo)
            labelEstadoPartido=view.findViewById(R.id.idEstadoPartido)
            labelEstadoApuesta=view.findViewById(R.id.idEstadoApuesta)
            labePuntos=view.findViewById(R.id.idPuntos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Inflar la vista
        val inflater = LayoutInflater.from(context)
        return ViewHolder(inflater.inflate(R.layout.activity_resultado_lista, parent, false))
    }

    override fun getItemCount(): Int=dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       var apuesta=dataList[position]
        //holder.labelId.text=apuesta.id
        holder.labelDeporte.text=apuesta.deporte
        holder.labelEquipo.text=apuesta.equipo
        holder.labelEstadoApuesta.text=apuesta.estadoApuesta
        holder.labelEstadoPartido.text=apuesta.estadoPartido
        holder.labePuntos.text=apuesta.puntos.toString()
    }
}