package com.example.sinanerestaurant

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sinanerestaurant.Model.Shop
import com.example.sinanerestaurant.Model.ShopItems
import com.example.sinanerestaurant.databinding.ActivityShopBinding
import com.google.gson.Gson
import java.io.File
import kotlin.math.abs

class ShopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.title = "Panier"

        val file = File(cacheDir.absolutePath + "/shop.json")

        if (file.exists()) {
            val shopItems: List<ShopItems> =
                Gson().fromJson(file.readText(), Shop::class.java).data
            display(shopItems)
        }

        binding.ShopDelete.setOnClickListener {
            deleteShopData()
            finish()
            changeActivity()
        }

        binding.homeButton.setOnClickListener {
            finish()
            changeActivity()
        }

        binding.buttonCommand.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/sinan_akg34/?hl=fr")
                )
            )
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun display(itemsList: List<ShopItems>) {
        binding.ShopList.layoutManager = LinearLayoutManager(this)
        binding.ShopList.adapter = ShopAdapterActivity(itemsList as ArrayList<ShopItems>) {
            deleteItemShop(it)
        }
        binding.ShopList.adapter?.notifyDataSetChanged()
    }

    private fun deleteItemShop(item: ShopItems) {
        val file = File(cacheDir.absolutePath + "/shop.json")
        var itemShop: List<ShopItems> = ArrayList()

        if (file.exists()) {
            itemShop = Gson().fromJson(file.readText(), Shop::class.java).data
            itemShop = itemShop - item
            updateSharedPreferences(item.quantity, item.item.prices[0].price.toFloat())

        }

        file.writeText(Gson().toJson(Shop(itemShop)))

        finish()
        this.recreate()
    }

    private fun deleteShopData() {
        File(cacheDir.absolutePath + "/shop.json").delete()
        this.getSharedPreferences(getString(R.string.file_name), MODE_PRIVATE).edit()
            .remove(getString(R.string.total_price)).apply()
        this.getSharedPreferences(getString(R.string.file_name), MODE_PRIVATE).edit()
            .remove(getString(R.string.total_quantity)).apply()
        Toast.makeText(this, getString(R.string.Shop_delete_all_txt), Toast.LENGTH_SHORT)
            .show()

    }

    private fun updateSharedPreferences(quantity: Float, price: Float) {
        val sharedPreferences =
            this.getSharedPreferences(getString(R.string.file_name), MODE_PRIVATE)

        val oldQuantity = sharedPreferences.getInt(getString(R.string.total_quantity), 0)
        val newQuantity = oldQuantity - quantity
        sharedPreferences.edit().putInt(getString(R.string.total_quantity), newQuantity.toInt())
            .apply()

        val oldPrice = sharedPreferences.getFloat(getString(R.string.total_price), 0.0f)
        val newPrice = abs(oldPrice - price)
        sharedPreferences.edit().putFloat(getString(R.string.total_price), newPrice).apply()

    }

    private fun changeActivity() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

}

