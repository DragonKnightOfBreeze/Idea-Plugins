package com.windea.plugin.idea.sbtext

import com.intellij.*
import org.jetbrains.annotations.*
import java.util.function.*

object SbTextBundle: DynamicBundle(sbTextBundleName) {
	fun lazyMessage(@PropertyKey(resourceBundle = sbTextBundleName) key: String, vararg params: Any): Supplier<String?> {
		return getLazyMessage(key, *params)
	}

	fun message(@PropertyKey(resourceBundle = sbTextBundleName) key: String, vararg params: Any): String {
		return getMessage(key, *params)
	}
}

