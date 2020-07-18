plugins {
	java
	kotlin("jvm") version "1.3.72"
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
		maven("https://maven.aliyun.com/nexus/content/groups/public")
		mavenCentral()
		jcenter()
	}
}

tasks {
	compileKotlin {
		incremental = true
		javaPackagePrefix = "com.windea.plugin.idea"
		kotlinOptions.jvmTarget = "11"
	}
	compileTestKotlin {
		incremental = true
		javaPackagePrefix = "com.windea.plugin.idea"
		kotlinOptions.jvmTarget = "11"
	}
}


