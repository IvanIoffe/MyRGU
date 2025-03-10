package com.application.myrgu.core.utils

import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerManager(
    private val shimmers: List<ShimmerFrameLayout>,
) {

    fun startShimmers() {
        shimmers.forEach { it.startShimmer() }
    }

    fun stopShimmers() {
        shimmers.forEach { it.stopShimmer() }
    }
}