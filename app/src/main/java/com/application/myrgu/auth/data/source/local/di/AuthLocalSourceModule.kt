package com.application.myrgu.auth.data.source.local.di

import com.application.myrgu.auth.data.source.local.AuthLocalDataSource
import com.application.myrgu.auth.data.source.local.DataStoreAuthLocalDataSource
import dagger.Binds
import dagger.Module

@Module(includes = [AuthLocalSourceModuleBinder::class])
class AuthLocalSourceModule

@Module
interface AuthLocalSourceModuleBinder {

    @Binds
    fun bindDataStoreAuthLocalDataSource(
        dataStoreAuthLocalDataSource: DataStoreAuthLocalDataSource,
    ): AuthLocalDataSource
}