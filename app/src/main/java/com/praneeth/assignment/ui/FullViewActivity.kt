package com.praneeth.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.praneeth.assignment.databinding.ActivityFullViewBinding

class FullViewActivity : AppCompatActivity() {


    lateinit var binding: ActivityFullViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var uri = intent.getStringExtra("uri")
        binding.ivFullImage.load(uri)
    }
}