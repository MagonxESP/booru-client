
plugins {
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.serialization") version "1.9.0"
	`java-library`
	`maven-publish`
	signing
	id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.1.1"
}

group = "io.github.magonxesp"
version = "0.1.0"

publishing {
	publications {
		register<MavenPublication>("booru-client") {
			from(components["java"])

			pom {
				url = "https://github.com/magonxesp/booru-client"
				licenses {
					license {
						name = "MIT"
					}
				}
				developers {
					developer {
						name = "MagonxESP"
						url = "https://github.com/magonxesp"
					}
				}
				scm {
					url = "https://github.com/magonxesp/booru-client"
				}
			}
		}
	}
}

centralPortal {
	username = System.getenv("SONATYPE_USERNAME")
	password = System.getenv("SONATYPE_PASSWORD")
}

repositories {
	mavenCentral()
	maven { setUrl("https://jitpack.io") }
}

dependencies {
	val ktor_version: String by project
	val kotlin_serialization_version: String by project
	val kotlin_corroutines_version: String by project
	val kotest_version: String by project

	implementation("io.ktor:ktor-client-core:$ktor_version")
	implementation("io.ktor:ktor-client-cio:$ktor_version")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization_version")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_corroutines_version")
	testImplementation(kotlin("test"))
	testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(8)
}

