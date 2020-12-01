package com.windea.plugin.idea.paradox.script.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.HighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey.*
import com.windea.plugin.idea.paradox.message

object ParadoxScriptAttributesKeys {
	@JvmField val SEPARATOR_KEY = createTextAttributesKey(message("paradox.script.syntax.separator"), OPERATION_SIGN)
	@JvmField val BRACES_KEY = createTextAttributesKey(message("paradox.script.syntax.braces"), BRACES)
	@JvmField val VARIABLE_KEY = createTextAttributesKey(message("paradox.script.syntax.variable"), STATIC_FIELD)
	@JvmField val PROPERTY_KEY_KEY = createTextAttributesKey(message("paradox.script.syntax.propertyKey"), INSTANCE_FIELD)
	@JvmField val KEYWORD_KEY = createTextAttributesKey(message("paradox.script.syntax.keyword"), KEYWORD)
	@JvmField val COLOR_KEY = createTextAttributesKey(message("paradox.script.syntax.color"), FUNCTION_DECLARATION)
	@JvmField val NUMBER_KEY = createTextAttributesKey(message("paradox.script.syntax.number"), NUMBER)
	@JvmField val STRING_KEY = createTextAttributesKey(message("paradox.script.syntax.string"), STRING)
	@JvmField val CODE_KEY = createTextAttributesKey(message("paradox.script.syntax.code"), IDENTIFIER)
	@JvmField val COMMENT_KEY = createTextAttributesKey(message("paradox.script.syntax.comment"), LINE_COMMENT)
	@JvmField val VALID_ESCAPE_KEY = createTextAttributesKey(message("paradox.script.syntax.validEscape"), VALID_STRING_ESCAPE)
	@JvmField val INVALID_ESCAPE_KEY = createTextAttributesKey(message("paradox.script.syntax.invalidEscape"), INVALID_STRING_ESCAPE)
	@JvmField val BAD_CHARACTER_KEY = createTextAttributesKey(message("paradox.script.syntax.badCharacter"), BAD_CHARACTER)

	@JvmField val LOCALISATION_PROPERTY_REFERENCE_KEY = createTextAttributesKey(message("paradox.script.syntax.localisationPropertyReference"), STRING)
	@JvmField val SCRIPT_PROPERTY_REFERENCE_KEY = createTextAttributesKey(message("paradox.script.syntax.scriptPropertyReference"), STRING)
}
