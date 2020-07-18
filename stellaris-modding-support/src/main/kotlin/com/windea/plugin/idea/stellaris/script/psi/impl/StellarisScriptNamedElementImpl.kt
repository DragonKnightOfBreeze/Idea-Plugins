package com.windea.plugin.idea.stellaris.script.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.stellaris.script.psi.*

abstract class StellarisScriptNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), StellarisScriptNamedElement
