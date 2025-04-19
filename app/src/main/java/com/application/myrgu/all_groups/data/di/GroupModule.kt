package com.application.myrgu.all_groups.data.di

import com.application.myrgu.all_groups.data.repository.GroupRepositoryImpl
import com.application.myrgu.all_groups.data.source.remote.di.GroupRemoteSourceModule
import com.application.myrgu.all_groups.domain.repository.GroupRepository
import dagger.Binds
import dagger.Module

@Module(includes = [GroupRemoteSourceModule::class])
interface GroupModule {

    @Binds
    fun bindGroupRepositoryImpl(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository
}