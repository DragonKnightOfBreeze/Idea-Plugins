package com.windea.plugin.idea.pdx.script.schema

import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.pdx.script.psi.*

//org.jetbrains.yaml.schema.YamlJsonLikePsiWalkerFactory

class PdxScriptJsonLikePsiWalkerFactory: JsonLikePsiWalkerFactory {
	override fun handles(element: PsiElement): Boolean {
		return element.containingFile is PdxScriptFile
	}

	override fun create(schemaObject: JsonSchemaObject): JsonLikePsiWalker {
		return PdxScriptJsonLikePsiWalker
	}
}
