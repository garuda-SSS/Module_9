plugins {
    id("java")
    id("io.freefair.lombok") version "8.14"
    kotlin("jvm") version "1.9.22"  // Kotlin для build-скрипта
    id("io.qameta.allure") version "2.12.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.projectlombok:lombok:1.18.38")
    testImplementation("org.assertj:assertj-core:4.0.0-M1")
    implementation("io.rest-assured:rest-assured:5.5.5")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("io.rest-assured:json-path:5.4.0")
    testImplementation("io.rest-assured:xml-path:5.4.0")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    // JDBI BOM (Bill of Materials) для управления версиями
    implementation(platform("org.jdbi:jdbi3-bom:3.43.0"))

    // JDBI Core + расширения
    implementation("org.jdbi:jdbi3-core")
    implementation("org.jdbi:jdbi3-sqlobject")
    implementation("org.jdbi:jdbi3-jackson2")
    implementation("org.jdbi:jdbi3-postgres")

    // PostgreSQL JDBC драйвер
    implementation("org.postgresql:postgresql:42.7.2")

    // Jackson для JSON
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")

    // Поддержка Java 8 Date/Time API (JSR-310)
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.19.2")

    // JPA (Jakarta Persistence API)
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Hibernate (реализация JPA)
    implementation("org.hibernate:hibernate-core:6.4.4.Final")

    // Драйвер БД (PostgreSQL)
    implementation("org.postgresql:postgresql:42.7.2")

    // HikariCP (пул соединений, опционально)
    implementation("com.zaxxer:HikariCP:5.1.0")

    // Логирование (Hibernate использует SLF4J)
    implementation("org.slf4j:slf4j-api:2.0.12")
    implementation("ch.qos.logback:logback-classic:1.4.14")  // Реализация Logback
    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-assertj:2.29.1")
    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    implementation("io.qameta.allure:allure-assertj:2.29.1")
}

tasks.test {
    useJUnitPlatform()
}