plugins {
	id("org.jetbrains.intellij") version "0.4.21"
	id("org.jetbrains.grammarkit") version "2020.1.2"
}

version = "1.0"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
	version = "2020.2"
	pluginName = "BBCode"
}

grammarKit {
	// version of IntelliJ patched JFlex (see bintray link below), Default is 1.7.0-1
	jflexRelease = "1.7.0-1"
	// tag or short commit hash of Grammar-Kit to use (see link below). Default is 2020.1
	grammarKitRelease = "2020.1"
}
