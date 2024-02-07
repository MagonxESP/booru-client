plugins {
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.serialization") version "1.9.0"
	`maven-publish`
}

group = "io.github.magonxesp"
version = "0.0.2"

publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/magonxesp/booru-client")
			credentials {
				username = System.getenv("GITHUB_USERNAME")
				password = System.getenv("GITHUB_TOKEN")
			}
		}
	}
	publications {
		register<MavenPublication>("gpr") {
			from(components["java"])
		}
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val ktor_version: String by project
	val kotlin_serialization_version: String by project
	val kotlin_corroutines_version: String by project
	val kotest_version: String by project
	val arrowkt_version: String by project

	implementation("io.ktor:ktor-client-core:$ktor_version")
	implementation("io.ktor:ktor-client-cio:$ktor_version")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlin_serialization_version")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_corroutines_version")
	implementation("io.arrow-kt:arrow-core:$arrowkt_version")
	implementation("io.arrow-kt:arrow-fx-coroutines:$arrowkt_version")
	testImplementation(kotlin("test"))
	testImplementation("io.kotest:kotest-runner-junit5:$kotest_version")
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(8)
}

