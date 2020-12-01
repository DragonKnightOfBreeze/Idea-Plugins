@file:Suppress("UNUSED_PARAMETER", "UNUSED_DESTRUCTURED_PARAMETER_ENTRY", "IntroduceWhenSubject", "MoveVariableDeclarationIntoWhen")

package com.windea.plugin.idea.pdx.script.psi.impl

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.script.psi.*
import com.windea.plugin.idea.pdx.script.psi.PdxScriptElementFactory.createPropertyKey
import com.windea.plugin.idea.pdx.script.psi.PdxScriptElementFactory.createValue
import com.windea.plugin.idea.pdx.script.psi.PdxScriptElementFactory.createVariableName
import com.windea.plugin.idea.pdx.script.reference.*
import org.apache.commons.imaging.color.*
import java.awt.*
import javax.swing.*

object PdxScriptPsiImplUtil {
	//region PdxScriptVariable
	@JvmStatic
	fun getName(element: PdxScriptVariable): String? {
		return element.stub?.key ?: element.variableName.text
	}
	
	@JvmStatic
	fun setName(element: PdxScriptVariable, name: String): PsiElement {
		element.variableName.replace(createVariableName(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxScriptVariable): PsiElement {
		return element.variableName.variableNameId
	}
	
	@JvmStatic
	fun getIcon(element: PdxScriptVariable, @Iconable.IconFlags flags: Int): Icon {
		return pdxScriptVariableIcon
	}
	
	@JvmStatic
	fun getValue(element: PdxScriptVariable): String? {
		return element.variableValue?.text?.unquote()
	}
	//endregion
	
	//region PdxScriptProperty
	@JvmStatic
	fun getName(element: PdxScriptProperty): String {
		return element.stub?.key ?: element.propertyKey.text.unquote()
	}
	
	@JvmStatic
	fun setName(element: PdxScriptProperty, name: String): PsiElement {
		element.propertyKey.replace(createPropertyKey(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxScriptProperty): PsiElement? {
		return element.propertyKey.let { it.propertyKeyId ?: it.quotedPropertyKeyId }
	}
	
	@JvmStatic
	fun getIcon(element: PdxScriptProperty, @Iconable.IconFlags flags: Int): Icon {
		return pdxScriptPropertyIcon
	}
	
	@JvmStatic
	fun getValue(element: PdxScriptProperty): String? {
		return element.propertyValue?.text?.unquote()
	}
	//endregion
	
	//region PdxScriptVariableReference
	@JvmStatic
	fun getName(element: PdxScriptVariableReference): String? {
		return element.variableReferenceId.text
	}
	
	@JvmStatic
	fun setName(element: PdxScriptVariableReference, name: String): PsiElement {
		element.replace(createValue(element.project, name))
		return element
	}
	
	@JvmStatic
	fun getNameIdentifier(element: PdxScriptVariableReference): PsiElement {
		return element.variableReferenceId
	}
	
	@JvmStatic
	fun getReference(element: PdxScriptVariableReference): PdxScriptVariablePsiReference {
		return PdxScriptVariablePsiReference(element, TextRange(0, element.textLength))
	}
	//endregion
	
	//region PdxScriptValue
	@JvmStatic
	fun getIcon(element: PdxScriptValue, @Iconable.IconFlags flags: Int): Icon {
		return pdxScriptValueIcon
	}
	//endregion
	
	//region PdxScriptBoolean
	@JvmStatic
	fun getValue(element: PdxScriptBoolean): String {
		return element.text
	}
	//endregion
	
	//region PdxScriptNumber
	@JvmStatic
	fun getValue(element: PdxScriptNumber): String {
		return element.text
	}
	//endregion
	
	//region PdxScriptStringValue
	@JvmStatic
	fun getValue(element: PdxScriptStringValue): String {
		return element.text.unquote()
	}
	//endregion
	
	//region PdxScriptString
	@JvmStatic
	fun getValue(element: PdxScriptString): String {
		return element.text.unquote()
	}
	
	private val emptyArray = arrayOf<PsiPolyVariantReference>()
	
	@JvmStatic
	fun getReference(element: PdxScriptString): PdxScriptStringAsPropertyPsiReference? {
		//过滤非法的情况
		if(element.value.isInvalidPropertyName) return null
		return PdxScriptStringAsPropertyPsiReference(element, TextRange(0, element.textLength))
	}
	//endregion
	
	//region PdxScriptColor
	@JvmStatic
	fun getValue(element: PdxScriptColor): String {
		return element.text
	}
	
	@JvmStatic
	fun getColor(element: PdxScriptColor): Color? {
		//忽略异常
		return runCatching {
			val text = element.text
			val colorType = text.substringBefore('{').trim()
			val args = text.substringAfter('{').substringBefore('}').trim().split("\\s+".toRegex())
			
			//根据不同的颜色类型得到不同的颜色对象
			when(colorType) {
				"rgb" -> args.let { (r, g, b) -> Color(r.toInt(), g.toInt(), b.toInt()) }
				"rgba" -> args.let { (r, g, b, a) -> Color(r.toInt(), g.toInt(), b.toInt(), a.toInt()) }
				"hsb" -> args.let { (h, s, b) -> Color.getHSBColor(h.toFloat(), s.toFloat(), b.toFloat()) }
				"hsv" -> args.let { (h, s, v) -> ColorHsv(h.toDouble(), s.toDouble(), v.toDouble()).toColor() }
				"hsl" -> args.let { (h, s, l) -> ColorHsl(h.toDouble(), s.toDouble(), l.toDouble()).toColor() }
				else -> null
			}
		}.getOrNull()
	}
	
	@JvmStatic
	fun setColor(element: PdxScriptColor, color: Color) {
		runCatching {
			val text = element.text
			val colorType = text.substringBefore('{').trim()
			
			//使用rgb或者rgba
			val shouldBeRgba = color.alpha != 255
			val newText = when {
				colorType == "rgba" || shouldBeRgba -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
				else -> "rgb { ${color.run { "$red $green $blue" }} }"
			}
			
			////根据不同的颜色类型生成不同的文本
			//val newText = when(colorType) {
			//	"rgb" -> "rgb { ${color.run { "$red $green $blue" }} }"
			//	"rgba" -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
			//	"hsv" -> "hsv { ${color.toColorHsv().run { "$H $S $V" }} }"
			//	"hsl" -> "hsl { ${color.toColorHsl().run { "$H $S $L" }} }"
			//	else -> "rgba { ${color.run { "$red $green $blue $alpha" }} }"
			//}
			val newColor = PdxScriptElementFactory.createValue(element.project, newText) as? PdxScriptColor
			if(newColor != null) element.replace(newColor)
		}
	}
	
	private fun ColorHsl.toColor() = Color(ColorConversions.convertHSLtoRGB(this))
	
	private fun Color.toColorHsl() = ColorConversions.convertRGBtoHSL(this.rgb)
	
	private fun ColorHsv.toColor() = Color(ColorConversions.convertHSVtoRGB(this))
	
	private fun Color.toColorHsv() = ColorConversions.convertRGBtoHSV(this.rgb)
	//endregion
	
	//region PdxScriptCode
	@JvmStatic
	fun getValue(element: PdxScriptCode): String? {
		return element.text
	}
	//endregion
	
	//region PdxScriptBlock
	@JvmStatic
	fun isEmpty(element: PdxScriptBlock): Boolean {
		element.forEachChild {
			if(it is PdxScriptProperty || it is PdxLocalisationProperty) return false
		}
		return true
	}
	
	@JvmStatic
	fun isNotEmpty(element: PdxScriptBlock): Boolean {
		element.forEachChild {
			if(it is PdxScriptProperty || it is PdxLocalisationProperty) return true
		}
		return true
	}
	
	@JvmStatic
	fun isObject(element: PdxScriptBlock): Boolean {
		element.forEachChild {
			when(it) {
				is PdxScriptProperty -> return true
				is PdxScriptValue -> return false
			}
		}
		return false
	}
	
	@JvmStatic
	fun isArray(element: PdxScriptBlock): Boolean {
		element.forEachChild {
			when(it) {
				is PdxScriptProperty -> return false
				is PdxScriptValue -> return true
			}
		}
		return false
	}
	
	@JvmStatic
	fun getComponents(element: PdxScriptBlock): List<PsiElement> {
		//如果存在元素为property，则认为所有合法的元素都是property
		return if(element.isObject) element.propertyList else element.valueList
	}
	//endregion
}
