plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.5.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.github.patrick.remapper") version "1.4.0"
}

group = "eu.virtusdevelops"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    paperweight.paperDevBundle("1.19.4-R0.1-SNAPSHOT")
    compileOnly(project(":core"))
    implementation(project(":nms:_common"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks{
    remap{
        archiveClassifier.set("remapped")
        version.set("1.19.4")
        inputTask.set(shadowJar)
    }
}