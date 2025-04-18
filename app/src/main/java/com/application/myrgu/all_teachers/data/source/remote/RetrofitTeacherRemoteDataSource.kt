package com.application.myrgu.all_teachers.data.source.remote

import com.application.myrgu.all_teachers.data.source.remote.model.AllTeachersRemote
import com.application.myrgu.all_teachers.data.source.remote.service.GetAllTeachersApiService
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.remoteRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitTeacherRemoteDataSource @Inject constructor(
    private val getAllTeachersApiService: GetAllTeachersApiService,
) : TeacherRemoteDataSource {

    override fun getAllTeachers(): Flow<Result<AllTeachersRemote>> {
        return remoteRequestFlow {
            getAllTeachersApiService.getAllTeachers()
        }
    }
}