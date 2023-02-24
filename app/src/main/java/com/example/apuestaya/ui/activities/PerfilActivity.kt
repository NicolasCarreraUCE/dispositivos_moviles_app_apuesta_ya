package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.apuestaya.databinding.ActivityPerfilBinding
import com.example.apuestaya.model.entities.api.user.Apuestas
import com.google.firebase.database.*

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding:ActivityPerfilBinding
    private lateinit var dataBase: DatabaseReference
    var user:String=""

    var listaApuesta= mutableListOf<Apuestas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        // Obtener usuario
        val usuarioIngresado = intent.extras?.getString(
            "usuario",
        ).toString()
        user=usuarioIngresado

        obtenerUsuario()


    }

    private fun obtenerUsuario(){
        dataBase= FirebaseDatabase.getInstance().getReference("Users")
        dataBase.child(user).get().addOnSuccessListener {
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

        System.out.println(user)
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