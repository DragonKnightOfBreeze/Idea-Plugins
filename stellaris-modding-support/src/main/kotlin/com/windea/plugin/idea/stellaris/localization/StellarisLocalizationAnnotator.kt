package com.windea.plugin.idea.stellaris.localization

import com.intellij.codeInsight.navigation.*
import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.editor.markup.*
import com.intellij.openapi.project.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.intellij.ui.awt.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.message
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.intentions.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import java.awt.event.*

class StellarisLocalizationAnnotator : Annotator, DumbAware {
	internal class LocalizationPropertyGutterIconRenderer(
		private val name:String,
		private val project:Project
	): GutterIconRenderer(),DumbAware {
		private val tooltip = message("stellaris.localization.annotator.localizationProperty",name)
		private val title = message("stellaris.localization.annotator.localizationProperty.title")

		override fun getIcon() = localizationPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = LocalizationPropertyNavigateAction(title,name,project)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is LocalizationPropertyGutterIconRenderer  && name == other.name
		override fun hashCode() = name.hashCode()
	}

	@Suppress("ComponentNotRegistered")
	internal class LocalizationPropertyNavigateAction(
		private val title: String,
		private val name:String,
		private val project:Project
	) : AnAction() {
		//懒加载
		private val elements by lazy{ findLocalizationProperties(name,project).toTypedArray() }

		override fun actionPerformed(e: AnActionEvent) {
			//如果只有一个，则直接导航，否则弹出popup再导航
			if(elements.size == 1) {
				OpenSourceUtil.navigate(true, elements.first())
			} else {
				NavigationUtil.getPsiElementPopup(elements, title).show(RelativePoint(e.inputEvent as MouseEvent))
			}
		}
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is StellarisLocalizationProperty -> annotateProperty(element, holder)
			is StellarisLocalizationLocale -> annotateLocale(element, holder)
			is StellarisLocalizationSerialNumber -> annotateSerialNumber(element, holder)
			is StellarisLocalizationColorfulText -> annotateColorfulText(element, holder)
			is StellarisLocalizationPropertyReference -> annotatePropertyReference(element, holder)
		}
	}
	
	private fun annotateProperty(element: StellarisLocalizationProperty, holder: AnnotationHolder) {
		//注明所有同名的属性
		val name = element.name ?: return
		holder.newSilentAnnotation(INFORMATION)
			.gutterIconRenderer(LocalizationPropertyGutterIconRenderer(name, element.project))
			.create()
	}
	
	private fun annotateLocale(element: StellarisLocalizationLocale, holder: AnnotationHolder) {
		if(element.stellarisLocale == null) {
			val localeId = element.name ?: return
			holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedLocale", localeId))
				.withFix(ChangeLocaleIntention.instance)
				.create()
		}
	}
	
	private fun annotateSerialNumber(element: StellarisLocalizationSerialNumber, holder: AnnotationHolder) {
		if(element.stellarisSerialNumber == null) {
			val serialNumberId = element.name ?: return
			holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedSerialNumber", serialNumberId))
				.withFix(ChangeSerialNumberIntention.instance)
				.create()
		}
	}
	
	private fun annotateColorfulText(element: StellarisLocalizationColorfulText, holder: AnnotationHolder) {
		//如果是颜色文本，则为颜色代码文本加上对应的颜色
		val colorId = element.name ?: return
		if(element.stellarisColor == null) {
			holder.newAnnotation(ERROR, message("stellaris.localization.annotator.unsupportedColor", colorId))
				.withFix(ChangeColorIntention.instance)
				.create()
		} else {
			val e = element.colorCode
			if(e != null) {
				annotateColor(colorId, holder, e.textRange)
			}
		}
	}
	
	private fun annotatePropertyReference(element: StellarisLocalizationPropertyReference, holder: AnnotationHolder) {
		//如果是属性引用，需要为属性引用参数加上对应的颜色
		val color = element.stellarisColor
		if(color != null) {
			val colorId = color.key
			val e = element.propertyReferenceParameter
			if(e != null) {
				val startOffset = e.startOffset
				annotateColor(colorId, holder, TextRange(startOffset, startOffset + 1))
			}
		}
	}
	
	private fun annotateColor(colorId: String, holder: AnnotationHolder, range: TextRange) {
		val attributesKey = StellarisLocalizationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(range).textAttributes(attributesKey)
			.create()
	}
}
