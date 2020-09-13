package com.windea.plugin.idea.sbtext.psi.impl

import com.intellij.psi.*
import com.intellij.refactoring.suggested.*
import com.windea.plugin.idea.sbtext.*
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
		val colorCode = element.name
		return when{
			colorCode == null ->  null
			colorCode.startsWith("#") -> colorCode.drop(1).toIntOrNull(16)?.let { Color(it) }
			else -> StarboundColor.map[colorCode]?.color
		}
	}

	@JvmStatic
	fun setColor(element:SbTextColorfulText,color:Color){
		//什么都不做
	}
	//endregion
}

