@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.icons.*
import com.intellij.openapi.util.Iconable.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyHeader
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.localization.reference.*
import javax.swing.*

object StellarisLocalizationPsiImplUtil {
	//region StellarisLocalizationPropertyHeader
	@JvmStatic
	fun getName(element: StellarisLocalizationPropertyHeader): String? {
		return element.text?.removePrefix("l_")
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationPropertyHeader, name: String): PsiElement {
		element.replace(createPropertyHeader(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationPropertyHeader): PsiElement? {
		return element
	}

	@JvmStatic
	fun getIcon(element: StellarisLocalizationPropertyHeader, @IconFlags flags: Int): Icon? {
		return AllIcons.FileTypes.Properties
	}
	//endregion

	//region StellarisLocalizationProperty
	@JvmStatic
	fun getName(element: StellarisLocalizationProperty): String? {
		return element.propertyKey.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationProperty): PsiElement? {
		return element.propertyKey
	}

	@JvmStatic
	fun getIcon(element: StellarisLocalizationProperty, @IconFlags flags: Int): Icon? {
		return PlatformIcons.PROPERTY_ICON
	}

	@JvmStatic
	fun getKey(element:StellarisLocalizationProperty):String{
		return element.propertyKey.text
	}

	@JvmStatic
	fun getValue(element:StellarisLocalizationProperty):String{
		return element.propertyValue?.text.orEmpty()
	}
	//endregion

	//region StellarisLocalizationPropertyValue
	@JvmStatic
	fun getValue(element: StellarisLocalizationPropertyValue): String? {
		return element.text?.unquote()
	}
	//endregion
}
