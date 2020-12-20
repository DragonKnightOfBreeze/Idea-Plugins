package com.windea.plugin.idea.paradox.script.psi

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.paradox.script.*
import com.windea.plugin.idea.paradox.script.psi.ParadoxScriptTypes.*

class ParadoxScriptFileStubElementType : ILightStubFileElementType<PsiFileStub<*>>(ParadoxScriptLanguage) {
	override fun getExternalId(): String {
		return "paradoxScript.file"
	}
	
	override fun getBuilder(): LightStubBuilder {
		return Builder()
	}
	
	class Builder : LightStubBuilder() {
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			val type = node.elementType
			val parentType = parent.elementType
			return type != ROOT_BLOCK && (parentType != ROOT_BLOCK || type != VARIABLE && type != PROPERTY)
		}
	}
}
