package com.application.myrgu.all_groups.data.source.remote.service

import com.application.myrgu.all_groups.data.source.remote.model.AllGroupsRemote
import retrofit2.Response
import retrofit2.http.GET

interface AllGroupsApiService {
    @GET("grouplist")
    suspend fun getAllGroups(): Response<AllGroupsRemote>
}