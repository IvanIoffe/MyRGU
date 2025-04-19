package com.application.myrgu.all_groups.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.all_groups.domain.model.Group
import com.application.myrgu.databinding.ItemGroupBinding

class GroupAdapter(
    private val listener: Listener,
) : ListAdapter<Group, GroupAdapter.GroupViewHolder>(GroupCallback()) {

    class GroupViewHolder(
        private val binding: ItemGroupBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(group: Group) {
            binding.textViewNumberGroup.text = group.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = getItem(position)
        holder.bind(group)
        holder.itemView.setOnClickListener {
            listener.onClick(group)
        }
    }

    interface Listener {
        fun onClick(group: Group)
    }
}

class GroupCallback : DiffUtil.ItemCallback<Group>() {
    override fun areItemsTheSame(oldItem: Group, newItem: Group): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Group, newItem: Group): Boolean {
        return oldItem == newItem
    }
}