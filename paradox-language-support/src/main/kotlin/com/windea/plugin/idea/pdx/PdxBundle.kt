package com.windea.plugin.idea.stellaris

import com.intellij.*
import org.jetbrains.annotations.*

object StellarisBundle : DynamicBundle(stellarisBundleName) {
	fun message(@PropertyKey(resourceBundle = stellarisBundleName) key: String, vararg params: Any): String {
		return StellarisBundle.getMessage(key, *params)
	}
}

@Suppress("NOTHING_TO_INLINE")
inline fun message(@PropertyKey(resourceBundle = stellarisBundleName) key: String, vararg params: Any): String {
	return StellarisBundle.getMessage(key, *params)
}
