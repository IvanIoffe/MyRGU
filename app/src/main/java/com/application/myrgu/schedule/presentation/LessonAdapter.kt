package com.application.myrgu.schedule.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.core.utils.Constants
import com.application.myrgu.databinding.ItemLessonBinding
import com.application.myrgu.schedule.domain.model.Lesson

class LessonAdapter : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(LessonCallback()) {

    class LessonViewHolder(
        private val binding: ItemLessonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) {
            binding.textViewLessonStartTime.text = lesson.startTime.toString()
            binding.textViewLessonEndTime.text = lesson.endTime.toString()
            binding.textViewLessonType.text = lesson.type ?: Constants.NO_TYPE_OF_LESSON
            binding.textViewLessonName.text = lesson.name
            binding.textViewLessonClassroom.text =
                lesson.classroom ?: Constants.NO_CLASSROOM_OF_LESSON
            binding.textViewLessonTeacher.text = lesson.teacher ?: Constants.NO_TEACHER_OF_LESSON
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