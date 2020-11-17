package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.*

interface StellarisLocalizationPropertyStub: StubElement<StellarisLocalizationProperty> {
	val key:String
}

