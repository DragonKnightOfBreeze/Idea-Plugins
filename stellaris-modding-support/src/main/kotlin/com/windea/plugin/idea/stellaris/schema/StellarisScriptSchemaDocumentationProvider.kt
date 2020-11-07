package com.windea.plugin.idea.stellaris.schema

import com.intellij.lang.documentation.*
import com.intellij.openapi.editor.*
import com.intellij.psi.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*

//org.jetbrains.yaml.schema.YamlJsonSchemaDocumentationProvider

class StellarisScriptSchemaDocumentationProvider:DocumentationProvider {
	override fun getQuickNavigateInfo(element: PsiElement?, originalElement: PsiElement?): String? {
		return findSchemaAndGenerateDoc(element, true)
	}

	override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
		return findSchemaAndGenerateDoc(element, false)
	}

	override fun getCustomDocumentationElement(editor: Editor, file: PsiFile, contextElement: PsiElement?, targetOffset: Int): PsiElement? {
		val service = JsonSchemaService.Impl.get(file.project)
		return if(service?.getSchemaObject(file) != null) contextElement else null
	}

	private fun findSchemaAndGenerateDoc(element: PsiElement?, preferShort: Boolean): String? {
		if(element == null) return null
		val jsonSchemaService = JsonSchemaService.Impl.get(element.project)?:return null
		val containingFile = element.containingFile?:return null
		val schemaObject = jsonSchemaService.getSchemaObject(containingFile)?:return null
		return JsonSchemaDocumentationProvider.generateDoc(element, schemaObject, preferShort, null)
	}
}
