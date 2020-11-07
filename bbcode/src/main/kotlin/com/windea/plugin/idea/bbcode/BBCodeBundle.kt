package com.windea.plugin.idea.bbcode

import com.intellij.*
import org.jetbrains.annotations.*
import java.util.function.*

object BBCodeBundle: DynamicBundle(bbcodeBundleName) {
	fun message(@PropertyKey(resourceBundle = bbcodeBundleName) key: String, vararg params: Any): String {
		return getMessage(key, *params)
	}
}

