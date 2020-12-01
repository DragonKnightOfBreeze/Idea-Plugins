package com.windea.plugin.idea.pdx.script.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.pdx.script.psi.*

abstract class PdxScriptNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), PdxScriptNamedElement
