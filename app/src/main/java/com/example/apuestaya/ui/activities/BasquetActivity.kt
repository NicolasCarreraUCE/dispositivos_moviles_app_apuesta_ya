package com.example.apuestaya.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apuestaya.R
import com.example.apuestaya.databinding.ActivityBasquetBinding



class BasquetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasquetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasquetBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}