package com.application.myrgu.all_groups.data.source.remote

import com.application.myrgu.all_groups.data.source.remote.model.AllGroupsRemote
import com.application.myrgu.all_groups.data.source.remote.service.AllGroupsApiService
import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.remoteRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrofitGroupRemoteDataSource @Inject constructor(
    private val allGroupsApiService: AllGroupsApiService,
) : GroupRemoteDataSource {

    override fun getAllGroups(): Flow<Result<AllGroupsRemote>> {
        return remoteRequestFlow {
            allGroupsApiService.getAllGroups()
        }
    }
}