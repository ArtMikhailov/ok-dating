package ru.otus.otuskotlin.marketplace.blackbox.docker

import com.amdev.blackbox.fixture.docker.AbstractDockerCompose

object WiremockDockerCompose : AbstractDockerCompose(
    "app-wiremock_1", 8080, "wiremock/docker-compose-wiremock.yml"
)