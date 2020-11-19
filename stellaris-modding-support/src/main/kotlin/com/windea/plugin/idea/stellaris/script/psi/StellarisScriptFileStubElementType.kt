package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.lang.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.windea.plugin.idea.stellaris.script.*

class StellarisScriptFileStubElementType : IStubFileElementType<PsiFileStub<*>>(StellarisScriptLanguage){
	override fun getExternalId(): String {
		return "stellarisScript.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	class Builder: DefaultStubBuilder(){
		//override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
		//	return parent.elementType != StellarisScriptTypes.ROOT_BLOCK &&
		//	       (node.elementType != StellarisScriptTypes.VARIABLE || node.elementType != StellarisScriptTypes.PROPERTY)
		//}
	}
}
