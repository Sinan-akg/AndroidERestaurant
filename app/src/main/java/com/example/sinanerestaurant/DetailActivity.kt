package com.example.sinanerestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sinanerestaurant.Model.Item
import com.example.sinanerestaurant.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
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
    }
}