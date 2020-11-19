package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> getQuickNavigateInfo(element.parent,originalElement)
			is StellarisScriptVariable -> "${getLocationText(element)}<br>${element.name}"
			is StellarisScriptProperty -> "${getLocationText(element)}<br>${element.name}"
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> generateDoc(element.parent,originalElement)
			is StellarisScriptVariable -> {
				writeString {
					append(DocumentationMarkup.DEFINITION_START)
					append("${getLocationText(element)}<br><b>${element.name}</b> = ${getVariableValueText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisScriptAttributesKeys.COMMENT_KEY
					val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
					if(docCommentText.isNotEmpty()) {
						append(DocumentationMarkup.CONTENT_START)
						append(docCommentText)
						append(DocumentationMarkup.CONTENT_END)
					}
				}
			}
			is StellarisScriptProperty -> {
				writeString {
					append(DocumentationMarkup.DEFINITION_START)
					append("${getLocationText(element)}<br><b>${element.name}</b> = ${getPropertyValueText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisScriptAttributesKeys.COMMENT_KEY
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
		return "[${element.stellarisPath}]"
	}

	private fun getVariableValueText(variable: StellarisScriptVariable):String{
		return variable.variableValue?.text?.truncate(60).orEmpty()
	}

	private fun getPropertyValueText(property: StellarisScriptProperty):String{
		return property.propertyValue?.value?.let {
			when {
				it is StellarisScriptBlock -> blockFolder
				else -> it.text.truncate(60)
			}
		}.orEmpty()
	}
}
