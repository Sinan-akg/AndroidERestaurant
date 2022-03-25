package com.example.sinanerestaurant

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sinanerestaurant.Model.Item
import com.example.sinanerestaurant.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

       // setContentView(R.layout.activity_detail)
        setContentView(binding.root)

        val item = intent.getSerializableExtra(EntreeActivity.ITEM_KEY) as Item
        binding.textedutitre.text = item.name_fr

        val carouselAdapter = CarouselAdapter(this, item.images)

        binding.detailSlider.adapter = carouselAdapter

        binding.textedutitre.text = item.name_fr

        binding.description.text = item.ingredients.joinToString ( ", " ){it.name_fr}

        binding.buttontotal.text = item.prices.joinToString( ", ") {"Total : " + it.price.toString() + " €"}


       // val button : Snackbar = Snackbar.make(binding.root,"Ajouté au panier", Snackbar.LENGTH_SHORT)
        //button.setOnClickListener {
            //  button.setActionTextColor(Color.WHITE)
          //  button.show()
        //}

        button = findViewById(R.id.buttontotal);
        button.setOnClickListener{
        val snackbar = Snackbar.make(binding.root, "Ajouté au panier", Snackbar.LENGTH_SHORT)
       snackbar.show()}


    }
}