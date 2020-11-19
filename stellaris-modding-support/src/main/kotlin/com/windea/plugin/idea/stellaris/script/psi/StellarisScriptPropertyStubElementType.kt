package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.psi.impl.*

class StellarisScriptPropertyStubElementType() : ILightStubElementType<StellarisScriptPropertyStub, StellarisScriptProperty>(
	"STELLRAIS_SCRIPT_PROPERTY",
	StellarisScriptLanguage
) {
	override fun createPsi(stub: StellarisScriptPropertyStub): StellarisScriptProperty {
		return StellarisScriptPropertyImpl(stub, this)
	}
	
	override fun createStub(psi: StellarisScriptProperty, parentStub: StubElement<*>): StellarisScriptPropertyStub {
		return StellarisScriptPropertyStubImpl(parentStub, psi.name.orEmpty())
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): StellarisScriptPropertyStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, StellarisScriptTypes.PROPERTY_KEY_ID)
		val key = intern(tree.charTable, keyNode)
		return StellarisScriptPropertyStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "stellarisScript.property"
	}
	
	override fun serialize(stub: StellarisScriptPropertyStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): StellarisScriptPropertyStub {
		return StellarisScriptPropertyStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: StellarisScriptPropertyStub, sink: IndexSink) {
		sink.occurrence(StellarisScriptPropertyKeyIndex.key, stub.key)
	}
	
	companion object {
		fun intern(table: CharTable, node: LighterASTNode?): String {
			return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}


