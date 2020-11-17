package com.windea.plugin.idea.stellaris.localization

import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.localization.psi.*
import java.io.*

object StellarisLocalizationRenderer {
	fun render(element:StellarisLocalizationPropertyValue):String{
		return renderTo(element,StringWriter()).toString()
	}
	
	fun renderTo(element: StellarisLocalizationPropertyValue, writer: Writer) {
		try {
			element.richTextList.forEach { renderTo(it, writer) }
		} catch(e: Exception) {
			writer.write(syntaxError)
		}
	}
	
	private fun renderTo(element: StellarisLocalizationRichText, writer: Writer) {
		when(element) {
			is StellarisLocalizationString -> writer.append(element.text)
			is StellarisLocalizationEscape -> {
				val elementText = element.text
				when {
					elementText == "\\n" -> writer.append("<br>")
					elementText == "\\t" -> writer.append("&emsp;")
					elementText.length > 1 -> writer.append(elementText[1])
				}
			}
			is StellarisLocalizationPropertyReference -> {
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
			is StellarisLocalizationIcon -> {
				//TODO 解析得到真正的图标
				writer.append("<code>").append(element.text).append("</code>")
			}
			is StellarisLocalizationSerialNumber -> {
				val placeholderText = element.stellarisSerialNumber?.placeholderText
				if(placeholderText != null) {
					writer.append(placeholderText)
					return
				}
				//如果解析引用失败，则直接使用原始文本
				writer.append("<code>").append(element.text).append("</code>")
			}
			is StellarisLocalizationCode -> {
				//TODO 解析得到真正的文本
				writer.append("<code>").append(element.text).append("</code>")
			}
			is StellarisLocalizationColorfulText -> {
				val rgbText = element.stellarisColor
				if(rgbText != null) writer.append("<span style='color: $rgbText;'>")
				for(v in element.richTextList) {
					renderTo(v, writer)
				}
				if(rgbText != null) writer.append("</span>")
			}
		}
	}
}
