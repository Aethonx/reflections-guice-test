plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.1"
}

group = "io.shaded.plugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation("com.google.inject:guice:5.0.1")
    implementation("org.reflections:reflections:0.10.2")
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}