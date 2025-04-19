package com.application.myrgu.schedule.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.R
import com.application.myrgu.databinding.ItemLessonBinding
import com.application.myrgu.schedule.domain.model.Lesson

class LessonAdapter : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(LessonCallback()) {

    class LessonViewHolder(
        private val binding: ItemLessonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) = with(binding) {
            val context = root.context

            textViewLessonStartTime.text = lesson.startTime.toString()
            textViewLessonEndTime.text = lesson.endTime.toString()
            textViewLessonType.text = lesson.type ?: context.getString(R.string.no_lesson_type)
            textViewLessonName.text = lesson.name
            textViewLessonClassroom.text = lesson.classroom ?: context.getString(R.string.no_classroom)
            textViewLessonTeacherOrGroups.text = lesson.teacherOrGroups ?: context.getString(R.string.no_data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class LessonCallback : DiffUtil.ItemCallback<Lesson>() {
    override fun areItemsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Lesson, newItem: Lesson): Boolean {
        return oldItem == newItem
    }
}