package com.windea.plugin.idea.stellaris.domain

/**
 * 本地化文件的编号枚举
 */
enum class StellarisSerialNumber(
	val text: String,
	val description:String
) {
	//1, 2, 3, 4...
	Cardinal("%C%","(serial number) Cardinal Number 1, 2, 3..."),

	//1st, 2nd, 3rd, 4th...
	Ordinal("%O%","(serial number) Ordinal Number 1st, 2nd, 3rd..."),

	//I, II, III, IV...
	Roman("%R%","(serial number) Roman Number I, II, III...");
}
