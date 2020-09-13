package com.windea.plugin.idea.bbcode

import com.intellij.psi.*
import com.intellij.psi.util.*
import com.intellij.spellchecker.tokenizer.*
import com.windea.plugin.idea.bbcode.psi.*
import com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*

class BBCodeSpellchecker: SpellcheckingStrategy(){
	override fun getTokenizer(element: PsiElement?): Tokenizer<*> {
		return when(element.elementType){
			ATTRIBUTE_VALUE -> TEXT_TOKENIZER
			TEXT_TOKEN -> TEXT_TOKENIZER
			else -> EMPTY_TOKENIZER
		}
	}
}
