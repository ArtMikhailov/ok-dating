package com.amdev.api.v1

import com.amdev.dating.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response = ProfileCreateResponse(
        requestId = "123",
        profile = ProfileResponseObject(
            name = "user name",
            description = "profile descriptoin",
            contact = "whats up",
            gender = Gender.MALE,
            visibility = ProfileVisibility.PUBLIC,
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"name\":\\s*\"user name\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as ProfileCreateResponse

        assertEquals(response, obj)
    }
}
