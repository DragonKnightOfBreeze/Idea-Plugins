package com.windea.plugin.idea.pdx.script.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.pdx.script.*
import com.windea.plugin.idea.pdx.script.psi.impl.*

class PdxScriptVariableStubElementType() : IStubElementType<PdxScriptVariableStub, PdxScriptVariable>(
	"STELLRAIS_SCRIPT_VARIABLE",
	PdxScriptLanguage
) {
	override fun createPsi(stub: PdxScriptVariableStub): PdxScriptVariable {
		return PdxScriptVariableImpl(stub, this)
	}
	
	override fun createStub(psi: PdxScriptVariable, parentStub: StubElement<*>): PdxScriptVariableStub {
		return PdxScriptVariableStubImpl(parentStub, psi.name.orEmpty())
	}
	
	//override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): PdxScriptVariableStub {
	//	val keyNode = LightTreeUtil.firstChildOfType(tree, node, PdxScriptTypes.VARIABLE_NAME_ID)
	//	val key = intern(tree.charTable, keyNode)
	//	return PdxScriptVariableStubImpl(parentStub, key)
	//}
	
	override fun getExternalId(): String {
		return "pdxScript.variable"
	}
	
	override fun serialize(stub: PdxScriptVariableStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): PdxScriptVariableStub {
		return PdxScriptVariableStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: PdxScriptVariableStub, sink: IndexSink) {
		sink.occurrence(PdxScriptVariableKeyIndex.key,stub.key)
	}
	
	companion object{
		fun intern(table: CharTable,node: LighterASTNode?):String{
			return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}
