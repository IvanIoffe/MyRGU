package com.application.myrgu.all_groups.domain.repository

import com.application.myrgu.core.data.Result
import com.application.myrgu.all_groups.domain.model.AllGroups
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    fun getAllGroups(): Flow<Result<AllGroups>>
}