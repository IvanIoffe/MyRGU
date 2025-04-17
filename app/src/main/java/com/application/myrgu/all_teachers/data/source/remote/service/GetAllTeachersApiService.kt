package com.application.myrgu.all_teachers.data.source.remote.service

import com.application.myrgu.all_teachers.data.source.remote.model.AllTeachersRemote
import retrofit2.Response
import retrofit2.http.GET

interface GetAllTeachersApiService {
    @GET("teacher_list")
    suspend fun getAllTeachers(): Response<AllTeachersRemote>
}