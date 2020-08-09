package com.windea.plugin.idea.stellaris.script.schema

import com.intellij.psi.*
import com.jetbrains.jsonSchema.extension.*
import com.jetbrains.jsonSchema.impl.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.script.psi.*

//org.jetbrains.yaml.schema.YamlJsonLikePsiWalkerFactory

class StellarisScriptJsonLikePsiWalkerFactory: JsonLikePsiWalkerFactory {
	override fun handles(element: PsiElement): Boolean {
		return element.containingFile is StellarisScriptFile
	}

	override fun create(schemaObject: JsonSchemaObject): JsonLikePsiWalker {
		return StellarisScriptJsonLikePsiWalker
	}
}
