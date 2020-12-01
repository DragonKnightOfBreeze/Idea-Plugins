package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.localisation.psi.impl.*

class PdxLocalisationPropertyStubElementType : ILightStubElementType<PdxLocalisationPropertyStub, PdxLocalisationProperty>(
	"STELLRAIS_LOCALISATION_PROPERTY",
	PdxLocalisationLanguage
) {
	override fun createPsi(stub: PdxLocalisationPropertyStub): PdxLocalisationProperty {
		return PdxLocalisationPropertyImpl(stub, this)
	}
	
	override fun createStub(psi: PdxLocalisationProperty, parentStub: StubElement<*>): PdxLocalisationPropertyStub {
		return PdxLocalisationPropertyStubImpl(parentStub, psi.name.orEmpty())
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): PdxLocalisationPropertyStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, PdxLocalisationTypes.PROPERTY_KEY_ID)
		val key = intern(tree.charTable, keyNode)
		return PdxLocalisationPropertyStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "pdxLocalisation.property"
	}
	
	override fun serialize(stub: PdxLocalisationPropertyStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): PdxLocalisationPropertyStub {
		return PdxLocalisationPropertyStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: PdxLocalisationPropertyStub, sink: IndexSink) {
		sink.occurrence(PdxLocalisationPropertyKeyIndex.key,stub.key)
	}
	
	companion object{
		fun intern(table: CharTable,node: LighterASTNode?):String{
		return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}
