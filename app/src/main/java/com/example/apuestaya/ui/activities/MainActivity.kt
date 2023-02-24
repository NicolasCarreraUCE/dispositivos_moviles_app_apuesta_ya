package com.example.apuestaya.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.apuestaya.user.cases.auth.SportAuthUC
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.apuestaya.databinding.ActivityMainBinding
import com.example.apuestaya.model.entities.api.user.UsuariosBase
import com.example.apuestaya.user.cases.auth.AuthUC
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataBase: DatabaseReference

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

    // https://gorest.co.in/public/v2/users
    private fun init() {

        binding.botonIngresar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    dataBase= FirebaseDatabase.getInstance().getReference("Users")
                    val call = AuthUC().getUsuarioAuntentificacion(binding.usuarioId.text.toString().toInt())
                    //binding.etiqueta.text = call?.name.toString()
                    val nombreUsuario=call?.name.toString()
                    val emailUsuario=call?.email.toString()
                    val puntosUsuario=100.00f
                    val idUsuario=call?.id
                    //val id= dataBase.push().key;
                    val userNuevo= UsuariosBase(idUsuario!!,nombreUsuario,emailUsuario,puntosUsuario)


                    val activo = call?.status.toString().equals("active")
                    if (activo){
                        dataBase.child(idUsuario.toString()).setValue(userNuevo)
                        val intent = Intent(this@MainActivity,DeportesActivity::class.java)
                        Toast.makeText(this@MainActivity, "Usuario Registrado", Toast.LENGTH_LONG).show()
                        intent.putExtra("usuario",idUsuario.toString())
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@MainActivity, "Usuario inactivo", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    throw Exception(e.message)
                }
            }
        }

        binding.boton2Ingresar.setOnClickListener {
            val idUser=binding.usuario2Id.text.toString()
            if(idUser.isNotEmpty()){
                dataBase= FirebaseDatabase.getInstance().getReference("Users")
                dataBase.child(idUser).get().addOnSuccessListener {
                    if(it.exists()){
                        val idPasar=it.child("id").value
                        Toast.makeText(this@MainActivity, "Bienvenido", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@MainActivity,DeportesActivity::class.java)
                        intent.putExtra("usuario",idPasar.toString())
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this@MainActivity, "Usuario no encontrado", Toast.LENGTH_LONG).show()
                    }
                }
            } else{
                Toast.makeText(this@MainActivity, "Usuario no encontrado", Toast.LENGTH_LONG).show()
            }
        }




    }
}