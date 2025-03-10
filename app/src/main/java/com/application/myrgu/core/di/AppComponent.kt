package com.application.myrgu.core.di

import android.content.Context
import com.application.myrgu.auth.data.di.AuthSourceModule
import com.application.myrgu.core.data.source.local.di.StorageModule
import com.application.myrgu.core.data.source.remote.di.NetworkModule
import com.application.myrgu.schedule.data.di.ScheduleSourceModule
import com.application.myrgu.all_groups.data.di.GroupSourceModule
import com.application.myrgu.all_teachers.data.di.TeacherSourceModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class, StorageModule::class, ViewModelModule::class, AuthSourceModule::class,
        ScheduleSourceModule::class, GroupSourceModule::class, TeacherSourceModule::class,
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