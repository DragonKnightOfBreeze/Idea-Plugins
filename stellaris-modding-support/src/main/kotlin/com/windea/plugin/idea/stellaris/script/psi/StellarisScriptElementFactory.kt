package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.script.*

object StellarisScriptElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): StellarisScriptFile {
		return PsiFileFactory.getInstance(project).createFileFromText(StellarisScriptLanguage, text) as StellarisScriptFile
	}

	@JvmStatic
	fun createVariableDefinition(project: Project, name: String, value: String): StellarisScriptVariableDefinition {
		return createDummyFile(project, "$name=$value").firstChild as StellarisScriptVariableDefinition
	}

	@JvmStatic
	fun createVariableName(project: Project, name: String): StellarisScriptVariableName {
		return createVariableDefinition(project, name, "0").variableName
	}

	@JvmStatic
	fun createVariableValue(project: Project, value: String): StellarisScriptVariableValue {
		return createVariableDefinition(project, "a", value).variableValue!!
	}

	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisScriptProperty {
		return createDummyFile(project, "$key=$value").firstChild as StellarisScriptProperty
	}

	@JvmStatic
	fun createPropertyKey(project: Project, key: String): StellarisScriptPropertyKey {
		return createProperty(project, key, "0").propertyKey
	}

	@JvmStatic
	fun createPropertyValue(project: Project, value: String): StellarisScriptPropertyValue {
		return createProperty(project, "a", value).propertyValue!!
	}

	@JvmStatic
	fun createValue(project:Project,value:String):StellarisScriptValue{
		return createDummyFile(project,value).firstChild as StellarisScriptValue
	}
}
