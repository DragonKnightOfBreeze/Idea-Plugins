package com.windea.plugin.idea.paradox.script.editor

import com.intellij.lang.documentation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.*
import com.windea.plugin.idea.paradox.script.highlighter.*
import com.windea.plugin.idea.paradox.script.psi.*
import org.jetbrains.annotations.*

class ParadoxScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is ParadoxScriptVariableName -> getQuickNavigateInfo(element.parent, originalElement)
			is ParadoxScriptVariable -> "${getLocationTextLine(element)}script variable \"${element.name}\""
			is ParadoxScriptProperty -> "${getLocationTextLine(element)}script property \"${element.name}\""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is ParadoxScriptVariableName -> generateDoc(element.parent, originalElement)
			is ParadoxScriptVariable -> generateVariableDoc(element)
			is ParadoxScriptProperty -> generatePropertyDoc(element)
			else -> null
		}
	}
	
	private fun generateVariableDoc(element: ParadoxScriptVariable): String? {
		val name = element.name ?: return null
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(script variable) <b>${name}</b> = ${getVariableValueText(element)}")
			append(DocumentationMarkup.DEFINITION_END)
			
			val docCommentText = getDocCommentTextFromPreviousComment(element)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
		}
	}
	
	private fun generatePropertyDoc(element: ParadoxScriptProperty): String {
		val name = element.name
		val project = element.project
		return buildString {
			append(DocumentationMarkup.DEFINITION_START)
			append(getLocationTextLine(element))
			append("(script property) <b>${name}</b> = ${getPropertyValueText(element)}")
			append(DocumentationMarkup.DEFINITION_END)
			
			val docCommentText = getDocCommentTextFromPreviousComment(element)
			if(docCommentText.isNotEmpty()) {
				append(DocumentationMarkup.CONTENT_START)
				append(docCommentText)
				append(DocumentationMarkup.CONTENT_END)
			}
			
			//过滤例外情况
			if(element.parent !is ParadoxScriptRootBlock || element.paradoxParentPath.isNullOrEmpty()) return@buildString
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
	
	private fun getPropertyDocSectionMap(element: ParadoxScriptProperty, name: String, project: Project): LinkedHashMap<String, String> {
		val sectionMap = linkedMapOf<String, String>()
		
		//添加渲染后的对应的图标到文档注释中
		val iconUrl = name.resolveIconUrl(false)
		if(iconUrl.isNotEmpty()) {
			val key = message("paradox.documentation.icon")
			val value = iconTag(iconUrl, iconSize * 2)
			sectionMap[key] = value
		}
		
		//添加渲染后的相关的本地化属性的值的文本到文档注释中
		val localisationProperties = findRelatedLocalisationProperties(name, project, inferredParadoxLocale)
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
		val properties = if(propertyValue is ParadoxScriptBlock) propertyValue.propertyList else null
		if(properties != null && properties.isNotEmpty()) {
			for(property in properties) {
				when(property.name) {
					"description" -> {
						val k = property.value ?: continue
						val value = findLocalisationProperty(k, project, inferredParadoxLocale)?.propertyValue?.renderRichText()
						val key = message("paradox.documentation.effect")
						sectionMap[key] = value ?: k
					}
					"tags" -> {
						val pv = property.propertyValue?.value as? ParadoxScriptBlock ?: continue
						val tags = pv.valueList.mapNotNull { if(it is ParadoxScriptString) it.value else null }
						val propValues = tags.mapNotNull { tag ->
							findLocalisationProperty(tag, project, inferredParadoxLocale)?.propertyValue
						}
						if(propValues.isEmpty()) continue
						val key = message("paradox.documentation.tags")
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
		return element.paradoxPath?.let{ "[$it]<br>"}.orEmpty()
	}
	
	private fun getVariableValueText(variable: ParadoxScriptVariable): String {
		return variable.variableValue?.text.orEmpty()
	}
	
	private fun getPropertyValueText(property: ParadoxScriptProperty): String {
		return property.propertyValue?.value?.let {
			when {
				it is ParadoxScriptBlock -> blockFolder
				else -> it.text
			}
		}.orEmpty()
	}
}
