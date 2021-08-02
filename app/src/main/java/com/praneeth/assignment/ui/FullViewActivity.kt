package com.praneeth.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.load
import com.praneeth.assignment.R
import kotlinx.android.synthetic.main.activity_full_view.*

class FullViewActivity : AppCompatActivity() {


    lateinit var ivFullImage :ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_view)

        ivFullImage = findViewById(R.id.ivFullImage)
        var uri = intent.getStringExtra("uri")
        ivFullImage.load(uri)
    }
}