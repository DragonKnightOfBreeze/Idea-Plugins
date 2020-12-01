package com.windea.plugin.idea.pdx.script.psi

import com.windea.plugin.idea.pdx.script.psi.*

@Suppress("UNUSED_PARAMETER")
interface PdxScriptStubElementTypes {
	companion object {
		@JvmField val FILE = PdxScriptFileStubElementType()
		@JvmField val PROPERTY = PdxScriptPropertyStubElementType()
		@JvmField val VARIABLE = PdxScriptVariableStubElementType()
		
		@JvmStatic fun getPropertyType(name:String) = PROPERTY
		@JvmStatic fun getVariableType(name:String) = VARIABLE
	}
}
