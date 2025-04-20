package com.application.myrgu.schedule.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.myrgu.databinding.ItemLessonBinding
import com.application.myrgu.schedule.domain.model.Lesson
import com.application.myrgu.schedule.domain.model.ScheduleType
import com.application.myrgu.schedule.utils.getDisplayClassroom
import com.application.myrgu.schedule.utils.getDisplayTeacherOrGroups
import com.application.myrgu.schedule.utils.getDisplayType

class LessonAdapter(
    private val scheduleType: ScheduleType?,
) : ListAdapter<Lesson, LessonAdapter.LessonViewHolder>(LessonCallback()) {

    inner class LessonViewHolder(
        private val binding: ItemLessonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(lesson: Lesson) = with(binding) {
            val context = root.context

            textViewLessonStartTime.text = lesson.startTime.toString()
            textViewLessonEndTime.text = lesson.endTime.toString()
            textViewLessonType.text = lesson.getDisplayType(context)
            textViewLessonName.text = lesson.name
            textViewLessonClassroom.text = lesson.getDisplayClassroom(context)
            textViewLessonTeacherOrGroups.text = lesson.getDisplayTeacherOrGroups(context, scheduleType)
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