package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptVariableStubImpl(
	parent: StubElement<*>,
	override val key: String
) : StubBase<StellarisScriptVariable>(parent, StellarisScriptStubElementTypes.VARIABLE), StellarisScriptVariableStub
