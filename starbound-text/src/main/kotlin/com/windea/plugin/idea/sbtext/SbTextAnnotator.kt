package com.windea.plugin.idea.sbtext

import com.intellij.lang.annotation.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.sbtext.SbTextBundle.message
import com.windea.plugin.idea.sbtext.psi.*

class SbTextAnnotator : Annotator, DumbAware {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element){
			is SbTextColorfulText -> {
				val colorId = element.name?:return
				if(element.color == null){
					holder.newAnnotation(HighlightSeverity.WARNING,message("sbText.annotator.unsupportedColor",colorId))
						.create()
				}
			}
		}
	}

}
