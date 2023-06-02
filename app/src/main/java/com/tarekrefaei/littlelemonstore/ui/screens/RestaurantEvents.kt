package com.tarekrefaei.littlelemonstore.ui.screens

sealed class RestaurantEvents {
    data class OnFilter(val query:String) : RestaurantEvents()
}