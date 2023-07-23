plugins {
    id("java")
}

group = "eu.virtusdevelops"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly(project(":core"))
}

