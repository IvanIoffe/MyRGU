package com.application.myrgu.all_teachers.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.R

class TeacherShimmerAdapter(
    private val itemCount: Int = 50,
) : RecyclerView.Adapter<TeacherShimmerAdapter.TeacherShimmerViewHolder>() {

    class TeacherShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherShimmerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teacher_shimmer, parent, false)

        return TeacherShimmerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeacherShimmerViewHolder, position: Int) {}

    override fun getItemCount() = itemCount
}