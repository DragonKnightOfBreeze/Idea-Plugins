package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.psi.stubs.*

interface StellarisScriptVariableStub: StubElement<StellarisScriptVariable> {
	val key:String
}
