package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.intentions.*
import com.windea.plugin.idea.stellaris.localization.psi.*

class StellarisLocalizationAnnotator : Annotator, DumbAware {
	class ColorGutterIconRenderer(
		private val color: StellarisColor
	) : GutterIconRenderer(), DumbAware {
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
					val localeId = element.name ?: return
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedLocale", localeId))
						.withFix(ChangeLocaleIntention.instance)
						.create()
				}
			}
			is StellarisLocalizationSerialNumber -> {
				if(element.serialNumber == null) {
					val serialNumberId = element.name ?: return
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedSerialNumber", serialNumberId))
						.withFix(ChangeSerialNumberIntention.instance)
						.create()
				}
			}
			//如果是颜色文本，则为颜色代码文本加上对应的颜色
			is StellarisLocalizationColorfulText -> {
				val colorId = element.name ?: return
				if(element.color == null) {
					holder.newAnnotation(WARNING, message("stellaris.localization.annotator.unsupportedColor", colorId))
						.withFix(ChangeColorIntention.instance)
						.create()
				} else {
					annotateColor(colorId, holder, element)
				}
			}
			else -> {
				//如果是属性引用参数，且长度为1，则认为可能是颜色码，则加上对应的颜色
				if(element.elementType == StellarisLocalizationTypes.PROPERTY_REFERENCE_PARAMETER && element.textLength == 1) {
					val colorId = element.text
					val color = StellarisColor.map[colorId]
					if(color != null) {
						annotateColorForPropertyReferenceParameter(colorId, holder, element)
					}
				}
			}
		}
	}

	private fun annotateColor(colorId: String, holder: AnnotationHolder, element: StellarisLocalizationColorfulText) {
		val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			//.gutterIconRenderer(ColorGutterIconRenderer(element.color!!))
			.range(element.nameIdentifier!!).textAttributes(attributesKey)
			.create()
	}

	private fun annotateColorForPropertyReferenceParameter(colorId: String, holder: AnnotationHolder, element: PsiElement) {
		val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			//.gutterIconRenderer(ColorGutterIconRenderer(element.color!!))
			.range(element).textAttributes(attributesKey)
			.create()
	}
}
