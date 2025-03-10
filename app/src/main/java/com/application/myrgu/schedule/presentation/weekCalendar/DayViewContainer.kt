package com.application.myrgu.schedule.presentation.weekCalendar

import android.graphics.Color
import android.view.View
import com.application.myrgu.R
import com.application.myrgu.databinding.ItemCalendarDayBinding
import com.application.myrgu.schedule.utils.getDisplayMonthName
import com.application.myrgu.schedule.utils.selectedDate
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class DayViewContainer(
    view: View,
    listener: ChangeDateListener,
) : ViewContainer(view) {
    private val binding: ItemCalendarDayBinding = ItemCalendarDayBinding.bind(view)
    private lateinit var day: WeekDay
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d")

    init {
        binding.root.setOnClickListener {
            if (selectedDate != day.date) {
                val oldDate = selectedDate
                selectedDate = day.date

                listener.onChanged(newDate = day.date, oldDate = oldDate)
            }
        }
    }

    fun bind(day: WeekDay) {
        this.day = day

        binding.apply {
            textViewDayOfWeek.text =
                day.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("RU"))
            textViewDateOfMonth.text = dateFormatter.format(day.date)
            textViewMonth.text = day.date.month.getDisplayMonthName()
        }

        updateUICalendarDayItem()
    }

    private fun updateUICalendarDayItem() {
        if (selectedDate == day.date) {
            binding.root.setBackgroundResource(R.drawable.date_of_month_background)
            binding.textViewDayOfWeek.setTextColor(view.context.getColor(R.color.white))
            binding.textViewDateOfMonth.setTextColor(view.context.getColor(R.color.white))
            binding.textViewMonth.setTextColor(view.context.getColor(R.color.white))
        } else {
            binding.root.setBackgroundColor(Color.TRANSPARENT)
            binding.textViewDayOfWeek.setTextColor(view.context.getColor(R.color.week_calendar_view_text))
            binding.textViewDateOfMonth.setTextColor(view.context.getColor(R.color.week_calendar_view_text))
            binding.textViewMonth.setTextColor(view.context.getColor(R.color.week_calendar_view_text))
        }
    }
}