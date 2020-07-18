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

public class StellarisScriptPropertyValueImpl extends ASTWrapperPsiElement implements StellarisScriptPropertyValue {

  public StellarisScriptPropertyValueImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitPropertyValue(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StellarisScriptBooleanLiteral getBooleanLiteral() {
    return findChildByClass(StellarisScriptBooleanLiteral.class);
  }

  @Override
  @Nullable
  public StellarisScriptList getList() {
    return findChildByClass(StellarisScriptList.class);
  }

  @Override
  @Nullable
  public StellarisScriptNumberLiteral getNumberLiteral() {
    return findChildByClass(StellarisScriptNumberLiteral.class);
  }

  @Override
  @Nullable
  public StellarisScriptStringLiteral getStringLiteral() {
    return findChildByClass(StellarisScriptStringLiteral.class);
  }

  @Override
  @Nullable
  public StellarisScriptVariableReference getVariableReference() {
    return findChildByClass(StellarisScriptVariableReference.class);
  }

  @Override
  @Nullable
  public String getValue() {
    return StellarisScriptPsiImplUtil.getValue(this);
  }

}
