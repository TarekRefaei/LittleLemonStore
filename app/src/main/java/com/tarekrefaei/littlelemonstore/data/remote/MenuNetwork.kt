package com.tarekrefaei.littlelemonstore.data.remote


data class MenuNetwork(
    val menu: List<MenuItemsNetwork>
)

data class MenuItemsNetwork(
    val id: Int?,
    val title: String?,
    val description: String?,
    val price: String?,
    val image: String?,
    val category: String?
)
