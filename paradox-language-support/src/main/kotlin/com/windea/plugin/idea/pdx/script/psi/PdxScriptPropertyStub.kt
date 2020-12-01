package com.windea.plugin.idea.pdx.script.psi

import com.intellij.psi.stubs.*

interface PdxScriptPropertyStub: StubElement<PdxScriptProperty> {
	val key:String
}

