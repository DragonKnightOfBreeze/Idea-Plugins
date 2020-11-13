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
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.settings.*
import java.awt.*
import java.awt.event.*

class StellarisScriptAnnotator : Annotator, DumbAware {
	class ScriptPropertyGutterIconRenderer(
		private val name: String,
		private vararg val properties: StellarisScriptProperty,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = message("stellaris.script.annotator.ScriptProperty", name)
		private val title = message("stellaris.script.annotator.ScriptProperty.title")
		
		override fun getIcon() = scriptPropertyGutterIcon
		
		override fun getTooltipText() = tooltip
		
		override fun getClickAction() = NavigateAction(title, *properties)
		
		override fun isNavigateAction() = true
		
		override fun equals(other: Any?) = other is ScriptPropertyGutterIconRenderer && name == other.name
		
		override fun hashCode() = name.hashCode()
	}
	
	class LocalizationPropertyGutterIconRenderer(
		private val name: String,
		private vararg val properties: StellarisLocalizationProperty,
	) : GutterIconRenderer(), DumbAware {
		private val tooltip = message("stellaris.script.annotator.localizationProperty", name)
		private val title = message("stellaris.script.annotator.localizationProperty.title")
		
		override fun getIcon() = localizationPropertyGutterIcon
		
		override fun getTooltipText() = tooltip
		
		override fun getClickAction() = NavigateAction(title, *properties)
		
		override fun isNavigateAction() = true
		
		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer && name == other.name
		
		override fun hashCode() = name.hashCode()
	}
	
	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val title: String,
		private vararg val elements: NavigatablePsiElement,
	) : AnAction() {
		override fun actionPerformed(e: AnActionEvent) {
			//如果只有一个，则直接导航，否则弹出popup再导航
			if(elements.size == 1) {
				OpenSourceUtil.navigate(true, elements.first())
			} else {
				NavigationUtil.getPsiElementPopup(elements, title)
					.show(RelativePoint(e.inputEvent as MouseEvent))
			}
		}
	}
	
	class ColorGutterIconRenderer(
		private val color: Color,
	) : GutterIconRenderer(), DumbAware {
		override fun getIcon() = ColorIcon(12, color)
		
		override fun isNavigateAction() = false
		
		override fun equals(other: Any?) = other is ColorGutterIconRenderer && color == other.color
		
		override fun hashCode() = color.hashCode()
	}
	
	private val state = StellarisSettingsState.getInstance()
	
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is StellarisScriptProperty -> {
				//如果配置解析内部引用 - 否则不解析，提高脚本文件的语法解析速度
				//关联scriptPropertyName和包含对应名称并作为合法前缀的localizationProperty
				if(state.resolveInternalReferences) {
					if(element.parent is StellarisScriptRootBlock) {
						val name = element.name ?: return
						val propertyGroups = findLocalizationProperties(element.project).filter {
							val propertyName = it.name ?: return@filter false
							name.equalsOrIsPrefixOf(propertyName)
						}.groupBy{ it.name!! }
						for((n,props) in propertyGroups) {
							val properties = props.toTypedArray()
							holder.newSilentAnnotation(INFORMATION)
								.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(n,*properties))
								.create()
						}
					}
				}
			}
			//字符串可能是script property、localization property
			is StellarisScriptString -> {
				//如果配置解析内部引用 - 否则不解析，提高脚本文件的语法解析速度
				//关联string和对应名称的scriptProperty/localizationProperty
				if(state.resolveInternalReferences) {
					val name = element.text.unquote()
					val reference = element.reference ?: return
					val resolves = reference.multiResolve(false)
					when {
						resolves.isEmpty() -> return
						reference.resolveAsLocalizationProperty -> {
							val properties = resolves.mapArray { it.element as StellarisLocalizationProperty }
							holder.newSilentAnnotation(INFORMATION)
								.textAttributes(StellarisScriptAttributesKeys.LOCALIZATION_PROPERTY_REFERENCE_KEY)
								.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(name, *properties))
								.create()
						}
						else -> {
							val properties = resolves.mapArray { it.element as StellarisScriptProperty }
							holder.newSilentAnnotation(INFORMATION)
								.textAttributes(StellarisScriptAttributesKeys.SCRIPT_PROPERTY_REFERENCE_KEY)
								.gutterIconRenderer(ScriptPropertyGutterIconRenderer(name, *properties))
								.create()
						}
					}
				}
			}
		}
	}
	
	private fun String.equalsOrIsPrefixOf(value:String):Boolean{
		return value == this || value.startsWith(this) && value[this.length] == '_'
	}
}
