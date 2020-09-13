package com.windea.plugin.idea.sbtext

import com.intellij.util.ui.*
import java.awt.*

//TODO 更加完善的预定义颜色

enum class StarboundColor(
	val key:String,
	val color: Color
) {
	Red("red",Color.RED),
	Green("green",Color.GREEN),
	Blue("blue",Color.BLUE),
	Yellow("yellow",Color.YELLOW),
	Cyan("cyan",Color.CYAN),
	Orange("orange",Color.orange),
	Pink("pink",Color.PINK),
	White("white",Color.WHITE),
	Gray("gray",Color.GRAY);

	val icon = ColorIcon(16, color)
	val gutterIcon = ColorIcon(12, color)

	fun format(text: String) = "^$key;$text^reset;"

	fun formatRemain(text: String) = "^$key;$text"

	companion object{
		val map = StarboundColor.values().associateBy { it.key }
	}
}
