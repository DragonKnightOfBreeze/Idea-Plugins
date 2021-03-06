package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*

class StellarisScriptFileStubElementType : IStubFileElementType<PsiFileStub<*>>(StellarisScriptLanguage) {
	override fun getExternalId(): String {
		return "stellarisScript.file"
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
