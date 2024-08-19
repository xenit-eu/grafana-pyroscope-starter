plugins {
	`java-library`
	`maven-publish`
	`signing`
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

publishing {
	publications {
		create<MavenPublication>("library") {
			from(components["java"])

			pom {
				url.set("https://github.com/xenit-eu/grafana-pyroscope-starter")
				name.set(project.name)
				description.set(project.description)

				scm {
					connection.set("scm:git:git@github.com:xenit-eu/grafana-pyroscope-starter.git")
					developerConnection.set("scm:git:git@github.com:xenit-eu/grafana-pyroscope-starter.git")
					url.set("https://github.com/xenit-eu/grafana-pyroscope-starter.git")
				}

				developers {
					developer {
						name.set("XeniT")
						organization.set("XeniT Solutions NV")
					}
				}

				licenses {
					license {
						name.set("The Apache License, Version 2.0")
						url.set("https://www.apache.org/licenses/LICENSE-2.0")
					}
				}
			}

			versionMapping {
				usage("java-api") {
					fromResolutionResult()
				}
				usage("java-runtime") {
					fromResolutionResult()
				}
			}
		}
	}

	repositories {
		maven {
			name = if (project.version.toString().endsWith("-SNAPSHOT")) {
				"sonatypeSnapshots"
			} else {
				"sonatypeMavenCentral"
			}

			url = uri(
				if (project.version.toString().endsWith("-SNAPSHOT")) {
					"https://s01.oss.sonatype.org/content/repositories/snapshots/"
				} else {
					"https://s01.oss.sonatype.org/service/local/"
				}
			)

			credentials {
				username = project.findProperty("sonatype_username") as String?
				password = project.findProperty("sonatype_password") as String?
			}
		}
	}
}

tasks.named("check") {
	dependsOn("checkMavenCentralRequirements")
}
