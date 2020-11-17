package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.psi.tree.*

interface StellarisLocalizationStubElementTypes {
	companion object {
		@JvmField val FILE = StellarisLocalizationStubFileElementType()
		
		@JvmField val PROPERTY = StellarisLocalizationPropertyStubElementType()
		
		@JvmField val PROPERTIES = TokenSet.create(PROPERTY);
	}
}
