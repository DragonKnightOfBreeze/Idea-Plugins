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

class StellarisScriptAnnotator : Annotator ,DumbAware{
	class ScriptPropertyGutterIconRenderer(
		private val name:String,
		private vararg val properties: StellarisScriptProperty
	): GutterIconRenderer(),DumbAware {
		private val tooltip = message("stellaris.script.annotator.externalScriptProperty",name)
		private val title = message("stellaris.script.annotator.externalScriptProperty.title")

		override fun getIcon() = scriptPropertyGutterIcon

		override fun getTooltipText() = tooltip

		override fun getClickAction() = NavigateAction(title,*properties)

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is ScriptPropertyGutterIconRenderer && name == other.name

		override fun hashCode() = name.hashCode()
	}

	class LocalizationPropertyGutterIconRenderer(
		private val name:String,
		private vararg val properties: StellarisLocalizationProperty
	): GutterIconRenderer(),DumbAware {
		private val tooltip = message("stellaris.script.annotator.localizationProperty",name)
		private val title = message("stellaris.script.annotator.localizationProperty.title")

		override fun getIcon() = localizationPropertyGutterIcon

		override fun getTooltipText() = tooltip

		override fun getClickAction() = NavigateAction(title, *properties)

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer  && name == other.name

		override fun hashCode() = name.hashCode()
	}

	@Suppress("ComponentNotRegistered")
	class NavigateAction(
		private val title:String,
		private vararg val elements: NavigatablePsiElement
	):AnAction(){
		override fun actionPerformed(e: AnActionEvent) {
			//如果只有一个，则直接导航，否则弹出popup再导航
			if(elements.size == 1){
				OpenSourceUtil.navigate(true,elements.first())
			}else{
				NavigationUtil.getPsiElementPopup(elements,title)
					.show(RelativePoint(e.inputEvent as  MouseEvent))
			}
		}
	}

	class ColorGutterIconRenderer(
		private val color: Color
	):GutterIconRenderer(),DumbAware{
		override fun getIcon() = ColorIcon(12,color)

		override fun isNavigateAction() = false

		override fun equals(other: Any?) = other is ColorGutterIconRenderer && color == other.color

		override fun hashCode() = color.hashCode()
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element){
			//字符串可能是script property、localization property
			//只显示gutterIcon，不更改文本颜色
			//本地化属性可以有多个
			is StellarisScriptString -> {
				//如果配置解析内部引用 - 否则不解析，提高脚本文件的语法解析速度
				if(StellarisSettingsState.getInstance().resolveInternalReferences) {
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
}
