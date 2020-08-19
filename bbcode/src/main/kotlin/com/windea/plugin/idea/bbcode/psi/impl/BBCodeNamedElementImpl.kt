package com.windea.plugin.idea.bbcode.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.bbcode.psi.*

abstract class BBCodeNamedElementImpl(node:ASTNode):ASTWrapperPsiElement(node) , BBCodeNamedElement

