package com.windea.plugin.idea.paradox.settings

import com.intellij.ui.layout.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*

class ParadoxSettingsComponent {
	companion object {
		private val performanceTitle = message("paradox.settings.performance")
		private val resolveScriptReferencesName = message("paradox.settings.performance.resolveScriptReferences")
		private val resolveScriptReferencesComment = message("paradox.settings.performance.resolveScriptReferences.comment")
		private val validateScriptName = message("paradox.settings.performance.validateScript")
		private val validateScriptComment = message("paradox.settings.performance.validateScript.comment")
		private val renderLocalisationText = message("paradox.settings.performance.renderLocalisationText")
		private val renderLocalisationTextComment = message("paradox.settings.performance.renderLocalisationText.comment")
	}
	
	lateinit var resolveScriptReferencesCheckBox: JCheckBox
	lateinit var validateScriptCheckBox: JCheckBox
	lateinit var renderLocalisationTextCheckBox: JCheckBox
	
	val panel = panel {
		titledRow(performanceTitle) {
			row {
				checkBox(resolveScriptReferencesName, true, resolveScriptReferencesComment)
					.apply { resolveScriptReferencesCheckBox = component }
			}
			row {
				checkBox(validateScriptName, true, validateScriptComment)
					.apply { validateScriptCheckBox = component }
			}
			row {
				checkBox(renderLocalisationText, true, renderLocalisationTextComment)
					.apply { renderLocalisationTextCheckBox = component }
			}
		}
	}
}