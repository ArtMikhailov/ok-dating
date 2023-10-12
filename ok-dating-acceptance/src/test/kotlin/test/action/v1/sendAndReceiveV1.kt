package com.amdev.blackbox.test.action.v1

import mu.KotlinLogging
import com.amdev.blackbox.fixture.client.Client

private val log = KotlinLogging.logger {}

suspend fun Client.sendAndReceiveV1(path: String, requestBody: String): String {
    log.info { "Send to v1/$path\n$requestBody" }

    val responseBody = sendAndReceiveV1("v1", path, requestBody)
    log.info { "Received\n$responseBody" }

    return responseBody
}