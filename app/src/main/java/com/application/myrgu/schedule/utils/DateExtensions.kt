package com.application.myrgu.schedule.utils

import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

fun Month.getDisplayMonthName(): String {
    return when (this) {
        Month.JANUARY -> "янв"
        Month.FEBRUARY -> "фев"
        Month.MARCH -> "мар"
        Month.APRIL -> "апр"
        Month.MAY -> "май"
        Month.JUNE -> "июн"
        Month.JULY -> "июл"
        Month.AUGUST -> "авг"
        Month.SEPTEMBER -> "сен"
        Month.OCTOBER -> "окт"
        Month.NOVEMBER -> "ноя"
        Month.DECEMBER -> "дек"
    }
}

val DayOfWeek.displayName: String
    get() = this.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("RU"))