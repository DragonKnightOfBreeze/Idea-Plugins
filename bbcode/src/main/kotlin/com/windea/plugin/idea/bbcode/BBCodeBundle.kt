package com.windea.plugin.idea.bbcode

import com.intellij.*
import org.jetbrains.annotations.*
import java.util.function.*

object BBCodeBundle: DynamicBundle(bbcodeBundleName) {
	fun lazyMessage(@PropertyKey(resourceBundle = bbcodeBundleName) key: String, vararg params: Any): Supplier<String?> {
		return getLazyMessage(key, *params)
	}

	fun message(@PropertyKey(resourceBundle = bbcodeBundleName) key: String, vararg params: Any): String {
		return getMessage(key, *params)
	}
}

