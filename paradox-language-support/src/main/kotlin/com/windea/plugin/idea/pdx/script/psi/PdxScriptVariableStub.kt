package com.windea.plugin.idea.pdx.script.psi

import com.intellij.psi.stubs.*

interface PdxScriptVariableStub: StubElement<PdxScriptVariable> {
	val key:String
}
