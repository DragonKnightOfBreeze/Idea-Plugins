package com.windea.plugin.idea.stellaris.script.psi

import com.windea.plugin.idea.stellaris.script.psi.*

interface StellarisScriptStubElementTypes {
	companion object {
		@JvmField val FILE = StellarisScriptFileStubElementType()
		@JvmField val ROOT_BLOCK = StellarisScriptRootBlockStubElementType()
		@JvmField val PROPERTY = StellarisScriptPropertyStubElementType()
		@JvmField val VARIABLE = StellarisScriptVariableStubElementType()
		
		@JvmStatic fun getRootBlockType(name:String) = ROOT_BLOCK
		@JvmStatic fun getPropertyType(name:String) = PROPERTY
		@JvmStatic fun getVariableType(name:String) = VARIABLE
	}
}
