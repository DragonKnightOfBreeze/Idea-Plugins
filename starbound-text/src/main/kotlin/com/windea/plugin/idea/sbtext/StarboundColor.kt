package com.windea.plugin.idea.sbtext

import com.intellij.util.ui.*
import org.intellij.lang.annotations.*
import java.awt.*

//TODO 更加完善的预定义颜色

//purple
//indigo
//brown
//cornflowerblue
//black
//shadow

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
	Purple("purple",Color(0x800080)),
	Indigo("indigo",Color(0x4b0082)),
	Brown("brown",Color(0xa52a2a)),
	White("white",Color.WHITE),
	Black("black",Color.BLACK),
	CornFlowerBlue("cornflowerblue",Color(0x6495ed)),
	Shadow("shadow",Color.DARK_GRAY), //认为如此
	Gray("gray",Color.GRAY),
	Grey("grey",Color.GRAY);

	val icon = ColorIcon(16, color)
	val gutterIcon = ColorIcon(12, color)

	fun format(text: String) = "^$key;$text^reset;"

	fun formatRemain(text: String) = "^$key;$text"

	companion object{
		val values = values()
		val map = StarboundColor.values().associateBy { it.key }
	}
}
