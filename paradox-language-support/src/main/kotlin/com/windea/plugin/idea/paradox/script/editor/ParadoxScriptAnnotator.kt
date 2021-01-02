@file:Suppress("UNCHECKED_CAST")

package com.windea.plugin.idea.paradox.script.editor

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.HighlightSeverity.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.highlighter.*
import com.windea.plugin.idea.paradox.script.highlighter.*
import com.windea.plugin.idea.paradox.script.psi.*
import com.windea.plugin.idea.paradox.settings.*

class ParadoxScriptAnnotator : Annotator, DumbAware {
	private val state = ParadoxSettingsState.getInstance()
	
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when(element) {
			is ParadoxScriptProperty -> annotateProperty(element, holder)
			is ParadoxScriptVariableReference -> annotateVariableReference(element, holder)
			is ParadoxScriptString -> annotateString(element, holder)
		}
	}
	
	private fun annotateProperty(element: ParadoxScriptProperty, holder: AnnotationHolder) {
		if(element.isRootProperty()) {
			val name = element.name
			val project = element.project
			val scope = element.resolveScope
			if(state.resolveScriptReferences) {
				//注明所有关联的本地化属性（如果存在）
				val relatedLocalisationProperties = findRelatedLocalisationProperties(name, project,null,scope).toTypedArray()
				if(relatedLocalisationProperties.isNotEmpty()) {
					val names = relatedLocalisationProperties.mapTo(linkedSetOf()) { it.name }.toTypedArray()
					holder.newSilentAnnotation(INFORMATION)
						.gutterIconRenderer(ParadoxRelatedLocalisationPropertiesGutterIconRenderer(names, relatedLocalisationProperties))
						.create()
				}
			}
		}
	}
	
	private fun annotateVariableReference(element:ParadoxScriptVariableReference,holder: AnnotationHolder){
		//注明无法解析的情况
		val reference = element.reference
		if(reference.resolve() == null){
			holder.newAnnotation(ERROR,message("paradox.script.annotator.unresolvedVariable",element.name))
				.create()
		}
	}
	
	private fun annotateString(element: ParadoxScriptString, holder: AnnotationHolder) {
		if(state.resolveScriptReferences) {
			//过滤非法情况
			val name = element.value
			if(name.isInvalidPropertyName()) return
			val project = element.project
			val scope = element.resolveScope
			
			//注明所有对应名称的脚本属性，或者本地化属性（如果存在）
			val scriptProperties = findScriptProperties(name, project, scope).toTypedArray()
			if(scriptProperties.isNotEmpty()) {
				holder.newSilentAnnotation(INFORMATION)
					.textAttributes(ParadoxScriptAttributesKeys.PROPERTY_KEY_KEY)
					.gutterIconRenderer(ParadoxStringScriptPropertyGutterIconRenderer(name, scriptProperties))
					.create()
				return
			}
			val localisationProperties = findLocalisationProperties(name, project, null, scope).toTypedArray()
			if(localisationProperties.isNotEmpty()) {
				holder.newSilentAnnotation(INFORMATION)
					.textAttributes(ParadoxLocalisationAttributesKeys.PROPERTY_KEY_KEY)
					.gutterIconRenderer(ParadoxStringLocalisationPropertyGutterIconRenderer(name, localisationProperties))
					.create()
			}
		}
	}
}
