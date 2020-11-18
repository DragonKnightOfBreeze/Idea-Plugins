package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.lang.*
import com.intellij.psi.impl.source.tree.*
import com.intellij.psi.stubs.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.localization.psi.impl.*

class StellarisLocalizationPropertyStubElementType() : ILightStubElementType<StellarisLocalizationPropertyStub, StellarisLocalizationProperty>(
	"STELLRAIS_LOCALIZATION_PROPERTY",
	StellarisLocalizationLanguage
) {
	override fun createPsi(stub: StellarisLocalizationPropertyStub): StellarisLocalizationProperty {
		return StellarisLocalizationPropertyImpl(stub, this)
	}
	
	override fun createStub(psi: StellarisLocalizationProperty, parentStub: StubElement<*>): StellarisLocalizationPropertyStub {
		return StellarisLocalizationPropertyStubImpl(parentStub, psi.name.orEmpty())
	}
	
	override fun createStub(tree: LighterAST, node: LighterASTNode, parentStub: StubElement<*>): StellarisLocalizationPropertyStub {
		val keyNode = LightTreeUtil.firstChildOfType(tree, node, StellarisLocalizationTypes.PROPERTY_KEY_ID)
		val key = intern(tree.charTable, keyNode)
		return StellarisLocalizationPropertyStubImpl(parentStub, key)
	}
	
	override fun getExternalId(): String {
		return "stellarisLocalization.property"
	}
	
	override fun serialize(stub: StellarisLocalizationPropertyStub, dataStream: StubOutputStream) {
		dataStream.writeName(stub.key)
	}
	
	override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): StellarisLocalizationPropertyStub {
		return StellarisLocalizationPropertyStubImpl(parentStub, dataStream.readNameString()!!)
	}
	
	override fun indexStub(stub: StellarisLocalizationPropertyStub, sink: IndexSink) {
		sink.occurrence(StellarisLocalizationPropertyKeyIndex.key,stub.key)
	}
	
	companion object{
		fun intern(table: CharTable,node: LighterASTNode?):String{
		return table.intern((node as LighterASTTokenNode).text).toString()
		}
	}
}
