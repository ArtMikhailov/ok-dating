package com.amdev.dating.mappers.v1

import org.junit.Test
import com.amdev.dating.api.v1.models.*
import com.amdev.dating.common.DatingContext

import com.amdev.dating.common.models.*
import com.amdev.dating.common.stubs.DatingStubs

import kotlin.test.assertEquals

class MapperTest {
    @Test
    fun fromTransport() {
        val req = ProfileCreateRequest(
            requestId = "1234",
            debug = ProfileDebug(
                mode = ProfileRequestDebugMode.STUB,
                stub = ProfileRequestDebugStubs.SUCCESS,
            ),
            profile = ProfileCreateObject(
                name = "username",
                description = "desc",
                gender = Gender.MALE,
                visibility = ProfileVisibility.PUBLIC,
            ),
        )

        val context = DatingContext()
        context.fromTransport(req)

        assertEquals(DatingStubs.SUCCESS, context.stubCase)
        assertEquals(DatingWorkMode.STUB, context.workMode)
        assertEquals("username", context.profileRequest.name)
        assertEquals(DatingVisibility.VISIBLE_PUBLIC, context.profileRequest.visibility)
        assertEquals(DatingGender.MALE, context.profileRequest.gender)
    }

    @Test
    fun toTransport() {
        val context = DatingContext(
            requestId = DatingRequestId("1234"),
            command = DatingCommand.CREATE,
            profileResponse = DatingProfile(
                name = "username",
                description = "desc",
                gender = DatingGender.MALE,
                visibility = DatingVisibility.VISIBLE_PUBLIC,
            ),
            errors = mutableListOf(
                DatingError(
                    code = "err",
                    group = "request",
                    field = "username",
                    message = "wrong name",
                )
            ),
            state = DatingState.RUNNING,
        )

        val req = context.toTransportProfile() as ProfileCreateResponse

        assertEquals("1234", req.requestId)
        assertEquals("username", req.profile?.name)
        assertEquals("desc", req.profile?.description)
        assertEquals(ProfileVisibility.PUBLIC, req.profile?.visibility)
        assertEquals(Gender.MALE, req.profile?.gender)
        assertEquals(1, req.errors?.size)
        assertEquals("err", req.errors?.firstOrNull()?.code)
        assertEquals("request", req.errors?.firstOrNull()?.group)
        assertEquals("username", req.errors?.firstOrNull()?.field)
        assertEquals("wrong name", req.errors?.firstOrNull()?.message)
    }
}
