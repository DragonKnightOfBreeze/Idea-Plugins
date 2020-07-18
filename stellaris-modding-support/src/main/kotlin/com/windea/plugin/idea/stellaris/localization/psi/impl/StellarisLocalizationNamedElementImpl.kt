package com.windea.plugin.idea.stellaris.localization.psi.impl

import com.intellij.extapi.psi.*
import com.intellij.lang.*
import com.windea.plugin.idea.stellaris.localization.psi.*

abstract class StellarisLocalizationNamedElementImpl(node: ASTNode) : ASTWrapperPsiElement(node), StellarisLocalizationNamedElement
