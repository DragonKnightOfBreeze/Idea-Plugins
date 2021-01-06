package com.windea.plugin.idea.paradox.script.psi

import com.intellij.lang.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.*
import com.windea.plugin.idea.paradox.script.psi.ParadoxScriptTypes.*

class ParadoxScriptFileStubElementType : IStubFileElementType<PsiFileStub<*>>(ParadoxScriptLanguage) {
	override fun getExternalId(): String {
		return "paradoxScript.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	override fun shouldBuildStubFor(file: VirtualFile?): Boolean {
		return file?.paradoxFileInfo?.rootType != null
	}
	
	class Builder : DefaultStubBuilder() {
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			//仅包括顶级的variable和作为类型定义的property
			val type = node.elementType
			val parentType = parent.elementType
			return when {
				type == VARIABLE && parentType == ROOT_BLOCK -> true
				type == PROPERTY && parent.treeParent?.treeParent?.treeParent?.elementType == ROOT_BLOCK -> true
				type == VALUE && (parentType == BLOCK || parentType == ROOT_BLOCK) -> true
				else -> false
			}
		}
	}
}
