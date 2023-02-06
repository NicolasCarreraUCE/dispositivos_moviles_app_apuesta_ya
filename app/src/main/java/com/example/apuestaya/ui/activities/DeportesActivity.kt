package com.example.apuestaya.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityMainBinding


class DeportesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeportesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDeportesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var boton = findViewById<Button>(R.id.botonFutbol)
        boton.setOnClickListener{
            val int = Intent(this,FutbolActivity::class.java)
            startActivity(int)
        }
    }
}