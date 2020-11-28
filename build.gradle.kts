plugins {
	java
	kotlin("jvm") version "1.4.0"
}

allprojects {
	group = "com.windea"

	apply {
		plugin("java")
		plugin("org.jetbrains.kotlin.jvm")
	}

	buildscript {
		repositories {
			maven("https://maven.aliyun.com/nexus/content/groups/public")
			mavenCentral()
			jcenter()
		}
	}

	repositories {
		maven("https://dl.bintray.com/kotlin/kotlin-eap")
		maven("https://maven.aliyun.com/nexus/content/groups/public")
		mavenCentral()
		jcenter()
	}

	dependencies {
		implementation(kotlin("stdlib"))
		implementation(kotlin("reflect:1.4.0"))
	}

	sourceSets {
		main {
			java.srcDir("src/main/gen")
			java.srcDir("src/main/kotlin")
		}
	}

	tasks {
		compileKotlin {
			incremental = true
			//javaPackagePrefix = "com.windea.plugin.idea"
			kotlinOptions.jvmTarget = "11"
		}
		compileTestKotlin {
			incremental = true
			//javaPackagePrefix = "com.windea.plugin.idea"
			kotlinOptions.jvmTarget = "11"
		}
	}
}




