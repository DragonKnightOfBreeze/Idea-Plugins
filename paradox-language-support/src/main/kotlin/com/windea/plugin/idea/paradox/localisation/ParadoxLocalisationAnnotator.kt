package com.windea.plugin.idea.paradox.localisation

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
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message
import com.windea.plugin.idea.paradox.localisation.highlighter.*
import com.windea.plugin.idea.paradox.localisation.intentions.*
import com.windea.plugin.idea.paradox.localisation.psi.*
import java.awt.event.*

class ParadoxLocalisationAnnotator : Annotator, DumbAware {
	private class LocalisationPropertyGutterIconRenderer(
		private val name:String,
		private val project:Project
	): GutterIconRenderer(),DumbAware {
		companion object{
			private val title = message("paradox.localisation.annotator.localisationProperty.title")
			private fun tooltip(name:String) = message("paradox.localisation.annotator.localisationProperty.tooltip",name)
		}

		private val tooltip = tooltip(name)
		
		override fun getIcon() = localisationPropertyGutterIcon
		override fun getTooltipText() = tooltip
		override fun getClickAction() = LocalisationPropertyNavigateAction(title,name,project)
		override fun isNavigateAction() = true
		override fun equals(other: Any?) = other is LocalisationPropertyGutterIconRenderer  && name == other.name
		override fun hashCode() = name.hashCode()
	}

	@Suppress("ComponentNotRegistered")
	private class LocalisationPropertyNavigateAction(
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
			is ParadoxLocalisationProperty -> annotateProperty(element, holder)
			is ParadoxLocalisationLocale -> annotateLocale(element, holder)
			is ParadoxLocalisationSerialNumber -> annotateSerialNumber(element, holder)
			is ParadoxLocalisationColorfulText -> annotateColorfulText(element, holder)
			is ParadoxLocalisationPropertyReference -> annotatePropertyReference(element, holder)
		}
	}
	
	private fun annotateProperty(element: ParadoxLocalisationProperty, holder: AnnotationHolder) {
		//注明所有同名的属性
		holder.newSilentAnnotation(INFORMATION)
			.gutterIconRenderer(LocalisationPropertyGutterIconRenderer(element.name, element.project))
			.create()
	}
	
	private fun annotateLocale(element: ParadoxLocalisationLocale, holder: AnnotationHolder) {
		if(element.paradoxLocale == null) {
			holder.newAnnotation(ERROR, message("paradox.localisation.annotator.unsupportedLocale", element.name))
				.withFix(ChangeLocaleIntention)
				.create()
		}
	}
	
	private fun annotateSerialNumber(element: ParadoxLocalisationSerialNumber, holder: AnnotationHolder) {
		if(element.paradoxSerialNumber == null) {
			holder.newAnnotation(ERROR, message("paradox.localisation.annotator.unsupportedSerialNumber", element.name))
				.withFix(ChangeSerialNumberIntention)
				.create()
		}
	}
	
	private fun annotateColorfulText(element: ParadoxLocalisationColorfulText, holder: AnnotationHolder) {
		//如果是颜色文本，则为颜色代码文本加上对应的颜色
		if(element.paradoxColor == null) {
			holder.newAnnotation(ERROR, message("paradox.localisation.annotator.unsupportedColor", element.name))
				.withFix(ChangeColorIntention)
				.create()
		} else {
			val e = element.colorCode
			if(e != null) annotateColor(element.name, holder, e.textRange)
		}
	}
	
	private fun annotatePropertyReference(element: ParadoxLocalisationPropertyReference, holder: AnnotationHolder) {
		//如果是属性引用，需要为属性引用参数加上对应的颜色
		val color = element.paradoxColor
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
		val attributesKey = ParadoxLocalisationAttributesKeys.COLOR_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(range).textAttributes(attributesKey)
			.create()
	}
}
