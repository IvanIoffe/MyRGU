package com.application.myrgu.all_groups.data.mapper

import com.application.myrgu.all_groups.data.source.remote.model.GroupRemote
import com.application.myrgu.all_groups.data.source.remote.model.AllGroupsRemote
import com.application.myrgu.all_groups.domain.model.Group
import com.application.myrgu.all_groups.domain.model.AllGroups

fun AllGroupsRemote.toAllGroups() =
    AllGroups(
        items = items.map { groupRemote -> groupRemote.toGroup() },
    )

fun GroupRemote.toGroup() =
    Group(
        id = id,
        number = number,
    )