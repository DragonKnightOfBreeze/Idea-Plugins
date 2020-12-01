package com.windea.plugin.idea.stellaris.script.psi

import com.windea.plugin.idea.stellaris.script.psi.*

@Suppress("UNUSED_PARAMETER")
interface StellarisScriptStubElementTypes {
	companion object {
		@JvmField val FILE = StellarisScriptFileStubElementType()
		@JvmField val PROPERTY = StellarisScriptPropertyStubElementType()
		@JvmField val VARIABLE = StellarisScriptVariableStubElementType()
		
		@JvmStatic fun getPropertyType(name:String) = PROPERTY
		@JvmStatic fun getVariableType(name:String) = VARIABLE
	}
}
