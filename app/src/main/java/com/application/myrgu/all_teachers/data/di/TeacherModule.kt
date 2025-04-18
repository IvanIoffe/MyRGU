package com.application.myrgu.all_teachers.data.di

import com.application.myrgu.all_teachers.data.repository.TeacherRepositoryImpl
import com.application.myrgu.all_teachers.data.source.remote.di.TeacherRemoteSourceModule
import com.application.myrgu.all_teachers.domain.repository.TeacherRepository
import dagger.Binds
import dagger.Module

@Module(includes = [TeacherRemoteSourceModule::class])
interface TeacherModule {

    @Binds
    fun bindTeacherRepositoryImpl(
        teacherRepositoryImpl: TeacherRepositoryImpl,
    ): TeacherRepository
}