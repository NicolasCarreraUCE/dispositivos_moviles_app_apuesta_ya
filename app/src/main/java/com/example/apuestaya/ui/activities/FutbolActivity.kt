package com.example.apuestaya.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityDeportesBinding
import com.example.apuestaya.databinding.ActivityFutbolBinding

class FutbolActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFutbolBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFutbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}