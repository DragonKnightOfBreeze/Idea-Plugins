package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> "${getLocationTextLine(element)}localization property \"${element.name}\""
			element is StellarisLocalizationLocale -> "locale \"${element.name}\""
			element is StellarisLocalizationIcon -> "icon \"${element.name}\""
			element is StellarisLocalizationSerialNumber -> "serial number \"${element.name}\""
			element is StellarisLocalizationColorfulText -> "color \"${element.name}\""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is StellarisLocalizationProperty -> generatePropertyDoc(element)
			element is StellarisLocalizationLocale -> generateLocaleDoc(element)
			element is StellarisLocalizationIcon -> generateIconDoc(element)
			element is StellarisLocalizationSerialNumber -> generateSerialNumberLog(element)
			element is StellarisLocalizationColorfulText -> generateColorfulTextDoc(element)
			else -> null
		}
	}
	
	private fun generatePropertyDoc(element: StellarisLocalizationProperty): String {
		val elementName = element.name
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(localization property) <b>").append(elementName).append("</b>")
			append(DocumentationMarkup.DEFINITION_END)
			
			//添加先前的行注释的文本到文档注释中
			val textAttributesKey = StellarisLocalizationAttributesKeys.COMMENT_KEY
			val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
			
			//添加渲染后的值的文本到文档注释中
			val propertyValue = element.propertyValue
			if(propertyValue != null) {
				append(DocumentationMarkup.SECTIONS_START)
				append(DocumentationMarkup.SECTION_HEADER_START)
				append(message("stellaris.documentation.text")).append(" ")
				append(DocumentationMarkup.SECTION_SEPARATOR).append("<p>")
				propertyValue.renderRichTextTo(this)
				append(DocumentationMarkup.SECTION_END)
				append(DocumentationMarkup.SECTIONS_END)
			}
		}
	}
	
	private fun generateLocaleDoc(element: StellarisLocalizationLocale): String? {
		val documentText = element.stellarisLocale?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateIconDoc(element: StellarisLocalizationIcon): String? {
		val name = element.name ?: return null
		val documentText = "(icon) <b>$name</b>"
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
			val iconUrl = name.resolveIconUrl()
			if(iconUrl.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				if(iconUrl[0] != '<') append(iconTag(iconUrl)) else append(iconUrl)
				append(DocumentationMarkup.CONTENT_END)
			}
		}
	}
	
	private fun generateSerialNumberLog(element: StellarisLocalizationSerialNumber): String? {
		val documentText = element.stellarisSerialNumber?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateColorfulTextDoc(element: StellarisLocalizationColorfulText): String? {
		val documentText = element.stellarisColor?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun getLocationTextLine(element: PsiElement): String {
		return element.stellarisPath?.let { "[$it]<br>" }.orEmpty()
	}
}
