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

class ParadoxRelatedLocalisationPropertiesGutterIconRenderer(
	private val names: Array<String>,
	private val properties: Array<ParadoxLocalisationProperty>,
) : GutterIconRenderer(), DumbAware {
	companion object{
		private val _title = message("paradox.script.gutterIcon.relatedLocalisationProperties.title")
		private fun _tooltip(name:String) = message("paradox.script.gutterIcon.relatedLocalisationProperties.tooltip", name)
	}
	
	private val tooltip = names.joinToString("<br>") { name -> _tooltip(name.escapeXml()) }
	
	override fun getIcon() = localisationPropertyGutterIcon
	override fun getTooltipText() = tooltip
	override fun getClickAction() = NavigateAction(properties)
	override fun isNavigateAction() = true
	override fun equals(other: Any?) = other is ParadoxRelatedLocalisationPropertiesGutterIconRenderer && names.contentEquals(other.names)
	override fun hashCode() = names.contentHashCode()
	
	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val elements: Array<out NavigatablePsiElement>,
	) : AnAction() {
		override fun actionPerformed(e: AnActionEvent) {
			//如果只有一个，则直接导航，否则弹出popup再导航
			when(elements.size) {
				0 -> return
				1 -> OpenSourceUtil.navigate(true, elements.first())
				else -> NavigationUtil.getPsiElementPopup(elements, _title).show(RelativePoint(e.inputEvent as MouseEvent))
			}
		}
	}
}