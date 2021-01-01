package com.windea.plugin.idea.paradox.localisation.editor

import com.intellij.codeInsight.daemon.*
import com.intellij.codeInsight.navigation.*
import com.intellij.openapi.editor.markup.*
import com.intellij.psi.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ParadoxLocalisationPropertyLineMarkerProvider : LineMarkerProviderDescriptor() {
	companion object {
		private val _name = message("paradox.localisation.gutterIcon.property")
		private val _title = message("paradox.localisation.gutterIcon.property.title")
		private fun _tooltip(name: String) = message("paradox.localisation.gutterIcon.property.tooltip", name)
	}
	
	override fun getName() = _name
	
	override fun getIcon() = localisationPropertyGutterIcon
	
	override fun getLineMarkerInfo(element: PsiElement): MyLineMarkerInfo? {
		println("getLineMarkerInfo")
		return if(element is ParadoxLocalisationProperty) MyLineMarkerInfo(element) else null
	}
	
	class MyLineMarkerInfo(element: ParadoxLocalisationProperty) : LineMarkerInfo<PsiElement>(
		element.propertyKey.propertyKeyId,
		element.textRange,
		localisationPropertyGutterIcon,
		{ _tooltip(it.text.unquote()) },
		{ mouseEvent, _ ->
			val name = element.name
			val project = element.project
			val scope = element.resolveScope
			val elements = findLocalisationProperties(name, project, null, scope).toTypedArray()
			if(elements.size == 1) {
				OpenSourceUtil.navigate(true, elements.first())
			} else {
				NavigationUtil.getPsiElementPopup(elements, _title).show(RelativePoint(mouseEvent))
			}
		},
		GutterIconRenderer.Alignment.LEFT,
		{ _name }
	)
}