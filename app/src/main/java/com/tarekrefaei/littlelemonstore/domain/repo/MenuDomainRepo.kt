package com.tarekrefaei.littlelemonstore.domain.repo

import com.tarekrefaei.littlelemonstore.domain.model.MenuItems
import com.tarekrefaei.littlelemonstore.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MenuDomainRepo {

    suspend fun getMenu(query:String): Flow<Resources<List<MenuItems>>>

}