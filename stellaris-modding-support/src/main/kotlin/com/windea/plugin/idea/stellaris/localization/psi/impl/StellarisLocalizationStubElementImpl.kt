package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.localization.*

open class StellarisLocalizationStubElementImpl<T : StubElement<*>> : StubBasedPsiElementBase<T> {
	constructor(stub: T, nodeType: IStubElementType<*, *>?) : super(stub, nodeType!!) {}
	constructor(node: ASTNode) : super(node) {}
	override fun getLanguage() = StellarisLocalizationLanguage
}
