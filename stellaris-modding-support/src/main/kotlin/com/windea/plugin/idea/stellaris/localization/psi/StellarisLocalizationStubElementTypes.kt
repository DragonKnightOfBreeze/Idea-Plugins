package com.windea.plugin.idea.stellaris.localization.psi

@Suppress("UNUSED_PARAMETER")
interface StellarisLocalizationStubElementTypes {
	companion object {
		@JvmField val FILE = StellarisLocalizationFileStubElementType()
		@JvmField val PROPERTY = StellarisLocalizationPropertyStubElementType()
		
		@JvmStatic fun getPropertyType(name:String) = PROPERTY
	}
}
