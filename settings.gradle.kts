pluginManagement {
	repositories {
		maven("https://dl.bintray.com/kotlin/kotlin-eap")
		
		mavenCentral()
		
		maven("https://plugins.gradle.org/m2/")
	}
}
rootProject.name = "Idea-Plugins"
include(
"stellaris-modding-support",
"yaml-extension"
)
include("untitled")
