package com.amdev.blackbox.test

import io.kotest.core.spec.style.FunSpec
import com.amdev.blackbox.fixture.client.Client
import com.amdev.blackbox.test.action.v1.createProfile

fun FunSpec.testApiV1(client: Client) {
    context("v1") {
        test("Create Profile ok") {
            client.createProfile()
        }
    }
}