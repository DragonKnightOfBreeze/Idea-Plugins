package com.windea.plugin.idea.stellaris.localization.reference

import com.intellij.patterns.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//引用：
//属性头部→属性头部
//属性引用 → 属性
//属性 → 属性 （可以覆盖）

@ExtensionPoint
class StellarisLocalizationReferenceContributor : PsiReferenceContributor() {
	override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
		//属性引用属性
		registrar.registerReferenceProvider(
			PlatformPatterns.psiElement(StellarisLocalizationProperty::class.java),
			object :PsiReferenceProvider(){
				override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiReference> {
					val property = element as StellarisLocalizationProperty
					return arrayOf(StellarisLocalizationPropertyPsiReference(property,property.textRange))
				}
			}
		)
	}
}
