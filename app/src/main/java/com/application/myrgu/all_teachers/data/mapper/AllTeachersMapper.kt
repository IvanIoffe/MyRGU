package com.application.myrgu.all_teachers.data.mapper

import com.application.myrgu.all_teachers.data.source.remote.model.TeacherRemote
import com.application.myrgu.all_teachers.data.source.remote.model.AllTeachersRemote
import com.application.myrgu.all_teachers.domain.model.Teacher
import com.application.myrgu.all_teachers.domain.model.AllTeachers

fun AllTeachersRemote.toAllTeachers() =
    AllTeachers(
        items = items.map { it.toTeacher() },
    )

fun TeacherRemote.toTeacher() =
    Teacher(
        id = id,
        imageUri = imageUrl,
        fullName = fullName,
        shortName = shortName,
        post = post,
    )