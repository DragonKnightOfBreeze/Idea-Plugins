package com.windea.plugin.idea.paradox.script.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.windea.plugin.idea.paradox.message

object ParadoxScriptAttributesKeys {
	private val separatorName = message("paradox.script.externalName.separator")
	private val bracesName = message("paradox.script.externalName.braces")
	private val variableName = message("paradox.script.externalName.variable")
	private val propertyKeyName = message("paradox.script.externalName.propertyKey")
	private val keywordName = message("paradox.script.externalName.keyword")
	private val colorName = message("paradox.script.externalName.color")
	private val numberName = message("paradox.script.externalName.number")
	private val stringName = message("paradox.script.externalName.string")
	private val codeName = message("paradox.script.externalName.code")
	private val commentName = message("paradox.script.externalName.comment")
	private val validEscapeName = message("paradox.script.externalName.validEscape")
	private val invalidEscapeName = message("paradox.script.externalName.invalidEscape")
	private val CharacterName = message("paradox.script.externalName.badCharacter")
	private val localisationPropertyReferenceName = message("paradox.script.externalName.localisationPropertyReference")
	private val scriptPropertyReferenceName = message("paradox.script.externalName.scriptPropertyReference")
	
	@JvmField val SEPARATOR_KEY = createTextAttributesKey(separatorName, OPERATION_SIGN)
	@JvmField val BRACES_KEY = createTextAttributesKey(bracesName, BRACES)
	@JvmField val VARIABLE_KEY = createTextAttributesKey(variableName, STATIC_FIELD)
	@JvmField val PROPERTY_KEY_KEY = createTextAttributesKey(propertyKeyName, INSTANCE_FIELD)
	@JvmField val KEYWORD_KEY = createTextAttributesKey(keywordName, KEYWORD)
	@JvmField val COLOR_KEY = createTextAttributesKey(colorName, FUNCTION_DECLARATION)
	@JvmField val NUMBER_KEY = createTextAttributesKey(numberName, NUMBER)
	@JvmField val STRING_KEY = createTextAttributesKey(stringName, STRING)
	@JvmField val CODE_KEY = createTextAttributesKey(codeName, IDENTIFIER)
	@JvmField val COMMENT_KEY = createTextAttributesKey(commentName, LINE_COMMENT)
	@JvmField val VALID_ESCAPE_KEY = createTextAttributesKey(validEscapeName, VALID_STRING_ESCAPE)
	@JvmField val INVALID_ESCAPE_KEY = createTextAttributesKey(invalidEscapeName, INVALID_STRING_ESCAPE)
	@JvmField val BAD_CHARACTER_KEY = createTextAttributesKey(CharacterName, BAD_CHARACTER)
	@JvmField val LOCALISATION_PROPERTY_REFERENCE_KEY = createTextAttributesKey(localisationPropertyReferenceName, STRING)
	@JvmField val SCRIPT_PROPERTY_REFERENCE_KEY = createTextAttributesKey(scriptPropertyReferenceName, STRING)
}
