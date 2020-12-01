package com.windea.plugin.idea.pdx.script.psi

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.pdx.script.*
import com.windea.plugin.idea.pdx.script.psi.PdxScriptTypes.*

class PdxScriptFileStubElementType : IStubFileElementType<PsiFileStub<*>>(PdxScriptLanguage) {
	override fun getExternalId(): String {
		return "pdxScript.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	class Builder : DefaultStubBuilder() {
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			val type = node.elementType
			val parentType = parent.elementType
			return type != ROOT_BLOCK && (parentType != ROOT_BLOCK || type != VARIABLE && type != PROPERTY)
		}
	}
}
