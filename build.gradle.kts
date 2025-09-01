plugins {
    id("java")
    id("io.freefair.lombok") version "8.14"
    kotlin("jvm") version "1.9.22"  // Kotlin для build-скрипта
    id("io.qameta.allure") version "2.12.0"
    id ("maven-publish")
    `maven-publish`                // Плагин для публикации
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(platform("org.junit:junit-bom:5.10.0"))
    api("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    api("org.junit.jupiter:junit-jupiter")
    api("org.projectlombok:lombok:1.18.38")
    api("org.assertj:assertj-core:4.0.0-M1")
    api("io.rest-assured:rest-assured:5.5.5")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    api("io.rest-assured:json-path:5.4.0")
    api("io.rest-assured:xml-path:5.4.0")
    api("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    // JDBI BOM (Bill of Materials) для управления версиями
    api(platform("org.jdbi:jdbi3-bom:3.43.0"))

    // JDBI Core + расширения
    api("org.jdbi:jdbi3-core")
    api("org.jdbi:jdbi3-sqlobject")
    api("org.jdbi:jdbi3-jackson2")
    api("org.jdbi:jdbi3-postgres")

    // PostgreSQL JDBC драйвер
    api("org.postgresql:postgresql:42.7.2")

    // Jackson для JSON
    api("com.fasterxml.jackson.core:jackson-databind:2.17.1")

    // Поддержка Java 8 Date/Time API (JSR-310)
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.2")

    // JPA (Jakarta Persistence API)
    api("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Hibernate (реализация JPA)
    api("org.hibernate:hibernate-core:6.4.4.Final")


    // HikariCP (пул соединений, опционально)
    api("com.zaxxer:HikariCP:5.1.0")

    // Логирование (Hibernate использует SLF4J)
    api("org.slf4j:slf4j-api:2.0.12")
    api("ch.qos.logback:logback-classic:1.4.14")  // Реализация Logback
    api("io.qameta.allure:allure-junit5:2.29.1")
    api("io.qameta.allure:allure-assertj:2.29.1")
    api("io.qameta.allure:allure-junit5:2.29.1")
    api("io.qameta.allure:allure-assertj:2.29.1")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"]) // <- Всё ещё работает для Kotlin JVM!
        }
    }
}