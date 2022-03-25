package com.example.sinanerestaurant

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sinanerestaurant.Model.Item
import com.squareup.picasso.Picasso


class CategoryAdapter(val data : ArrayList<Item>, val clickListener: (Item) -> Unit) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val itemTextView : TextView = view.findViewById(R.id.textItem)
        val image: ImageView = view.findViewById(R.id.imagePicasso)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_category, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Log.i("XXX","onBindViewHolder")
        val item = data[position]
        holder.itemTextView.text = item.name_fr

        val url = item.images[0]
        Picasso.get()
            .load(url.ifEmpty{null})
            .placeholder(R.drawable.logo_chef)
            .fit()
            .into(holder.image)

        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}
