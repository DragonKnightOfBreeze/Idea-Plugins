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

class ParadoxDefinitionLocalisationLineMarkerProvider: LineMarkerProviderDescriptor(){
	companion object {
		private val _name = message("paradox.script.gutterIcon.definitionLocalisation")
		private val _title = message("paradox.script.gutterIcon.definitionLocalisation.title")
		private fun _tooltip(name: String,type:String) = message("paradox.script.gutterIcon.definitionLocalisation.tooltip", name, type)
	}
	
	override fun getName() = _name
	
	override fun getIcon() = definitionLocalisationGutterIcon
	
	override fun getLineMarkerInfo(element: PsiElement): LineMarker? {
		return when(element) {
			is ParadoxScriptProperty -> LineMarker(element, element.paradoxTypeMetadata ?: return null)
			else -> null
		}
	}
	
	class LineMarker(element: ParadoxScriptProperty,typeMetadata: ParadoxTypeMetadata) : LineMarkerInfo<PsiElement>(
		element.propertyKey.let { it.propertyKeyId ?: it.quotedPropertyKeyId!! },
		element.textRange,
		definitionLocalisationGutterIcon,
		{ typeMetadata.localisation.entries.joinToString("<br>"){ (k,v)-> _tooltip(v,k.name) }},
		{ mouseEvent, _ ->
			val names = typeMetadata.localisation.values
			val project = element.project
			val scope = element.resolveScope
			val elements = findLocalisationProperties(names, null, project, scope).toTypedArray()
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