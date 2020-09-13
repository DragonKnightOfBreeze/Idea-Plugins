package com.windea.plugin.idea.sbtext

import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.spellchecker.tokenizer.*
import com.windea.plugin.idea.sbtext.psi.*

class SbTextSpellchecker: SpellcheckingStrategy(){
	override fun getTokenizer(element: PsiElement?): Tokenizer<*> {
		return when(element.elementType){
			SbTextTypes.TEXT_TOKEN -> TEXT_TOKENIZER
			else -> EMPTY_TOKENIZER
		}
	}
}
