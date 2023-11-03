package com.amdev.dating.common.models

data class DatingProfileFilter(
    var searchString: String = "",
    var ownerId: DatingUserId = DatingUserId.NONE,
    var dealSide: DatingGender = DatingGender.NONE,
)
