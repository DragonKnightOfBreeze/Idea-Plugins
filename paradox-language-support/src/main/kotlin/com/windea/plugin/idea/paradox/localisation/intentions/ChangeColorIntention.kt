package com.windea.plugin.idea.paradox.localisation.intentions

import com.intellij.codeInsight.intention.*
import com.intellij.openapi.command.WriteCommandAction.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.paradox.*
import com.windea.plugin.idea.paradox.localisation.psi.*

class ChangeColorIntention : IntentionAction {
	companion object{
		val instance = ChangeColorIntention()
	}

	private val text = message("paradox.localisation.intention.changeColor")

	override fun startInWriteAction() = false

	override fun getText() = text

	override fun getFamilyName() = text

	override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
		if(editor == null || file == null) return false
		val element = file.findElementAt(editor.caretModel.offset)?.parentOfType<ParadoxLocalisationColorfulText>()
		return element != null
	}

	override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
		if(editor == null || file == null) return
		val element = file.findElementAt(editor.caretModel.offset)?.parentOfType<ParadoxLocalisationColorfulText>() ?: return
		val values = localisationColorfulTextCache.register(project)
		JBPopupFactory.getInstance().createListPopup(Popup(element, values)).showInBestPositionFor(editor)
	}

	private class Popup(
		private val value: ParadoxLocalisationColorfulText,
		values: Array<ParadoxLocalisationColorfulText>
	) : BaseListPopupStep<ParadoxLocalisationColorfulText>(message("paradox.localisation.intention.changeColor.title"), *values) {
		override fun getIconFor(value: ParadoxLocalisationColorfulText) = value.paradoxColor!!.icon

		override fun getTextFor(value: ParadoxLocalisationColorfulText) = value.paradoxColor!!.popupText

		override fun getDefaultOptionIndex() = 0

		override fun isSpeedSearchEnabled(): Boolean = true

		override fun onChosen(selectedValue: ParadoxLocalisationColorfulText?, finalChoice: Boolean): PopupStep<*>? {
			if(selectedValue != null) {
				//需要在WriteCommandAction里面执行
				runWriteCommandAction(selectedValue.project) { value.setName(selectedValue.name!!) }
			}
			return PopupStep.FINAL_CHOICE
		}
	}
}
