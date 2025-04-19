package com.application.myrgu.auth.data.di

import com.application.myrgu.auth.data.repository.AuthRepositoryImpl
import com.application.myrgu.auth.data.source.local.di.AuthLocalSourceModule
import com.application.myrgu.auth.data.source.remote.di.AuthRemoteSourceModule
import com.application.myrgu.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module

@Module(includes = [AuthRemoteSourceModule::class, AuthLocalSourceModule::class])
interface AuthModule {

    @Binds
    fun bindAuthRepositoryImpl(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}