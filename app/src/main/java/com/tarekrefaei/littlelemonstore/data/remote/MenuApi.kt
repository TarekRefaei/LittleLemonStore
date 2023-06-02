package com.tarekrefaei.littlelemonstore.data.remote

import retrofit2.http.GET

interface MenuApi {

    @GET("Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
    suspend fun getMenu(): MenuNetwork
}