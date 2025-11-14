plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.intellij:forms_rt:7.0.3")
}

application {
    mainClass.set("chinese.checkers.Main")
}

tasks.test {
    useJUnitPlatform()
}