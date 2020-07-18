package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.documentation.*
import com.intellij.openapi.util.text.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//com.intellij.lang.properties.PropertiesDocumentationProvider

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			is StellarisLocalizationProperty -> {
				"${element.name} ${getLocationText(element)}"
			}
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			is StellarisLocalizationPropertyHeader -> {
				buildString{
					val description = enumValues<StellarisLocalizationLocale>().first { it.text == element.name }.description
					append(DocumentationMarkup.DEFINITION_START)
					append(description)
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			is StellarisLocalizationProperty -> {
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append("<b>${element.name}</b>: ${getPropertyValueText(element).quote()} ${getLocationText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisLocalizationSyntaxHighlighter.COMMENT_KEY
					val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
					if(docCommentText.isNotEmpty()) {
						append(DocumentationMarkup.CONTENT_START)
						append(docCommentText)
						append(DocumentationMarkup.CONTENT_END)
					}
				}
			}
			else -> null
		}
	}

	private fun getLocationText(element: PsiElement): String {
		val file = element.containingFile ?: return ""
		return "[${file.name}]"
	}

	private fun getPropertyValueText(property: StellarisLocalizationProperty): String {
		val value = property.propertyValue?.text
		return if(value == null) "" else StringUtil.unescapeXmlEntities(value)
	}
}
