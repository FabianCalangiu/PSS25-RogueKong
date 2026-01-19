plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

javafx {
    version = "25"
    modules = listOf("javafx.controls", "javafx.fxml", "javafx.graphics")
}

application {
    mainClass.set("it.unibo.roguekong.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "it.unibo.roguekong.Main"
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}