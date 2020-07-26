package com.windea.plugin.idea.stellaris.localization.intentions

import com.intellij.codeInsight.intention.*
import com.intellij.openapi.application.*
import com.intellij.openapi.command.WriteCommandAction.*
import com.intellij.openapi.editor.*
import com.intellij.openapi.project.*
import com.intellij.openapi.ui.popup.*
import com.intellij.openapi.ui.popup.util.*
import com.intellij.psi.*
import com.intellij.psi.util.*
import com.windea.plugin.idea.stellaris.*
import com.windea.plugin.idea.stellaris.annotations.*
import com.windea.plugin.idea.stellaris.localization.psi.*

@ExtensionPoint
class StellarisLocalizationChangeColorIntention : IntentionAction {
	companion object{
		val instance = StellarisLocalizationChangeColorIntention()
	}

	override fun startInWriteAction() = false

	override fun getText() = StellarisBundle.message("stellaris.localization.intention.changeColor")

	override fun getFamilyName() = text

	override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?): Boolean {
		if(editor == null || file == null) return false
		val element = file.findElementAt(editor.caretModel.offset)?.parentOfType<StellarisLocalizationColorfulText>()
		return element != null
	}

	override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
		if(editor == null || file == null) return
		val element = file.findElementAt(editor.caretModel.offset)?.parentOfType<StellarisLocalizationColorfulText>() ?: return
		val values = localizationColorfulTextCache.register(project)
		JBPopupFactory.getInstance().createListPopup(Popup(element, values)).showInBestPositionFor(editor)
	}

	private class Popup(
		private val value: StellarisLocalizationColorfulText,
		values: Array<StellarisLocalizationColorfulText>
	) : BaseListPopupStep<StellarisLocalizationColorfulText>(StellarisBundle.message("stellaris.localization.intention.changeColor.title"), *values) {
		override fun getIconFor(value: StellarisLocalizationColorfulText) = value.color!!.icon

		override fun getTextFor(value: StellarisLocalizationColorfulText) = value.color!!.popupText

		override fun getDefaultOptionIndex() = 0

		override fun isSpeedSearchEnabled(): Boolean = true

		override fun onChosen(selectedValue: StellarisLocalizationColorfulText?, finalChoice: Boolean): PopupStep<*>? {
			if(selectedValue != null) {
				//需要在WriteCommandAction里面执行
				runWriteCommandAction(selectedValue.project) { value.setName(selectedValue.name!!) }
			}
			return PopupStep.FINAL_CHOICE
		}
	}
}
