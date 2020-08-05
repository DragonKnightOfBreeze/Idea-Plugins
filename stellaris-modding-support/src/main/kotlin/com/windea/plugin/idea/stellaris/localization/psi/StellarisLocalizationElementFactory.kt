package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.localization.*

object StellarisLocalizationElementFactory {
	@JvmStatic
	fun createDummyFile(project: Project, text: String): StellarisLocalizationFile {
		return PsiFileFactory.getInstance(project).createFileFromText(StellarisLocalizationLanguage, text) as StellarisLocalizationFile
	}

	@JvmStatic
	fun createLocale(project: Project, locale: String): StellarisLocalizationLocale {
		return createDummyFile(project, "$locale:").locale!!
	}

	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisLocalizationProperty {
		return createDummyFile(project, "l_english:\n$key:0 \"$value\"").properties.first()
	}

	@JvmStatic
	fun createPropertyKey(project: Project, key: String): StellarisLocalizationPropertyKey {
		return createProperty(project, key, "").propertyKey
	}

	@JvmStatic
	fun createPropertyValue(project: Project, value: String): StellarisLocalizationPropertyValue {
		return createProperty(project, "a", value).propertyValue!!
	}

	@JvmStatic
	fun createPropertyReference(project: Project, value: String): StellarisLocalizationPropertyReference {
		return createPropertyValue(project, "$$value$").richTextList.first().propertyReference!!
	}

	@JvmStatic
	fun createIcon(project: Project, value: String): StellarisLocalizationIcon {
		return createPropertyValue(project, "£$value£").richTextList.first().icon!!
	}

	@JvmStatic
	fun createSerialNumber(project: Project, value: String): StellarisLocalizationSerialNumber {
		return createPropertyValue(project, "%$value%").richTextList.first().serialNumber!!
	}

	@JvmStatic
	fun createColorfulText(project: Project, value: String): StellarisLocalizationColorfulText {
		return createPropertyValue(project, "§$value §!").richTextList.first().colorfulText!!
	}
}

