package com.amdev.dating.common

import kotlinx.datetime.Instant
import com.amdev.dating.common.models.*
import com.amdev.dating.common.stubs.DatingStubs

data class DatingContext(
    var command: DatingCommand = DatingCommand.NONE,
    var state: DatingState = DatingState.NONE,
    val errors: MutableList<DatingError> = mutableListOf(),

    var workMode: DatingWorkMode = DatingWorkMode.PROD,
    var stubCase: DatingStubs = DatingStubs.NONE,

    var requestId: DatingRequestId = DatingRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var profileRequest: DatingProfile = DatingProfile(),
    var profileFilterRequest: DatingProfileFilter = DatingProfileFilter(),
    var profileResponse: DatingProfile = DatingProfile(),
    var profilesResponse: MutableList<DatingProfile> = mutableListOf(),
)
