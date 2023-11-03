package com.amdev.dating.mappers.v1

import com.amdev.dating.api.v1.models.*
import com.amdev.dating.common.DatingContext
import com.amdev.dating.common.models.*
import com.amdev.dating.common.stubs.DatingStubs
import com.amdev.dating.mappers.v1.exceptions.UnknownRequestClass


fun DatingContext.fromTransport(request: IRequest) = when (request) {
    is ProfileCreateRequest -> fromTransport(request)
    is ProfileReadRequest -> fromTransport(request)
    is ProfileUpdateRequest -> fromTransport(request)
    is ProfileDeleteRequest -> fromTransport(request)
    is ProfileSearchRequest -> fromTransport(request)
    is ProfileOffersRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}

private fun kotlin.String?.tProfileId() = this?.let { DatingProfileId(it) } ?: DatingProfileId.NONE
private fun String?.toProfileWithId() = DatingProfile(id = this.tProfileId())
private fun IRequest?.requestId() = this?.requestId?.let { DatingRequestId(it) } ?: DatingRequestId.NONE

private fun ProfileDebug?.transportToWorkMode(): DatingWorkMode = when (this?.mode) {
    ProfileRequestDebugMode.PROD -> DatingWorkMode.PROD
    ProfileRequestDebugMode.TEST -> DatingWorkMode.TEST
    ProfileRequestDebugMode.STUB -> DatingWorkMode.STUB
    null -> DatingWorkMode.PROD
}

private fun ProfileDebug?.transportToStubCase(): DatingStubs = when (this?.stub) {
    ProfileRequestDebugStubs.SUCCESS -> DatingStubs.SUCCESS
    ProfileRequestDebugStubs.NOT_FOUND -> DatingStubs.NOT_FOUND
    ProfileRequestDebugStubs.BAD_ID -> DatingStubs.BAD_ID
    ProfileRequestDebugStubs.BAD_TITLE -> DatingStubs.BAD_TITLE
    ProfileRequestDebugStubs.BAD_DESCRIPTION -> DatingStubs.BAD_DESCRIPTION
    ProfileRequestDebugStubs.BAD_VISIBILITY -> DatingStubs.BAD_VISIBILITY
    ProfileRequestDebugStubs.CANNOT_DELETE -> DatingStubs.CANNOT_DELETE
    ProfileRequestDebugStubs.BAD_SEARCH_STRING -> DatingStubs.BAD_SEARCH_STRING
    null -> DatingStubs.NONE
}

fun DatingContext.fromTransport(request: ProfileCreateRequest) {
    command = DatingCommand.CREATE
    requestId = request.requestId()
    profileRequest = request.profile?.toInternal() ?: DatingProfile()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun DatingContext.fromTransport(request: ProfileReadRequest) {
    command = DatingCommand.READ
    requestId = request.requestId()
    profileRequest = request.profile?.id.toProfileWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun DatingContext.fromTransport(request: ProfileUpdateRequest) {
    command = DatingCommand.UPDATE
    requestId = request.requestId()
    profileRequest = request.profile?.toInternal() ?: DatingProfile()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun DatingContext.fromTransport(request: ProfileDeleteRequest) {
    command = DatingCommand.DELETE
    requestId = request.requestId()
    profileRequest = request.profile?.id.toProfileWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun DatingContext.fromTransport(request: ProfileSearchRequest) {
    command = DatingCommand.SEARCH
    requestId = request.requestId()
    profileFilterRequest = request.profileFilter.toInternal()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun DatingContext.fromTransport(request: ProfileOffersRequest) {
    command = DatingCommand.OFFERS
    requestId = request.requestId()
    profileRequest = request.profile?.id.toProfileWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

private fun ProfileSearchFilter?.toInternal(): DatingProfileFilter = DatingProfileFilter(
    searchString = this?.searchString ?: ""
)

private fun ProfileCreateObject.toInternal(): DatingProfile = DatingProfile(
    name = this.name ?: "",
    description = this.description ?: "",
    gender = this.gender.fromTransport(),
    visibility = this.visibility.fromTransport(),
)

private fun ProfileUpdateObject.toInternal(): DatingProfile = DatingProfile(
    id = this.id.tProfileId(),
    name = this.name ?: "",
    description = this.description ?: "",
    gender = this.gender.fromTransport(),
    visibility = this.visibility.fromTransport(),
)

private fun ProfileVisibility?.fromTransport(): DatingVisibility = when (this) {
    ProfileVisibility.PUBLIC -> DatingVisibility.VISIBLE_PUBLIC
    ProfileVisibility.OWNER_ONLY -> DatingVisibility.VISIBLE_TO_OWNER
    ProfileVisibility.REGISTERED_ONLY -> DatingVisibility.VISIBLE_TO_GROUP
    null -> DatingVisibility.NONE
}

private fun Gender?.fromTransport(): DatingGender = when (this) {
    Gender.FEMALE -> DatingGender.FEMALE
    Gender.MALE -> DatingGender.MALE
    null -> DatingGender.NONE
}

