package com.windea.plugin.idea.pdx.script.editor

import com.intellij.lang.documentation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.*
import com.windea.plugin.idea.pdx.script.highlighter.*
import com.windea.plugin.idea.pdx.script.psi.*
import org.jetbrains.annotations.*

class PdxScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is PdxScriptVariableName -> getQuickNavigateInfo(element.parent, originalElement)
			is PdxScriptVariable -> "${getLocationTextLine(element)}script variable \"${element.name}\""
			is PdxScriptProperty -> "${getLocationTextLine(element)}script property \"${element.name}\""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is PdxScriptVariableName -> generateDoc(element.parent, originalElement)
			is PdxScriptVariable -> generateVariableDoc(element)
			is PdxScriptProperty -> generatePropertyDoc(element)
			else -> null
		}
	}
	
	private fun generateVariableDoc(element: PdxScriptVariable): String? {
		val name = element.name ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(script variable) <b>${name}</b> = ${getVariableValueText(element)}")
			append(DocumentationMarkup.DEFINITION_END)
			
			val textAttributesKey = PdxScriptAttributesKeys.COMMENT_KEY
			val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
		}
	}
	
	private fun generatePropertyDoc(element: PdxScriptProperty): String {
		val name = element.name
		val project = element.project
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(script property) <b>${name}</b> = ${getPropertyValueText(element)}")
			append(DocumentationMarkup.DEFINITION_END)
			
			val textAttributesKey = PdxScriptAttributesKeys.COMMENT_KEY
			val docCommentText = getDocCommentHtmlFromPreviousComment(element, textAttributesKey)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
			
			//过滤例外情况
			if(element.parent !is PdxScriptRootBlock || element.pdxParentPath.isNullOrEmpty()) return@buildString
			//过滤非法情况
			if(name.isInvalidPropertyName) return@buildString
			
			//添加额外内容到文档注释中
			val sectionMap = getPropertyDocSectionMap(element, name, project)
			
			if(sectionMap.isNotEmpty()){
				append(DocumentationMarkup.SECTIONS_START)
				for((k,v) in sectionMap) {
					append(DocumentationMarkup.SECTION_HEADER_START)
					append(k).append(" ")
					append(DocumentationMarkup.SECTION_SEPARATOR).append("<p>")
					append(v)
					append(DocumentationMarkup.SECTION_END)
				}
				append(DocumentationMarkup.SECTIONS_END)
			}
		}
	}
	
	private fun getPropertyDocSectionMap(element: PdxScriptProperty, name: String, project: Project): LinkedHashMap<String, String> {
		val sectionMap = linkedMapOf<String, String>()
		
		//添加渲染后的对应的图标到文档注释中
		val iconUrl = name.resolveIconUrl(false)
		if(iconUrl.isNotEmpty()) {
			val key = message("pdx.documentation.icon")
			val value = iconTag(iconUrl, iconSize * 2)
			sectionMap[key] = value
		}
		
		//添加渲染后的相关的本地化属性的值的文本到文档注释中
		val localisationProperties = findRelatedLocalisationProperties(name, project, inferredPdxLocale)
			.distinctBy { it.name } //过滤重复的属性
		if(localisationProperties.isNotEmpty()) {
			for(property in localisationProperties) {
				val key = message(property.getRelatedLocalisationPropertyKey())
				val value = property.propertyValue?.renderRichText()
				if(value != null) sectionMap[key] = value
			}
		}
		
		//添加渲染后的作为其属性的值的文本到文档注释中
		val propertyValue = element.propertyValue?.value
		val properties = if(propertyValue is PdxScriptBlock) propertyValue.propertyList else null
		if(properties != null && properties.isNotEmpty()) {
			for(property in properties) {
				when(property.name) {
					"description" -> {
						val k = property.value ?: continue
						val value = findLocalisationProperty(k, project, inferredPdxLocale)?.propertyValue?.renderRichText()
						val key = message("pdx.documentation.effect")
						sectionMap[key] = value ?: k
					}
					"tags" -> {
						val pv = property.propertyValue?.value as? PdxScriptBlock ?: continue
						val tags = pv.valueList.mapNotNull { if(it is PdxScriptString) it.value else null }
						val propValues = tags.mapNotNull { tag ->
							findLocalisationProperty(tag, project, inferredPdxLocale)?.propertyValue
						}
						if(propValues.isEmpty()) continue
						val key = message("pdx.documentation.tags")
						val value = buildString {
							var addNewLine = false
							for(propValue in propValues) {
								if(addNewLine) append("<br>") else addNewLine = true
								propValue.renderRichTextTo(this)
							}
						}
						sectionMap[key] = value
					}
				}
			}
		}
		
		return sectionMap
	}
	
	private fun getLocationTextLine(element: PsiElement): String {
		return element.pdxPath?.let{ "[$it]<br>"}.orEmpty()
	}
	
	private fun getVariableValueText(variable: PdxScriptVariable): String {
		return variable.variableValue?.text.orEmpty()
	}
	
	private fun getPropertyValueText(property: PdxScriptProperty): String {
		return property.propertyValue?.value?.let {
			when {
				it is PdxScriptBlock -> blockFolder
				else -> it.text
			}
		}.orEmpty()
	}
}
