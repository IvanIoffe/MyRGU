package com.application.myrgu.core.di

import android.content.Context
import com.application.myrgu.auth.data.di.AuthModule
import com.application.myrgu.core.data.source.local.di.StorageModule
import com.application.myrgu.core.data.source.remote.di.NetworkModule
import com.application.myrgu.schedule.data.di.ScheduleModule
import com.application.myrgu.all_groups.data.di.GroupModule
import com.application.myrgu.all_teachers.data.di.TeacherModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class, StorageModule::class, ViewModelModule::class, AuthModule::class,
        ScheduleModule::class, GroupModule::class, TeacherModule::class,
    ]
)
@Singleton
interface AppComponent {

    val daggerViewModelFactory: DaggerViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}