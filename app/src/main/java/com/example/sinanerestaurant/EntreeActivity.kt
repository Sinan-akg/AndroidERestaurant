package com.example.sinanerestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sinanerestaurant.Model.DataResult
import com.example.sinanerestaurant.databinding.ActivityEntreeBinding
import com.google.gson.Gson
import org.json.JSONObject

class EntreeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEntreeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEntreeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val categoryName = intent.getStringExtra("category1")

        binding.category1.text = categoryName

        binding.recyclerView1.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView1.adapter = CategoryAdapter(arrayListOf()) { }
        getDataFromApi(intent.getStringExtra("category1") ?: "")
    }

    companion object {
        val ITEM_KEY = "item"
    }

    private fun getDataFromApi(category: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", 1)

        val stringReq = JsonObjectRequest(
            Request.Method.POST, url,
            jsonObject, { response ->
                val strResp = response.toString()
                val dataResult = Gson().fromJson(strResp, DataResult::class.java)

                val items =
                    dataResult.data.firstOrNull { it.name_fr == category }?.items ?: arrayListOf()
                binding.recyclerView1.adapter = CategoryAdapter(items) {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ITEM_KEY, it)
                    startActivity(intent)
                }
            }, {

                Log.d("API", "error ${it.message}")
            }
        )
        queue.add(stringReq)
    }

}