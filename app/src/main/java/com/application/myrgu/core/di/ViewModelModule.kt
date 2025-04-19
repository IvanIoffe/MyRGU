package com.application.myrgu.core.di

import androidx.lifecycle.ViewModel
import com.application.myrgu.auth.presentation.AuthViewModel
import com.application.myrgu.auth.presentation.UserAuthDataViewModel
import com.application.myrgu.core.presentation.MainFragmentViewModel
import com.application.myrgu.all_groups.presentation.AllGroupsViewModel
import com.application.myrgu.all_teachers.presentation.AllTeachersViewModel
import com.application.myrgu.schedule.presentation.ScheduleViewModel
import com.application.myrgu.schedule.presentation.weekCalendar.DayOfWeekViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ClassKey(AuthViewModel::class)
    fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(UserAuthDataViewModel::class)
    fun bindUserAuthDataViewModel(userAuthDataViewModel: UserAuthDataViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(MainFragmentViewModel::class)
    fun bindMainFragmentViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(ScheduleViewModel::class)
    fun bindScheduleViewModel(scheduleViewModel: ScheduleViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(DayOfWeekViewModel::class)
    fun bindDayOfWeekViewModel(dayOfWeekViewModel: DayOfWeekViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(AllGroupsViewModel::class)
    fun bindAllGroupsViewModel(allGroupsViewModel: AllGroupsViewModel): ViewModel

    @Binds
    @IntoMap
    @ClassKey(AllTeachersViewModel::class)
    fun bindAllTeachersViewModel(allTeachersViewModel: AllTeachersViewModel): ViewModel
}