package com.application.myrgu.all_teachers.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.application.myrgu.databinding.ItemTeacherBinding
import com.application.myrgu.all_teachers.domain.model.Teacher

class TeacherAdapter(
    private val listener: Listener,
) : ListAdapter<Teacher, TeacherAdapter.TeacherViewHolder>(TeacherCallback()) {

    class TeacherViewHolder(
        private val binding: ItemTeacherBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(teacher: Teacher) {
            binding.imageViewTeacherPhoto.load(teacher.imageUri)
            binding.textViewTeacherName.text = teacher.fullName
            binding.textViewTeacherPost.text = teacher.post
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding =
            ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = getItem(position)
        holder.itemView.setOnClickListener {
            listener.onClick(teacher)
        }
        holder.bind(teacher)
    }

    interface Listener {
        fun onClick(teacher: Teacher)
    }
}

class TeacherCallback : DiffUtil.ItemCallback<Teacher>() {
    override fun areItemsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Teacher, newItem: Teacher): Boolean {
        return oldItem == newItem
    }
}