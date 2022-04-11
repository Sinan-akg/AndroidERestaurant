package com.example.sinanerestaurant

import android.content.Context
import android.content.Intent
import android.graphics.Color.RED
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sinanerestaurant.Model.Item
import com.google.android.material.snackbar.Snackbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.sinanerestaurant.Model.Shop
import com.example.sinanerestaurant.Model.ShopItems
import com.example.sinanerestaurant.ble.BLEActivity
import com.example.sinanerestaurant.databinding.ActivityDetailBinding
import com.google.gson.Gson
import java.io.File


class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    lateinit var button: Button
    lateinit var resetbutton: Button
    lateinit var imageplus: ImageView
    lateinit var imagemoins: ImageView
    lateinit var textnombre: TextView
    var num: Float = 1F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(EntreeActivity.ITEM_KEY) as Item
        binding.textedutitre.text = item.name_fr
        binding.description.text = item.ingredients.joinToString(", ") { it.name_fr }

        val carouselAdapter = CarouselAdapter(this, item.images)
        binding.detailSlider.adapter = carouselAdapter

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
            updateFile(ShopItems(item, num))

            //updateSharedPreferences(num, (item.prices[0].price.toFloat() * num))

        }

        //affichage incrementation et decrementation de la quantité souhaitée

        var tv = findViewById<TextView>(R.id.textnombre)

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

        }

        imagemoins = findViewById(R.id.imagemoins)
        imagemoins.setOnClickListener {

            if(num<2F)
            {
                num = 2F
            }

            num--
            tv.setText("$num")
            textnombre.text = num.toString()
            totalComplete(item, num)

        }

        resetbutton = findViewById(R.id.resetbutton)
        resetbutton.setOnClickListener {

            Toast.makeText(this@DetailActivity, "Suppression des éléments", Toast.LENGTH_SHORT)
                .show()
            num = 1F
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

    private fun updateFile(itemShop: ShopItems) {
        val file = File(cacheDir.absolutePath + "/shop.json")
        var itemsShop: List<ShopItems> = ArrayList()

        if (file.exists()) {
            itemsShop = Gson().fromJson(file.readText(), Shop::class.java).data
        }

        var double = false
        for (i in itemsShop.indices) {
            if (itemsShop[i].item == itemShop.item) {
                itemsShop[i].quantity += itemShop.quantity
                double = true
            }
        }

        if (!double) {
            itemsShop = itemsShop + itemsShop
        }

        file.writeText(Gson().toJson(Shop(itemsShop)))
    }

    private fun updateSharedPreferences(quantity: Float, price: Float) {
        val sharedPreferences = this.getSharedPreferences(getString(R.string.file_name), Context.MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.total_quantity), 0)
        val newQuantity = oldQuantity + quantity
        sharedPreferences.edit().putFloat(getString(R.string.total_quantity),
            newQuantity.toInt().toFloat()
        ).apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.total_price), 0.0f)
        val newPrice = oldPrice + price
        sharedPreferences.edit().putFloat(getString(R.string.total_price), newPrice).apply()
    }

}





