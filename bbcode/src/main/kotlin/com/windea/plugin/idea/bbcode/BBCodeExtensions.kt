package com.windea.plugin.idea.bbcode

import java.net.*
import java.util.*

//region Stdlib
fun Boolean.toInt() = if(this) 1 else 0

inline fun <reified T : Any> String.toClassPathResource(): URL = T::class.java.getResource(this)
//endregion
