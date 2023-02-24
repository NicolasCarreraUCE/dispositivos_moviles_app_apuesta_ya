package com.example.apuestaya.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.model.entities.api.user.Apuestas
import com.google.firebase.database.*


class DeportesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeportesBinding
    private lateinit var dataBaseApuesta: DatabaseReference
    val listaApuesta= mutableListOf<Apuestas>()
    var user:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeportesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        val saludo = intent.extras?.getString(
            "usuario",
        ).toString()

        user=saludo

        System.out.println(saludo)

        //Codigo de prueba para agrergar una apuesta (Funciona)
/*
        dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Apuestas").child(user)
        val id=dataBaseApuesta.push().key
        val deporte="Basquet"
        val equipo="Angeles Lakers"
        val estadoPartido="Termianda"
        val estadoApuesta="Perdida"
        val puntos=10.5f

        val apuesta= Apuestas(id!!,deporte,equipo,estadoPartido,estadoApuesta,puntos)

        dataBaseApuesta.child(id).setValue(apuesta)
        Toast.makeText(this@DeportesActivity, "Apuesta Registrada", Toast.LENGTH_LONG).show()
*/


        // Codigo para consultar todas las apuestas de un usuario
        /*
        dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Apuestas")


        dataBaseApuesta.child(user).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children){
                    val deporte=i.child("deporte").value.toString()
                    val equipo=i.child("equipo").value.toString()
                    val idApuesta=i.child("id").value.toString()
                    val estadoParitod=i.child("estadoPartido").value.toString()
                    val estadoApuestasss=i.child("estadoApuesta").value.toString()
                    val puntitos=i.child("puntos").value.toString()

                    val apuestad=Apuestas(idApuesta,deporte,equipo,estadoParitod,estadoApuestasss,puntitos.toFloat())
                    listaApuesta.add(apuestad)

                    System.out.println(i)
                }

                for(apu in listaApuesta){
                    System.out.println(apu)
                }
                System.out.println(listaApuesta.size)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

         */

    }
    private fun init(){
        binding.botonFutbol.setOnClickListener {
            val intent = Intent(this,FutbolActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }

        binding.botonBasquet.setOnClickListener {
            val intent = Intent(this,BasquetActivity::class.java)
            intent.putExtra("usuario",user)
            startActivity(intent)
        }

       // binding.botonOtro.setOnClickListener {
       //     val intent = Intent(this,OtroActivity::class.java)

       //    startActivity(intent)
       // }

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