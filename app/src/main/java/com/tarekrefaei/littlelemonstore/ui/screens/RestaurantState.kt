package com.tarekrefaei.littlelemonstore.ui.screens

import com.tarekrefaei.littlelemonstore.domain.model.MenuItems

data class RestaurantState(
    val menuList: List<MenuItems> = emptyList(),
    var isLoading: Boolean = false,
    val searchQuery:String = ""
)