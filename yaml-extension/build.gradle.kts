plugins {
	id("org.jetbrains.intellij") version "0.4.21"
}

version = "1.0.0"

sourceSets {
	main {
		java.srcDir("src/main/gen")
	}
}

dependencies {
	implementation(kotlin("stdlib"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
	version = "2020.1.2"
}

tasks {
	compileKotlin {
		incremental = true
		javaPackagePrefix = "com.windea.plugin.idea.yamlext"
		kotlinOptions.jvmTarget = "11"
	}
	compileTestKotlin {
		incremental = true
		javaPackagePrefix = "com.windea.plugin.idea.yamlext"
		kotlinOptions.jvmTarget = "11"
	}
	patchPluginXml {
		changeNotes("""
        Add change notes here.<br>
        <em>most HTML tags may be used</em>
        """.trimIndent())
	}
}
