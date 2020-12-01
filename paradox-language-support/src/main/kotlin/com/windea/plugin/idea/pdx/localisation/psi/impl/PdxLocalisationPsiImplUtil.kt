@file:Suppress("UNCHECKED_CAST", "UNUSED_PARAMETER")

package com.windea.plugin.idea.pdx.localisation.psi.impl

import com.intellij.openapi.util.Iconable.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createColorfulText
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createIcon
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createLocale
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createPropertyKey
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createPropertyReference
import com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationElementFactory.createSerialNumber
import com.windea.plugin.idea.pdx.localisation.reference.*
import javax.swing.*

//NOTE getName 确定进行重构和导航时显示的PsiElement的名字
//NOTE setName 确定进行重命名时的逻辑
//NOTE getNameIdentifier ？
//NOTE getTextOffset 确定选中一个PsiElement时，哪一部分会高亮显示
//NOTE getReference 确定选中一个PsiElement时，哪些其他的PsiElement会同时高亮显示

object PdxLocalisationPsiImplUtil {
	//region PdxLocalisationLocale
	@JvmStatic
	fun getName(element: PdxLocalisationLocale): String? {
		return element.localeId.text
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationLocale, name: String): PsiElement {
		element.localeId.replace(createLocale(element.project, name).localeId)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationLocale): PsiElement {
		return element.localeId
	}
	
	@JvmStatic
	fun getIcon(element: PdxLocalisationLocale, @IconFlags flags: Int): Icon {
		return pdxLocalisationLocaleIcon
	}
	
	@JvmStatic
	fun getPdxLocale(element: PdxLocalisationLocale): PdxLocale? {
		return PdxLocale.map[element.name]
	}
	//endregion
	
	//region PdxLocalisationProperty
	@JvmStatic
	fun getName(element: PdxLocalisationProperty): String {
		return element.stub?.key?: element.propertyKey.text
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationProperty): PsiElement {
		return element.propertyKey.propertyKeyId
	}
	
	@JvmStatic
	fun getIcon(element: PdxLocalisationProperty, @IconFlags flags: Int): Icon {
		return pdxLocalisationPropertyIcon
	}
	
	@JvmStatic
	fun getValue(element: PdxLocalisationProperty): String? {
		return element.propertyValue?.text?.unquote()
	}
	
	@JvmStatic
	fun getPdxLocale(element: PdxLocalisationProperty): PdxLocale? {
		return (element.containingFile as? PdxLocalisationFile)?.pdxLocale
	}
	//endregion
	
	//region PdxLocalisationPropertyReference
	@JvmStatic
	fun getName(element: PdxLocalisationPropertyReference): String? {
		return element.propertyReferenceId?.text
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationPropertyReference, name: String): PsiElement {
		element.propertyReferenceId?.replace(createPropertyReference(element.project, name).propertyReferenceId!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationPropertyReference): PsiElement? {
		return element.propertyReferenceId
	}
	
	@JvmStatic
	fun getTextOffset(element: PdxLocalisationPropertyReference): Int {
		return element.startOffset + 1
	}
	
	@JvmStatic
	fun getReference(element: PdxLocalisationPropertyReference): PdxLocalisationPropertyPsiReference? {
		return element.propertyReferenceId?.let { PdxLocalisationPropertyPsiReference(element, it.textRangeInParent) }
	}
	
	@JvmStatic
	fun getPdxColor(element: PdxLocalisationPropertyReference): PdxColor? {
		val colorCode = element.propertyReferenceParameter?.text?.firstOrNull()
		if(colorCode != null && colorCode.isUpperCase()) {
			return PdxColor.map[colorCode.toString()]
		}
		return null
	}
	//endregion
	
	//region PdxLocalisationIcon
	@JvmStatic
	fun getName(element: PdxLocalisationIcon): String? {
		return element.iconId?.text
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationIcon, name: String): PsiElement {
		element.iconId?.replace(createIcon(element.project, name).iconId!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationIcon): PsiElement? {
		return element.iconId
	}
	
	@JvmStatic
	fun getTextOffset(element: PdxLocalisationIcon): Int {
		return element.startOffset + 1
	}
	//endregion
	
	//region PdxLocalisationSerialNumber
	@JvmStatic
	fun getName(element: PdxLocalisationSerialNumber): String? {
		return element.serialNumberId?.text?.toUpperCase()
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationSerialNumber, name: String): PsiElement {
		element.serialNumberId?.replace(createSerialNumber(element.project, name).serialNumberId!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationSerialNumber): PsiElement? {
		return element.serialNumberId
	}
	
	@JvmStatic
	fun getTextOffset(element: PdxLocalisationSerialNumber): Int {
		return element.startOffset + 1
	}
	
	@JvmStatic
	fun getPdxSerialNumber(element: PdxLocalisationSerialNumber): PdxSerialNumber? {
		val name = element.name ?: return null
		return PdxSerialNumber.map[name]
	}
	//endregion
	
	//region PdxLocalisationColorfulText
	@JvmStatic
	fun getName(element: PdxLocalisationColorfulText): String? {
		return element.colorCode?.text?.toUpperCase()
	}
	
	@JvmStatic
	fun setName(element: PdxLocalisationColorfulText, name: String): PsiElement {
		element.colorCode?.replace(createColorfulText(element.project, name).colorCode!!)
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxLocalisationColorfulText): PsiElement? {
		return element.colorCode
	}
	
	@JvmStatic
	fun getTextOffset(element: PdxLocalisationColorfulText): Int {
		return element.startOffset + 1
	}
	
	@JvmStatic
	fun getPdxColor(element: PdxLocalisationColorfulText): PdxColor? {
		return element.name?.let { name -> PdxColor.map[name] }
	}
	//endregion
}
