package com.application.myrgu.all_groups.data.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.core.data.mapper.mapToDomainModelFlow
import com.application.myrgu.all_groups.data.mapper.toAllGroups
import com.application.myrgu.all_groups.data.source.remote.GroupRemoteDataSource
import com.application.myrgu.all_groups.domain.model.AllGroups
import com.application.myrgu.all_groups.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupRemoteDataSource: GroupRemoteDataSource,
) : GroupRepository {

    override fun getAllGroups(): Flow<Result<AllGroups>> {
        return groupRemoteDataSource.getAllGroups().mapToDomainModelFlow(
            mapper = { allGroupsRemote ->
                allGroupsRemote.toAllGroups()
            }
        )
    }
}