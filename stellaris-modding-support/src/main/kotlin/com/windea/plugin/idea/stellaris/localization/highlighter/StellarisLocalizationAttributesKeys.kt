package com.windea.plugin.idea.stellaris.localization.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.StellarisBundle.message

@Suppress("DEPRECATION")
object StellarisLocalizationAttributesKeys {
	//private val ICON = IDENTIFIER.defaultAttributes.clone().apply { foregroundColor = Color(0x5C8AE6) }

	@JvmField val COLON_KEY = createTextAttributesKey(message("stellaris.localization.syntax.colon"), OPERATION_SIGN)
	@JvmField val NUMBER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.number"), NUMBER)
	@JvmField val LOCALE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.Locale"), KEYWORD)
	@JvmField val PROPERTY_KEY_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyKey"), KEYWORD)
	@JvmField val STRING_KEY = createTextAttributesKey(message("stellaris.localization.syntax.string"), STRING)
	@JvmField val COMMENT_KEY = createTextAttributesKey(message("stellaris.localization.syntax.comment"), LINE_COMMENT)
	@JvmField val MARKER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.marker"), KEYWORD)
	@JvmField val PROPERTY_REFERENCE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.propertyReference"), KEYWORD)
	@JvmField val PARAMETER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.parameter"), IDENTIFIER)
	@JvmField val CODE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.code"), IDENTIFIER)
	@JvmField val ICON_KEY = createTextAttributesKey(message("stellaris.localization.syntax.icon"), IDENTIFIER)
	@JvmField val SERIAL_NUMBER_ID_KEY = createTextAttributesKey(message("stellaris.localization.syntax.serialNumberId"), IDENTIFIER)
	@JvmField val COLOR_ID_KEY = createTextAttributesKey(message("stellaris.localization.syntax.colorId"), IDENTIFIER)
	@JvmField val VALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.validEscape"), VALID_STRING_ESCAPE)
	@JvmField val INVALID_ESCAPE_KEY = createTextAttributesKey(message("stellaris.localization.syntax.invalidEscape"), INVALID_STRING_ESCAPE)
	@JvmField val BAD_CHARACTER_KEY = createTextAttributesKey(message("stellaris.localization.syntax.badCharacter"), BAD_CHARACTER)

	@JvmField val COLOR_ID_KEYS = StellarisColor.map.mapValues { (_, value) ->
		val attributes = IDENTIFIER.defaultAttributes.clone().apply {
			foregroundColor = value.color
		}
		createTextAttributesKey("${message("stellaris.localization.syntax.colorId")}_${value.key}", attributes)
	}
}
