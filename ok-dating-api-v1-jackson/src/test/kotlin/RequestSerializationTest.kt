package com.amdev.api.v1

import com.amdev.dating.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {
    private val request = ProfileCreateRequest(
        requestId = "123",
        debug = ProfileDebug(
            mode = ProfileRequestDebugMode.STUB,
            stub = ProfileRequestDebugStubs.BAD_TITLE
        ),
        profile = ProfileCreateObject(
            name = "User name",
            description = "profile description",
            contact = "telegram",
            gender = Gender.FEMALE,
            visibility = ProfileVisibility.PUBLIC,
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"name\":\\s*\"User name\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badTitle\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as ProfileCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"requestId": "123"}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, ProfileCreateRequest::class.java)

        assertEquals("123", obj.requestId)
    }
}
