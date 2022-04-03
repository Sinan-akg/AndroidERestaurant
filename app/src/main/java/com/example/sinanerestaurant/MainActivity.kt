package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sinanerestaurant.ble.BLEActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Accueil" // titre accueil
        val secondActivityBtn = findViewById<View>(R.id.button)

        secondActivityBtn.setOnClickListener() {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topicon, menu)
        return true
    }

    fun goToBluetooth() {
        val myIntent2 = Intent(this, BLEActivity::class.java)
        startActivity(myIntent2)
    }
    fun goToShopping(){
        val myIntent3 = Intent(this, ShopActivity::class.java )
        startActivity(myIntent3)
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth1 -> {
           Toast.makeText(this@MainActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
            goToBluetooth()
           true
        }

        R.id.shopbag -> {
            Toast.makeText(this@MainActivity, "Panier", Toast.LENGTH_SHORT).show()
            goToShopping()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }

    }
}