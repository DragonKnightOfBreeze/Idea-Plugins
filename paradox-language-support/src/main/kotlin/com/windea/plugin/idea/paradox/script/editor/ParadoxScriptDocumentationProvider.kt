package com.windea.plugin.idea.paradox.script.editor

import com.intellij.lang.documentation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*
import com.windea.plugin.idea.paradox.settings.*

class ParadoxScriptDocumentationProvider : AbstractDocumentationProvider() {
	companion object {
		private val iconTitle = message("paradox.documentation.icon")
		private val effectTitle = message("paradox.documentation.effect")
		private val tagsTitle = message("paradox.documentation.tags")
	}
	
	private val state = ParadoxSettingsState.getInstance()
	
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			is ParadoxScriptVariableName -> getQuickNavigateInfo(element.parent, originalElement) //防止意外情况
			is ParadoxScriptVariable -> getVariableInfo(element)
			is ParadoxScriptProperty -> getPropertyInfo(element)
			else -> null
		}
	}
	
	private fun getVariableInfo(element: ParadoxScriptVariable): String {
		return buildString {
			element.paradoxPath?.let { paradoxPath -> append("[").append(paradoxPath).append("]<br>") }
			append("script variable <b>").append(element.name).append("</b>")
			element.unquotedValue?.let { unquotedValue -> append(" = ").append(unquotedValue.escapeXml()) }
		}
	}
	
	private fun getPropertyInfo(element: ParadoxScriptProperty): String {
		return buildString {
			element.paradoxPath?.let { append("[").append(it).append("]<br>") }
			append("script property <b>").append(element.name.escapeXml()).append("</b>")
			element.truncatedValue?.let { truncatedValue -> append(" = ").append(truncatedValue.escapeXml()) }
		}
	}
	
	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return when(element) {
			is ParadoxScriptVariableName -> generateDoc(element.parent, originalElement) //防止意外情况
			is ParadoxScriptVariable -> getVariableDoc(element)
			is ParadoxScriptProperty -> getPropertyDoc(element)
			else -> null
		}
	}
	
	private fun getVariableDoc(element: ParadoxScriptVariable): String {
		return buildString {
			definition {
				element.paradoxPath?.let { paradoxPath -> append("[").append(paradoxPath).append("]<br>") }
				append("script variable <b>").append(element.name).append("</b>")
				element.unquotedValue?.let { unquotedValue -> append(" = ").append(unquotedValue.escapeXml()) }
			}
			//之前的单行注释文本
			val docText = getDocTextFromPreviousComment(element)
			if(docText.isNotEmpty()) {
				content {
					append(docText.escapeXml())
				}
			}
		}
	}
	
	private fun getPropertyDoc(element: ParadoxScriptProperty): String {
		val name = element.name
		val project = element.project
		return buildString {
			definition {
				element.paradoxPath?.let { append("[").append(it).append("]<br>") }
				append("script property <b>").append(element.name.escapeXml()).append("</b>")
				element.truncatedValue?.let { truncatedValue -> append(" = ").append(truncatedValue.escapeXml()) }
			}
			//之前的单行注释文本
			val docText = getDocTextFromPreviousComment(element)
			if(docText.isNotEmpty()) {
				content {
					append(docText.escapeXml())
				}
			}
			//相关的本地化文本
			if(state.renderLocalisationText) {
				if(element.isRootProperty() && !name.isInvalidPropertyName()) {
					val sections = getPropertySections(element, name, project)
					if(sections.isNotEmpty()) {
						sections {
							for((title, value) in sections) {
								section(title, value)
							}
						}
					}
				}
			}
		}
	}
	
	private fun getPropertySections(element: ParadoxScriptProperty, name: String, project: Project): Map<String, String> {
		val sectionMap = linkedMapOf<String, String>()
		
		//添加渲染后的对应的图标到文档注释中
		val iconUrl = name.resolveIconUrl(false)
		if(iconUrl.isNotEmpty()) {
			val value = iconTag(iconUrl, iconSize * 2)
			sectionMap[iconTitle] = value
		}
		
		//添加渲染后的相关的本地化属性的值的文本到文档注释中
		val localisationProperties = findRelatedLocalisationProperties(name, project, inferredParadoxLocale)
			.distinctBy { it.name } //过滤重复的属性
		if(localisationProperties.isNotEmpty()) {
			for(property in localisationProperties) {
				val value = property.propertyValue?.renderRichText()
				if(value != null) sectionMap[message(property.getRelatedLocalisationPropertyKey())] = value
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
						sectionMap[effectTitle] = value ?: k
					}
					"tags" -> {
						val pv = property.propertyValue?.value as? ParadoxScriptBlock ?: continue
						val tags = pv.valueList.mapNotNull { if(it is ParadoxScriptString) it.value else null }
						val propValues = tags.mapNotNull { tag ->
							findLocalisationProperty(tag, project, inferredParadoxLocale)?.propertyValue
						}
						if(propValues.isEmpty()) continue
						val value = buildString {
							var addNewLine = false
							for(propValue in propValues) {
								if(addNewLine) append("<br>") else addNewLine = true
								propValue.renderRichTextTo(this)
							}
						}
						sectionMap[tagsTitle] = value
					}
				}
			}
		}
		
		return sectionMap
	}
}
