@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.openapi.util.Iconable.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.stellaris.*
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
	fun getNameIdentifier(element: StellarisLocalizationLocale): PsiElement {
		return element.localeId
	}
	
	@JvmStatic
	fun getIcon(element: StellarisLocalizationLocale, @IconFlags flags: Int): Icon {
		return stellarisLocalizationLocaleIcon
	}
	
	@JvmStatic
	fun getStellarisLocale(element: StellarisLocalizationLocale): StellarisLocale? {
		return StellarisLocale.map[element.name]
	}
	//endregion
	
	//region StellarisLocalizationProperty
	@JvmStatic
	fun getName(element: StellarisLocalizationProperty): String {
		return element.stub?.key?: element.propertyKey.text
	}
	
	@JvmStatic
	fun setName(element: StellarisLocalizationProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationProperty): PsiElement {
		return element.propertyKey.propertyKeyId
	}
	
	@JvmStatic
	fun getIcon(element: StellarisLocalizationProperty, @IconFlags flags: Int): Icon {
		return stellarisLocalizationPropertyIcon
	}
	
	@JvmStatic
	fun getValue(element: StellarisLocalizationProperty): String? {
		return element.propertyValue?.text?.unquote()
	}
	
	@JvmStatic
	fun getStellarisLocale(element: StellarisLocalizationProperty): StellarisLocale? {
		return (element.containingFile as? StellarisLocalizationFile)?.stellarisLocale
	}
	//endregion
	
	//region StellarisLocalizationPropertyReference
	@JvmStatic
	fun getName(element: StellarisLocalizationPropertyReference): String? {
		return element.propertyReferenceId?.text
	}
	
	@JvmStatic
	fun setName(element: StellarisLocalizationPropertyReference, name: String): PsiElement {
		element.propertyReferenceId?.replace(createPropertyReference(element.project, name).propertyReferenceId!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationPropertyReference): PsiElement? {
		return element.propertyReferenceId
	}
	
	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationPropertyReference): Int {
		return element.startOffset + 1
	}
	
	@JvmStatic
	fun getReference(element: StellarisLocalizationPropertyReference): StellarisLocalizationPropertyPsiReference? {
		return element.propertyReferenceId?.let { StellarisLocalizationPropertyPsiReference(element, it.textRangeInParent) }
	}
	
	@JvmStatic
	fun getStellarisColor(element: StellarisLocalizationPropertyReference): StellarisColor? {
		val colorCode = element.propertyReferenceParameter?.text?.firstOrNull()
		if(colorCode != null && colorCode.isUpperCase()) {
			return StellarisColor.map[colorCode.toString()]
		}
		return null
	}
	//endregion
	
	//region StellarisLocalizationIcon
	@JvmStatic
	fun getName(element: StellarisLocalizationIcon): String? {
		return element.iconId?.text
	}
	
	@JvmStatic
	fun setName(element: StellarisLocalizationIcon, name: String): PsiElement {
		element.iconId?.replace(createIcon(element.project, name).iconId!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationIcon): PsiElement? {
		return element.iconId
	}
	
	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationIcon): Int {
		return element.startOffset + 1
	}
	//endregion
	
	//region StellarisLocalizationSerialNumber
	@JvmStatic
	fun getName(element: StellarisLocalizationSerialNumber): String? {
		return element.serialNumberId?.text?.toUpperCase()
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
	fun getStellarisSerialNumber(element: StellarisLocalizationSerialNumber): StellarisSerialNumber? {
		val name = element.name ?: return null
		return StellarisSerialNumber.map[name]
	}
	//endregion
	
	//region StellarisLocalizationColorfulText
	@JvmStatic
	fun getName(element: StellarisLocalizationColorfulText): String? {
		return element.colorCode?.text?.toUpperCase()
	}
	
	@JvmStatic
	fun setName(element: StellarisLocalizationColorfulText, name: String): PsiElement {
		element.colorCode?.replace(createColorfulText(element.project, name).colorCode!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: StellarisLocalizationColorfulText): PsiElement? {
		return element.colorCode
	}
	
	@JvmStatic
	fun getTextOffset(element: StellarisLocalizationColorfulText): Int {
		return element.startOffset + 1
	}
	
	@JvmStatic
	fun getStellarisColor(element: StellarisLocalizationColorfulText): StellarisColor? {
		return element.name?.let { name -> StellarisColor.map[name] }
	}
	//endregion
}
