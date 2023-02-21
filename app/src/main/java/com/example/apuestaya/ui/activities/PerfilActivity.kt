package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityPerfilBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPerfilBinding
    private lateinit var dataBase: DatabaseReference
    var user:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()


        val usuarioIngresado = intent.extras?.getString(
            "usuario",
        ).toString()

        user=usuarioIngresado
        dataBase= FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(usuarioIngresado).get().addOnSuccessListener {
            if(it.exists()){
                val idPasar=it.child("id").value
                val nombre=it.child("nombre").value
                val correo=it.child("correo").value
                val puntos=it.child("puntos").value
                binding.emailUsuario.text=correo.toString()
                binding.nombreUsuario.text=nombre.toString()
                binding.puntosUsuario.text=puntos.toString()

            }
            else{
                Toast.makeText(this@PerfilActivity, "Usuario no encontrado", Toast.LENGTH_LONG).show()
            }
        }

        System.out.println(usuarioIngresado)
    }

    private fun init(){

        binding.irApuestas.setOnClickListener {
            val intent = Intent(this,RegistroApuestasActivity::class.java)
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