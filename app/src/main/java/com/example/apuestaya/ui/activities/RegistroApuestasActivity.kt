package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apuestaya.databinding.ActivityRegistroApuestasBinding
import com.example.apuestaya.model.adapters.ApuestasAdapter
import com.example.apuestaya.model.entities.api.user.Apuestas
import com.google.firebase.database.*
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers

class RegistroApuestasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroApuestasBinding
    var user:String=""

    private lateinit var dataBaseApuesta: DatabaseReference
    private lateinit var recycler:RecyclerView
    var listaApuesta= mutableListOf<Apuestas>()
    private var  listaApuestas2:MutableList<Apuestas> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroApuestasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Metodos Adicionales


            init()
            val saludo = intent.extras?.getString(
                "usuario",
            ).toString()

            user=saludo

            System.out.println(saludo)


                consultaApuestas()

        Handler(Looper.getMainLooper()).postDelayed({
            recyclerVista()
        },1000)



    }

    override fun onResume() {
        super.onResume()

    }


    private fun consultaApuestas(){
        //Base de Datos

        lifecycleScope.launch(Dispatchers.IO){
            dataBaseApuesta= FirebaseDatabase.getInstance().getReference("Apuestas")


            dataBaseApuesta.child(user).addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        val deporte=i.child("deporte").value.toString()
                        val equipo=i.child("equipo").value.toString()
                        val idApuesta=i.child("id").value.toString()
                        val estadoParitod=i.child("estadoPartido").value.toString()
                        val estadoApuestasss=i.child("estadoApuesta").value.toString()
                        val puntitos=i.child("puntos").value.toString()

                        val apuestad= Apuestas(idApuesta,deporte,equipo,estadoParitod,estadoApuestasss,puntitos.toFloat())
                        listaApuesta.add(apuestad)

                        System.out.println(i)
                    }

                    for(apu in listaApuesta){

                        System.out.println("pruebass")
                        var apu1: Apuestas = Apuestas(apu.id,apu.deporte,apu.equipo,apu.estadoPartido,apu.estadoApuesta,apu.puntos)

                        System.out.println(apu1)
                        listaApuestas2.add(apu1)
                        System.out.println(listaApuestas2.size)
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

    }

    private fun recyclerVista(){


        System.out.println("Sera que entra lista 1")
        System.out.println(listaApuestas2.size)
        System.out.println("Sera que entra lista 2")
        System.out.println(listaApuesta.size)

        if(listaApuestas2.isNotEmpty()){
            recycler=binding.recyclerListaApuestas
            recycler.layoutManager=LinearLayoutManager(this)
            recycler.adapter=ApuestasAdapter(this,listaApuestas2)
        }
        else{
            Toast.makeText(this@RegistroApuestasActivity, "Sin apuestas", Toast.LENGTH_LONG).show()
        }




    }


    private fun init(){
        binding.regresarPerfil.setOnClickListener {
            val intent = Intent(this,PerfilActivity::class.java)
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