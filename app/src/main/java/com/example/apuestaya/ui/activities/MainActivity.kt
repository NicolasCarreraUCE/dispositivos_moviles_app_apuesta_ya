package com.example.apuestaya.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.apuestaya.databinding.ActivityMainBinding
import com.example.apuestaya.user.cases.auth.AuthUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }
    override fun onPause() {
        super.onPause()
        println("PAUSANDO APP")

    }

    override fun onResume() {
        super.onResume()
        println("REANUDANDO APP")
    }

    private fun init() {

        binding.botonIngresar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    val call = AuthUC().getUsuarioAuntentificacion(binding.usuarioId.text.toString().toInt())
                    //binding.etiqueta.text = call?.name.toString()
                    val activo = call?.status.toString().equals("active")
                    if (activo){
                        val intent = Intent(this@MainActivity,DeportesActivity::class.java)

                        startActivity(intent)

                    } else {
                        Toast.makeText(this@MainActivity, "Usuario inactivo", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    throw Exception(e.message)
                }
            }
        }

    }
}