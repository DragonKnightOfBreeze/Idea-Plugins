package com.windea.plugin.idea.paradox.localisation.psi

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.paradox.*

interface ParadoxLocalisationPropertyStub: StubElement<ParadoxLocalisationProperty> {
	val key:String
}

