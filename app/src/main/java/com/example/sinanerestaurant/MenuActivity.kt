package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val actionBar = supportActionBar
        actionBar!!.title = "Page Principale" // titre Page Principale

        val textClickable = findViewById<TextView>(R.id.Entree)
        textClickable.setOnClickListener {

            val myIntent = Intent(this, EntreeActivity::class.java)
            startActivity(myIntent)

            textClickable.movementMethod = LinkMovementMethod.getInstance();
        }


        val textClickable2 = findViewById<TextView>(R.id.plat)
        textClickable2.setOnClickListener {

            val myIntent2 = Intent(this, PlatActivity::class.java)
            startActivity(myIntent2)

            textClickable2.movementMethod = LinkMovementMethod.getInstance();

        }

        val textClickable3 = findViewById<TextView>(R.id.dessert)
        textClickable3.setOnClickListener {

            val myIntent3 = Intent(this, DessertActivity::class.java)
            startActivity(myIntent3)

            textClickable3.movementMethod = LinkMovementMethod.getInstance();

        }
    }
}