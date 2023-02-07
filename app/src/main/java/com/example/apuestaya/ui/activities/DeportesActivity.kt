package com.example.apuestaya.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apuestaya.databinding.ActivityDeportesBinding


class DeportesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeportesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeportesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        binding.botonFutbol.setOnClickListener {
            val intent = Intent(this,FutbolActivity::class.java)

            startActivity(intent)
        }

        binding.botonBasquet.setOnClickListener {
            val intent = Intent(this,BasquetActivity::class.java)

            startActivity(intent)
        }

       // binding.botonOtro.setOnClickListener {
       //     val intent = Intent(this,OtroActivity::class.java)

       //    startActivity(intent)
       // }

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