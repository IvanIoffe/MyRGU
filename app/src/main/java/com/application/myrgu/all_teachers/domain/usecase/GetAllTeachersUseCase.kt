package com.application.myrgu.all_teachers.domain.usecase

import com.application.myrgu.all_teachers.domain.model.AllTeachers
import com.application.myrgu.all_teachers.domain.repository.TeacherRepository
import com.application.myrgu.core.data.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTeachersUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository,
) {
    operator fun invoke(): Flow<Result<AllTeachers>> = teacherRepository.getAllTeachers()
}