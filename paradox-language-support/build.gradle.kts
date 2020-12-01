import org.jetbrains.grammarkit.tasks.*

plugins {
	id("org.jetbrains.intellij") version "0.4.21"
	id("org.jetbrains.grammarkit") version "2020.1.2"
}

version = "0.1"

intellij {
	version = "2020.2"
	pluginName = "Paradox Language Support"
}

grammarKit {
	jflexRelease = "1.7.0-1"
	grammarKitRelease = "2020.1"
}

dependencies{
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect:1.4.0"))
}
