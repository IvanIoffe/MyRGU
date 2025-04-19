package com.application.myrgu.all_groups.domain.usecase

import com.application.myrgu.core.data.Result
import com.application.myrgu.all_groups.domain.model.AllGroups
import com.application.myrgu.all_groups.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGroupsUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    operator fun invoke(): Flow<Result<AllGroups>> = groupRepository.getAllGroups()
}