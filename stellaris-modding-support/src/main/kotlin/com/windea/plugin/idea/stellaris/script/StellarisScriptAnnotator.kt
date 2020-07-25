package com.windea.plugin.idea.stellaris.script

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.pom.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.highlighter.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptAnnotator : Annotator ,DumbAware{
	class ScriptPropertyGutterIconRenderer(
		private val property:StellarisScriptProperty
	): GutterIconRenderer(),DumbAware {
		override fun getIcon() = externalLocalizationPropertyIcon

		override fun getTooltipText() = message("stellaris.script.annotator.externalScriptProperty", property.name!!)

		override fun getClickAction(): AnAction? = NavigateAction(property)

		override fun equals(other: Any?) = other is ScriptPropertyGutterIconRenderer && property == other.property

		override fun hashCode() = property.hashCode()
	}

	class LocalizationPropertyGutterIconRenderer(
		private val property:StellarisLocalizationProperty
	): GutterIconRenderer(),DumbAware {
		override fun getIcon() = externalScriptPropertyIcon

		override fun getTooltipText() = message("stellaris.script.annotator.externalLocalizationProperty", property.name!!)

		override fun getClickAction(): AnAction? = NavigateAction(property)

		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer && property == other.property

		override fun hashCode() = property.hashCode()
	}

	private class NavigateAction(
		private val navigable: Navigatable
	):AnAction(){
		override fun actionPerformed(e: AnActionEvent) {
			OpenSourceUtil.navigate(true,navigable)
		}
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element){
			//字符串可能是script property、localization property
			is StellarisScriptStringLiteral -> {
				val resolve = element.reference?.resolve() ?: return
				if(resolve is StellarisLocalizationProperty) {
					holder.newSilentAnnotation(INFORMATION)
						.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(resolve))
						.textAttributes(StellarisLocalizationAttributesKeys.PROPERTY_KEY_KEY).create()
				}
				if(resolve is StellarisScriptProperty) {
					holder.newSilentAnnotation(INFORMATION)
						.gutterIconRenderer(ScriptPropertyGutterIconRenderer(resolve))
						.textAttributes(StellarisScriptAttributesKeys.PROPERTY_KEY_KEY).create()
				}
			}
			//NOTE 不能这样做：可能是外部变量，而来源难以判断
			//is StellarisScriptVariableReference -> {
			//	val resolve = element.reference?.resolve()
			//	if(resolve == null) {
			//		holder.newAnnotation(WARNING, message("stellaris.script.annotator.unresolvedVariable"))
			//		.textAttributes(VARIABLE_KEY).create()
			//	}
			//}
		}
	}
}
