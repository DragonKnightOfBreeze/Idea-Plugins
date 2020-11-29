package com.windea.plugin.idea.stellaris.script.editor

import com.intellij.lang.documentation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*
import org.jetbrains.annotations.*

class StellarisScriptDocumentationProvider : AbstractDocumentationProvider() {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> getQuickNavigateInfo(element.parent, originalElement)
			is StellarisScriptVariable -> "${getLocationText(element)}<br>script variable \"${element.name}\""
			is StellarisScriptProperty -> "${getLocationText(element)}<br>script property \"${element.name}\""
			else -> null
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			//防止意外情况
			is StellarisScriptVariableName -> generateDoc(element.parent, originalElement)
			is StellarisScriptVariable -> generateVariableDoc(element)
			is StellarisScriptProperty -> generatePropertyDoc(element)
			else -> null
		}
	}
	
	private fun generateVariableDoc(element: StellarisScriptVariable): String? {
		val name = element.name ?: return null
		return buildString {
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
	
	private fun generatePropertyDoc(element: StellarisScriptProperty): String {
		val name = element.name
		val project = element.project
		return buildString {
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
			
			//过滤例外情况
			if(element.parent !is StellarisScriptRootBlock || !element.stellarisPath.contains('/')) return@buildString
			//过滤非法情况
			if(name.isInvalidPropertyName()) return@buildString
			
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
	
	private fun getPropertyDocSectionMap(element: StellarisScriptProperty, name: String, project: Project): LinkedHashMap<String, String> {
		val sectionMap = linkedMapOf<String, String>()
		
		//添加渲染后的对应的图标到文档注释中
		val iconUrl = name.resolveIconUrl(false)
		if(iconUrl.isNotEmpty()) {
			val key = message("stellaris.documentation.icon")
			val value = iconTag(iconUrl, iconSize * 2)
			sectionMap[key] = value
		}
		
		//添加渲染后的相关的本地化属性的值的文本到文档注释中
		val localizationProperties = findRelatedLocalizationProperties(name, project, inferredStellarisLocale)
			.distinctBy { it.name } //过滤重复的属性
		if(localizationProperties.isNotEmpty()) {
			for(property in localizationProperties) {
				val key = message(property.getRelatedLocalizationPropertyKey())
				val value = property.propertyValue?.renderRichText()
				if(value != null) sectionMap[key] = value
			}
		}
		
		//添加渲染后的作为其属性的值的文本到文档注释中
		val propertyValue = element.propertyValue?.value
		val properties = if(propertyValue is StellarisScriptBlock) propertyValue.propertyList else null
		if(properties != null && properties.isNotEmpty()) {
			for(property in properties) {
				when(property.name) {
					"description" -> {
						val k = property.value ?: continue
						val value = findLocalizationProperty(k, project, inferredStellarisLocale)?.propertyValue?.renderRichText()
						val key = message("stellaris.documentation.effect")
						sectionMap[key] = value ?: k
					}
					"tags" -> {
						val pv = property.propertyValue?.value as? StellarisScriptBlock ?: continue
						val tags = pv.valueList.mapNotNull { if(it is StellarisScriptString) it.value else null }
						val propValues = tags.mapNotNull { tag ->
							findLocalizationProperty(tag, project, inferredStellarisLocale)?.propertyValue
						}
						if(propValues.isEmpty()) continue
						val key = message("stellaris.documentation.tags")
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
	
	private fun getLocationText(element: PsiElement): String {
		return "[${element.stellarisPath}]"
	}
	
	private fun getVariableValueText(variable: StellarisScriptVariable): String {
		return variable.variableValue?.text.orEmpty()
	}
	
	private fun getPropertyValueText(property: StellarisScriptProperty): String {
		return property.propertyValue?.value?.let {
			when {
				it is StellarisScriptBlock -> blockFolder
				else -> it.text
			}
		}.orEmpty()
	}
}
