package com.windea.plugin.idea.stellaris

import com.intellij.*
import org.jetbrains.annotations.*
import java.util.function.*

object StellarisBundle : DynamicBundle(stellarisBundleName) {
	fun message(@PropertyKey(resourceBundle = stellarisBundleName) key: String, vararg params: Any): String {
		return StellarisBundle.getMessage(key, *params)
	}
}
