package com.windea.plugin.idea.stellaris.localization

import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import com.windea.plugin.idea.stellaris.script.psi.*
import java.io.*

/**
 * 本地化文本的渲染器。
 *
 * 基于颜色渲染文本。
 */
object StellarisLocalizationRichTextRenderer {
	fun render(element:StellarisLocalizationPropertyValue):String{
		return renderTo(element,StringBuilder()).toString()
	}
	
	fun renderTo(element: StellarisLocalizationPropertyValue, buffer: Appendable) {
		try {
			element.richTextList.forEach { renderTo(it, buffer) }
		} catch(e: Exception) {
			e.printStackTrace()
			buffer.append("<code>(syntax error)</code>")
		}
	}
	
	private fun renderTo(element: StellarisLocalizationRichText, buffer: Appendable) {
		when(element) {
			is StellarisLocalizationString -> buffer.append(element.text)
			is StellarisLocalizationEscape -> renderEscapeTo(element, buffer)
			is StellarisLocalizationPropertyReference -> renderPropertyReferenceTo(element, buffer)
			is StellarisLocalizationIcon -> renderIconTo(element,buffer)
			is StellarisLocalizationSerialNumber -> renderSerialNumberTo(element, buffer)
			is StellarisLocalizationCode -> renderCodeTo(element,buffer)
			is StellarisLocalizationColorfulText -> renderColorfulTextTo(element, buffer)
		}
	}
	
	private fun renderEscapeTo(element: StellarisLocalizationEscape, buffer: Appendable) {
		val elementText = element.text
		when {
			elementText == "\\n" -> buffer.append("<br>")
			elementText == "\\t" -> buffer.append("&emsp;")
			elementText.length > 1 -> buffer.append(elementText[1])
		}
	}
	
	private fun renderPropertyReferenceTo(element: StellarisLocalizationPropertyReference, buffer: Appendable) {
		//TODO 适用引用参数
		val reference = element.reference
		if(reference != null) {
			val resolve = reference.resolve() as? StellarisLocalizationProperty
			if(resolve != null) {
				val propertyValue = resolve.propertyValue
				if(propertyValue != null) {
					val rgbText = element.stellarisColor?.rgbText
					if(rgbText != null) buffer.append("<span style='color: $rgbText;'>")
					renderTo(propertyValue, buffer)
					if(rgbText != null) buffer.append("</span>")
					return
				}
			}
		}
		//如果解析引用失败，则直接使用原始文本
		buffer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderIconTo(element: StellarisLocalizationIcon, buffer: Appendable) {
		val name = element.name ?: return
		val iconUrl = name.resolveIconUrl()
		if(iconUrl.isNotEmpty()) {
			if(iconUrl[0] != '<') buffer.append(iconTag(iconUrl)) else buffer.append(iconUrl)
		}
	}
	
	private fun renderSerialNumberTo(element: StellarisLocalizationSerialNumber, buffer: Appendable) {
		val placeholderText = element.stellarisSerialNumber?.placeholderText
		if(placeholderText != null) {
			buffer.append(placeholderText)
			return
		}
		//如果解析引用失败，则直接使用原始文本
		buffer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderCodeTo(element: StellarisLocalizationCode, buffer: Appendable) {
		buffer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderColorfulTextTo(element: StellarisLocalizationColorfulText, buffer: Appendable) {
		val rgbText = element.stellarisColor?.rgbText
		if(rgbText != null) buffer.append("<span style='color: $rgbText;'>")
		for(v in element.richTextList) {
			renderTo(v, buffer)
		}
		if(rgbText != null) buffer.append("</span>")
	}
}

