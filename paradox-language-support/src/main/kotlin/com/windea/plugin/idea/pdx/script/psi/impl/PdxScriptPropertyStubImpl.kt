package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptPropertyStubImpl(
	parent: StubElement<*>,
	override val key: String
) : StubBase<StellarisScriptProperty>(parent, StellarisScriptStubElementTypes.PROPERTY), StellarisScriptPropertyStub

