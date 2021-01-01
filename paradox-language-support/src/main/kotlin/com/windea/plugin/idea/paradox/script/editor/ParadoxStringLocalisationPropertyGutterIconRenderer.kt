package com.windea.plugin.idea.paradox.script.editor

import com.intellij.codeInsight.navigation.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*
import java.awt.event.*

class ParadoxStringLocalisationPropertyGutterIconRenderer(
	private val name: String,
	private val properties: Array<ParadoxLocalisationProperty>
): GutterIconRenderer(), DumbAware {
	companion object{
		private val title = message("paradox.localisation.gutterIcon.property.title")
		private fun tooltip(name:String) = message("paradox.localisation.gutterIcon.property.tooltip", name)
	}
	
	private val tooltip = tooltip(name)
	
	override fun getIcon() = localisationPropertyGutterIcon
	override fun getTooltipText() = tooltip
	override fun getClickAction() = NavigateAction(properties)
	override fun isNavigateAction() = true
	override fun equals(other: Any?) = other is ParadoxStringLocalisationPropertyGutterIconRenderer && name == other.name
	override fun hashCode() = name.hashCode()
	
	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val elements: Array<out NavigatablePsiElement>,
	) : AnAction() {
		override fun actionPerformed(e: AnActionEvent) {
			//如果只有一个，则直接导航，否则弹出popup再导航
			if(elements.size == 1) {
				OpenSourceUtil.navigate(true, elements.first())
			} else {
				NavigationUtil.getPsiElementPopup(elements, title).show(RelativePoint(e.inputEvent as MouseEvent))
			}
		}
	}
}