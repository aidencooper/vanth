import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
	java
	id("org.springframework.boot") version "4.1.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "net.aidencooper.vanth.media"
version = "0.0.1-SNAPSHOT"

java {
  	toolchain {
    	languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// JDBC
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc-test")

	// JUnit
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Lombok
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")

	// MapStruct
	annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
	implementation ("org.mapstruct:mapstruct:1.6.3")

	// Rest Client
	implementation("org.springframework.boot:spring-boot-starter-restclient")
	testImplementation("org.springframework.boot:spring-boot-starter-restclient-test")

	// SQLite
	runtimeOnly("org.xerial:sqlite-jdbc")

	// Web
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
}

tasks.withType<BootRun> {
	jvmArgs(
		"--enable-native-access=ALL-UNNAMED",
		"--sun-misc-unsafe-memory-access=allow"
	)
}

val testDatabase = layout.buildDirectory.file("test-vanth-media.db")
tasks.withType<Test> {
	doFirst { testDatabase.get().asFile.delete() }
  	useJUnitPlatform()
}