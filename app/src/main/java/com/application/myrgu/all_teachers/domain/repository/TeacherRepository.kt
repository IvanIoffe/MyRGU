package com.application.myrgu.all_teachers.domain.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.all_teachers.domain.model.AllTeachers
import kotlinx.coroutines.flow.Flow

interface TeacherRepository {

    fun getAllTeachers(): Flow<Result<AllTeachers>>
}