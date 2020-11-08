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
import com.windea.plugin.idea.stellaris.script.*

class StellarisLocalizationAnnotator : Annotator, DumbAware {
	class LocalizationPropertyGutterIconRenderer(
		private val name:String
	): GutterIconRenderer(),DumbAware {
		private val tooltip = message("stellaris.localization.annotator.localizationProperty",name)
		private val title = message("stellaris.localization.annotator.localizationProperty.title")

		override fun getIcon() = localizationPropertyGutterIcon

		override fun getTooltipText() = tooltip

		override fun getClickAction() = null //TODO

		override fun isNavigateAction() = true

		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer  && name == other.name

		override fun hashCode() = name.hashCode()
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			//如果是本地化属性，则加上gutterIcon
			//TODO 可以点击gutterIcon导航到相同名字不同语言区域的本地化属性
			is StellarisLocalizationProperty ->{
				val name = element.name?:return
				holder.newSilentAnnotation(INFORMATION)
					.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(name))
					.create()
			}
			is StellarisLocalizationLocale -> {
				if(element.locale == null) {
					val localeId = element.name ?: return
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedLocale", localeId))
						.withFix(ChangeLocaleIntention.instance)
						.create()
				}
			}
			is StellarisLocalizationSerialNumber -> {
				if(element.serialNumber == null) {
					val serialNumberId = element.name ?: return
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedSerialNumber", serialNumberId))
						.withFix(ChangeSerialNumberIntention.instance)
						.create()
				}
			}
			//如果是颜色文本，则为颜色代码文本加上对应的颜色
			is StellarisLocalizationColorfulText -> {
				val colorId = element.name ?: return
				if(element.color == null) {
					holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedColor", colorId))
						.withFix(ChangeColorIntention.instance)
						.create()
				} else {
					annotateColor(colorId, holder, element)
				}
			}
			else -> {
				//如果是属性引用参数，则认为第一个字符可能是颜色码，则加上对应的颜色
				if(element.elementType == StellarisLocalizationTypes.PROPERTY_REFERENCE_PARAMETER) {
					val text = element.text
					if(text.firstOrNull()?.isUpperCase() == true) {
						val colorId = text.substring(0,1)
						val color = StellarisColor.map[colorId]
						if(color != null) {
							annotateColorForPropertyReferenceParameter(colorId, holder, element)
						}
					}
				}
			}
		}
	}

	private fun annotateColor(colorId: String, holder: AnnotationHolder, element: StellarisLocalizationColorfulText) {
		val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(element.nameIdentifier!!).textAttributes(attributesKey)
			.create()
	}

	private fun annotateColorForPropertyReferenceParameter(colorId: String, holder: AnnotationHolder, element: PsiElement) {
		val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(element).textAttributes(attributesKey)
			.create()
	}
}
