@file:Suppress("HasPlatformType")

package com.windea.plugin.idea.stellaris.localization.codeInsight

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.patterns.PlatformPatterns.*
import com.intellij.psi.*
import com.intellij.util.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.domain.*
import com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*

//pattern是通过调试确定的

@ExtensionPoint
class StellarisLocalizationCompletionContributor : CompletionContributor() {
	class LocaleCompletionProvider : CompletionProvider<CompletionParameters>() {
		override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
			for(locale in StellarisLocale.values()) {
				val insertHandler = InsertHandler<LookupElement> { c, _ ->
					//c.offsetMap.addOffset(CompletionInitializationContext.START_OFFSET, c.startOffset - 1)
					//不能这样做：这是插入之后的动作
					//val caretModel = c.editor.caretModel
					//caretModel.moveToOffset(caretModel.offset - 1)
				}
				val lookupElement = LookupElementBuilder.create(locale.key).withTypeText(locale.description).withInsertHandler(insertHandler)
				result.addElement(lookupElement)
			}
		}
	}

	//虽然不知道为什么，但是还是一个一个试吧……

	init {
		extend(
			CompletionType.BASIC,
			//psiElement().withParent(psiElement(PsiErrorElement::class.java).withParent(PsiFile::class.java)),
			psiElement().afterSibling(psiElement(LOCALE)).andOr(
				psiElement().withParent(psiElement(PsiErrorElement::class.java).withParent(PsiFile::class.java))
			),
			//psiElement(TokenType.BAD_CHARACTER).withParent(psiElement(PsiErrorElement::class.java).withParent(PsiFile::class.java)),
			//psiElement(TokenType.BAD_CHARACTER).withText("l"),
			//psiElement(LOCALE),
			LocaleCompletionProvider()
		)
	}

	override fun beforeCompletion(context: CompletionInitializationContext) {
		//不能这样做：这会在"l_"处才开始代码补全
		//println(context.dummyIdentifier)
		//context.dummyIdentifier = context.file.findElementAt(context.startOffset-1)?.text.orEmpty()
		//println(context.dummyIdentifier)
		//if(context.dummyIdentifier.isEmpty()) context.offsetMap.addOffset(CompletionInitializationContext.START_OFFSET, context.startOffset - 1)
		//println(context.startOffset)
		//println(context.identifierEndOffset)
		//println(context.replacementOffset)
	}

	override fun fillCompletionVariants(parameters: CompletionParameters, result: CompletionResultSet) {
		println("current " + parameters.position)
		println("parent " + parameters.position.parent)
		println(parameters.position.prevSibling)
		println(parameters.position.nextSibling)
		super.fillCompletionVariants(parameters, result)
	}
}

