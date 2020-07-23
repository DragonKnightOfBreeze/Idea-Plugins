import org.jetbrains.grammarkit.tasks.*

plugins {
	id("org.jetbrains.intellij") version "0.4.21"
	id("org.jetbrains.grammarkit") version "2020.2.1"
}

version = "1.5"

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
	pluginName = "Stellaris Modding Support"
}

grammarKit {
	// version of IntelliJ patched JFlex (see bintray link below), Default is 1.7.0-1
	jflexRelease = "1.7.0-1"
	// tag or short commit hash of Grammar-Kit to use (see link below). Default is 2020.1
	grammarKitRelease = "2020.1"
}

tasks {
	compileKotlin {
		kotlinOptions.jvmTarget = "11"
	}
	compileTestKotlin {
		kotlinOptions.jvmTarget = "11"
	}
	register<GenerateParser>("generateParser") {
		// source bnf file
		source = "src/main/kotlin/com/windea/plugin/idea/stellaris/StellarisLocalization.bnf"
		// optional, task-specific root for the generated files. Default: none
		targetRoot = "src/main/gen"
		// path to a parser file, relative to the targetRoot
		pathToParser = "com/windea/plugin/idea/stellaris/psi/StellarisLocalizationParser.java"
		// path to a directory with generated psi files, relative to the targetRoot
		pathToPsiRoot = "com/windea/plugin/idea/stellaris/psi"
		// if set, plugin will remove a parser output file and psi output directory before generating new ones. Default: false
		purgeOldFiles = true
	}
	register<GenerateLexer>("generateLexer") {
		// source flex file
		source = "src/main/kotlin/com/windea/plugin/idea/stellaris/StellarisLocalization.flex"
		// target directory for lexer
		targetDir = "src/main/gen/com/windea/plugin/idea/stellaris/psi/"
		// target classname, target file will be targetDir/targetClass.java
		targetClass = "StellarisLocalizationLexer"
		// optional, path to the task-specific skeleton file. Default: none
		skeleton = "/idea-flex.skeleton"
		// if set, plugin will remove a lexer output file before generating new one. Default: false
		purgeOldFiles = true
	}
}
