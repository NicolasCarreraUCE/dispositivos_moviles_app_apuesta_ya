package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.databinding.ActivityPartidosBasquetBinding
import com.example.apuestaya.model.adapters.BasketballAdapter
import com.example.apuestaya.model.entities.api.basketball.Response
import com.example.apuestaya.user.cases.auth.SportAuthUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosBasquetActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPartidosBasquetBinding
    private lateinit var recyclerView: RecyclerView
    var user:String=""
    private var idLiga : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartidosBasquetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        val saludo = intent.extras?.getString(
            "usuario",
        ).toString()

        user=saludo

        idLiga = intent.extras?.getInt("ID_LIGA")!!.toInt()

        lifecycleScope.launch(Dispatchers.Main){
            initRecyclerView()
        }
    }

    private suspend fun initRecyclerView() {
        recyclerView = binding.listaPartidosBasquet
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaDePartidos: List<Response>? = SportAuthUC().getJuegosBasketball(idLiga, "2022-2023")?.response

        val partidosDisponibles = mutableListOf<Response>()

        if (listaDePartidos != null) {
            for(response in listaDePartidos) {
                if (response.scores.home.total == 0) {
                    partidosDisponibles.add(response)
                }
            }
        }

        recyclerView.adapter = BasketballAdapter(user, partidosDisponibles!!.toList(), this)
    }

    private fun init(){
        binding.regresar.setOnClickListener {
            val intent = Intent(this,BasquetActivity::class.java)
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