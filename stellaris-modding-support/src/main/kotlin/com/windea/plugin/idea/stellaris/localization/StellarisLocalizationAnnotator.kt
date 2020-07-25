package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.intentions.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationAnnotator : Annotator, DumbAware {
	class ColorGutterIconRenderer(
		private val color:StellarisColor
	):GutterIconRenderer(),DumbAware{
		override fun getIcon() = color.gutterIcon

		override fun equals(other: Any?) = other is ColorGutterIconRenderer && color == other.color

		override fun hashCode() = color.hashCode()
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			//如果有无法解析的枚举项，则报错
			is StellarisLocalizationLocale -> {
				if(element.locale == null) {
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedLocale"))
						.withFix(StellarisLocalizationChangeLocaleIntention).create()
				}
			}
			is StellarisLocalizationSerialNumber -> {
				if(element.serialNumber == null) {
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedSerialNumber"))
						.withFix(StellarisLocalizationChangeSerialNumberIntention).create()
				}
			}
			//如果是颜色文本，则为颜色代码文本加粗，并加上对应的颜色
			is StellarisLocalizationColorfulText -> {
				if(element.color == null) {
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedColor"))
						.withFix(StellarisLocalizationChangeColorIntention).create()
				} else {
					val attributesKey = StellarisLocalizationAttributesKeys.COLOR_CODE_KEYS[element.name] ?: return
					holder.newSilentAnnotation(INFORMATION)
						.gutterIconRenderer(ColorGutterIconRenderer(element.color!!))
						.range(element.colorfulTextId!!).textAttributes(attributesKey).create()
				}
			}
		}
	}
}
