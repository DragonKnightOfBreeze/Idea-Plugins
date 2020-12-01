package com.windea.plugin.idea.pdx.localisation

import com.windea.plugin.idea.pdx.*
import com.windea.plugin.idea.pdx.localisation.psi.*
import com.windea.plugin.idea.pdx.script.psi.*
import java.io.*

/**
 * 本地化文本的渲染器。
 *
 * 基于颜色渲染文本。
 */
object PdxLocalisationRichTextRenderer {
	fun render(element:PdxLocalisationPropertyValue):String{
		val buffer = StringBuilder()
		renderTo(element,buffer)
		return buffer.toString()
	}
	
	fun renderTo(element: PdxLocalisationPropertyValue, buffer: Appendable) {
		try {
			element.richTextList.forEach { renderTo(it, buffer) }
		} catch(e: Exception) {
			e.printStackTrace()
			buffer.append("<code>(syntax error)</code>")
		}
	}
	
	private fun renderTo(element: PdxLocalisationRichText, buffer: Appendable) {
		when(element) {
			is PdxLocalisationString -> buffer.append(element.text)
			is PdxLocalisationEscape -> renderEscapeTo(element, buffer)
			is PdxLocalisationPropertyReference -> renderPropertyReferenceTo(element, buffer)
			is PdxLocalisationIcon -> renderIconTo(element,buffer)
			is PdxLocalisationSerialNumber -> renderSerialNumberTo(element, buffer)
			is PdxLocalisationCode -> renderCodeTo(element,buffer)
			is PdxLocalisationColorfulText -> renderColorfulTextTo(element, buffer)
		}
	}
	
	private fun renderEscapeTo(element: PdxLocalisationEscape, buffer: Appendable) {
		val elementText = element.text
		when {
			elementText == "\\n" -> buffer.append("<br>")
			elementText == "\\t" -> buffer.append("&emsp;")
			elementText.length > 1 -> buffer.append(elementText[1])
		}
	}
	
	private fun renderPropertyReferenceTo(element: PdxLocalisationPropertyReference, buffer: Appendable) {
		val reference = element.reference
		if(reference != null) {
			val resolve = reference.resolve() as? PdxLocalisationProperty
			if(resolve != null) {
				val propertyValue = resolve.propertyValue
				if(propertyValue != null) {
					val rgbText = element.pdxColor?.rgbText
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
	
	private fun renderIconTo(element: PdxLocalisationIcon, buffer: Appendable) {
		val name = element.name ?: return
		val iconUrl = name.resolveIconUrl()
		if(iconUrl.isNotEmpty()) {
			if(iconUrl[0] != '<') buffer.append(iconTag(iconUrl)) else buffer.append(iconUrl)
		}
	}
	
	private fun renderSerialNumberTo(element: PdxLocalisationSerialNumber, buffer: Appendable) {
		val placeholderText = element.pdxSerialNumber?.placeholderText
		if(placeholderText != null) {
			buffer.append(placeholderText)
			return
		}
		//如果解析引用失败，则直接使用原始文本
		buffer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderCodeTo(element: PdxLocalisationCode, buffer: Appendable) {
		buffer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderColorfulTextTo(element: PdxLocalisationColorfulText, buffer: Appendable) {
		val rgbText = element.pdxColor?.rgbText
		if(rgbText != null) buffer.append("<span style='color: $rgbText;'>")
		for(v in element.richTextList) {
			renderTo(v, buffer)
		}
		if(rgbText != null) buffer.append("</span>")
	}
}
