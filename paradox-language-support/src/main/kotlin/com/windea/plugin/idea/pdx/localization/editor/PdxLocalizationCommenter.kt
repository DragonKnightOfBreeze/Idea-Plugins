package com.windea.plugin.idea.stellaris.localization.editor

import com.intellij.lang.*

class StellarisLocalizationCommenter : Commenter {
	override fun getLineCommentPrefix() = "#"

	override fun getCommentedBlockCommentPrefix() = null

	override fun getCommentedBlockCommentSuffix() = null

	override fun getBlockCommentPrefix() = null

	override fun getBlockCommentSuffix() = null
}
