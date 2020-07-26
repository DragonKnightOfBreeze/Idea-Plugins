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
			is StellarisScriptVariableDefinition -> "${getLocationText(element)}<br>${element.name}"
			is StellarisScriptProperty -> "${getLocationText(element)}<br>${element.name}"
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
					append("${getLocationText(element)}<br><b>${element.name}</b> = ${getVariableValueText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					val textAttributesKey = StellarisScriptAttributesKeys.COMMENT_KEY
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
					append("${getLocationText(element)}<br><b>${element.name}</b> = ${getPropertyValueText(element)}")
					append(DocumentationMarkup.DEFINITION_END)

					//只有顶级才生成文档注释中的描述
					if(element.parent !is PsiFile) return@buildString

					val textAttributesKey = StellarisScriptAttributesKeys.COMMENT_KEY
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
		return property.propertyValue?.let {
			when {
				it.block != null -> "{...}"
				//it.array != null -> "{...}"
				//it.`object` != null -> "{...}"
				else -> it.text
			}
		}.orEmpty()
	}
}
