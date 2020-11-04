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
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public class StellarisScriptItemImpl extends ASTWrapperPsiElement implements StellarisScriptItem {

  public StellarisScriptItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisScriptVisitor visitor) {
    visitor.visitItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisScriptVisitor) accept((StellarisScriptVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StellarisScriptBlock getBlock() {
    return findChildByClass(StellarisScriptBlock.class);
  }

  @Override
  @Nullable
  public StellarisScriptBoolean getBoolean() {
    return findChildByClass(StellarisScriptBoolean.class);
  }

  @Override
  @Nullable
  public StellarisScriptColor getColor() {
    return findChildByClass(StellarisScriptColor.class);
  }

  @Override
  @Nullable
  public StellarisScriptNumber getNumber() {
    return findChildByClass(StellarisScriptNumber.class);
  }

  @Override
  @Nullable
  public StellarisScriptString getString() {
    return findChildByClass(StellarisScriptString.class);
  }

  @Override
  @Nullable
  public Icon getIcon(@IconFlags int flags) {
    return StellarisScriptPsiImplUtil.getIcon(this, flags);
  }

}
