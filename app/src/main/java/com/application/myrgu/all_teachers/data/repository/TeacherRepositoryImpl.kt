package com.application.myrgu.all_teachers.data.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.mapper.mapToDomainModelFlow
import com.application.myrgu.all_teachers.data.mapper.toAllTeachers
import com.application.myrgu.all_teachers.data.source.remote.TeacherRemoteDataSource
import com.application.myrgu.all_teachers.domain.model.AllTeachers
import com.application.myrgu.all_teachers.domain.repository.TeacherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(
    private val teacherRemoteDataSource: TeacherRemoteDataSource,
) : TeacherRepository {

    override fun getAllTeachers(): Flow<Result<AllTeachers>> {
        return teacherRemoteDataSource.getAllTeachers().mapToDomainModelFlow(
            mapper = { allTeachersRemote ->
                allTeachersRemote.toAllTeachers()
            }
        )
    }
}