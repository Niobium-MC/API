import java.util.Properties

plugins {
    id("java")
    id("maven-publish")
}

group = "fr.sorway.niobium"
version = "1.0-beta.30"

val versionFile = file("version.txt")

if (!versionFile.exists()) {
    versionFile.writeText("1.0")
}

version = versionFile.readText().trim()

tasks.register("incrementVersion") {
    doLast {
        val parts = version.toString().split(".").toMutableList()
        val lastIndex = parts.lastIndex
        val minor = parts[lastIndex].toInt() + 1
        parts[lastIndex] = minor.toString()

        val newVersion = parts.joinToString(".")
        versionFile.writeText(newVersion)

        println("Version mise à jour vers : $newVersion")
    }
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    //Minecraft
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    //HikariCP
    implementation("com.zaxxer:HikariCP:5.1.0")

    //Message
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
}

//Encoding UTF-8
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>{
    options.encoding = "UTF-8"
}

tasks.withType<Jar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.getByName<Jar>("jar") {
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}

val reposiliteProps = Properties()
file("reposilite.properties").inputStream().use {
    reposiliteProps.load(it)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "Niobium-API"
        }
    }

    repositories {
        maven {
            name = "reposilite"
            url = uri("https://maven.sorway.fr/snapshots")
            credentials {
                username = reposiliteProps.getProperty("username")
                password = reposiliteProps.getProperty("password")
            }
        }
    }
}

tasks.named("build") {
    dependsOn("incrementVersion")
}

tasks.register("publishJar") {
    group = "publishing"
    description = "Build et publie le JAR sur Reposilite"

    dependsOn("build")
    finalizedBy("publish")
}
