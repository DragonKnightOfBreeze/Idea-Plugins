package com.windea.plugin.idea.pdx.localisation.psi.impl

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationPropertyStubImpl(
	parent: StubElement<*>,
	override val key: String
) : StubBase<PdxLocalisationProperty>(parent, PdxLocalisationStubElementTypes.PROPERTY), PdxLocalisationPropertyStub

