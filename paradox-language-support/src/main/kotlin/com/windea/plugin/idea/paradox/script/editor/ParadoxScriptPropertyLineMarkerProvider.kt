package com.windea.plugin.idea.paradox.script.editor

import com.intellij.codeInsight.daemon.*
import com.intellij.codeInsight.navigation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.psi.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.script.psi.*

class ParadoxScriptPropertyLineMarkerProvider : LineMarkerProviderDescriptor() {
	companion object {
		private val _name = message("paradox.script.gutterIcon.property")
		private val _title = message("paradox.script.gutterIcon.property.title")
		private fun _tooltip(name: String) = message("paradox.script.gutterIcon.property.tooltip", name)
	}
	
	override fun getName() = _name
	
	override fun getIcon() = scriptPropertyGutterIcon
	
	override fun getLineMarkerInfo(element: PsiElement): MyLineMarkerInfo? {
		return when(element) {
			is ParadoxScriptProperty -> if(element.isRootProperty()) MyLineMarkerInfo(element) else null
			else -> null
		}
	}
	
	class MyLineMarkerInfo(element: ParadoxScriptProperty) : LineMarkerInfo<PsiElement>(
		element.propertyKey,
		element.textRange,
		localisationPropertyGutterIcon,
		{ _tooltip(it.text.unquote()) },
		{ mouseEvent, _ ->
			val name = element.name
			val project = element.project
			val scope = element.resolveScope
			val elements = findScriptProperties(name, project, scope).toTypedArray()
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