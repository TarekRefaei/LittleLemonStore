package com.tarekrefaei.littlelemonstore.data

import com.tarekrefaei.littlelemonstore.data.local.MenuDataBase
import com.tarekrefaei.littlelemonstore.data.mapper.toMenuEntity
import com.tarekrefaei.littlelemonstore.data.mapper.toMenuListing
import com.tarekrefaei.littlelemonstore.data.remote.MenuApi
import com.tarekrefaei.littlelemonstore.domain.model.MenuItems
import com.tarekrefaei.littlelemonstore.domain.repo.MenuDomainRepo
import com.tarekrefaei.littlelemonstore.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MenuDataImpl @Inject constructor(
    private val api: MenuApi,
    db: MenuDataBase,
) : MenuDomainRepo {

    private val dao = db.dao

    override suspend fun getMenu(
        query: String,
    ): Flow<Resources<List<MenuItems>>> {
        return flow {
            emit(
                Resources.Loading(isLoading = true)
            )

            val localList = dao.filterMenuListing(query = query)

            emit(
                Resources.Success(
                    data = localList.map {
                        it.toMenuListing()
                    }
                )
            )

            val isDbEmpty = localList.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDbEmpty

            if (shouldLoadFromCache) {
                emit(Resources.Loading(isLoading = false))
                return@flow
            }

            val remoteListing = try {
                api.getMenu()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error(message = e.message.toString()))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error(message = e.message.toString()))
                null
            }

            remoteListing?.let { listing ->
                emit(Resources.Loading(isLoading = true))
                dao.clearMenuListing()
                dao.insertMenuList(
                    listing.menu.map {
                        it.toMenuEntity()
                    }
                )
                emit(
                    Resources.Success(
                        data = dao
                            .filterMenuListing("")
                            .map {
                                it.toMenuListing()
                            }
                    )
                )
                emit(
                    Resources.Loading(false)
                )
            }
        }
    }
}