package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationDocumentationProvider : AbstractDocumentationProvider() {
	companion object{
		private val textTitle = message("paradox.documentation.text")
	}
	
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is ParadoxLocalisationProperty -> """${locationTextLine(element)}localisation property "${element.name}""""
			element is ParadoxLocalisationLocale -> """locale "${element.name}""""
			element is ParadoxLocalisationIcon -> """icon "${element.name}""""
			element is ParadoxLocalisationSerialNumber -> """serial number "${element.name}""""
			element is ParadoxLocalisationColorfulText -> """color "${element.name}""""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when {
			element is ParadoxLocalisationProperty -> generatePropertyDoc(element)
			element is ParadoxLocalisationLocale -> generateLocaleDoc(element)
			element is ParadoxLocalisationIcon -> generateIconDoc(element)
			element is ParadoxLocalisationSerialNumber -> generateSerialNumberLog(element)
			element is ParadoxLocalisationColorfulText -> generateColorfulTextDoc(element)
			else -> null
		}
	}
	
	private fun generatePropertyDoc(element: ParadoxLocalisationProperty): String {
		val elementName = element.name
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(locationTextLine(element))
			append("(localisation property) <b>").append(elementName).append("</b>")
			append(DocumentationMarkup.DEFINITION_END)
			
			//添加先前的行注释的文本到文档注释中
			val docCommentText = getDocCommentTextFromPreviousComment(element)
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
				append(textTitle).append(" ")
				append(DocumentationMarkup.SECTION_SEPARATOR).append("<p>")
				propertyValue.renderRichTextTo(this)
				append(DocumentationMarkup.SECTION_END)
				append(DocumentationMarkup.SECTIONS_END)
			}
		}
	}
	
	private fun generateLocaleDoc(element: ParadoxLocalisationLocale): String? {
		val documentText = element.paradoxLocale?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateIconDoc(element: ParadoxLocalisationIcon): String? {
		val name = element.name
		if(name.isEmpty()) return null
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
	
	private fun generateSerialNumberLog(element: ParadoxLocalisationSerialNumber): String? {
		val documentText = element.paradoxSerialNumber?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun generateColorfulTextDoc(element: ParadoxLocalisationColorfulText): String? {
		val documentText = element.paradoxColor?.documentText ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(documentText)
			append(DocumentationMarkup.DEFINITION_END)
		}
	}
	
	private fun locationTextLine(element: PsiElement): String {
		return element.paradoxPath?.let { "[$it]<br>" }.orEmpty()
	}
}
