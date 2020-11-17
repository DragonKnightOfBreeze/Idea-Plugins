package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.lang.*
import com.intellij.psi.stubs.*
import com.intellij.psi.tree.*
import com.intellij.util.diff.*
import com.windea.plugin.idea.stellaris.localization.*


class StellarisLocalizationStubFileElementType : ILightStubFileElementType<PsiFileStub<*>>(StellarisLocalizationLanguage) {
	
	override fun parseContentsLight(chameleon: ASTNode): FlyweightCapableTreeStructure<LighterASTNode?>? {
		val psi = chameleon.psi ?: error("Bad chameleon: $chameleon")
		val project = psi.project
		val factory = PsiBuilderFactory.getInstance()
		val builder = factory.createBuilder(project, chameleon)
		val parser = Parser()
		parser.parseLight(this, builder)
		return builder.lightTree
	}
	
	class Parser : PsiParser, LightPsiParser {
		override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
			doParse(root, builder);
			return builder.treeBuilt;
		}
		
		override fun parseLight(root: IElementType, builder: PsiBuilder) {
			doParse(root, builder);
		}
		
		private fun doParse(root: IElementType, builder: PsiBuilder) {
			val rootMarker = builder.mark()
			while (!builder.eof()) {
				parseProperty(builder)
			}
			rootMarker.done(root);
		}
		
		companion object{
			fun parseProperty(builder: PsiBuilder){
				builder.advanceLexer()
			}
		}
	}
}
