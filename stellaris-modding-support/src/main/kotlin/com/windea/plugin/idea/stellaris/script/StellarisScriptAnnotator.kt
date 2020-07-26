@file:Suppress("UNCHECKED_CAST")

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
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*

@ExtensionPoint
class StellarisScriptAnnotator : Annotator ,DumbAware{
	class ScriptPropertyGutterIconRenderer(
		private val name:String,
		private vararg val properties: StellarisScriptProperty
	): GutterIconRenderer(),DumbAware {
		override fun getIcon() = externalScriptPropertyIcon

		override fun getTooltipText() = message("stellaris.script.annotator.externalScriptProperty", name)

		override fun getClickAction() = NavigateAction(*properties)

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is ScriptPropertyGutterIconRenderer && name == other.name

		override fun hashCode() = name.hashCode()
	}

	class LocalizationPropertyGutterIconRenderer(
		private val name:String,
		private vararg val properties: StellarisLocalizationProperty
	): GutterIconRenderer(),DumbAware {
		override fun getIcon() = externalLocalizationPropertyIcon

		override fun getTooltipText() = message("stellaris.script.annotator.externalLocalizationProperty", name)

		override fun getClickAction() = NavigateAction(*properties)

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer  && name == other.name

		override fun hashCode() = name.hashCode()
	}

	class NavigateAction(
		private vararg val navigatables: Navigatable
	):AnAction(){
		override fun actionPerformed(e: AnActionEvent) {
			OpenSourceUtil.navigate(true,*navigatables)
		}
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element){
			//字符串可能是script property、localization property
			//只显示gutterIcon，不更改文本颜色
			//本地化属性可以有多个
			is StellarisScriptString -> {
				val name = element.text.unquote()
				val reference = element.reference?:return
				val resolves = reference.multiResolve(false).mapArray { it.element }
				when {
					resolves.isEmpty() -> return
					reference.resolveAsLocalizationProperty -> {
						resolves as Array<StellarisLocalizationProperty>
						holder.newSilentAnnotation(INFORMATION)
							.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(name,*resolves))
							//.textAttributes(StellarisLocalizationAttributesKeys.PROPERTY_KEY_KEY)
							.create()
					}
					else -> {
						resolves as Array<StellarisScriptProperty>
						holder.newSilentAnnotation(INFORMATION)
							.gutterIconRenderer(ScriptPropertyGutterIconRenderer(name,*resolves))
							//.textAttributes(StellarisScriptAttributesKeys.PROPERTY_KEY_KEY)
							.create()
					}
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
