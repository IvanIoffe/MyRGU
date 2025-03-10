package com.application.myrgu.auth.data.source.remote.di

import com.application.myrgu.auth.data.source.remote.AuthRemoteDataSource
import com.application.myrgu.auth.data.source.remote.RetrofitAuthRemoteDataSource
import com.application.myrgu.auth.data.source.remote.service.AuthApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [AuthRemoteSourceModuleProvider::class, AuthRemoteSourceModuleBinder::class])
class AuthRemoteSourceModule

@Module
class AuthRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit.Builder): AuthApiService {
        return retrofit
            .build()
            .create()
    }
}

@Module
interface AuthRemoteSourceModuleBinder {

    @Binds
    fun bindRetrofitAuthRemoteDataSource(
        retrofitAuthRemoteDataSource: RetrofitAuthRemoteDataSource,
    ): AuthRemoteDataSource
}