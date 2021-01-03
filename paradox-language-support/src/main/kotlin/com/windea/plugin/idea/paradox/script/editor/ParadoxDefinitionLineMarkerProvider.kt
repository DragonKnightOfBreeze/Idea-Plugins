package com.windea.plugin.idea.paradox.script.editor

import com.intellij.codeInsight.daemon.*
import com.intellij.codeInsight.navigation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.psi.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.model.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxDefinitionLineMarkerProvider : LineMarkerProviderDescriptor() {
	companion object {
		private val _name = message("paradox.script.gutterIcon.definition")
		private val _title = message("paradox.script.gutterIcon.definition.title")
		private fun _tooltip(name: String,type:String) = message("paradox.script.gutterIcon.definition.tooltip", name,type)
	}
	
	override fun getName() = _name
	
	override fun getIcon() = definitionGutterIcon
	
	override fun getLineMarkerInfo(element: PsiElement): LineMarker? {
		return when(element) {
			is ParadoxScriptProperty -> {
				val typeMetadata = element.paradoxTypeMetadata ?: return null
				LineMarker(element, typeMetadata)
			}
			else -> null
		}
	}
	
	class LineMarker(element: ParadoxScriptProperty,typeMetadata: ParadoxTypeMetadata) : LineMarkerInfo<PsiElement>(
		element.propertyKey.let { it.propertyKeyId ?: it.quotedPropertyKeyId!! },
		element.textRange,
		definitionGutterIcon,
		{ _tooltip(typeMetadata.name.escapeXml(),typeMetadata.type) },
		{ mouseEvent, _ ->
			val project = element.project
			val scope = element.resolveScope
			val elements = findScriptProperties(typeMetadata.name, project, scope).toTypedArray()
			when(elements.size) {
				0 -> {}
				1 -> OpenSourceUtil.navigate(true, elements.first())
				else -> NavigationUtil.getPsiElementPopup(elements, _title).show(RelativePoint(mouseEvent))
			}
		},
		GutterIconRenderer.Alignment.LEFT,
		{ _name }
	)
}