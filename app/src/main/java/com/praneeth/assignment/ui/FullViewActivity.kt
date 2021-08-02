package com.praneeth.assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.praneeth.assignment.R
import kotlinx.android.synthetic.main.activity_full_view.*

class FullViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_view)

        var uri = intent.getStringExtra("uri")
        ivFullImage.load(uri)
    }
}