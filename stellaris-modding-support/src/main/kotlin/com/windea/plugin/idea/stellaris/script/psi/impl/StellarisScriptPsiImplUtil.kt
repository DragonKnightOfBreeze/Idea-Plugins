@file:Suppress("UNUSED_PARAMETER", "UNUSED_DESTRUCTURED_PARAMETER_ENTRY", "IntroduceWhenSubject", "MoveVariableDeclarationIntoWhen")

package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createVariableName
import com.windea.plugin.idea.stellaris.script.reference.*
import org.apache.commons.imaging.color.*
import java.awt.*
import javax.swing.*

object StellarisScriptPsiImplUtil {
	//region StellarisScriptVariable
	@JvmStatic
	fun getName(element: StellarisScriptVariable): String? {
		return element.stub?.key?: element.variableName.text
	}

	@JvmStatic
	fun setName(element: StellarisScriptVariable, name: String): PsiElement {
		element.variableName.replace(createVariableName(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getIcon(element: StellarisScriptVariable, @Iconable.IconFlags flags: Int): Icon {
		return stellarisScriptVariableIcon
	}

	@JvmStatic
	fun getValue(element: StellarisScriptVariable): String? {
		return element.variableValue?.text?.unquote()
	}
	//endregion
	
	//region StellarisScriptProperty
	@JvmStatic
	fun getName(element: StellarisScriptProperty): String {
		return element.stub?.key?: element.propertyKey.text.unquote()
	}

	@JvmStatic
	fun setName(element: StellarisScriptProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}

	@JvmStatic
	fun getIcon(element: StellarisScriptProperty, @Iconable.IconFlags flags: Int): Icon {
		return stellarisScriptPropertyIcon
	}
	
	@JvmStatic
	fun getValue(element: StellarisScriptProperty): String? {
		return element.propertyValue?.text?.unquote()
	}
	//endregion
	
	//region StellarisScriptVariableReference
	@JvmStatic
	fun getReference(element: StellarisScriptVariableReference): StellarisScriptVariablePsiReference {
		return StellarisScriptVariablePsiReference(element, element.variableReferenceId.textRangeInParent)
	}
	//endregion

	//region StellarisScriptValue
	@JvmStatic
	fun getIcon(element: StellarisScriptValue, @Iconable.IconFlags flags: Int): Icon {
		return stellarisScriptValueIcon
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

	//region StellarisScriptStringValue
	@JvmStatic
	fun getValue(element: StellarisScriptStringValue): String {
		return element.text.unquote()
	}
	//endregion

	//region StellarisScriptString
	@JvmStatic
	fun getValue(element: StellarisScriptString): String {
		return element.text.unquote()
	}
	
	private val emptyArray = arrayOf<PsiPolyVariantReference>()
	
	@JvmStatic
	fun getReference(element: StellarisScriptString):StellarisScriptStringAsPropertyPsiReference?{
		//过滤非法的情况
		if(element.value.isInvalidPropertyName()) return null
		return StellarisScriptStringAsPropertyPsiReference(element, TextRange(0, element.textLength))
	}
	//endregion

	//region StellarisScriptColor
	@JvmStatic
	fun getValue(element: StellarisScriptColor): String {
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
			val newColor = StellarisScriptElementFactory.createValue(element.project, newText) as? StellarisScriptColor
			if(newColor != null) element.replace(newColor)
		}
	}

	private fun ColorHsl.toColor() =Color(ColorConversions.convertHSLtoRGB(this))

	private fun Color.toColorHsl() = ColorConversions.convertRGBtoHSL(this.rgb)

	private fun ColorHsv.toColor() =Color(ColorConversions.convertHSVtoRGB(this))

	private fun Color.toColorHsv() = ColorConversions.convertRGBtoHSV(this.rgb)
	//endregion

	//region StellarisScriptCode
	@JvmStatic
	fun getValue(element: StellarisScriptCode): String? {
		return element.text
	}
	//endregion

	//region StellarisScriptBlock
	@JvmStatic
	fun isEmpty(element:StellarisScriptBlock):Boolean{
		element.forEachChild {
			if(it is StellarisScriptProperty || it is StellarisLocalizationProperty) return false
		}
		return true
	}

	@JvmStatic
	fun isNotEmpty(element:StellarisScriptBlock):Boolean{
		element.forEachChild {
			if(it is StellarisScriptProperty || it is StellarisLocalizationProperty) return true
		}
		return true
	}

	@JvmStatic
	fun isObject(element: StellarisScriptBlock): Boolean {
		element.forEachChild {
			when(it){
				is StellarisScriptProperty ->return true
				is StellarisScriptValue -> return false
			}
		}
		return false
	}

	@JvmStatic
	fun isArray(element: StellarisScriptBlock): Boolean {
		element.forEachChild {
			when(it){
				is StellarisScriptProperty ->return false
				is StellarisScriptValue -> return true
			}
		}
		return false
	}

	@JvmStatic
	fun getComponents(element: StellarisScriptBlock): List<PsiElement> {
		//如果存在元素为property，则认为所有合法的元素都是property
		return if(element.isObject) element.propertyList else element.valueList
	}
	//endregion
}
