package com.windea.plugin.idea.sbtext.psi.impl

import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.sbtext.psi.*
import java.awt.*

object SbTextPsiImplUtil {
	//region SbTextColorfulText
	@JvmStatic
	fun getName(element:SbTextColorfulText):String?{
		return element.colorMarker.colorCode?.text
	}

	@JvmStatic
	fun setName(element:SbTextColorfulText,name:String):PsiElement{
		return element
	}

	@JvmStatic
	fun getNameIdentifier(element: SbTextColorfulText):PsiElement?{
		return element.colorMarker.colorCode
	}

	@JvmStatic
	fun getTextOffset(element:SbTextColorfulText):Int{
		return element.colorMarker.colorCode?.startOffset?:element.colorMarker.startOffset + 1
	}

	@JvmStatic
	fun getColor(element:SbTextColorfulText): Color? {
		return null
	}

	@JvmStatic
	fun setColor(element:SbTextColorfulText,color:Color){

	}
	//endregion
}

