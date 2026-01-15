plugins {
    java
    application
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

application {
    mainClass.set("it.unibo.roguekong.Main")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

dependencies {
    // JavaFX (versione compatibile)
    val javafxVersion = "21"
    val javafxModules = listOf("base", "controls", "graphics")

    for (module in javafxModules) {
        implementation("org.openjfx:javafx-$module:$javafxVersion")
    }

    // Test (opzionale)
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}