package com.example.sinanerestaurant.Model

import java.io.Serializable

data class ShopItems(
    val item: Item,
    var quantity: Float
) : Serializable