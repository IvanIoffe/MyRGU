package com.application.myrgu.schedule.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.R

class LessonShimmerAdapter(
    private val itemCount: Int,
) : RecyclerView.Adapter<LessonShimmerAdapter.LessonShimmerViewHolder>() {

    class LessonShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonShimmerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson, parent, false)

        return LessonShimmerViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonShimmerViewHolder, position: Int) {}

    override fun getItemCount() = itemCount
}