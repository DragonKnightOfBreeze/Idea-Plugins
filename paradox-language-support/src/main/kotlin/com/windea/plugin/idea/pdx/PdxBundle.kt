package com.windea.plugin.idea.pdx

import com.intellij.*
import org.jetbrains.annotations.*

object PdxBundle : DynamicBundle(pdxBundleName) {
	fun message(@PropertyKey(resourceBundle = pdxBundleName) key: String, vararg params: Any): String {
		return PdxBundle.getMessage(key, *params)
	}
}

@Suppress("NOTHING_TO_INLINE")
inline fun message(@PropertyKey(resourceBundle = pdxBundleName) key: String, vararg params: Any): String {
	return PdxBundle.getMessage(key, *params)
}
