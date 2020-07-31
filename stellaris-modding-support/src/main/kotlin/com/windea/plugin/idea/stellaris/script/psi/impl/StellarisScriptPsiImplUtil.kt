@file:Suppress("UNUSED_PARAMETER", "UNUSED_DESTRUCTURED_PARAMETER_ENTRY")

package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.intellij.ui.*
import com.jetbrains.rd.util.string.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createVariableName
import com.windea.plugin.idea.stellaris.script.reference.*
import org.apache.commons.imaging.color.*
import org.apache.xmlgraphics.java2d.color.ColorUtil
import java.awt.*
import javax.swing.*

object StellarisScriptPsiImplUtil {
	//region StellarisScriptVariableDefinition
	@JvmStatic
	fun getName(element: StellarisScriptVariableDefinition): String? {
		return element.variableName.text
	}

	@JvmStatic
	fun setName(element: StellarisScriptVariableDefinition, name: String): PsiElement {
		element.variableName.replace(createVariableName(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisScriptVariableDefinition): PsiElement? {
		return element.variableName
	}

	@JvmStatic
	fun getTextOffset(element: StellarisScriptVariableDefinition): Int {
		return element.startOffset
	}

	@JvmStatic
	fun getIcon(element: StellarisScriptVariableDefinition, @Iconable.IconFlags flags: Int): Icon? {
		return scriptVariableIcon
	}

	@JvmStatic
	fun getValue(element: StellarisScriptVariableDefinition): String? {
		return element.variableValue?.text
	}
	//endregion

	//region StellarisScriptVariableReference
	//@JvmStatic
	//fun getName(element: StellarisScriptVariableReference): String? {
	//	return element.text
	//}
	//
	//@JvmStatic
	//fun setName(element: StellarisScriptVariableReference, name: String): PsiElement {
	//	element.replace(createVariableName(element.project, name))
	//	return element
	//}
	//
	//@JvmStatic
	//fun getNameIdentifier(element: StellarisScriptVariableReference): PsiElement? {
	//	return element
	//}
	//
	//@JvmStatic
	//fun getIcon(element: StellarisScriptVariableReference, @Iconable.IconFlags flags: Int): Icon? {
	//	return PlatformIcons.VARIABLE_READ_ACCESS
	//}

	@JvmStatic
	fun getReference(element: StellarisScriptVariableReference): StellarisScriptVariablePsiReference {
		return StellarisScriptVariablePsiReference(element, element.variableReferenceId.textRangeInParent)
	}
	//endregion

	//region StellarisScriptVariableValue
	@JvmStatic
	fun getValue(element: StellarisScriptVariableValue): String? {
		return element.text?.unquote()
	}
	//endregion

	//region StellarisScriptProperty
	@JvmStatic
	fun getName(element: StellarisScriptProperty): String? {
		return element.propertyKey.text
	}

	@JvmStatic
	fun setName(element: StellarisScriptProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisScriptProperty): PsiElement? {
		return element.propertyKey
	}

	@JvmStatic
	fun getIcon(element: StellarisScriptProperty, @Iconable.IconFlags flags: Int): Icon? {
		return scriptPropertyIcon
	}
	//endregion

	//region StellarisScriptBlock
	@JvmStatic
	fun isEmpty(element: StellarisScriptBlock): Boolean {
		return element.firstChild == null
	}

	@JvmStatic
	fun isArray(element: StellarisScriptBlock): Boolean {
		return element.firstChild is StellarisScriptString?
	}

	@JvmStatic
	fun isObject(element: StellarisScriptBlock): Boolean {
		return element.firstChild is StellarisScriptProperty?
	}

	@JvmStatic
	fun getComponents(element: StellarisScriptBlock): List<PsiElement> {
		//如果存在元素为property，则认为所有合法的元素都是property
		return if(element.isObject) element.propertyList else element.itemList
	}
	//endregion

	////region StellarisScriptArray
	//@JvmStatic
	//fun getComponents(element: StellarisScriptArray): List<PsiElement> {
	//	return  element.stringList
	//}
	////endregion
	//
	////region StellarisScriptObject
	//@JvmStatic
	//fun getComponents(element: StellarisScriptObject): List<PsiElement> {
	//	return element.propertyList
	//}
	//endregion

	//region StellarisScriptItem,
	@JvmStatic
	fun getIcon(element: StellarisScriptItem, @Iconable.IconFlags flags: Int): Icon? {
		return scriptItemIcon
	}

	@JvmStatic
	fun getValue(element: StellarisScriptItem): String {
		return element.text.unquote()
	}
	//endregion

	//region StellarisScriptBoolean
	@JvmStatic
	fun getValue(element: StellarisScriptBoolean): String {
		return element.text
	}
	//endregion

	//region StellarisScriptNumber
	@JvmStatic
	fun getValue(element: StellarisScriptNumber): String {
		return element.text
	}
	//endregion

	//region StellarisScriptString
	@JvmStatic
	fun getValue(element: StellarisScriptString): String {
		return element.text.unquote()
	}

	@JvmStatic
	fun getReference(element: StellarisScriptString): StellarisScriptStringPsiReference? {
		//只有可能是属性的键的情况下才有可能是引用
		if(!element.isValidPropertyKey) return null
		return StellarisScriptStringPsiReference(element, TextRange(0, element.textLength))
	}

	//不包含空格的情况下才有可能是属性的键
	@JvmStatic
	fun isValidPropertyKey(element: StellarisScriptString): Boolean {
		return !element.value.any { it.isWhitespace() }
	}
	//endregion

	//region StellarisScriptColor
	@JvmStatic
	fun getValue(element: StellarisScriptColor): String? {
		return element.text
	}

	@JvmStatic
	fun getColor(element:StellarisScriptColor): Color?{
		return runCatching {
			val text = element.text
			val colorType = text.substringBefore('{').trim()
			val args = text.substringAfter('{').substringBefore('}').trim()
				.split("\\s+".toRegex())

			when(colorType) {
				"rgb" -> args.let { (r, g, b) -> Color(r.toInt(), g.toInt(), b.toInt()) }
				"rgba" -> args.let { (r, g, b,a) -> Color(r.toInt(), g.toInt(), b.toInt(),a.toInt()) }
				"hsb" -> args.let { (h, s, b) -> Color.getHSBColor(h.toFloat(),s.toFloat(),b.toFloat()) }
				"hsv" -> args.let { (h, s, v) -> TODO() }
				else -> null
			}
		}.getOrNull()
	}
	//endregion
}
