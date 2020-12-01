package com.windea.plugin.idea.pdx.localisation

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
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.message
import com.windea.plugin.idea.pdx.localisation.highlighter.*
import com.windea.plugin.idea.pdx.localisation.intentions.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import java.awt.event.*

class PdxLocalisationAnnotator : Annotator, DumbAware {
	internal class LocalisationPropertyGutterIconRenderer(
		private val name:String,
		private val project:Project
	): GutterIconRenderer(),DumbAware {
		private val tooltip = message("pdx.localisation.annotator.localisationProperty",name)
		private val title = message("pdx.localisation.annotator.localisationProperty.title")

		override fun getIcon() = localisationPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = LocalisationPropertyNavigateAction(title,name,project)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is LocalisationPropertyGutterIconRenderer  && name == other.name
		override fun hashCode() = name.hashCode()
	}

	@Suppress("ComponentNotRegistered")
	internal class LocalisationPropertyNavigateAction(
		private val title: String,
		private val name:String,
		private val project:Project
	) : AnAction() {
		//懒加载
		private val elements by lazy{ findLocalisationProperties(name,project).toTypedArray() }

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
			is PdxLocalisationProperty -> annotateProperty(element, holder)
			is PdxLocalisationLocale -> annotateLocale(element, holder)
			is PdxLocalisationSerialNumber -> annotateSerialNumber(element, holder)
			is PdxLocalisationColorfulText -> annotateColorfulText(element, holder)
			is PdxLocalisationPropertyReference -> annotatePropertyReference(element, holder)
		}
	}
	
	private fun annotateProperty(element: PdxLocalisationProperty, holder: AnnotationHolder) {
		//注明所有同名的属性
		val name = element.name
		holder.newSilentAnnotation(INFORMATION)
			.gutterIconRenderer(LocalisationPropertyGutterIconRenderer(name, element.project))
			.create()
	}
	
	private fun annotateLocale(element: PdxLocalisationLocale, holder: AnnotationHolder) {
		if(element.pdxLocale == null) {
			val localeId = element.name ?: return
			holder.newAnnotation(ERROR, message("pdx.localisation.annotator.unsupportedLocale", localeId))
				.withFix(ChangeLocaleIntention.instance)
				.create()
		}
	}
	
	private fun annotateSerialNumber(element: PdxLocalisationSerialNumber, holder: AnnotationHolder) {
		if(element.pdxSerialNumber == null) {
			val serialNumberId = element.name ?: return
			holder.newAnnotation(ERROR, message("pdx.localisation.annotator.unsupportedSerialNumber", serialNumberId))
				.withFix(ChangeSerialNumberIntention.instance)
				.create()
		}
	}
	
	private fun annotateColorfulText(element: PdxLocalisationColorfulText, holder: AnnotationHolder) {
		//如果是颜色文本，则为颜色代码文本加上对应的颜色
		val colorId = element.name ?: return
		if(element.pdxColor == null) {
			holder.newAnnotation(ERROR, message("pdx.localisation.annotator.unsupportedColor", colorId))
				.withFix(ChangeColorIntention.instance)
				.create()
		} else {
			val e = element.colorCode
			if(e != null) {
				annotateColor(colorId, holder, e.textRange)
			}
		}
	}
	
	private fun annotatePropertyReference(element: PdxLocalisationPropertyReference, holder: AnnotationHolder) {
		//如果是属性引用，需要为属性引用参数加上对应的颜色
		val color = element.pdxColor
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
		val attributesKey = PdxLocalisationAttributesKeys.COLOR_ID_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(range).textAttributes(attributesKey)
			.create()
	}
}
