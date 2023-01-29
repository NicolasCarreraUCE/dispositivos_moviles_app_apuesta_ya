package com.example.apuestaya.ui.activities

import android.annotation.SuppressLint
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

        binding.boton.setOnClickListener {
            init()
        }
    }

    private fun init() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val call = AuthUC().getUsuarioAuntentificacion(binding.usuarioId.text.toString().toInt())
                //binding.etiqueta.text = call?.name.toString()
                val activo = call?.status.toString().equals("active")
                if (activo){
                    Toast.makeText(this@MainActivity, "Usuario activo", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "Usuario inactivo", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                throw Exception(e.message)
            }
        }
    }
}