@file:Suppress("UNUSED_PARAMETER", "UNUSED_DESTRUCTURED_PARAMETER_ENTRY", "IntroduceWhenSubject", "MoveVariableDeclarationIntoWhen")

package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createVariableName
import com.windea.plugin.idea.stellaris.script.reference.*
import org.apache.commons.imaging.color.*
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
		return stellarisScriptVariableIcon
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
		return stellarisScriptPropertyIcon
	}
	//endregion

	//region StellarisScriptBlock
	@JvmStatic
	fun isEmpty(element:StellarisScriptBlock):Boolean{
		return element.children.isEmpty()
	}

	@JvmStatic
	fun isNotEmpty(element:StellarisScriptBlock):Boolean{
		return element.children.isNotEmpty()
	}

	@JvmStatic
	fun isOSbject(element: StellarisScriptBlock): Boolean {
		return element.firstChild is StellarisScriptProperty
	}

	@JvmStatic
	fun isArray(element: StellarisScriptBlock): Boolean {
		return element.firstChild is StellarisScriptItem
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
		return stellarisScriptItemIcon
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
		//忽略异常
		return runCatching {
			val text = element.text
			val colorType = text.substringBefore('{').trim()
			val args = text.substringAfter('{').substringBefore('}').trim().split("\\s+".toRegex())

			//根据不同的颜色类型得到不同的颜色对象
			when(colorType) {
				"rgb" -> args.let { (r, g, b) -> Color(r.toInt(), g.toInt(), b.toInt()) }
				"rgba" -> args.let { (r, g, b,a) -> Color(r.toInt(), g.toInt(), b.toInt(),a.toInt()) }
				"hsb" -> args.let { (h, s, b) -> Color.getHSBColor(h.toFloat(),s.toFloat(),b.toFloat()) }
				"hsv" -> args.let { (h, s, v) -> ColorHsv(h.toDouble(),s.toDouble(),v.toDouble()).toColor() }
				"hsl" -> args.let { (h, s, l) -> ColorHsl(h.toDouble(),s.toDouble(),l.toDouble()).toColor() }
				else -> null
			}
		}.getOrNull()
	}

	@JvmStatic
	fun setColor(element:StellarisScriptColor,color:Color){
		runCatching {
			val text = element.text
			val colorType = text.substringBefore('{').trim()

			//使用rgb或者rgba
			val shouldBeRgba = color.alpha != 255
			val newText = when{
				colorType == "rgba" || shouldBeRgba -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
				else ->  "rgb { ${color.run { "$red $green $blue" }} }"
			}

			////根据不同的颜色类型生成不同的文本
			//val newText = when(colorType) {
			//	"rgb" -> "rgb { ${color.run { "$red $green $blue" }} }"
			//	"rgba" -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
			//	"hsv" -> "hsv { ${color.toColorHsv().run { "$H $S $V" }} }"
			//	"hsl" -> "hsl { ${color.toColorHsl().run { "$H $S $L" }} }"
			//	else -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
			//}

			element.replace(StellarisScriptElementFactory.createColor(element.project, newText))
		}
	}

	private fun ColorHsl.toColor() =Color(ColorConversions.convertHSLtoRGB(this))

	private fun Color.toColorHsl() = ColorConversions.convertRGBtoHSL(this.rgb)

	private fun ColorHsv.toColor() =Color(ColorConversions.convertHSVtoRGB(this))

	private fun Color.toColorHsv() = ColorConversions.convertRGBtoHSV(this.rgb)
	//endregion
}
