package com.windea.plugin.idea.stellaris.enums

import com.intellij.util.ui.*
import com.windea.plugin.idea.stellaris.*
import java.awt.*

/**
 * 本地化文件的颜色枚举
 */
enum class StellarisColor(
	val key: String,
	val description: String,
	val color: Color
) {
	Blue("B", "Blue", Color.BLUE),
	Teal("E", "Teal", Color(0x8080)),
	Green("G", "Green", Color.GREEN),
	Orange("H", "Orange", Color.ORANGE),
	Brown("L", "Brown", Color(0xa52a2a)),
	Purple("M", "Purple", Color(0x800080)),
	LightRed("P", "Light Red", Color(0xcd5c5c)),
	Red("R", "Red", Color.RED),
	DarkOrange("S", "Dark Orange", Color(0xff8c00)),
	LightGrey("T", "Light Grey", Color.LIGHT_GRAY),
	White("W", "White", Color.WHITE),
	Yellow("Y", "Yellow", Color.YELLOW);

	val documentText = "(color) $description"
	val popupText = "'$key' - $description"
	val icon = ColorIcon(16, color)
	val gutterIcon = ColorIcon(12, color)

	fun format(text: String) = "£${key}$text£!"

	fun formatRemain(text: String) = "£${key}$text"

	companion object {
		val map = values().associateBy { it.key }
		val keys = values().mapArray { it.key }
	}
}
