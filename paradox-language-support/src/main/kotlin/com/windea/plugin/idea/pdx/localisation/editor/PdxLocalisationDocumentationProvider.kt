package com.windea.plugin.idea.pdx.localisation.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.highlighter.*
import com.windea.plugin.idea.pdx.localisation.psi.*

class PdxLocalisationDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is PdxLocalisationProperty -> "${getLocationTextLine(element)}localisation property \"${element.name}\""
			element is PdxLocalisationLocale -> "locale \"${element.name}\""
			element is PdxLocalisationIcon -> "icon \"${element.name}\""
			element is PdxLocalisationSerialNumber -> "serial number \"${element.name}\""
			element is PdxLocalisationColorfulText -> "color \"${element.name}\""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is PdxLocalisationProperty -> generatePropertyDoc(element)
			element is PdxLocalisationLocale -> generateLocaleDoc(element)
			element is PdxLocalisationIcon -> generateIconDoc(element)
			element is PdxLocalisationSerialNumber -> generateSerialNumberLog(element)
			element is PdxLocalisationColorfulText -> generateColorfulTextDoc(element)
			else -> null
		}
	}
	
	private fun generatePropertyDoc(element: PdxLocalisationProperty): String {
		val elementName = element.name
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(localisation property) <b>").append(elementName).append("</b>")
			append(DocumentationMarkup.DEFINITION_END)
			
			//添加先前的行注释的文本到文档注释中
			val textAttributesKey = PdxLocalisationAttributesKeys.COMMENT_KEY
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
				append(message("pdx.documentation.text")).append(" ")
				append(DocumentationMarkup.SECTION_SEPARATOR).append("<p>")
				propertyValue.renderRichTextTo(this)
				append(DocumentationMarkup.SECTION_END)
				append(DocumentationMarkup.SECTIONS_END)
			}
		}
	}
	
	private fun generateLocaleDoc(element: PdxLocalisationLocale): String? {
		val documentText = element.pdxLocale?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateIconDoc(element: PdxLocalisationIcon): String? {
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
	
	private fun generateSerialNumberLog(element: PdxLocalisationSerialNumber): String? {
		val documentText = element.pdxSerialNumber?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateColorfulTextDoc(element: PdxLocalisationColorfulText): String? {
		val documentText = element.pdxColor?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun getLocationTextLine(element: PsiElement): String {
		return element.pdxPath?.let { "[$it]<br>" }.orEmpty()
	}
}
