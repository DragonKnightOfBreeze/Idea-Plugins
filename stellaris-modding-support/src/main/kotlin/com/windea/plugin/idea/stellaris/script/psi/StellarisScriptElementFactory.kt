package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.script.*

object StellarisScriptElementFactory {
	private val logger = getLogger<StellarisScriptElementFactory>()

	/**创建文件*/
	@JvmStatic
	fun createDummyFile(project: Project, text: String): PsiFile {
		return PsiFileFactory.getInstance(project).createFileFromText(StellarisScriptLanguage, text)
			.also { logger.info { "create dummy file: '$it" } }
	}

	/**创建变量*/
	@JvmStatic
	fun createVariableDefinition(project: Project, name: String, value: String): StellarisScriptVariableDefinition {
		return (createDummyFile(project, "$name=$value").firstChild as StellarisScriptVariableDefinition)
			.also { logger.info { "create variable definition: '$it" } }
	}

	/**创建变量的名字*/
	@JvmStatic
	fun createVariableName(project: Project, name: String): StellarisScriptVariableName {
		return createVariableDefinition(project, name, "0").variableName
			.also { logger.info { "create variable: '$it" } }
	}

	/**创建变量的值*/
	@JvmStatic
	fun createVariableValue(project: Project, value: String): StellarisScriptVariableValue {
		return createVariableDefinition(project, "a", value).variableValue!!
			.also { logger.info { "create variable value: '$it" } }
	}

	/**创建属性*/
	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisScriptProperty {
		return (createDummyFile(project, "$key=$value").firstChild as StellarisScriptProperty)
			.also { logger.info { "create property: '$it'" } }
	}

	/**创建属性的键*/
	@JvmStatic
	fun createPropertyKey(project: Project, key: String): PsiElement {
		return createProperty(project, key, "0").propertyKey
			.also { logger.info { "create property key: '$it'" } }
	}

	/**创建属性的值*/
	@JvmStatic
	fun createPropertyValue(project: Project, value: String): PsiElement {
		return createProperty(project, "a", value).propertyValue
			.also { logger.info { "create property value: '$it'" } }
	}
}
