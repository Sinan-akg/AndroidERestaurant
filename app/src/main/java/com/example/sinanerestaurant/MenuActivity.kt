package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.sinanerestaurant.ble.BLEActivity
import com.example.sinanerestaurant.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MenuActivity", "Création d'une nouvelle page")

        val actionBar = supportActionBar
        actionBar!!.title = "Menus" // titre accueil

        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //setContentView(R.layout.activity_menu)

        fun goToCategory(category1: String) {
            val myIntent = Intent(this, EntreeActivity::class.java)
            Toast.makeText(
                this@MenuActivity,
                category1,
                Toast.LENGTH_SHORT
            ).show()
            myIntent.putExtra("category1", category1)
            startActivity(myIntent)
        }

        fun goToHome() {
            val myIntent1 = Intent(this, MainActivity::class.java)
            Toast.makeText(
                this@MenuActivity,
                "Redirection vers la page d'accueil",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(myIntent1)
        }

        binding.Chef.setOnClickListener {
            goToHome()
        }

        binding.Entree.setOnClickListener {
            goToCategory(getString(R.string.section_Entrees))
        }

        binding.plat.setOnClickListener {
            goToCategory(getString(R.string.section_Plats))
        }

        binding.dessert.setOnClickListener {

            goToCategory(getString(R.string.section_Desserts))
        }

    }

    fun goToBluetooth() {
        val myIntent2 = Intent(this, BLEActivity::class.java)
        startActivity(myIntent2)
    }
    fun goToShopping() {
        val myIntent3 = Intent(this, ShopActivity::class.java)
        startActivity(myIntent3)
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("home", "Mon activité est détruite")
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topicon, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth1 -> {
            Toast.makeText(this@MenuActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
            goToBluetooth()
            true
        }

        R.id.shopbag -> {
            Toast.makeText(this@MenuActivity, "Panier", Toast.LENGTH_SHORT).show()
            goToShopping()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }

    }
}


