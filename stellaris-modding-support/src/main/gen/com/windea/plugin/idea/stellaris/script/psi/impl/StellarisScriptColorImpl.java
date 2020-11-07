// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;
import com.windea.plugin.idea.stellaris.script.psi.*;
import java.awt.Color;

public class StellarisScriptColorImpl extends StellarisScriptStringValueImpl implements StellarisScriptColor {

  public StellarisScriptColorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitColor(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getColorToken() {
    return findNotNullChildByType(COLOR_TOKEN);
  }

  @Override
  @Nullable
  public String getValue() {
    return StellarisScriptPsiImplUtil.getValue(this);
  }

  @Override
  @Nullable
  public Color getColor() {
    return StellarisScriptPsiImplUtil.getColor(this);
  }

  @Override
  public void setColor(@NotNull Color color) {
    StellarisScriptPsiImplUtil.setColor(this, color);
  }

}
