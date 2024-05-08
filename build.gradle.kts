plugins {
    id("java")
    id("maven-publish")
}

group = "fr.sorway"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Niobium-MC/API")
            credentials {
                username = project.findProject("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProject("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }

    publications {
        register("jar", MavenPublication::class) {
            artifactId = "niobium-api"
            from(components["java"])
            pom {
                url.set("https://github.com/Niobium-MC/API.git")
            }
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(20))
}

tasks.test {
    useJUnitPlatform()
}