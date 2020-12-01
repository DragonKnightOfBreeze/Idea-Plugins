package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.lang.*
import com.intellij.lang.impl.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.intellij.util.*
import com.intellij.util.diff.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationTypes.*

class PdxLocalisationFileStubElementType : IStubFileElementType<PsiFileStub<*>>(PdxLocalisationLanguage){
	override fun getExternalId(): String {
		return "pdxLocalisation.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	class Builder: DefaultStubBuilder(){
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			return node.elementType != PROPERTY
		}
	}
}
