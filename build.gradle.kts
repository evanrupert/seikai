import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.flywaydb.gradle.task.FlywayMigrateTask

plugins {
	id("org.springframework.boot") version "2.2.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"

    id("org.flywaydb.flyway") version "6.3.1"

	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "io.studioreach"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
    jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.postgresql:postgresql:42.2.2")
    implementation("org.jetbrains.exposed", "exposed-core", "0.22.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.22.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

defaultTasks("bootRun")

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.register<FlywayMigrateTask>("migratedb") {
    url = "jdbc:postgresql://localhost:5432/seikai"
    user = "postgres"
    password = "password"
    locations = arrayOf("filesystem:migrations")
}

