package com.application.myrgu.all_teachers.domain.usecase

import com.application.myrgu.all_teachers.domain.repository.TeacherRepository
import javax.inject.Inject

class GetAllTeachersUseCase @Inject constructor(
    private val teacherRepository: TeacherRepository,
) {
    operator fun invoke() = teacherRepository.getAllTeachers()
}