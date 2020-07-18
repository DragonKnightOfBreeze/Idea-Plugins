package com.windea.plugin.idea.stellaris.script.reference

import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.annotations.*

//引用：
//"key=@value" -> @value: variable

@ExtensionPoint
class StellarisScriptReferenceContributor : PsiReferenceContributor() {
	override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
		registrar.registerReferenceProvider(
			PlatformPatterns.psiElement(PsiLiteralValue::class.java),
			object : PsiReferenceProvider() {
				override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
					//得到字面量元素的值，如果等于某个变量的名字，则使用那个变量
					val value = (element as PsiLiteralValue).value as? String
					//if(value != null && value.startsWith('@')) {
					//	val textRanges = value.indicesOf('$').chunked(2).map { TextRange(it[0], it[1]) }
					//	return textRanges.mapArray { StellarisScriptPropertyPsiReference(element, it) }
					//}
					return PsiReference.EMPTY_ARRAY
				}
			}
		)
	}
}
