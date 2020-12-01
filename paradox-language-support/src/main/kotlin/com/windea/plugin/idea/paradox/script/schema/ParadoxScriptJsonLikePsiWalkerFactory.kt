package com.windea.plugin.idea.paradox.script.schema

import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.paradox.script.psi.*

//org.jetbrains.yaml.schema.YamlJsonLikePsiWalkerFactory

class ParadoxScriptJsonLikePsiWalkerFactory: JsonLikePsiWalkerFactory {
	override fun handles(element: PsiElement): Boolean {
		return element.containingFile is ParadoxScriptFile
	}

	override fun create(schemaObject: JsonSchemaObject): JsonLikePsiWalker {
		return ParadoxScriptJsonLikePsiWalker
	}
}
