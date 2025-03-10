package com.application.myrgu.all_teachers.data.source.remote.di

import com.application.myrgu.all_teachers.data.source.remote.RetrofitTeacherRemoteDataSource
import com.application.myrgu.all_teachers.data.source.remote.TeacherRemoteDataSource
import com.application.myrgu.all_teachers.data.source.remote.service.GetAllTeachersApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module(includes = [TeacherRemoteSourceModuleProvider::class, TeacherRemoteSourceModuleBinder::class])
class TeacherRemoteSourceModule

@Module
class TeacherRemoteSourceModuleProvider {

    @Singleton
    @Provides
    fun provideGetAllTeachersApiService(retrofit: Retrofit.Builder): GetAllTeachersApiService {
        return retrofit
            .build()
            .create()
    }
}

@Module
interface TeacherRemoteSourceModuleBinder {

    @Binds
    fun bindRetrofitTeacherRemoteDataSource(
        retrofitTeacherRemoteDataSource: RetrofitTeacherRemoteDataSource,
    ): TeacherRemoteDataSource
}