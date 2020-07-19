@file:Suppress("IntroduceWhenSubject")

package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.documentation.*
import com.intellij.openapi.util.text.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//com.intellij.lang.properties.PropertiesDocumentationProvider

//必须是PsiNamedElement

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> "${getLocationText(element)}<br>${element.name}"
			element is StellarisLocalizationPropertyHeader -> "locale \"${element.name}\""
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
					append("${getLocationText(element)}<br><b>${element.name}</b>: ${getPropertyValueText(element).quote()}")
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
			element is StellarisLocalizationPropertyHeader -> {
				buildString{
					append(DocumentationMarkup.DEFINITION_START)
					append(stellarisLocaleMap[element.name])
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationIcon -> {
				buildString{
					append(DocumentationMarkup.DEFINITION_START)
					append("(icon) ${element.name}")
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationColorfulText -> {
				buildString{
					append(DocumentationMarkup.DEFINITION_START)
					append(stellarisColorMap[element.name])
					append(DocumentationMarkup.DEFINITION_END)
				}
			}
			element is StellarisLocalizationSerialNumber -> {
				buildString{
					append(DocumentationMarkup.DEFINITION_START)
					append(stellarisSerialNumberMap[element.name])
					append(DocumentationMarkup.DEFINITION_END)
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
