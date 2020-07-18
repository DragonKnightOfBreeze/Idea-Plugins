@file:Suppress("UNUSED_PARAMETER")

package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.icons.*
import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.script.psi.*
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createPropertyKey
import com.windea.plugin.idea.stellaris.script.psi.StellarisScriptElementFactory.createVariableName
import com.windea.plugin.idea.stellaris.script.reference.*
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
	fun getIcon(element: StellarisScriptVariableDefinition, @Iconable.IconFlags flags: Int): Icon? {
		return PlatformIcons.VARIABLE_ICON
	}

	@JvmStatic
	fun getValue(element: StellarisScriptVariableDefinition):String?{
		return element.variableValue?.text
	}
	//endregion

	//region StellarisScriptVariableReference
	@JvmStatic
	fun getName(element: StellarisScriptVariableReference): String? {
		return element.text
	}

	@JvmStatic
	fun setName(element: StellarisScriptVariableReference, name: String): PsiElement {
		element.replace(createVariableName(element.project, name))
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: StellarisScriptVariableReference): PsiElement? {
		return element
	}

	@JvmStatic
	fun getIcon(element: StellarisScriptVariableReference, @Iconable.IconFlags flags: Int): Icon? {
		return PlatformIcons.VARIABLE_READ_ACCESS
	}

	@JvmStatic
	fun getReference(element: StellarisScriptVariableReference):PsiReference{
		return StellarisScriptVariablePsiReference(element, TextRange(0,element.textLength))
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
		return PlatformIcons.PROPERTY_ICON
	}
	//endregion

	//region StellarisScriptPropertyValue
	@JvmStatic
	fun getValue(element: StellarisScriptPropertyValue): String? {
		return element.text?.unquote()
	}
	//endregion

	//region StellarisScriptPropertyList
	@JvmStatic
	fun getComponents(element: StellarisScriptList): List<PsiElement> {
		//如果存在元素为text，则认为所有合法的元素都是text
		return if(element.textList.isEmpty()) element.propertyList else element.textList
	}
	//endregion

	//region StellarisScriptPlainText
	@JvmStatic
	fun getIcon(element: StellarisScriptPlainText, @Iconable.IconFlags flags: Int): Icon? {
		return AllIcons.Nodes.Constant
	}

	@JvmStatic
	fun getValue(element: StellarisScriptPlainText): String? {
		return element.text?.unquote()
	}
	//endregion

	//region StellarisScriptStringLiteral
	@JvmStatic
	fun getReference(element:StellarisScriptStringLiteral):PsiReference{
		return StellarisScriptStringLiteralPsiReference(element, TextRange(0,element.textLength))
	}
	//endregion
}
