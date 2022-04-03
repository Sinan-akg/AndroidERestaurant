package com.example.sinanerestaurant

import android.util.Log
import android.view.*
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.sinanerestaurant.Model.Item
import com.squareup.picasso.Picasso
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity


internal class CategoryAdapter(val data : ArrayList<Item>, val clickListener: (Item) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    internal inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val itemTextView: TextView = view.findViewById(R.id.textItem)
        val image: ImageView = view.findViewById(R.id.imagePicasso)
        val priceTextView: TextView = view.findViewById(R.id.textPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.i("XXX", "onBindViewHolder")
        val item = data[position]
        holder.itemTextView.text = item.name_fr

        val prix: String = item.prices[0].price + "€"
        holder.priceTextView.text = prix

        val url = item.images[0]
        Picasso.get()
            .load(url.ifEmpty { null })
            .placeholder(R.drawable.logo_chef)
            .fit()
            .into(holder.image)

        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.topicon, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.bluetooth1 -> {
            Toast.makeText(this@CategoryAdapter, "Bluetooth", Toast.LENGTH_SHORT).show()
            true
        }

        R.id.shopbag -> {
            Toast.makeText(this@CategoryAdapter, "Panier", Toast.LENGTH_SHORT).show()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }*/

    class CategoryActivity : AppCompatActivity() {
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.topicon, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.bluetooth1 -> {
                Toast.makeText(this, "Bluetooth", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.shopbag -> {
                Toast.makeText(this, "Panier", Toast.LENGTH_SHORT).show()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }


        }

    }
}