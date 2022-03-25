package com.example.sinanerestaurant.Model

import java.io.Serializable

data class Category(val name_fr: String, val items: ArrayList<Item>): Serializable
