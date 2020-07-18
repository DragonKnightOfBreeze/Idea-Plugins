// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.stellaris.script.psi.*;

public class StellarisScriptListImpl extends ASTWrapperPsiElement implements StellarisScriptList {

  public StellarisScriptListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StellarisScriptProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StellarisScriptProperty.class);
  }

  @Override
  @NotNull
  public List<StellarisScriptPlainText> getTextList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StellarisScriptPlainText.class);
  }

  @Override
  @NotNull
  public List<PsiElement> getComponents() {
    return StellarisScriptPsiImplUtil.getComponents(this);
  }

}
