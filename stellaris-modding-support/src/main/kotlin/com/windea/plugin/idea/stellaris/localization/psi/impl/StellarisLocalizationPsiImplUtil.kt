@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.icons.*
import com.intellij.openapi.util.*
import com.intellij.openapi.util.Iconable.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createColorfulText
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createIcon
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyHeader
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyReference
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createSerialNumber
import com.windea.plugin.idea.stellaris.localization.reference.*
import javax.swing.*

object StellarisLocalizationPsiImplUtil {
	//region StellarisLocalizationPropertyHeader
	@JvmStatic
	fun getName(element: StellarisLocalizationPropertyHeader): String {
		return element.headerToken.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationPropertyHeader, name: String): PsiElement {
		element.headerToken.replace(createPropertyHeader(element.project, name).headerToken)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationPropertyHeader): PsiElement? {
		return element.headerToken
	}

	@JvmStatic
	fun getIcon(element: StellarisLocalizationPropertyHeader, @IconFlags flags: Int): Icon? {
		return AllIcons.FileTypes.Properties
	}

	@JvmStatic
	fun getLocale(element:StellarisLocalizationPropertyHeader):StellarisLocale?{
		return StellarisLocale.map[element.name]
	}

	@JvmStatic
	fun getDocumentation(element:StellarisLocalizationPropertyHeader):String?{
		return element.locale?.description?.toDefinitionText()
	}
	//endregion

	//region StellarisLocalizationProperty
	@JvmStatic
	fun getName(element: StellarisLocalizationProperty): String {
		return element.propertyKey.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationProperty): PsiElement? {
		return element.propertyKey.keyToken
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

	//region StellarisLocalizationPropertyReference
	@JvmStatic
	fun getName(element: StellarisLocalizationPropertyReference): String {
		return element.keyToken.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationPropertyReference, name: String): PsiElement {
		element.replace(createPropertyReference(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationPropertyReference): PsiElement? {
		return element.keyToken
	}

	@JvmStatic
	fun getTextOffset(element:StellarisLocalizationPropertyReference):Int{
		return element.keyToken.textOffset
	}

	@JvmStatic
	fun getReference(element:StellarisLocalizationPropertyReference):PsiReference{
		return StellarisLocalizationPropertyPsiReference(element, TextRange(1,element.textLength-1))
	}
	//endregion

	//region StellarisLocalizationIcon
	@JvmStatic
	fun getName(element: StellarisLocalizationIcon): String {
		return element.iconText.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationIcon, name: String): PsiElement {
		element.iconText.replace(createIcon(element.project, name).iconText)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationIcon): PsiElement? {
		return element.iconText
	}

	@JvmStatic
	fun getTextOffset(element:StellarisLocalizationIcon):Int{
		return element.iconText.textOffset
	}

	@JvmStatic
	fun getDocumentation(element:StellarisLocalizationIcon):String?{
		return "(icon) ${element.name}".toDefinitionText()
	}
	//endregion

	//region StellarisLocalizationSerialNumber
	@JvmStatic
	fun getName(element: StellarisLocalizationSerialNumber): String {
		return element.serialNumberCode.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationSerialNumber, name: String): PsiElement {
		element.serialNumberCode.replace(createSerialNumber(element.project, name).serialNumberCode)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationSerialNumber): PsiElement? {
		return element.serialNumberCode
	}

	@JvmStatic
	fun getTextOffset(element:StellarisLocalizationSerialNumber):Int{
		return element.serialNumberCode.textOffset
	}

	@JvmStatic
	fun getSerialNumber(element:StellarisLocalizationSerialNumber):StellarisSerialNumber?{
		return StellarisSerialNumber.map[element.name]
	}

	@JvmStatic
	fun getDocumentation(element:StellarisLocalizationSerialNumber):String?{
		return element.serialNumber?.description?.toDefinitionText()
	}
	//endregion

	//region StellarisLocalizationColorfulText
	@JvmStatic
	fun getName(element: StellarisLocalizationColorfulText): String {
		return element.colorfulTextCode.text.orEmpty()
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationColorfulText, name: String): PsiElement {
		element.colorfulTextCode.replace(createColorfulText(element.project, name).colorfulTextCode)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationColorfulText): PsiElement? {
		return element.colorfulTextCode
	}

	@JvmStatic
	fun getTextOffset(element:StellarisLocalizationColorfulText):Int{
		return element.colorfulTextCode.textOffset
	}

	@JvmStatic
	fun getColor(element:StellarisLocalizationColorfulText):StellarisColor?{
		return StellarisColor.map[element.name]
	}

	@JvmStatic
	fun getDocumentation(element:StellarisLocalizationColorfulText):String?{
		return element.color?.description?.toDefinitionText()
	}
	//endregion
}
