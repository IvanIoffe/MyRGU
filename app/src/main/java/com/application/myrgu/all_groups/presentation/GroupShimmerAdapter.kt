package com.application.myrgu.all_groups.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.R

class GroupShimmerAdapter(
    private val itemCount: Int = 50,
) : RecyclerView.Adapter<GroupShimmerAdapter.GroupShimmerViewHolder>() {

    class GroupShimmerViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupShimmerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group_shimmer, parent, false)

        return GroupShimmerViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupShimmerViewHolder, position: Int) {}

    override fun getItemCount(): Int = itemCount
}