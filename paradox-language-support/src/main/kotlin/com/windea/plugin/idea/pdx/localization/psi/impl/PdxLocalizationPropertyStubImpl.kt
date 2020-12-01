package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationPropertyStubImpl(
	parent: StubElement<*>,
	override val key: String
) : StubBase<StellarisLocalizationProperty>(parent, StellarisLocalizationStubElementTypes.PROPERTY), StellarisLocalizationPropertyStub

