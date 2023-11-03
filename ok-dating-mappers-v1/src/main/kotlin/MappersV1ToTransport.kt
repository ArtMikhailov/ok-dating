package com.amdev.dating.mappers.v1

import com.amdev.dating.api.v1.models.*
import com.amdev.dating.common.DatingContext
import com.amdev.dating.common.models.*
import com.amdev.dating.mappers.v1.exceptions.UnknownDatingCommand


fun DatingContext.toTransportProfile(): IResponse = when (val cmd = command) {
    DatingCommand.CREATE -> toTransportCreate()
    DatingCommand.READ -> toTransportRead()
    DatingCommand.UPDATE -> toTransportUpdate()
    DatingCommand.DELETE -> toTransportDelete()
    DatingCommand.SEARCH -> toTransportSearch()
    DatingCommand.OFFERS -> toTransportOffers()
    DatingCommand.NONE -> throw UnknownDatingCommand(cmd)
}

fun DatingContext.toTransportCreate() = ProfileCreateResponse(
    responseType = "create",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profile = profileResponse.toTransportProfile()
)

fun DatingContext.toTransportRead() = ProfileReadResponse(
    responseType = "read",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profile = profileResponse.toTransportProfile()
)

fun DatingContext.toTransportUpdate() = ProfileUpdateResponse(
    responseType = "update",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profile = profileResponse.toTransportProfile()
)

fun DatingContext.toTransportDelete() = ProfileDeleteResponse(
    responseType = "delete",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profile = profileResponse.toTransportProfile()
)

fun DatingContext.toTransportSearch() = ProfileSearchResponse(
    responseType = "search",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profiles = profilesResponse.toTransportProfile()
)

fun DatingContext.toTransportOffers() = ProfileOffersResponse(
    responseType = "offers",
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == DatingState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    profiles = profilesResponse.toTransportProfile()
)

fun List<DatingProfile>.toTransportProfile(): List<ProfileResponseObject>? = this
    .map { it.toTransportProfile() }
    .toList()
    .takeIf { it.isNotEmpty() }

fun DatingContext.toTransportInit() = ProfileInitResponse(
    requestId = this.requestId.asString().takeIf { it.isNotBlank() },
    result = if (errors.isEmpty()) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
)

private fun DatingProfile.toTransportProfile(): ProfileResponseObject = ProfileResponseObject(
    id = id.takeIf { it != DatingProfileId.NONE }?.asString(),
    name = name.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    ownerId = ownerId.takeIf { it != DatingUserId.NONE }?.asString(),
    gender = gender.toTransportProfile(),
    visibility = visibility.toTransportProfile(),
    permissions = permissionsClient.toTransportProfile(),
)

private fun Set<DatingProfilePermissionClient>.toTransportProfile(): Set<ProfilePermissions>? = this
    .map { it.toTransportProfile() }
    .toSet()
    .takeIf { it.isNotEmpty() }

private fun DatingProfilePermissionClient.toTransportProfile() = when (this) {
    DatingProfilePermissionClient.READ -> ProfilePermissions.READ
    DatingProfilePermissionClient.UPDATE -> ProfilePermissions.UPDATE
    DatingProfilePermissionClient.MAKE_VISIBLE_OWNER -> ProfilePermissions.MAKE_VISIBLE_OWN
    DatingProfilePermissionClient.MAKE_VISIBLE_GROUP -> ProfilePermissions.MAKE_VISIBLE_GROUP
    DatingProfilePermissionClient.MAKE_VISIBLE_PUBLIC -> ProfilePermissions.MAKE_VISIBLE_PUBLIC
    DatingProfilePermissionClient.DELETE -> ProfilePermissions.DELETE
}

private fun DatingVisibility.toTransportProfile(): ProfileVisibility? = when (this) {
    DatingVisibility.VISIBLE_PUBLIC -> ProfileVisibility.PUBLIC
    DatingVisibility.VISIBLE_TO_GROUP -> ProfileVisibility.REGISTERED_ONLY
    DatingVisibility.VISIBLE_TO_OWNER -> ProfileVisibility.OWNER_ONLY
    DatingVisibility.NONE -> null
}

private fun DatingGender.toTransportProfile(): Gender? = when (this) {
    DatingGender.FEMALE -> Gender.FEMALE
    DatingGender.MALE -> Gender.MALE
    DatingGender.NONE -> null
}

private fun List<DatingError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportProfile() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun DatingError.toTransportProfile() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)
