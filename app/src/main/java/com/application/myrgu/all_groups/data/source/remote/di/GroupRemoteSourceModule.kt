package com.application.myrgu.all_groups.data.source.remote.di

import com.application.myrgu.all_groups.data.source.remote.GroupRemoteDataSource
import com.application.myrgu.all_groups.data.source.remote.RetrofitGroupRemoteDataSource
import com.application.myrgu.all_groups.data.source.remote.service.AllGroupsApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [GroupRemoteSourceModuleProvider::class, GroupRemoteSourceModuleBinder::class])
class GroupRemoteSourceModule

@Module
class GroupRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideAllGroupApiService(retrofit: Retrofit.Builder): AllGroupsApiService {
        return retrofit
            .build()
            .create()
    }
}

@Module
interface GroupRemoteSourceModuleBinder {

    @Binds
    fun bindRetrofitGroupRemoteDataSource(
        retrofitGroupRemoteDataSource: RetrofitGroupRemoteDataSource,
    ): GroupRemoteDataSource
}