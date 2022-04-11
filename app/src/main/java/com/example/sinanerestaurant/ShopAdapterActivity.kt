package com.example.sinanerestaurant

import android.view.LayoutInflater
import com.example.sinanerestaurant.Model.ShopItems
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sinanerestaurant.databinding.CellShopBinding
import com.squareup.picasso.Picasso

class ShopAdapterActivity(
private val Shop: ArrayList<ShopItems>,
private val onShopClick: (ShopItems) -> Unit
) : RecyclerView.Adapter<ShopAdapterActivity.ShopViewHolder>() {

    class ShopViewHolder(binding: CellShopBinding) : RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.NomPlat
        val price: TextView = binding.PricePlat
        val quantity: TextView = binding.QuantityPlat
        val imageplus : ImageView = binding.ButtonHigh
        val imagemoins : ImageView = binding.ButtonMinus
        val totalbutton : Button = binding.totalButton
        val reset: Button = binding.ResetButton
        val imageChef: ImageView = binding.ImageChef

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            CellShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val command = Shop[position]

        holder.name.text = command.item.name_fr

        Picasso.get().load(command.item.images[0].ifEmpty { null })
            .placeholder(R.drawable.cuisinier)
            .error(R.drawable.cuisinier)
            .into(holder.imageChef)

        val price = " Total : ${command.item.prices[0].price.toFloat() * command.quantity} €"
        holder.price.text = price


        val quantity = "Quantité : ${command.quantity}"
        holder.quantity.text = quantity

        holder.reset.setOnClickListener {
            onShopClick(command)
        }

    }

    override fun getItemCount(): Int {
        return Shop.size
    }

}