package com.windea.plugin.idea.sbtext

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.sbtext.SbTextBundle.message
import com.windea.plugin.idea.sbtext.highlighter.SbTextAttributesKeys.COLOR_KEYS
import com.windea.plugin.idea.sbtext.psi.*

class SbTextAnnotator : Annotator, DumbAware {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is SbTextColorfulText -> {
				val colorId = element.name ?: return
				if(element.color == null) {
					holder.newAnnotation(WARNING, message("sbText.annotator.unsupportedColor", colorId)).create()
				} else {
					annotateColor(colorId, holder, element)
				}
			}
		}
	}

	private fun annotateColor(colorId: String, holder: AnnotationHolder, element: SbTextColorfulText) {
		val attributesKey = COLOR_KEYS[colorId] ?: return
		//val strings = element.collectDescendantsOfType<SbTextString>()
		//for(string in strings) {
		//	//高亮所有的颜色文本
		//	holder.newSilentAnnotation(INFORMATION).range(string).textAttributes(attributesKey).create()
		//}
		val string = element.string?:return
		holder.newSilentAnnotation(INFORMATION).range(string).textAttributes(attributesKey).create()
	}

	//private fun annotateColor(colorId: String, holder: AnnotationHolder, element: SbTextColorfulText) {
	//	val attributesKey = COLOR_KEYS[colorId] ?: return
	//  //高亮颜色码
	//	holder.newSilentAnnotation(INFORMATION)
	//		.range(element.colorMarker.colorCode!!).textAttributes(attributesKey)
	//		.create()
	//}
}

