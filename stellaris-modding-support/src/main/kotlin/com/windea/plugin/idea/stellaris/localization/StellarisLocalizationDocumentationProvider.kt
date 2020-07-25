@file:Suppress("IntroduceWhenSubject")

package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.documentation.*
import com.intellij.openapi.util.text.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

//com.intellij.lang.properties.PropertiesDocumentationProvider

//必须是PsiNamedElement

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	private val logger = getLogger<StellarisLocalizationDocumentationProvider>()

	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> "${element.name} ${getLocationText(element)}"
			element is StellarisLocalizationLocale -> "locale \"${element.name}\""
			element is StellarisLocalizationIcon -> "icon \"${element.name}\""
			element is StellarisLocalizationColorfulText -> "color \"${element.name}\""
			element is StellarisLocalizationSerialNumber -> "serial number \"${element.name}\""
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		logger.info{"element: $element"}
		logger.info{"element text: ${element?.text}"}
		logger.info{"original element: $originalElement"}
		logger.info{"original element text: ${originalElement?.text}"}

		return when {
			element is StellarisLocalizationProperty -> {
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append("<b>${element.name}</b>: ${getPropertyValueText(element)} ${getLocationText(element)}")
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
			element is StellarisLocalizationLocale -> element.locale?.description?.toDefinitionText()
			element is StellarisLocalizationIcon ->element.name?.let { name -> "(icon) $name".toDefinitionText() }
			element is StellarisLocalizationColorfulText -> element.color?.description?.toDefinitionText()
			element is StellarisLocalizationSerialNumber -> element.serialNumber?.description?.toDefinitionText()
			else -> null
		}
	}

	private fun getLocationText(element: PsiElement): String {
		val file = element.containingFile ?: return ""
		return "[${file.name}]"
	}

	private fun getPropertyValueText(element: StellarisLocalizationProperty): String {
		return  StringUtil.unescapeXmlEntities(element.value)
	}
}

