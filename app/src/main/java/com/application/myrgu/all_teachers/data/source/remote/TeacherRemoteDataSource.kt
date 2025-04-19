package com.application.myrgu.all_teachers.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.all_teachers.data.source.remote.model.AllTeachersRemote
import kotlinx.coroutines.flow.Flow

interface TeacherRemoteDataSource {

    fun getAllTeachers(): Flow<Result<AllTeachersRemote>>
}