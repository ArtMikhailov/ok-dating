package com.amdev.dating.common.models

data class DatingProfile(
    var id: DatingProfileId = DatingProfileId.NONE,
    var name: String = "",
    var description: String = "",
    var ownerId: DatingUserId = DatingUserId.NONE,
    var gender: DatingGender = DatingGender.NONE,
    var visibility: DatingVisibility = DatingVisibility.NONE,
    val permissionsClient: MutableSet<DatingProfilePermissionClient> = mutableSetOf()
)
