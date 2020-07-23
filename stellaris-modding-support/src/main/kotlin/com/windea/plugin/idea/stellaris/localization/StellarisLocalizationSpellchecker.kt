package com.windea.plugin.idea.stellaris.localization

import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.spellchecker.tokenizer.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//拼写检查：
//检查key、value、comment

@ExtensionPoint
class StellarisLocalizationSpellchecker : SpellcheckingStrategy() {
	override fun getTokenizer(element: PsiElement): Tokenizer<*> {
		return when(element.elementType) {
			KEY_TOKEN -> TEXT_TOKENIZER
			VALUE_TOKEN -> TEXT_TOKENIZER
			COMMENT,ROOT_COMMENT,END_OF_LINE_COMMENT -> TEXT_TOKENIZER
			CODE_TEXT -> TEXT_TOKENIZER
			else -> EMPTY_TOKENIZER
		}
	}
}
