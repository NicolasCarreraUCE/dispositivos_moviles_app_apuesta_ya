package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityFutbolBinding

class FutbolActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFutbolBinding
    var user:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFutbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        val saludo = intent.extras?.getString(
            "usuario",
        ).toString()

        user=saludo

        System.out.println(saludo)


    }

    private fun init(){
        binding.botonLigaEspaniola.setOnClickListener {
            val intent = Intent(this,PartidosFutbolActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }
        binding.botonLigaInglesa.setOnClickListener {
            val intent = Intent(this,PartidosFutbolActivity::class.java)
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