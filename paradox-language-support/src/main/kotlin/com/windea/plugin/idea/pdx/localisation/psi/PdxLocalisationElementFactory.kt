package com.windea.plugin.idea.pdx.localisation.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.localisation.*

object PdxLocalisationElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): PdxLocalisationFile {
		return PsiFileFactory.getInstance(project).createFileFromText(PdxLocalisationLanguage, text) as PdxLocalisationFile
	}

	@JvmStatic
	fun createLocale(project: Project, locale: String): PdxLocalisationLocale {
		return createDummyFile(project, "$locale:").locale!!
	}

	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): PdxLocalisationProperty {
		return createDummyFile(project, "l_english:\n$key:0 \"$value\"").properties.first()
	}

	@JvmStatic
	fun createPropertyKey(project: Project, key: String): PdxLocalisationPropertyKey {
		return createProperty(project, key, "").propertyKey
	}

	@JvmStatic
	fun createPropertyValue(project: Project, value: String): PdxLocalisationPropertyValue {
		return createProperty(project, "a", value).propertyValue!!
	}

	@JvmStatic
	fun createPropertyReference(project: Project, name: String): PdxLocalisationPropertyReference {
		return createPropertyValue(project, "$$name$").richTextList.first() as PdxLocalisationPropertyReference
	}

	@JvmStatic
	fun createIcon(project: Project, name: String): PdxLocalisationIcon {
		return createPropertyValue(project, "£$name£").richTextList.first() as PdxLocalisationIcon
	}

	@JvmStatic
	fun createSerialNumber(project: Project, name: String): PdxLocalisationSerialNumber {
		return createPropertyValue(project, "%$name%").richTextList.first() as PdxLocalisationSerialNumber
	}

	@JvmStatic
	fun createColorfulText(project: Project, name: String,value:String = ""): PdxLocalisationColorfulText {
		return createPropertyValue(project, "§$name$value§!").richTextList.first() as PdxLocalisationColorfulText
	}
}
