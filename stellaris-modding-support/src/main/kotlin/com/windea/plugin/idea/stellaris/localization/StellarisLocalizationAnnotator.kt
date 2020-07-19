package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationAnnotator : Annotator ,DumbAware{
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element){
			//如果是颜色文本，则加上对应颜色的下划线
			is StellarisLocalizationColorfulText -> {
				val name = element.name?:return
				val attributesKey = StellarisLocalizationAttributesKeys.COLOR_CODE_KEYS[name] ?: return
				holder.newSilentAnnotation(INFORMATION)
					.range(element.colorfulTextCode)
					.textAttributes(attributesKey).create()
			}
		//	is StellarisLocalizationPropertyHeader -> {
		//		holder.newSilentAnnotation(INFORMATION).textAttributes(PROPERTY_HEADER_KEY).create()
		//	}
		//	is StellarisLocalizationPropertyKey -> {
		//		holder.newSilentAnnotation(INFORMATION).textAttributes(PROPERTY_KEY_KEY).create()
		//	}
		}
	}
}
