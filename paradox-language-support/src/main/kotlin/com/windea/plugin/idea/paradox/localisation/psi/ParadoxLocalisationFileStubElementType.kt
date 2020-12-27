package com.windea.plugin.idea.paradox.localisation.psi

import com.intellij.lang.*
import com.intellij.lang.impl.*
import com.intellij.openapi.util.*
import com.intellij.openapi.vfs.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.intellij.util.*
import com.intellij.util.diff.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.*
import com.windea.plugin.idea.paradox.localisation.psi.ParadoxLocalisationTypes.*

class ParadoxLocalisationFileStubElementType : IStubFileElementType<PsiFileStub<*>>(ParadoxLocalisationLanguage){
	override fun getExternalId(): String {
		return "paradoxLocalisation.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	override fun shouldBuildStubFor(file: VirtualFile?): Boolean {
		return file?.paradoxRootType != null
	}
	
	class Builder: DefaultStubBuilder(){
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			//仅包括property
			val type = node.elementType
			return type != PROPERTY
		}
	}
}
