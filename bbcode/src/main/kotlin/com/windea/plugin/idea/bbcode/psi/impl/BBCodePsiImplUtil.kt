package com.windea.plugin.idea.bbcode.psi.impl

import com.intellij.openapi.util.*
import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.bbcode.*
import com.windea.plugin.idea.bbcode.psi.*
import javax.swing.*

object BBCodePsiImplUtil {
	//region BBCodeTag
	@JvmStatic
	fun getName(element: BBCodeTag): String? {
		return element.tagPrefix.tagName?.text
	}

	@JvmStatic
	fun setName(element: BBCodeTag,name:String): PsiElement {
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: BBCodeTag): PsiElement? {
		return element.tagPrefix.tagName
	}

	@JvmStatic
	fun getTextOffset(element: BBCodeTag): Int {
		return element.tagPrefix.tagName?.startOffset ?: element.tagPrefix.startOffset + 1
	}

	@JvmStatic
	fun getIcon(element: BBCodeTag, @Iconable.IconFlags flags: Int): Icon? {
		return bbcodeTagIcon
	}
	//endregion

	//region BBCodeAttribute
	@JvmStatic
	fun getName(element: BBCodeAttribute): String? {
		return element.attributeName.text
	}

	@JvmStatic
	fun setName(element: BBCodeAttribute,name:String): PsiElement {
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: BBCodeAttribute): PsiElement? {
		return element.attributeName
	}

	@JvmStatic
	fun getTextOffset(element: BBCodeAttribute): Int {
		return element.attributeName.startOffset
	}

	@JvmStatic
	fun getValue(element: BBCodeAttribute): String? {
		return element.attributeValue?.text
	}
	//endregion

	//region BBCodeText
	@JvmStatic
	fun getValue(element: BBCodeText): String? {
		return element.textToken.text
	}
	//endregion
}
