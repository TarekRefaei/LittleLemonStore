package com.tarekrefaei.littlelemonstore.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MenuEntity(
    @PrimaryKey val id: Int? = null,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)
