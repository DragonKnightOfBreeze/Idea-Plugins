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
				val elementName = element.name?:return null
				val elementValue = element.value
				val truncatedElementValue = elementValue.truncate(78 - (elementName.length)).quote()
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(getLocationText(element))
					append("<br><b>",elementName,"</b>: ", truncatedElementValue)
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
			element is StellarisLocalizationLocale -> {
				val documentText = element.stellarisLocale?.documentText ?: return null
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(documentText)
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationIcon -> {
				val documentText = element.name?.let { name -> "(icon) $name"}?:return null
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(documentText)
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationColorfulText ->{
				val documentText = element.stellarisColor?.documentText ?: return null
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(documentText)
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationSerialNumber -> {
				val documentText = element.stellarisSerialNumber?.documentText ?: return null
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append(documentText)
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			else -> null
		}
	}

	private fun getLocationText(element: PsiElement): String {
		return "[${element.filePath}]"
	}
}
