package com.windea.plugin.idea.paradox.model

import com.windea.plugin.idea.paradox.*

/**
 * 本地化文件的编号枚举
 */
enum class ParadoxSerialNumber(
	val key: String,
	val description:String,
	val placeholderText :String
) {
	Cardinal("C","Cardinal Number 1, 2, 3...","1"),
	Ordinal("O","Ordinal Number 1st, 2nd, 3rd...","1st"),
	Roman("R","Roman Number I, II, III...","I");

	val documentText = "(serial number) <b>$description</b>"
	val popupText = "'$key' - $description"

	companion object{
		val values = values()
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
