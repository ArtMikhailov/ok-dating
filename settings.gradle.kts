rootProject.name = "ok-dating"

pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion apply false
        id("org.openapi.generator") version openapiVersion apply false
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

include("ok-dating-acceptance")
include("ok-dating-api-v1-jackson")
include("ok-dating-common")
include("ok-dating-mappers-v1")