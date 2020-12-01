package com.windea.plugin.idea.pdx.script.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.pdx.script.*
import com.windea.plugin.idea.pdx.script.psi.impl.*

class PdxScriptPropertyStubElementType() : ILightStubElementType<PdxScriptPropertyStub, PdxScriptProperty>(
	"STELLRAIS_SCRIPT_PROPERTY",
	PdxScriptLanguage
) {
	override fun createPsi(stub: PdxScriptPropertyStub): PdxScriptProperty {
		return PdxScriptPropertyImpl(stub, this)
	}
	
	override fun createStub(psi: PdxScriptProperty, parentStub: StubElement<*>): PdxScriptPropertyStub {
		return PdxScriptPropertyStubImpl(parentStub, psi.name.orEmpty())
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): PdxScriptPropertyStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, PdxScriptTypes.PROPERTY_KEY_ID)
		val key = intern(tree.charTable, keyNode)
		return PdxScriptPropertyStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "pdxScript.property"
	}
	
	override fun serialize(stub: PdxScriptPropertyStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): PdxScriptPropertyStub {
		return PdxScriptPropertyStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: PdxScriptPropertyStub, sink: IndexSink) {
		sink.occurrence(PdxScriptPropertyKeyIndex.key, stub.key)
	}
	
	companion object {
		fun intern(table: CharTable, node: LighterASTNode?): String {
			return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}


