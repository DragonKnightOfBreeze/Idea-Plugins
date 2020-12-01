package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.intellij.psi.stubs.*
import com.windea.plugin.idea.stellaris.script.*
import com.windea.plugin.idea.stellaris.script.*

open class StellarisScriptStubElementImpl<T : StubElement<*>> : StubBasedPsiElementBase<T> {
	constructor(stub: T, nodeType: IStubElementType<*, *>?) : super(stub, nodeType!!) {}
	constructor(node: ASTNode) : super(node) {}
	override fun getLanguage() = StellarisScriptLanguage
}
