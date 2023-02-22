package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.databinding.ActivityPartidosFutbolBinding
import com.example.apuestaya.model.entities.api.enfrentamientos.Response
import com.example.apuestaya.ui.adapters.PartidosAdapter
import com.example.apuestaya.user.cases.auth.SportAuthUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosFutbolActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPartidosFutbolBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartidosFutbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        lifecycleScope.launch(Dispatchers.Main){
            initRecyclerView()
        }
    }

    private suspend fun initRecyclerView() {
        recyclerView = binding.listaPartidosFutbol
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaDePartidos: List<Response>? = SportAuthUC().getEnfrentamientos()?.response

        recyclerView.adapter = PartidosAdapter(listaDePartidos!!)
    }

    private fun init(){
        binding.regresar.setOnClickListener {
            val intent = Intent(this,FutbolActivity::class.java)

            startActivity(intent)
        }
        binding.botonPartidos.setOnClickListener {
            val intent = Intent(this,DeportesActivity::class.java)

            startActivity(intent)
        }

        binding.botonPerfil.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)

            startActivity(intent)
        }

        binding.botonSalir.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}