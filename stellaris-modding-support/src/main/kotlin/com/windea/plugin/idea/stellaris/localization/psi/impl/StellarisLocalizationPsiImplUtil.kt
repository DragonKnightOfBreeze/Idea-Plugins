@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.openapi.util.*
import com.intellij.openapi.util.Iconable.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createColorfulText
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createIcon
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createLocale
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createPropertyReference
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationElementFactory.createSerialNumber
import com.windea.plugin.idea.stellaris.localization.reference.*
import javax.swing.*

//NOTE getName 确定进行重构和导航时显示的PsiElement的名字
//NOTE setName 确定进行重命名时的逻辑
//NOTE getNameIdentifier ？
//NOTE getTextOffset 确定选中一个PsiElement时，哪一部分会高亮显示
//NOTE getReference 确定选中一个PsiElement时，哪些其他的PsiElement会同时高亮显示

object StellarisLocalizationPsiImplUtil {
	//region StellarisLocalizationLocale
	@JvmStatic
	fun getName(element: StellarisLocalizationLocale): String? {
		return element.localeId.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationLocale, name: String): PsiElement {
		element.localeId.replace(createLocale(element.project, name).localeId)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationLocale): PsiElement? {
		return element.localeId
	}

	@JvmStatic
	fun getIcon(element: StellarisLocalizationLocale, @IconFlags flags: Int): Icon? {
		return localizationLocaleIcon
	}

	@JvmStatic
	fun getLocale(element: StellarisLocalizationLocale): StellarisLocale? {
		return StellarisLocale.map[element.name]
	}

	@JvmStatic
	fun getReference(element: StellarisLocalizationLocale): StellarisLocalizationLocalePsiReference {
		return StellarisLocalizationLocalePsiReference(element, element.localeId.textRangeInParent)
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
		return element.propertyKey.propertyKeyId
	}

	@JvmStatic
	fun getIcon(element: StellarisLocalizationProperty, @IconFlags flags: Int): Icon? {
		return localizationPropertyIcon
	}

	@JvmStatic
	fun getKey(element: StellarisLocalizationProperty): String {
		return element.propertyKey.text
	}

	@JvmStatic
	fun getValue(element: StellarisLocalizationProperty): String {
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
	fun getName(element: StellarisLocalizationPropertyReference): String? {
		return element.propertyKeyId?.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationPropertyReference, name: String): PsiElement {
		element.propertyKeyId?.replace(createPropertyReference(element.project, name).propertyKeyId!!)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationPropertyReference): PsiElement? {
		return element.propertyKeyId
	}

	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationPropertyReference): Int {
		return element.startOffset + 1
	}

	@JvmStatic
	fun getReference(element: StellarisLocalizationPropertyReference): StellarisLocalizationPropertyPsiReference {
		return StellarisLocalizationPropertyPsiReference(element, TextRange(1,element.textLength-1))
	}
	//endregion

	//region StellarisLocalizationIcon
	@JvmStatic
	fun getName(element: StellarisLocalizationIcon): String? {
		return element.iconName?.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationIcon, name: String): PsiElement {
		element.iconName?.replace(createIcon(element.project, name).iconName!!)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationIcon): PsiElement? {
		return element.iconName
	}

	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationIcon): Int {
		return element.startOffset + 1
	}

	@JvmStatic
	fun getReference(element: StellarisLocalizationIcon): StellarisLocalizationIconPsiReference {
		return StellarisLocalizationIconPsiReference(element, TextRange(1,element.textLength-1))
	}
	//endregion

	//region StellarisLocalizationSerialNumber
	@JvmStatic
	fun getName(element: StellarisLocalizationSerialNumber): String? {
		return element.serialNumberId?.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationSerialNumber, name: String): PsiElement {
		element.serialNumberId?.replace(createSerialNumber(element.project, name).serialNumberId!!)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationSerialNumber): PsiElement? {
		return element.serialNumberId
	}

	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationSerialNumber): Int {
		return element.startOffset + 1
	}

	@JvmStatic
	fun getSerialNumber(element: StellarisLocalizationSerialNumber): StellarisSerialNumber? {
		return element.name?.let { name -> StellarisSerialNumber.map[name] }
	}

	@JvmStatic
	fun getReference(element: StellarisLocalizationSerialNumber): StellarisLocalizationSerialNumberPsiReference {
		return StellarisLocalizationSerialNumberPsiReference(element, TextRange(1,element.textLength-1))
	}
	//endregion

	//region StellarisLocalizationColorfulText
	@JvmStatic
	fun getName(element: StellarisLocalizationColorfulText): String? {
		return element.colorfulTextId?.text
	}

	@JvmStatic
	fun setName(element: StellarisLocalizationColorfulText, name: String): PsiElement {
		element.colorfulTextId?.replace(createColorfulText(element.project, name).colorfulTextId!!)
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationColorfulText): PsiElement? {
		return element.colorfulTextId
	}

	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationColorfulText): Int {
		return element.startOffset + 1
	}

	@JvmStatic
	fun getColor(element: StellarisLocalizationColorfulText): StellarisColor? {
		return element.name?.let { name -> StellarisColor.map[name] }
	}

	@JvmStatic
	fun getReference(element: StellarisLocalizationColorfulText): StellarisLocalizationColorfulTextPsiReference {
		return StellarisLocalizationColorfulTextPsiReference(element, TextRange(1,element.textLength-1))
	}
	//endregion
}
