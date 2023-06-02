package com.tarekrefaei.littlelemonstore.di

import com.tarekrefaei.littlelemonstore.data.MenuDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

//    @Binds
//    @Singleton
//    abstract fun bindMenuRepo(
//        menuDataImpl: MenuDataImpl
//    ): MenuDataImpl

}