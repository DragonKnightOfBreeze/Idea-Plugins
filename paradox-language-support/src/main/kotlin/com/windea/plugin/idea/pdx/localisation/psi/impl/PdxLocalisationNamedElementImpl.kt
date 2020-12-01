package com.windea.plugin.idea.pdx.localisation.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.pdx.localisation.psi.*

abstract class PdxLocalisationNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), PdxLocalisationNamedElement
