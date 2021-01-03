package com.windea.plugin.idea.paradox.script.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.*
import com.windea.plugin.idea.paradox.script.psi.impl.*

class ParadoxScriptPropertyStubElementType : ILightStubElementType<ParadoxScriptPropertyStub, ParadoxScriptProperty>(
	"PARADOX_SCRIPT_PROPERTY",
	ParadoxScriptLanguage
) {
	override fun createPsi(stub: ParadoxScriptPropertyStub): ParadoxScriptProperty {
		return ParadoxScriptPropertyImpl(stub, this)
	}
	
	override fun createStub(psi: ParadoxScriptProperty, parentStub: StubElement<*>): ParadoxScriptPropertyStub {
		//这里使用scriptProperty.paradoxTypeMetadata.name而非scriptProperty.name
		return ParadoxScriptPropertyStubImpl(parentStub, psi.paradoxTypeMetadata?.name ?: psi.name)
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): ParadoxScriptPropertyStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, ParadoxScriptTypes.PROPERTY_KEY_ID)
		val key = intern(tree.charTable, keyNode)
		return ParadoxScriptPropertyStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "paradoxScript.property"
	}
	
	override fun serialize(stub: ParadoxScriptPropertyStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): ParadoxScriptPropertyStub {
		return ParadoxScriptPropertyStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: ParadoxScriptPropertyStub, sink: IndexSink) {
		sink.occurrence(ParadoxScriptPropertyKeyIndex.key, stub.key)
	}
	
	companion object {
		fun intern(table: CharTable, node: LighterASTNode?): String {
			return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}


