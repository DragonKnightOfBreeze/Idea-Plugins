package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.psi.stubs.*

interface StellarisScriptPropertyStub: StubElement<StellarisScriptProperty> {
	val key:String
}

