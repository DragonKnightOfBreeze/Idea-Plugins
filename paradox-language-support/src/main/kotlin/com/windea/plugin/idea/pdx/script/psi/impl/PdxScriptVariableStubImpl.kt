package com.windea.plugin.idea.pdx.script.psi.impl

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.pdx.script.psi.*

class PdxScriptVariableStubImpl(
	parent: StubElement<*>,
	override val key: String
) : StubBase<PdxScriptVariable>(parent, PdxScriptStubElementTypes.VARIABLE), PdxScriptVariableStub

