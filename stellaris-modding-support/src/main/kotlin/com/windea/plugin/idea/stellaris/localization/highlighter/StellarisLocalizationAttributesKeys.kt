@file:Suppress("DEPRECATION")

package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.intellij.openapi.editor.markup.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message
import com.windea.plugin.idea.stellaris.domain.*
import java.awt.*

object StellarisLocalizationAttributesKeys {
	val COLON_KEY = createTextAttributesKey(message("stellaris.localization.syntax.colon"), OPERATION_SIGN)
	val NUMBER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.number"), NUMBER)
	val PROPERTY_HEADER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyHeader"), KEYWORD)
	val PROPERTY_KEY_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyKey"), KEYWORD)
	val PROPERTY_VALUE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyValue"), STRING)
	val COMMENT_KEY = createTextAttributesKey(message("stellaris.localization.syntax.comment"), LINE_COMMENT)
	val MARKER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.marker"), KEYWORD)
	val CODE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.code"), IDENTIFIER)
	val ICON_KEY = createTextAttributesKey(
		message("stellaris.localization.syntax.icon"),
		IDENTIFIER.defaultAttributes.clone().apply { foregroundColor = Color(0x5C8AE6) }
	)
	val SERIAL_NUMBER_CODE_KEY = createTextAttributesKey(
		message("stellaris.localization.syntax.serialNumberCode"),
		IDENTIFIER.defaultAttributes.clone().apply { this.fontType = Font.BOLD }
	)
	val COLOR_CODE_KEY = createTextAttributesKey(
		message("stellaris.localization.syntax.colorCode"),
		IDENTIFIER.defaultAttributes.clone().apply { this.fontType = Font.BOLD }
	)
	val VALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.validEscape"), VALID_STRING_ESCAPE)
	val INVALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.invalidEscape"), INVALID_STRING_ESCAPE)
	val BAD_CHARACTER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.badCharacter"), BAD_CHARACTER)

	val COLOR_CODE_KEYS = StellarisColor.map.mapValues { (_, value) ->
		createTextAttributesKey(
			"${message("stellaris.localization.syntax.colorCode")}_${value.key}",
			COLOR_CODE_KEY.defaultAttributes.clone().apply {
				foregroundColor = value.color
				//effectColor = value.color
				//effectType = EffectType.BOLD_LINE_UNDERSCORE
				fontType = Font.BOLD
			}
		)
	}
}
