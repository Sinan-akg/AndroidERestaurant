package com.example.sinanerestaurant


import android.content.Intent
import android.graphics.Color.RED
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sinanerestaurant.Model.Item
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import com.example.sinanerestaurant.ble.BLEActivity
import com.example.sinanerestaurant.databinding.ActivityDetailBinding
import java.lang.Integer.parseInt


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    lateinit var button: Button
    lateinit var resetbutton: Button
    lateinit var imageplus: ImageView
    lateinit var imagemoins: ImageView
    lateinit var textnombre: TextView
    var num: Float = 0F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val item = intent.getSerializableExtra(EntreeActivity.ITEM_KEY) as Item
        binding.textedutitre.text = item.name_fr
        binding.description.text = item.ingredients.joinToString(", ") { it.name_fr }

        val carouselAdapter = CarouselAdapter(this, item.images)
        binding.detailSlider.adapter = carouselAdapter

        //binding.buttontotal.text = item.prices.joinToString { "Total : " + it.price.toString() + "€" }

        button = findViewById(R.id.buttontotal)
        button.setOnClickListener {

            //snackbar and close button

            val snack: Snackbar =
                Snackbar.make(binding.root, "Ajouté au panier", Snackbar.LENGTH_SHORT)
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            view.layoutParams = params

            snack.setAction("Close", View.OnClickListener {
                    snack.setActionTextColor(RED)
                    snack.dismiss()
                })

                snack.show()
            }

        //affichage incrementation et decrementation de la quantité souhaitée

        var tv = findViewById<TextView>(R.id.textnombre)
        //  var nombre = {it.price}


        textnombre = findViewById(R.id.textnombre)
        textnombre.text = num.toString()
        totalComplete(item, num)

// set on-click listener for ImageView

        imageplus = findViewById(R.id.imageplus)
        imageplus.setOnClickListener {

            num++
            tv.setText("$num")
            textnombre.text = num.toString()
            totalComplete(item, num)
         //  binding.buttontotal.text = (item.prices[0].price.toFloat()*num).toString() + "€"
          //  binding.buttontotal.text = item.prices.joinToString { parseInt({it.price} + {it.price}).toString() +  "€"}

        }

        imagemoins = findViewById(R.id.imagemoins)
        imagemoins.setOnClickListener {

            if(num<1F)
            {
                num = 0F
            }


            num--
            tv.setText("$num")
            textnombre.text = num.toString()
            totalComplete(item, num)

          //  binding.buttontotal.text = (item.prices[0].price.toFloat()*num).toString() + "€"
        }

        resetbutton = findViewById(R.id.resetbutton)
        resetbutton.setOnClickListener {

            Toast.makeText(this@DetailActivity, "Suppression des éléments", Toast.LENGTH_SHORT)
                .show()
            num = 0F
            tv.setText("$num")
            textnombre.text = num.toString()
            totalComplete(item, num)
        }

    }

   private fun goToBluetooth() {
        val myIntent2 = Intent(this, BLEActivity::class.java)
        startActivity(myIntent2)
    }

    private fun goToShopping() {
        val myIntent3 = Intent(this, ShopActivity::class.java)
        startActivity(myIntent3)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topicon, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth1 -> {
            Toast.makeText(this@DetailActivity, "Bluetooth", Toast.LENGTH_SHORT).show()
            goToBluetooth()
            true
        }

        R.id.shopbag -> {
            Toast.makeText(this@DetailActivity, "Panier", Toast.LENGTH_SHORT).show()
            goToShopping()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    private fun totalComplete(item: Item, selected: Float) {
        val totalPrice: String = item.prices[0].price
        val total1: Float = totalPrice.toFloat() * selected
        val totalString: String = "Total : " + total1.toString() + "€"
       binding.buttontotal.text = totalString

    }
}

