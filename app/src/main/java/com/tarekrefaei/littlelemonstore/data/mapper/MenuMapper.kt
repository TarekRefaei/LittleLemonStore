package com.tarekrefaei.littlelemonstore.data.mapper

import com.tarekrefaei.littlelemonstore.data.local.MenuEntity
import com.tarekrefaei.littlelemonstore.data.remote.MenuItemsNetwork
import com.tarekrefaei.littlelemonstore.domain.model.MenuItems

fun MenuItemsNetwork.toMenuEntity(): MenuEntity {
    return MenuEntity(
        id = id,
        title = title ?: "",
        description = description ?: "",
        image = image ?: "",
        price = price ?: "",
        category = category ?: ""
    )
}

fun MenuEntity.toMenuListing():MenuItems{
    return MenuItems(
        id = id,
        title = title,
        image = image,
        description = description,
        price = price,
        category = category
    )
}




