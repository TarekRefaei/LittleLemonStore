package com.tarekrefaei.littlelemonstore.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuDBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMenuList(
        menuListEntity: List<MenuEntity>
    )

    @Query("DELETE FROM MenuEntity")
    suspend fun clearMenuListing()


    @Query(
        """
        SELECT *
        FROM MenuEntity
        WHERE LOWER(category) LIKE '%' || LOWER(:query)    
    """
    )
    suspend fun filterMenuListing(query: String): List<MenuEntity>

}