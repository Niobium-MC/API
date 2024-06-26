plugins {
    id("java")
    id("maven-publish")
}

group = "fr.sorway.niobium"
version = "1.0-beta.30"

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

tasks.getByName<Jar>("jar") {
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

tasks.test {
    useJUnitPlatform()
}