plugins {
    id("java")
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.6"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.compileJava {
    options.release.set(21)
}

group = "me.keehl"
version = "1.4-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(project(":core"))
    implementation(project(":paper"))
    implementation(project(":spigot"))

    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}

tasks.shadowJar {
    archiveClassifier.set("") // make it the main jar name
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["shadow"])
            artifactId = "dialog-builder"
        }
    }

    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("../build/repo"))
        }
    }
}

tasks.test {
    useJUnitPlatform()
}