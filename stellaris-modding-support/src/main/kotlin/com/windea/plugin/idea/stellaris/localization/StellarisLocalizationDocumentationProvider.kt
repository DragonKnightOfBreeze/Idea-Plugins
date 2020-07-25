@file:Suppress("IntroduceWhenSubject")

package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.domain.*

//com.intellij.lang.properties.PropertiesDocumentationProvider

//必须是PsiNamedElement

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	private val logger = getLogger<StellarisLocalizationDocumentationProvider>()

	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return if(element is PsiDocumentedElement) element.navigateInfo else null
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return if(element is PsiDocumentedElement) element.documentation else null
	}
}
