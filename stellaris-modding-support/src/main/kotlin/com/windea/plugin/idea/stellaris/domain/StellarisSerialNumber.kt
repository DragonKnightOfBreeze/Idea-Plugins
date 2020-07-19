package com.windea.plugin.idea.stellaris.domain

import com.windea.plugin.idea.stellaris.*

/**
 * 本地化文件的编号枚举
 */
enum class StellarisSerialNumber(
	val key: String,
	val description:String
) {
	Cardinal("C","(serial number) Cardinal Number 1, 2, 3..."),
	Ordinal("O","(serial number) Ordinal Number 1st, 2nd, 3rd..."),
	Roman("R","(serial number) Roman Number I, II, III...");

	val popupText = "'$key' - $description"

	companion object{
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
