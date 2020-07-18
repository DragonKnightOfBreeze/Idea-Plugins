package com.windea.plugin.idea.stellaris.localization.psi

import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.jetbrains.rd.util.*
import com.windea.plugin.idea.stellaris.localization.*

object StellarisLocalizationElementFactory {
	private val logger = getLogger<StellarisLocalizationElementFactory>()

	/**创建文件*/
	@JvmStatic
	fun createDummyFile(project: Project, text: String): PsiFile {
		return PsiFileFactory.getInstance(project).createFileFromText(StellarisLocalizationLanguage, text)
			.also { logger.info { "create dummy file: '$it'" } }
	}

	@JvmStatic
	fun createPropertyHeader(project: Project, locale: String): StellarisLocalizationPropertyHeader {
		return (createDummyFile(project, "l_$locale:").firstChild as StellarisLocalizationPropertyHeader)
			.also { logger.info { "create property header: '$it'" } }
	}

	/**创建属性*/
	@JvmStatic
	fun createProperty(project: Project, key: String, value: String): StellarisLocalizationProperty {
		return (createDummyFile(project, "l_english:\n$key:0 $value").lastChild as StellarisLocalizationProperty)
			.also { logger.info { "create property: '$it'" } }
	}

	/**创建属性的键*/
	@JvmStatic
	fun createPropertyKey(project: Project, key: String): PsiElement {
		return createProperty(project, key, "\"\"").propertyKey
			.also { logger.info { "create property key: '$it'" } }
	}

	/**创建属性的值*/
	@JvmStatic
	fun createPropertyValue(project: Project, value: String): PsiElement {
		return createProperty(project, "a", value).propertyValue!!
			.also { logger.info { "create property value: '$it'" } }
	}
}

