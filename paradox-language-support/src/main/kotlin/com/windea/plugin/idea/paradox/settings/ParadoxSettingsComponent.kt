package com.windea.plugin.idea.paradox.settings

import com.intellij.ui.layout.*
import com.windea.plugin.idea.paradox.*
import javax.swing.*

class ParadoxSettingsComponent {
	companion object {
		private val _performanceTitle = message("paradox.settings.performance")
		private val _resolveScriptReferencesName = message("paradox.settings.performance.resolveScriptReferences")
		private val _resolveScriptReferencesComment = message("paradox.settings.performance.resolveScriptReferences.comment")
		private val _validateScriptName = message("paradox.settings.performance.validateScript")
		private val _validateScriptComment = message("paradox.settings.performance.validateScript.comment")
		private val _renderLocalisationText = message("paradox.settings.performance.renderLocalisationText")
		private val _renderLocalisationTextComment = message("paradox.settings.performance.renderLocalisationText.comment")
	}
	
	lateinit var resolveScriptReferencesCheckBox: JCheckBox
	lateinit var validateScriptCheckBox: JCheckBox
	lateinit var renderLocalisationTextCheckBox: JCheckBox
	
	val panel = panel {
		titledRow(_performanceTitle) {
			row {
				checkBox(_resolveScriptReferencesName, true, _resolveScriptReferencesComment)
					.apply { resolveScriptReferencesCheckBox = component }
			}
			row {
				checkBox(_validateScriptName, true, _validateScriptComment)
					.apply { validateScriptCheckBox = component }
			}
			row {
				checkBox(_renderLocalisationText, true, _renderLocalisationTextComment)
					.apply { renderLocalisationTextCheckBox = component }
			}
		}
	}
}