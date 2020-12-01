package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.pdx.*

interface PdxLocalisationPropertyStub: StubElement<PdxLocalisationProperty> {
	val key:String
}

