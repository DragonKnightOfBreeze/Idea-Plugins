package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> "${getLocationText(element)}<br>${element.name}"
			element is StellarisLocalizationLocale -> "locale \"${element.name}\""
			element is StellarisLocalizationIcon -> "icon \"${element.name}\""
			element is StellarisLocalizationColorfulText -> "color \"${element.name}\""
			element is StellarisLocalizationSerialNumber -> "serial number \"${element.name}\""
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> {
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(getLocationText(element))
					append("<br><b>",element.name,"</b>: ",element.value.truncate(80-(element.name?.length?:0)))
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisLocalizationAttributesKeys.COMMENT_KEY
					val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
					if(docCommentText.isNotEmpty()) {
						append(DocumentationMarkup.CONTENT_START)
						append(docCommentText)
						append(DocumentationMarkup.CONTENT_END)
					}
				}
			}
			element is StellarisLocalizationLocale -> element.locale?.documentText?.toDefinitionText()
			element is StellarisLocalizationIcon ->element.name?.let { name -> "(icon) $name".toDefinitionText() }
			element is StellarisLocalizationColorfulText -> element.color?.documentText?.toDefinitionText()
			element is StellarisLocalizationSerialNumber -> element.serialNumber?.documentText?.toDefinitionText()
			else -> null
		}
	}

	private fun getLocationText(element: PsiElement): String {
		val file = element.containingFile ?: return ""
		return "[${file.name}]"
	}
}
