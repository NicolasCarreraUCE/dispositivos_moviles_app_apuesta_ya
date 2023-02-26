package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityPartidosFutbolBinding
import com.example.apuestaya.model.adapters.PartidosAdapter
import com.example.apuestaya.model.entities.api.enfrentamientos.Response

import com.example.apuestaya.user.cases.auth.SportAuthUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosFutbolActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPartidosFutbolBinding
    private lateinit var recyclerView: RecyclerView
    var user : String=""
    private var idLiga : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartidosFutbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        val saludo = intent.extras?.getString(
            "usuario",
        ).toString()

        user = saludo

        idLiga = intent.extras?.getInt("ID_LIGA")!!.toInt()

        lifecycleScope.launch(Dispatchers.Main){
            initRecyclerView()
        }
    }

    private suspend fun initRecyclerView() {
        recyclerView = binding.listaPartidosFutbol
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaDePartidos: List<Response>? = SportAuthUC().getEnfrentamientos(idLiga, 2022, "NS")?.response

        recyclerView.adapter = PartidosAdapter(user, listaDePartidos!!, this)
    }

    private fun init(){
        binding.regresar.setOnClickListener {
            val intent = Intent(this,FutbolActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }
        binding.botonPartidos.setOnClickListener {
            val intent = Intent(this,DeportesActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }

        binding.botonPerfil.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }

        binding.botonSalir.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}