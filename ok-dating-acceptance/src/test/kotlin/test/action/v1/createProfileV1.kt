package com.amdev.blackbox.test.action.v1

import io.kotest.assertions.withClue
import io.kotest.matchers.should
import com.amdev.blackbox.fixture.client.Client

suspend fun Client.createProfile(): Unit =
    withClue("createProfileV1") {
        val response = sendAndReceiveV1(
            "profile/create", """
                {
                    "name": "Alex"
                }
            """.trimIndent()
        )

        response should haveNoErrors
    }
