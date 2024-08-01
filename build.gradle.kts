plugins {
	`java-library`
	id("io.freefair.lombok") version "8.6"
}

group = "com.contentgrid.observability"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.2"))
	implementation("org.springframework.boot:spring-boot-autoconfigure")
	implementation("io.pyroscope:agent:0.13.1")
	implementation("jakarta.annotation:jakarta.annotation-api")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
