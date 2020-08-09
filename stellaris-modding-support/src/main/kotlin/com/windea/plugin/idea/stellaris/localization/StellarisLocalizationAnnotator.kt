package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.enums.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.intentions.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationAnnotator : Annotator, DumbAware {
	class ColorGutterIconRenderer(
		private val color:StellarisColor
	):GutterIconRenderer(),DumbAware{
		override fun getIcon() = color.gutterIcon

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is ColorGutterIconRenderer && color == other.color

		override fun hashCode() = color.hashCode()
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			//如果有无法解析的枚举项，则报错
			is StellarisLocalizationLocale -> {
				if(element.locale == null) {
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedLocale"))
						.withFix(ChangeLocaleIntention.instance)
						.create()
				}
			}
			is StellarisLocalizationSerialNumber -> {
				if(element.serialNumber == null) {
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedSerialNumber"))
						.withFix(ChangeSerialNumberIntention.instance)
						.create()
				}
			}
			//如果是颜色文本，则为颜色代码文本加粗，并加上对应的颜色
			//取消在页面右边显示彩色文本的颜色（因为可能有很多）
			is StellarisLocalizationColorfulText -> {
				if(element.color == null) {
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedColor"))
						.withFix(ChangeColorIntention.instance)
						.create()
				} else {
					val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[element.name] ?: return
					holder.newSilentAnnotation(INFORMATION)
						//.gutterIconRenderer(ColorGutterIconRenderer(element.color!!))
						.range(element.nameIdentifier!!).textAttributes(attributesKey)
						.create()
				}
			}
		}
	}
}
