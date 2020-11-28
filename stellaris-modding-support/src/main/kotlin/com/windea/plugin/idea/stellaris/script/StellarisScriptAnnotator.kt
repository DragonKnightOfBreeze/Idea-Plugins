@file:Suppress("UNCHECKED_CAST")

package com.windea.plugin.idea.stellaris.script

import com.intellij.codeInsight.navigation.*
import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.message
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.settings.*
import java.awt.*
import java.awt.event.*

class StellarisScriptAnnotator : Annotator, DumbAware {
	class OverriddenScriptPropertiesGutterIconRenderer(
		private val name: String,
		private val properties: Array<StellarisScriptProperty>,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = message("stellaris.script.annotator.overriddenScriptProperties", name)
		private val title = message("stellaris.script.annotator.overriddenScriptProperties.title")
		
		override fun getIcon() = overriddenScriptPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = NavigateAction(title, properties)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is OverriddenScriptPropertiesGutterIconRenderer && name == other.name
		override fun hashCode() = name.hashCode()
	}
	
	class ScriptPropertyGutterIconRenderer(
		private val name: String,
		private val properties: Array<StellarisScriptProperty>,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = message("stellaris.script.annotator.scriptProperty", name)
		private val title = message("stellaris.script.annotator.scriptProperty.title")
		
		override fun getIcon() = scriptPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = NavigateAction(title, properties)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is ScriptPropertyGutterIconRenderer && name == other.name
		override fun hashCode() = name.hashCode()
	}
	
	class LocalizationPropertyGutterIconRenderer(
		private val name: String,
		private val properties: Array<StellarisLocalizationProperty>,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = message("stellaris.script.annotator.localizationProperty", name)
		private val title = message("stellaris.script.annotator.localizationProperty.title")
		
		override fun getIcon() = localizationPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = NavigateAction(title, properties)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer && name == other.name
		override fun hashCode() = name.hashCode()
	}
	
	class RelatedLocalizationPropertiesGutterIconRenderer(
		private val names: Array<String>,
		private val properties: Array<StellarisLocalizationProperty>,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = names.joinToString("<br>") { name ->
			message("stellaris.script.annotator.relatedLocalizationProperties", name)
		}
		private val title = message("stellaris.script.annotator.relatedLocalizationProperties.title")
		
		override fun getIcon() = localizationPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = NavigateAction(title, properties)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is RelatedLocalizationPropertiesGutterIconRenderer && names.contentEquals(other.names)
		override fun hashCode() = names.contentHashCode()
	}
	
	class ColorGutterIconRenderer(
		private val color: Color,
	) : GutterIconRenderer(), DumbAware {
		override fun getIcon() = ColorIcon(12, color)
		override fun isNavigateAction() = false
		override fun equals(other: Any?) = other is ColorGutterIconRenderer && color == other.color
		override fun hashCode() = color.hashCode()
	}
	
	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val title: String,
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
	
	private val state = StellarisSettingsState.getInstance()
	
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is StellarisScriptProperty -> annotateProperty(element, holder)
			is StellarisScriptString -> annotateString(element, holder)
		}
	}
	
	private fun annotateProperty(element: StellarisScriptProperty, holder: AnnotationHolder) {
		if(!state.resolveInternalReferences) return
		//过滤例外情况
		if(element.parent !is StellarisScriptRootBlock || !element.stellarisPath.contains('/')) return
		val name = element.name?:return
		val project = element.project
		
		//关联scriptPropertyName和包含对应名称并作为合法前缀的localizationProperty
		val relatedLocalizationProperties = element.findRelatedLocalizationProperties().toTypedArray()
		if(relatedLocalizationProperties.isNotEmpty()) {
			val names = relatedLocalizationProperties.mapTo(linkedSetOf()) { it.name!! }.toTypedArray()
			holder.newSilentAnnotation(INFORMATION)
				.gutterIconRenderer(RelatedLocalizationPropertiesGutterIconRenderer(names, relatedLocalizationProperties))
				.create()
		}
		
		//处理被重载的情况
		val overriddenScriptPropertyList = findScriptProperties(name,project)
		if(overriddenScriptPropertyList.size > 1){
			val overriddenScriptProperties = overriddenScriptPropertyList.filter{
				!PsiManager.getInstance(project).areElementsEquivalent(element,it)
			}.toTypedArray()
			holder.newSilentAnnotation(INFORMATION)
				.gutterIconRenderer(OverriddenScriptPropertiesGutterIconRenderer(name, overriddenScriptProperties))
				.create()
		}
	}
	
	private fun annotateString(element: StellarisScriptString, holder: AnnotationHolder) {
		if(!state.resolveInternalReferences) return
		//过滤非法情况
		val name = element.value
		if(name.isInvalidPropertyName()) return
		val project = element.project
		
		//关联string和对应名称的scriptProperty/localizationProperty
		val scriptProperties = findScriptProperties(name, project).toTypedArray()
		if(scriptProperties.isNotEmpty()) {
			holder.newSilentAnnotation(INFORMATION)
				.textAttributes(StellarisScriptAttributesKeys.SCRIPT_PROPERTY_REFERENCE_KEY)
				.gutterIconRenderer(ScriptPropertyGutterIconRenderer(name, scriptProperties))
				.create()
			return
		}
		val localizationProperties = findLocalizationProperties(name, project).toTypedArray()
		if(localizationProperties.isNotEmpty()) {
			holder.newSilentAnnotation(INFORMATION)
				.textAttributes(StellarisScriptAttributesKeys.LOCALIZATION_PROPERTY_REFERENCE_KEY)
				.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(name, localizationProperties))
				.create()
		}
	}
}
