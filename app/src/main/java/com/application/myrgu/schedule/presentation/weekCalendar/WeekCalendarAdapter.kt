package com.application.myrgu.schedule.presentation.weekCalendar

import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.WeekDayBinder

class WeekCalendarAdapter(
    private val listener: ChangeDateListener,
) : WeekDayBinder<DayViewContainer> {

    override fun create(view: View): DayViewContainer = DayViewContainer(view, listener)

    override fun bind(container: DayViewContainer, data: WeekDay) = container.bind(data)
}