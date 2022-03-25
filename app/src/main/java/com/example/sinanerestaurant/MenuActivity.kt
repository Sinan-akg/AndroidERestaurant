package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.sinanerestaurant.databinding.ActivityMenuBinding
import java.util.*

// val actionBar = supportActionBar
//actionBar!!.title="Entrées"

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MenuActivity", "Création d'une nouvelle page")


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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("home", "Mon activité est détruite")
    }
}
        /*binding.Entree.setOnClickListener {

        val myIntent = Intent(this, EntreeActivity::class.java)



        Toast.makeText(
           this@MenuActivity,
           "Redirection vers la page des entrées",
            Toast.LENGTH_SHORT
        ).show()
        startActivity(myIntent)

            binding.Entree.movementMethod = LinkMovementMethod.getInstance();
        }



        binding.plat.setOnClickListener {

            val myIntent2 = Intent(this, EntreeActivity::class.java)


            Toast.makeText(
                this@MenuActivity,
                "Redirection vers la page des plats",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(myIntent2)

            binding.plat.movementMethod = LinkMovementMethod.getInstance();

        }


        binding.dessert.setOnClickListener {

            val myIntent3 = Intent(this, EntreeActivity::class.java)
            Toast.makeText(
                this@MenuActivity,
                "Redirection vers la page des desserts",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(myIntent3)

            binding.dessert.movementMethod = LinkMovementMethod.getInstance();

        }
*/


