package com.windea.plugin.idea.paradox.library

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.paradox.script.psi.*
import java.io.*

class StellarisLibraryProvider :PlatformPrebuiltStubsProviderBase() {
	override val dirName: String = "stellaris"
	override val stubVersion: Int =ParadoxScriptStubElementTypes.FILE.stubVersion
}