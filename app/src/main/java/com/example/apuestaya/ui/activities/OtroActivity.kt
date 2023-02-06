package com.example.apuestaya.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apuestaya.R

import com.example.apuestaya.databinding.ActivityOtroBinding

class OtroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}