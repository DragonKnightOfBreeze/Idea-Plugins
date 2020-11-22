package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.documentation.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*

class StellarisScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> getQuickNavigateInfo(element.parent,originalElement)
			is StellarisScriptVariable -> "${getLocationText(element)}<br>script variable \"${element.name}\""
			is StellarisScriptProperty -> "${getLocationText(element)}<br>script property \"${element.name}\""
			else -> null
		}
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> generateDoc(element.parent,originalElement)
			is StellarisScriptVariable -> generateVariableDoc(element)
			is StellarisScriptProperty -> generatePropertyDoc(element)
			else -> null
		}
	}
	
	private fun generateVariableDoc(element: StellarisScriptVariable): String? {
		val name = element.name ?: return null
		return writeString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationText(element))
			append("<br>")
			append("(script variable) <b>${name}</b> = ${getVariableValueText(element)}")
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
	
	private fun generatePropertyDoc(element: StellarisScriptProperty): String? {
		val name = element.name ?: return null
		return writeString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationText(element))
			append("<br>")
			append("(script property) <b>${element.name}</b> = ${getPropertyValueText(element)}")
			append(DocumentationMarkup.DEFINITION_END)
			
			val textAttributesKey = StellarisScriptAttributesKeys.COMMENT_KEY
			val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
			
			//添加额外内容到文档注释中
			var isSectionsStart = false
			
			//添加渲染后的对应的图标到文档注释中
			val iconUrl = name.resolveIconUrl(false)
			if(iconUrl.isNotEmpty()){
				if(!isSectionsStart) append(DocumentationMarkup.SECTIONS_START)
				isSectionsStart = true
				append(DocumentationMarkup.SECTION_HEADER_START)
				append(message("stellaris.documentation.icon")).append(" ")
				append(DocumentationMarkup.SECTION_SEPARATOR,"<p>")
				append(iconTag(iconUrl,iconSize*2))
				append(DocumentationMarkup.SECTION_END)
			}
			
			//添加渲染后的相关的本地化属性的值的文本到文档注释中
			//过滤例外情况
			if(element.parent !is StellarisScriptRootBlock || !element.stellarisPath.contains('/')) return@writeString
			//过滤非法情况
			if(name.isInvalidPropertyName()) return@writeString
			val relatedLocalizationProperties = element.findRelatedLocalizationProperties(inferedStellarisLocale)
				.distinctBy { it.name } //过滤重复的属性
			if(relatedLocalizationProperties.isNotEmpty()) {
				for(property in relatedLocalizationProperties) {
					val propertyName = property.name ?: continue
					val propertyValue = property.propertyValue
					if(propertyValue != null) {
						if(!isSectionsStart) append(DocumentationMarkup.SECTIONS_START)
						isSectionsStart = true
						
						append(DocumentationMarkup.SECTION_HEADER_START)
						append(message(propertyName.getRelatedLocalizationPropertyI18nKey(name.length))).append(" ")
						append(DocumentationMarkup.SECTION_SEPARATOR,"<p>")
						propertyValue.renderRichTextTo(this)
						append(DocumentationMarkup.SECTION_END)
					}
				}
			}
			
			//添加渲染后的标签文本到文档注释中
			val blockValue = element.propertyValue?.value
			if(blockValue is StellarisScriptBlock) {
				val tagsProp = blockValue.propertyList.find { it.name == "tags" }
				val tagsPropBlockValue = tagsProp?.propertyValue?.value
				if(tagsPropBlockValue is StellarisScriptBlock) {
					val tags = tagsPropBlockValue.valueList.mapArray { it.text.unquote() }
					if(tags.isNotEmpty()) {
						if(!isSectionsStart) append(DocumentationMarkup.SECTIONS_START)
						isSectionsStart = true
						
						append(DocumentationMarkup.SECTION_HEADER_START)
						append(message("stellaris.documentation.tags")).append(" ")
						append(DocumentationMarkup.SECTION_SEPARATOR,"<p>")
						var addNewLine = false
						for(tag in tags) {
							val prop = findLocalizationProperty(tag, element.project, inferedStellarisLocale)
							val propValue = prop?.propertyValue
							if(propValue != null) {
								if(addNewLine) append("<br>") else addNewLine = true
								propValue.renderRichTextTo(this)
							}
						}
						append(DocumentationMarkup.SECTION_END)
					}
				}
			}
			if(isSectionsStart) append(DocumentationMarkup.SECTIONS_END)
		}
	}
	
	private fun getLocationText(element: PsiElement): String {
		return "[${element.stellarisPath}]"
	}

	private fun getVariableValueText(variable: StellarisScriptVariable):String{
		return variable.variableValue?.text.orEmpty()
	}

	private fun getPropertyValueText(property: StellarisScriptProperty):String{
		return property.propertyValue?.value?.let {
			when {
				it is StellarisScriptBlock -> blockFolder
				else -> it.text
			}
		}.orEmpty()
	}
}
