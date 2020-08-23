package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.codeInsight.completion.*
import com.jetbrains.jsonSchema.ide.*

//org.jetbrains.yaml.schema.YamlJsonSchemaCompletionContributor

//需要包含作为前缀的字符到结果中
//需要将结果中的冒号替换为等号

class StellarisScriptSchemaCompletionContributor : CompletionContributor() {
	override fun beforeCompletion(context: CompletionInitializationContext) {
		context.dummyIdentifier = ""
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		val prefix = parameters.position.prevSibling?.text
		val resultWithPrefix = if(prefix == null) result else result.withPrefixMatcher(prefix)
		val jsonPointerPosition = parameters.position
		val jsonSchemaService = JsonSchemaService.Impl.get(jsonPointerPosition.project)
		val schemaObject = jsonSchemaService.getSchemaObject(parameters.originalFile) ?: return
		JsonSchemaCompletionContributor0.doCompletion(parameters, resultWithPrefix, schemaObject, false)
	}
}
