package com.windea.plugin.idea.pdx.script.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.script.*

object PdxScriptElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): PdxScriptFile {
		return PsiFileFactory.getInstance(project).createFileFromText(PdxScriptLanguage, text) as PdxScriptFile
	}

	@JvmStatic
	fun createVariable(project: Project, name: String, value: String): PdxScriptVariable {
		return createDummyFile(project, "$name=$value").variables.first()
	}

	@JvmStatic
	fun createVariableName(project: Project, name: String): PdxScriptVariableName {
		return createVariable(project, name, "0").variableName
	}

	@JvmStatic
	fun createVariableValue(project: Project, value: String): PdxScriptVariableValue {
		return createVariable(project, "a", value).variableValue!!
	}

	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): PdxScriptProperty {
		return createDummyFile(project, "$key=$value").properties.first()
	}

	@JvmStatic
	fun createPropertyKey(project: Project, key: String): PdxScriptPropertyKey {
		return createProperty(project, key, "0").propertyKey
	}

	@JvmStatic
	fun createPropertyValue(project: Project, value: String): PdxScriptPropertyValue {
		return createProperty(project, "a", value).propertyValue!!
	}

	@JvmStatic
	fun createValue(project:Project,value:String):PdxScriptValue{
		return createPropertyValue(project,value).value
	}
}
