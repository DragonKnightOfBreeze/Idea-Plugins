package com.windea.plugin.idea.paradox.script.editor

import com.intellij.codeInsight.navigation.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.search.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.paradox.*
import java.awt.event.*

class ParadoxScriptPropertyGutterIconRenderer(
	private val name:String,
	private val project: Project,
	private val scope: GlobalSearchScope
): GutterIconRenderer(), DumbAware {
	companion object{
		private val title = message("paradox.script.gutterIcon.property.title")
		private fun tooltip(name:String) = message("paradox.script.gutterIcon.property.tooltip", name)
	}

	private val tooltip = tooltip(name)
	
	override fun getIcon() = scriptPropertyGutterIcon
	override fun getTooltipText() = tooltip
	override fun getClickAction() = NavigateAction(name, project,scope)
	override fun isNavigateAction() = true
	override fun equals(other: Any?) = other is ParadoxScriptPropertyGutterIconRenderer && name == other.name
	override fun hashCode() = name.hashCode()
	
	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val name:String, 
		private val project: Project, 
		private val scope: GlobalSearchScope
	) : AnAction() {
		override fun actionPerformed(e: AnActionEvent) {
			val elements = findScriptProperties(name, project,scope).toTypedArray()
			//如果只有一个，则直接导航，否则弹出popup再导航
			when(elements.size) {
				0 -> return
				1 -> OpenSourceUtil.navigate(true, elements.first())
				else -> NavigationUtil.getPsiElementPopup(elements, title).show(RelativePoint(e.inputEvent as MouseEvent))
			}
		}
	}
}