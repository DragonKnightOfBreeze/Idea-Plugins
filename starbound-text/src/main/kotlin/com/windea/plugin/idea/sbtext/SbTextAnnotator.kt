package com.windea.plugin.idea.sbtext

import com.intellij.codeInspection.*
import com.intellij.codeInspection.ProblemHighlightType.*
import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.sbtext.SbTextBundle.message
import com.windea.plugin.idea.sbtext.highlighter.*
import com.windea.plugin.idea.sbtext.highlighter.SbTextAttributesKeys.COLOR_KEYS
import com.windea.plugin.idea.sbtext.psi.*

class SbTextAnnotator : Annotator, DumbAware {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is SbTextColorfulText -> {
				val colorId = element.name ?: return
				if(element.color == null) {
					holder.newAnnotation(WARNING, message("sbText.annotator.unsupportedColor", colorId))
						.create()
				} else {
					annotateColor(colorId, holder, element)
				}
			}
		}
	}

	private fun annotateColor(colorId: String, holder: AnnotationHolder, element: SbTextColorfulText) {
		val attributesKey = COLOR_KEYS[colorId] ?: return
		holder.newSilentAnnotation(INFORMATION)
			.range(element.colorMarker.colorCode!!).textAttributes(attributesKey)
			.create()
	}

}
