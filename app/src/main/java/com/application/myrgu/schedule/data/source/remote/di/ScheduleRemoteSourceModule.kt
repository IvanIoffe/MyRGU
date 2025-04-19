package com.application.myrgu.schedule.data.source.remote.di

import com.application.myrgu.schedule.data.source.remote.RetrofitScheduleRemoteDataSource
import com.application.myrgu.schedule.data.source.remote.ScheduleRemoteDataSource
import com.application.myrgu.schedule.data.source.remote.service.ScheduleApiService
import com.application.myrgu.schedule.data.source.remote.service.ScheduleVersionApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [ScheduleRemoteSourceModuleProvider::class, ScheduleRemoteSourceModuleBinder::class])
class ScheduleRemoteSourceModule

@Module
class ScheduleRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideScheduleApiService(retrofit: Retrofit.Builder): ScheduleApiService {
        return retrofit
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideScheduleVersionApiService(retrofit: Retrofit.Builder): ScheduleVersionApiService {
        return retrofit
            .build()
            .create()
    }
}

@Module
interface ScheduleRemoteSourceModuleBinder {

    @Binds
    fun bindRetrofitScheduleRemoteDataSource(
        retrofitScheduleRemoteDataSource: RetrofitScheduleRemoteDataSource,
    ): ScheduleRemoteDataSource
}