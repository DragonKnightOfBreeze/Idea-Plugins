package com.windea.plugin.idea.pdx.localisation.psi

@Suppress("UNUSED_PARAMETER")
interface PdxLocalisationStubElementTypes {
	companion object {
		@JvmField val FILE = PdxLocalisationFileStubElementType()
		@JvmField val PROPERTY = PdxLocalisationPropertyStubElementType()
		
		@JvmStatic fun getPropertyType(name:String) = PROPERTY
	}
}
