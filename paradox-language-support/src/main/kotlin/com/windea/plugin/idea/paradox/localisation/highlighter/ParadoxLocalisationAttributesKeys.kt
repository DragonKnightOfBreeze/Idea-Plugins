package com.windea.plugin.idea.paradox.localisation.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.message

@Suppress("DEPRECATION")
object ParadoxLocalisationAttributesKeys {
	private val colonName = message("paradox.localisation.externalName.colon")
	private val numberName = message("paradox.localisation.externalName.number")
	private val localeName = message("paradox.localisation.externalName.locale")
	private val propertyKeyName = message("paradox.localisation.externalName.propertyKey")
	private val stringName = message("paradox.localisation.externalName.string")
	private val commentName = message("paradox.localisation.externalName.comment")
	private val markerName = message("paradox.localisation.externalName.marker")
	private val propertyReferenceName = message("paradox.localisation.externalName.propertyReference")
	private val parameterName = message("paradox.localisation.externalName.parameter")
	private val codeName = message("paradox.localisation.externalName.code")
	private val iconName = message("paradox.localisation.externalName.icon")
	private val serialNumberName = message("paradox.localisation.externalName.serialNumber")
	private val colorName = message("paradox.localisation.externalName.color")
	private val validEscapeName = message("paradox.localisation.externalName.validEscape")
	private val invalidEscapeName = message("paradox.localisation.externalName.invalidEscape")
	private val badCharacterName = message("paradox.localisation.externalName.badCharacter")
	
	@JvmField val COLON_KEY = createTextAttributesKey(colonName, OPERATION_SIGN)
	@JvmField val NUMBER_KEY = createTextAttributesKey(numberName, NUMBER)
	@JvmField val LOCALE_KEY = createTextAttributesKey(localeName, KEYWORD)
	@JvmField val PROPERTY_KEY_KEY = createTextAttributesKey(propertyKeyName, KEYWORD)
	@JvmField val STRING_KEY = createTextAttributesKey(stringName, STRING)
	@JvmField val COMMENT_KEY = createTextAttributesKey(commentName, LINE_COMMENT)
	@JvmField val MARKER_KEY = createTextAttributesKey(markerName, KEYWORD)
	@JvmField val PROPERTY_REFERENCE_KEY = createTextAttributesKey(propertyReferenceName, KEYWORD)
	@JvmField val PARAMETER_KEY = createTextAttributesKey(parameterName, IDENTIFIER)
	@JvmField val CODE_KEY = createTextAttributesKey(codeName, IDENTIFIER)
	@JvmField val ICON_KEY = createTextAttributesKey(iconName, IDENTIFIER)
	@JvmField val SERIAL_NUMBER_KEY = createTextAttributesKey(serialNumberName, IDENTIFIER)
	@JvmField val COLOR_KEY = createTextAttributesKey(colorName, IDENTIFIER)
	@JvmField val VALID_ESCAPE_KEY = createTextAttributesKey(validEscapeName, VALID_STRING_ESCAPE)
	@JvmField val INVALID_ESCAPE_KEY = createTextAttributesKey(invalidEscapeName, INVALID_STRING_ESCAPE)
	@JvmField val BAD_CHARACTER_KEY = createTextAttributesKey(badCharacterName, BAD_CHARACTER)

	@JvmField val COLOR_KEYS = ParadoxColor.map.mapValues { (_, value) ->
		createTextAttributesKey("${colorName}_${value.key}", IDENTIFIER.defaultAttributes.clone().apply {
			foregroundColor = value.color
		})
	}
}
