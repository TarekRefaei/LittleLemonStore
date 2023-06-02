package com.tarekrefaei.littlelemonstore.di

import android.app.Application
import androidx.room.Room
import com.tarekrefaei.littlelemonstore.data.local.MenuDataBase
import com.tarekrefaei.littlelemonstore.data.remote.MenuApi
import com.tarekrefaei.littlelemonstore.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestaurantApi(): MenuApi {
        return Retrofit.Builder()
            .baseUrl(Utils.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MenuApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRestaurantDatabase(app: Application): MenuDataBase {
        return Room.databaseBuilder(
            app,
            MenuDataBase::class.java,
            "MenuDB.db"
        ).build()
    }
}