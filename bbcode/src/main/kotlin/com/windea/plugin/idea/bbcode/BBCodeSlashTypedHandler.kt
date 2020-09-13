package com.windea.plugin.idea.bbcode

import com.intellij.codeInsight.editorActions.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.bbcode.psi.*

//自动补全标签后缀

class BBCodeSlashTypedHandler : TypedHandlerDelegate() {
	override fun charTyped(c: Char, project: Project, editor: Editor, file: PsiFile): Result {
		if(file !is BBCodeFile) return Result.CONTINUE

		if(c == '/') {
			val provider = file.viewProvider
			val offset = editor.caretModel.offset
			var element = provider.findElementAt(offset - 1, BBCodeLanguage::class.java)
			if(element is PsiWhiteSpace) element = element.prevSibling
			val tag = element?.parentOfType<BBCodeTag>(true)
			if(tag != null) {
				val name = tag.name
				EditorModificationUtil.insertStringAtCaret(editor, "$name]", false)
				return Result.STOP
			}
		}
		return Result.CONTINUE
	}
}
