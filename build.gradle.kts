plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "tech.indus340"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("io.github.sashirestela:simple-openai:3.8.0") {
		exclude("org.slf4j","slf4j-simple")
	}
// https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
	testImplementation("ch.qos.logback:logback-classic:1.5.8")

	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
	implementation("dev.langchain4j:langchain4j:0.34.0")
	implementation("dev.langchain4j:langchain4j-open-ai:0.34.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
