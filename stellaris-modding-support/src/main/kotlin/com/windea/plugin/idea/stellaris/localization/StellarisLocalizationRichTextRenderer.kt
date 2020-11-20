package com.windea.plugin.idea.stellaris.localization

import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import java.io.*

/**
 * 本地化文本的渲染器。
 *
 * 基于颜色渲染文本。
 */
object StellarisLocalizationRichTextRenderer {
	fun render(element:StellarisLocalizationPropertyValue):String{
		return renderTo(element,StringWriter()).toString()
	}
	
	fun renderTo(element: StellarisLocalizationPropertyValue, writer: Writer) {
		try {
			element.richTextList.forEach { renderTo(it, writer) }
		} catch(e: Exception) {
			e.printStackTrace()
			writer.write("<code>(syntax error)</code>")
		}
	}
	
	private fun renderTo(element: StellarisLocalizationRichText, writer: Writer) {
		when(element) {
			is StellarisLocalizationString -> writer.append(element.text)
			is StellarisLocalizationEscape -> renderEscapeTo(element, writer)
			is StellarisLocalizationPropertyReference -> renderPropertyReferenceTo(element, writer)
			is StellarisLocalizationIcon -> renderIconTo(writer, element)
			is StellarisLocalizationSerialNumber -> renderSerialNumberTo(element, writer)
			is StellarisLocalizationCode -> renderCodeTo(writer, element)
			is StellarisLocalizationColorfulText -> renderColorfulTextTo(element, writer)
		}
	}
	
	private fun renderEscapeTo(element: StellarisLocalizationEscape, writer: Writer) {
		val elementText = element.text
		when {
			elementText == "\\n" -> writer.append("<br>")
			elementText == "\\t" -> writer.append("&emsp;")
			elementText.length > 1 -> writer.append(elementText[1])
		}
	}
	
	private fun renderPropertyReferenceTo(element: StellarisLocalizationPropertyReference, writer: Writer) {
		//TODO 适用引用参数
		val reference = element.reference
		if(reference != null) {
			val resolve = reference.resolve() as? StellarisLocalizationProperty
			if(resolve != null) {
				val propertyValue = resolve.propertyValue
				if(propertyValue != null) {
					val rgbText = element.stellarisColor?.rgbText
					if(rgbText != null) writer.append("<span style='color: $rgbText;'>")
					renderTo(propertyValue, writer)
					if(rgbText != null) writer.append("</span>")
					return
				}
			}
		}
		//如果解析引用失败，则直接使用原始文本
		writer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderIconTo(writer: Writer, element: StellarisLocalizationIcon) {
		val name = element.name ?: return
		val iconUrl = StellarisLocalizationIconUrlResolver.resolve(name)
		if(iconUrl.isNotEmpty()) {
			if(iconUrl[0] != '<') writer.append(iconTag(iconUrl)) else writer.append(iconUrl)
		}
		println(writer.toString())
	}
	
	private fun renderSerialNumberTo(element: StellarisLocalizationSerialNumber, writer: Writer) {
		val placeholderText = element.stellarisSerialNumber?.placeholderText
		if(placeholderText != null) {
			writer.append(placeholderText)
			return
		}
		//如果解析引用失败，则直接使用原始文本
		writer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderCodeTo(writer: Writer, element: StellarisLocalizationCode) {
		writer.append("<code>").append(element.text).append("</code>")
	}
	
	private fun renderColorfulTextTo(element: StellarisLocalizationColorfulText, writer: Writer) {
		val rgbText = element.stellarisColor?.rgbText
		if(rgbText != null) writer.append("<span style='color: $rgbText;'>")
		for(v in element.richTextList) {
			renderTo(v, writer)
		}
		if(rgbText != null) writer.append("</span>")
	}
}
