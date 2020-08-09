package com.windea.plugin.idea.stellaris.script

import com.intellij.lang.*
import com.windea.plugin.idea.stellaris.annotations.*

class StellarisScriptCommenter : Commenter {
	override fun getLineCommentPrefix() = "#"

	override fun getCommentedBlockCommentPrefix() = null

	override fun getCommentedBlockCommentSuffix() = null

	override fun getBlockCommentPrefix() = null

	override fun getBlockCommentSuffix() = null
}
