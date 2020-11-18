package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.psi.impl.*

class StellarisScriptVariableStubElementType() : ILightStubElementType<StellarisScriptVariableStub, StellarisScriptVariable>(
	"STELLRAIS_SCRIPT_VARIABLE",
	StellarisScriptLanguage
) {
	override fun createPsi(stub: StellarisScriptVariableStub): StellarisScriptVariable {
		return StellarisScriptVariableImpl(stub, this)
	}
	
	override fun createStub(psi: StellarisScriptVariable, parentStub: StubElement<*>): StellarisScriptVariableStub {
		return StellarisScriptVariableStubImpl(parentStub, psi.name.orEmpty())
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): StellarisScriptVariableStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, StellarisScriptTypes.VARIABLE_NAME_ID)
		val key = intern(tree.charTable, keyNode)
		return StellarisScriptVariableStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "stellarisScript.variable"
	}
	
	override fun serialize(stub: StellarisScriptVariableStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): StellarisScriptVariableStub {
		return StellarisScriptVariableStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: StellarisScriptVariableStub, sink: IndexSink) {
		sink.occurrence(StellarisScriptVariableKeyIndex.key,stub.key)
	}
	
	companion object{
		fun intern(table: CharTable,node: LighterASTNode?):String{
			return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}
