// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.stellaris.script.psi.*;

public class StellarisScriptBlockImpl extends ASTWrapperPsiElement implements StellarisScriptBlock {

  public StellarisScriptBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitBlock(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<StellarisScriptItem> getItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StellarisScriptItem.class);
  }

  @Override
  @NotNull
  public List<StellarisScriptProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, StellarisScriptProperty.class);
  }

  @Override
  public boolean isEmpty() {
    return StellarisScriptPsiImplUtil.isEmpty(this);
  }

  @Override
  public boolean isNotEmpty() {
    return StellarisScriptPsiImplUtil.isNotEmpty(this);
  }

  @Override
  public boolean isObject() {
    return StellarisScriptPsiImplUtil.isObject(this);
  }

  @Override
  public boolean isArray() {
    return StellarisScriptPsiImplUtil.isArray(this);
  }

  @Override
  @NotNull
  public List<PsiElement> getComponents() {
    return StellarisScriptPsiImplUtil.getComponents(this);
  }

}
