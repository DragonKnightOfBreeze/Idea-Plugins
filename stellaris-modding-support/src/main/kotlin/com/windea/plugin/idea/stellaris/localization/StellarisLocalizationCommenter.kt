package com.windea.plugin.idea.stellaris.localization

import com.intellij.lang.*
import com.windea.plugin.idea.stellaris.annotations.*

class StellarisLocalizationCommenter : Commenter {
	override fun getLineCommentPrefix() = "#"

	override fun getCommentedBlockCommentPrefix() = null

	override fun getCommentedBlockCommentSuffix() = null

	override fun getBlockCommentPrefix() = null

	override fun getBlockCommentSuffix() = null
}

