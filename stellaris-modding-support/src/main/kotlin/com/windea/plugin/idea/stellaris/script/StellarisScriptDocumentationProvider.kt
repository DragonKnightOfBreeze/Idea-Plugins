@file:Suppress("UNNECESSARY_SAFE_CALL", "DuplicatedCode")

package com.windea.plugin.idea.stellaris.script

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*

//com.intellij.lang.properties.PropertiesDocumentationProvider

class StellarisScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			is StellarisScriptVariableDefinition -> {
				"${element.name} ${getLocationText(element)}"
			}
			is StellarisScriptProperty -> {
				"${element.name} ${getLocationText(element)}"
			}
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> {
				generateDoc(element.parent,originalElement)
			}
			is StellarisScriptVariableDefinition -> {
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append("<b>${element.name}</b> = ${getVariableValueText(element)} ${getLocationText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisScriptSyntaxHighlighter.COMMENT_KEY
					val docCommentText = getDocCommentHtmlFromPreviousComment(element,textAttributesKey)
					if(docCommentText.isNotEmpty()) {
						append(DocumentationMarkup.CONTENT_START)
						append(docCommentText)
						append(DocumentationMarkup.CONTENT_END)
					}
				}
			}
			is StellarisScriptProperty -> {
				buildString {
					append(DocumentationMarkup.DEFINITION_START)
					append("<b>${element.name}</b> = ${getPropertyValueText(element)} ${getLocationText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisScriptSyntaxHighlighter.COMMENT_KEY
					val docCommentText = getDocCommentHtmlFromPreviousComment(element,textAttributesKey)
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

	private fun getVariableValueText(variable:StellarisScriptVariableDefinition):String{
		return variable.variableValue?.text.orEmpty()
	}

	private fun getPropertyValueText(property:StellarisScriptProperty):String{
		return property.propertyValue?.let { if(it.list != null) "{...}" else it.text }.orEmpty()
	}
}
