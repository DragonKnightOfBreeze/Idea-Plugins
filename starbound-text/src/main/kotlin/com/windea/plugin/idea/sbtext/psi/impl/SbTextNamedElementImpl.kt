package com.windea.plugin.idea.sbtext.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.sbtext.psi.*

abstract class SbTextNamedElementImpl(node: ASTNode): ASTWrapperPsiElement(node), SbTextNamedElement
