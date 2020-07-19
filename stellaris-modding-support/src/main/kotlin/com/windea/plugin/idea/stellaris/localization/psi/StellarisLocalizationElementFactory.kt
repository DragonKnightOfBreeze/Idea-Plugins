package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.localization.*

object StellarisLocalizationElementFactory {
	private val logger = getLogger<StellarisLocalizationElementFactory>()

	/**创建文件*/
	@JvmStatic
	fun createDummyFile(project: Project, text: String): StellarisLocalizationFile {
		return (PsiFileFactory.getInstance(project).createFileFromText(StellarisLocalizationLanguage, text) as StellarisLocalizationFile)
			.also { logger.info { "create dummy file:" } }
	}

	@JvmStatic
	fun createLocale(project: Project, locale: String): StellarisLocalizationLocale {
		return createDummyFile(project, "$locale:").Locale!!
			.also { logger.info { "create locale: '$it'" } }
	}

	/**创建属性*/
	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisLocalizationProperty {
		return createDummyFile(project, "l_english:\n$key:0 \"$value\"").properties.firstOrNull()!!
			.also { logger.info { "create property: '$it'" } }
	}

	/**创建属性的键*/
	@JvmStatic
	fun createPropertyKey(project: Project, key: String): StellarisLocalizationPropertyKey {
		return createProperty(project, key, "").propertyKey
			.also { logger.info { "create property key: '$it'" } }
	}

	/**创建属性的值*/
	@JvmStatic
	fun createPropertyValue(project: Project, value: String): StellarisLocalizationPropertyValue {
		return createProperty(project, "a", value).propertyValue!!
			.also { logger.info { "create property value: '\"$it\"'" } }
	}

	/**创建属性的引用*/
	@JvmStatic
	fun createPropertyReference(project: Project, value: String): StellarisLocalizationPropertyReference {
		return createPropertyValue(project, "$$value$").richTextList.first().propertyReference!!
			.also { logger.info { "create property reference: '$it'" } }
	}

	/**创建图标*/
	@JvmStatic
	fun createIcon(project: Project, value: String): StellarisLocalizationIcon {
		return createPropertyValue(project, "£$value£").richTextList.first().icon!!
			.also { logger.info { "create icon: '$it'" } }
	}

	/**创建编号*/
	@JvmStatic
	fun createSerialNumber(project: Project, value: String): StellarisLocalizationSerialNumber {
		return createPropertyValue(project, "%$value%").richTextList.first().serialNumber!!
			.also { logger.info { "create serial number: '$it'" } }
	}

	/**创建彩色文本*/
	@JvmStatic
	fun createColorfulText(project: Project, value: String): StellarisLocalizationColorfulText {
		println(createPropertyValue(project, "§$value §!"))
		println(createPropertyValue(project, "§$value §!").richTextList)
		println(createPropertyValue(project, "§$value §!").richTextList.first())
		println(createPropertyValue(project, "§$value §!").richTextList.first().colorfulText)
		return createPropertyValue(project, "§$value §!").richTextList.first().colorfulText!!
			.also { logger.info { "create colorful text: '$it'" } }
	}
}

