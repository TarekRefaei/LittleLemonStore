package com.tarekrefaei.littlelemonstore.domain.model

data class MenuListing(
    val menu: List<MenuItems>
)

data class MenuItems(
    val id: Int?=null,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

