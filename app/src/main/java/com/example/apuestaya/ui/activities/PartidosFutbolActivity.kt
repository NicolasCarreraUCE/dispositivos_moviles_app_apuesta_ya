package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityPartidosFutbolBinding

class PartidosFutbolActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPartidosFutbolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartidosFutbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
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