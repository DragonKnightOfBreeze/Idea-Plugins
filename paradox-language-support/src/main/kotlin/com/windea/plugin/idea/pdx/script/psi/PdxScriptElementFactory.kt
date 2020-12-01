package com.windea.plugin.idea.stellaris.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.script.*

object StellarisScriptElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): StellarisScriptFile {
		return PsiFileFactory.getInstance(project).createFileFromText(StellarisScriptLanguage, text) as StellarisScriptFile
	}

	@JvmStatic
	fun createVariable(project: Project, name: String, value: String): StellarisScriptVariable {
		return createDummyFile(project, "$name=$value").variables.first()
	}

	@JvmStatic
	fun createVariableName(project: Project, name: String): StellarisScriptVariableName {
		return createVariable(project, name, "0").variableName
	}

	@JvmStatic
	fun createVariableValue(project: Project, value: String): StellarisScriptVariableValue {
		return createVariable(project, "a", value).variableValue!!
	}

	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisScriptProperty {
		return createDummyFile(project, "$key=$value").properties.first()
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
		return createPropertyValue(project,value).value
	}
}
