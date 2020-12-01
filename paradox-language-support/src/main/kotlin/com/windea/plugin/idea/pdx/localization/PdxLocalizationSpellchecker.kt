package com.windea.plugin.idea.stellaris.localization

import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.spellchecker.tokenizer.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//拼写检查：
//检查key、value、comment

class StellarisLocalizationSpellchecker : SpellcheckingStrategy() {
	override fun getTokenizer(element: PsiElement): Tokenizer<*> {
		return when(element.elementType) {
			PROPERTY_KEY_ID -> TEXT_TOKENIZER
			STRING_TOKEN -> TEXT_TOKENIZER
			COMMENT,ROOT_COMMENT,END_OF_LINE_COMMENT -> TEXT_TOKENIZER
			CODE_TEXT_TOKEN -> TEXT_TOKENIZER
			else -> EMPTY_TOKENIZER
		}
	}
}
