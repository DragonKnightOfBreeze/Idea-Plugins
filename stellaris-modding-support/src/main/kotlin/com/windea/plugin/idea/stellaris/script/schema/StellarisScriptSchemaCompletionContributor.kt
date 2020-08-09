package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInsight.completion.*
import com.jetbrains.jsonSchema.ide.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.annotations.*

//org.jetbrains.yaml.schema.YamlJsonSchemaCompletionContributor

class StellarisScriptSchemaCompletionContributor : CompletionContributor() {
	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		val position = parameters.position
		val jsonSchemaService = JsonSchemaService.Impl.get(position.project)
		val schemaObject = jsonSchemaService.getSchemaObject(parameters.originalFile) ?: return
		JsonSchemaCompletionContributor.doCompletion(parameters, result, schemaObject, false)
	}
}
