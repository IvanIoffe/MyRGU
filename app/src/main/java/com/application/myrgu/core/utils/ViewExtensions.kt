package com.application.myrgu.core.utils

import android.view.View

fun View.changeVisibility(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.INVISIBLE
}