package com.windea.plugin.idea.stellaris

import com.intellij.*
import org.jetbrains.annotations.*
import java.util.function.*

object StellarisBundle : DynamicBundle(stellarisBundleName) {
	fun lazyMessage(@PropertyKey(resourceBundle = stellarisBundleName) key: String, vararg params: Any): Supplier<String?> {
		return StellarisBundle.getLazyMessage(key, *params)
	}

	fun message(@PropertyKey(resourceBundle = stellarisBundleName) key: String, vararg params: Any): String {
		return StellarisBundle.getMessage(key, *params)
	}
}
