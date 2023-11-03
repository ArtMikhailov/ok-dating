plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":ok-dating-api-v1-jackson"))
    implementation(project(":ok-dating-common"))

    testImplementation(kotlin("test-junit"))
}