package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityApuestaBinding
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.model.entities.api.user.Apuestas
import com.example.apuestaya.model.entities.api.user.UsuariosBase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.random.Random

class ApuestaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApuestaBinding
    private lateinit var dataBaseApuesta: DatabaseReference
    private lateinit var dataBaseUsuario: DatabaseReference
    var user: String = ""

    private lateinit var usuarioDB : UsuariosBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApuestaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        val saludo = intent.extras?.getString(
            "usuario",
        ).toString()

        user = saludo

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN

        val numeroAleatorio = Random.nextDouble()

        Picasso.get().load(intent.extras?.getString("HOME_LOGO").toString()).into(binding.equipo1)
        Picasso.get().load(intent.extras?.getString("AWAY_LOGO").toString()).into(binding.equipo2)
        binding.nombreEquipoUno.text = intent.extras?.getString("HOME_NAME").toString()
        binding.nombreEquipoDos.text = intent.extras?.getString("AWAY_NAME").toString()
        binding.probabilidadEquipoUno.text = df.format((1.0 / numeroAleatorio)).toString()
        binding.probabilidadEquipoDos.text = df.format((1.0 / (1.0 - numeroAleatorio))).toString()
        binding.hora.text = intent.extras?.getString("HORA").toString()
    }

    private fun init() {

        usuarioDB = UsuariosBase(0,"","",0.0f)

        lifecycleScope.launch(Dispatchers.Main){
            dataBaseUsuario = FirebaseDatabase.getInstance().getReference("Users")
            dataBaseUsuario.child(user).get().addOnSuccessListener {
                if (it.exists()) {
                    usuarioDB.id = it.child("id").value.toString().toInt()
                    usuarioDB.nombre = it.child("nombre").value.toString()
                    usuarioDB.correo = it.child("correo").value.toString()
                    usuarioDB.puntos = it.child("puntos").value.toString().toFloat()
                    binding.credito.text = "Credito: ${usuarioDB.puntos}"
                }
            }
        }

        binding.apostarEquipoUno.setOnClickListener {
            intent.putExtra("usuario", user)
            //Codigo de prueba para agrergar una apuesta (Funciona)
            dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Apuestas").child(user)
            val id = dataBaseApuesta.push().key
            val deporte = intent.extras?.getString("DEPORTE").toString()
            val equipo = intent.extras?.getString("HOME_NAME").toString()
            val estadoPartido = "Pendiente"
            val estadoApuesta = "Pendiente"
            val puntos = binding.obtenerCantidad.text.toString().toFloat()

            if(puntos < usuarioDB.puntos) {
                val apuesta= Apuestas(id!!,deporte,equipo,estadoPartido,estadoApuesta,puntos)

                dataBaseApuesta.child(id).setValue(apuesta)

                Toast.makeText(this@ApuestaActivity, "Apuesta Registrada", Toast.LENGTH_LONG).show()

                dataBaseUsuario = FirebaseDatabase.getInstance().getReference("Users")

                val valorapuesta = usuarioDB.puntos - puntos

                val usuario= mapOf<String,String>(
                    "id" to usuarioDB.id.toString(),
                    "nombre" to usuarioDB.nombre,
                    "correo" to usuarioDB.correo,
                    "puntos" to valorapuesta.toString()

                )
                dataBaseUsuario.child(user).updateChildren(usuario)

                Toast.makeText(this@ApuestaActivity, "Usuario actualizado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@ApuestaActivity, "No tines los puntos suficientes para hacer la apuesta", Toast.LENGTH_LONG).show()
            }

        }

        binding.apostarEquipoDos.setOnClickListener {
            intent.putExtra("usuario", user)
            //Codigo de prueba para agrergar una apuesta (Funciona)
            dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Apuestas").child(user)
            val id = dataBaseApuesta.push().key
            val deporte = intent.extras?.getString("DEPORTE").toString()
            val equipo = intent.extras?.getString("AWAY_NAME").toString()
            val estadoPartido = "Pendiente"
            val estadoApuesta = "Pendiente"
            val puntos = binding.obtenerCantidad.text.toString().toFloat()

            if(puntos < usuarioDB.puntos) {
                val apuesta= Apuestas(id!!,deporte,equipo,estadoPartido,estadoApuesta,puntos)

                dataBaseApuesta.child(id).setValue(apuesta)
                Toast.makeText(this@ApuestaActivity, "Apuesta Registrada", Toast.LENGTH_LONG).show()

                val valorapuesta = usuarioDB.puntos - puntos

                val usuario= mapOf<String,String>(
                    "id" to usuarioDB.id.toString(),
                    "nombre" to usuarioDB.nombre,
                    "correo" to usuarioDB.correo,
                    "puntos" to valorapuesta.toString()

                )
                dataBaseApuesta.child(user).updateChildren(usuario)

                Toast.makeText(this@ApuestaActivity, "Usuario actualizado", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@ApuestaActivity, "No tines los puntos suficientes para hacer la apuesta", Toast.LENGTH_LONG).show()
            }
        }

        binding.botonPartidos.setOnClickListener {
            val intent = Intent(this, DeportesActivity::class.java)
            intent.putExtra("usuario", user)
            startActivity(intent)
        }

        binding.botonPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            intent.putExtra("usuario", user)
            startActivity(intent)
        }

        binding.botonSalir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun update(){
        dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Users")
        dataBaseApuesta.child(user).get().addOnSuccessListener {
            if (it.exists()) {
                val idPasar = it.child("id").value
                val nombre = it.child("nombre").value
                val correo = it.child("correo").value
                val puntos = it.child("puntos").value

                val valorapuesta=puntos.toString()
                //En vez del 50f poner el valor de puntos apostados.
                val valorapuestaFloat=valorapuesta.toFloat()-50f

                val usuario= mapOf<String,String>(
                    "id" to idPasar.toString(),
                    "nombre" to nombre.toString(),
                    "correo" to correo.toString(),
                    "puntos" to valorapuestaFloat.toString()

                )
                dataBaseApuesta.child(user).updateChildren(usuario)

                Toast.makeText(this@ApuestaActivity, "Usuario actualizado", Toast.LENGTH_LONG).show()
            }
        }
    }
}
