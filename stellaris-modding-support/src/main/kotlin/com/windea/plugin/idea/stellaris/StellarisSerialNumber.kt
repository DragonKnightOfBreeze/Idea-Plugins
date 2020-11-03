package com.windea.plugin.idea.stellaris

/**
 * 本地化文件的编号枚举
 */
enum class StellarisSerialNumber(
	val key: String,
	val description:String
) {
	Cardinal("C","Cardinal Number 1, 2, 3..."),
	Ordinal("O","Ordinal Number 1st, 2nd, 3rd..."),
	Roman("R","Roman Number I, II, III...");

	val documentText = "(serial number) $description"
	val popupText = "'$key' - $description"

	companion object{
		val values = values()
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
