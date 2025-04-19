package com.application.myrgu.all_groups.data.source.remote

import com.application.myrgu.core.data.Result
import com.application.myrgu.all_groups.data.source.remote.model.AllGroupsRemote
import kotlinx.coroutines.flow.Flow

interface GroupRemoteDataSource {

    fun getAllGroups(): Flow<Result<AllGroupsRemote>>
}