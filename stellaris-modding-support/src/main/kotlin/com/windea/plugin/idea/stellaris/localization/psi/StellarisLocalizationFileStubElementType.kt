package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.lang.*
import com.intellij.lang.impl.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.intellij.util.*
import com.intellij.util.diff.*
import com.windea.plugin.idea.stellaris.localization.*

class StellarisLocalizationFileStubElementType : IStubFileElementType<PsiFileStub<*>>(StellarisLocalizationLanguage){
	override fun getExternalId(): String {
		return "stellarisLocalization.file"
	}
	
	override fun getBuilder(): StubBuilder {
		return Builder()
	}
	
	class Builder: DefaultStubBuilder(){
		override fun skipChildProcessingWhenBuildingStubs(parent: ASTNode, node: ASTNode): Boolean {
			return node.elementType != StellarisLocalizationTypes.PROPERTY
		}
	}
}

//{
//	override fun parseContentsLight(chameleon: ASTNode): FlyweightCapableTreeStructure<LighterASTNode?>? {
//		val psi = chameleon.psi ?: error("Bad chameleon: $chameleon")
//		val project = psi.project
//		val factory = PsiBuilderFactory.getInstance()
//		val builder = factory.createBuilder(project, chameleon)
//		val parser = Parser()
//		parser.parseLight(this, builder)
//		return builder.lightTree
//	}
//
//	companion object {
//		private val MATCH_BY_KEY = TripleFunction { oldNode: ASTNode, newNode: LighterASTNode, structure: FlyweightCapableTreeStructure<LighterASTNode> ->
//			if(oldNode.elementType === StellarisLocalizationTypes.PROPERTY) {
//				val oldName = oldNode.findChildByType(StellarisLocalizationTypes.PROPERTY_KEY_ID)
//				if(oldName != null) {
//					val oldNameStr = oldName.chars
//					val newNameStr = findKeyCharacters(newNode, structure)
//					if(!Comparing.equal(oldNameStr, newNameStr)) {
//						return@TripleFunction ThreeState.NO
//					}
//				}
//			}
//			ThreeState.UNSURE
//		}
//
//		private fun findKeyCharacters(newNode: LighterASTNode, structure: FlyweightCapableTreeStructure<LighterASTNode>): CharSequence? {
//			val childrenRef: Ref<Array<LighterASTNode>> = Ref.create(null)
//			val childrenCount = structure.getChildren(newNode, childrenRef)
//			val children = childrenRef.get() as Array<LighterASTNode>
//			try {
//				val nodes = children
//				val size = children.size
//				for(i in 0 until size) {
//					val node = nodes[i]
//					if(node.tokenType === StellarisLocalizationTypes.PROPERTY_KEY) {
//						return (node as LighterASTTokenNode).text
//					}
//				}
//			} finally {
//				structure.disposeChildren(children, childrenCount)
//			}
//			return null
//		}
//
//	}
//
//	class Parser : PsiParser, LightPsiParser {
//		override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
//			doParse(root, builder)
//			return builder.treeBuilt
//		}
//
//		override fun parseLight(root: IElementType, builder: PsiBuilder) {
//			doParse(root, builder);
//		}
//
//		private fun doParse(root: IElementType, builder: PsiBuilder) {
//			builder.putUserData(PsiBuilderImpl.CUSTOM_COMPARATOR, MATCH_BY_KEY)
//
//			val rootMarker = builder.mark()
//			while(!builder.eof()) {
//				parseProperty(builder)
//			}
//			rootMarker.done(root)
//		}
//
//		companion object {
//			fun parseProperty(builder: PsiBuilder) {
//				if(builder.tokenType == StellarisLocalizationTypes.PROPERTY_KEY) {
//					val prop = builder.mark()
//					builder.advanceLexer() //TODO
//					prop.done(StellarisLocalizationTypes.PROPERTY)
//				} else {
//					builder.advanceLexer()
//					builder.error("Error when parsing.")
//				}
//			}
//		}
//	}
//}
