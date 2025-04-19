package com.application.myrgu.schedule.presentation.weekCalendarShimmer

import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.WeekDayBinder

class WeekCalendarShimmerAdapter : WeekDayBinder<DayShimmerViewContainer> {

    override fun create(view: View): DayShimmerViewContainer = DayShimmerViewContainer(view)

    override fun bind(container: DayShimmerViewContainer, data: WeekDay) {}
}