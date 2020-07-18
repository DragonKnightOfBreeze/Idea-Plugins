package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.highlighter.*
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.PROPERTY_HEADER_KEY
import com.windea.plugin.idea.stellaris.localization.highlighter.StellarisLocalizationSyntaxHighlighter.Companion.PROPERTY_KEY_KEY
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.highlighter.*

@ExtensionPoint
class StellarisLocalizationAnnotator : Annotator ,DumbAware{
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		//when(element){
		//	is StellarisLocalizationPropertyHeader -> {
		//		holder.newSilentAnnotation(INFORMATION).textAttributes(PROPERTY_HEADER_KEY).create()
		//	}
		//	is StellarisLocalizationPropertyKey -> {
		//		holder.newSilentAnnotation(INFORMATION).textAttributes(PROPERTY_KEY_KEY).create()
		//	}
		//}
	}
}
