package com.windea.plugin.idea.sbtext

import java.net.*

//region Stdlib
fun Boolean.toInt() = if(this) 1 else 0

inline fun <reified T : Any> String.toClassPathResource(): URL = T::class.java.getResource(this)
//endregion
