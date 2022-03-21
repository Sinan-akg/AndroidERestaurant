package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title="Accueil" // titre accueil
        val secondActivityBtn = findViewById<View>(R.id.button)

        secondActivityBtn.setOnClickListener() {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}