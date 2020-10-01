// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.sbtext.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.sbtext.psi.SbTextTypes.*;
import com.windea.plugin.idea.sbtext.psi.*;
import java.awt.Color;

public class SbTextColorfulTextImpl extends SbTextNamedElementImpl implements SbTextColorfulText {

  public SbTextColorfulTextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SbTextVisitor visitor) {
    visitor.visitColorfulText(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SbTextVisitor) accept((SbTextVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SbTextColorMarker getColorMarker() {
    return findNotNullChildByClass(SbTextColorMarker.class);
  }

  @Override
  @Nullable
  public SbTextColorResetMarker getColorResetMarker() {
    return findChildByClass(SbTextColorResetMarker.class);
  }

  @Override
  @Nullable
  public SbTextString getString() {
    return findChildByClass(SbTextString.class);
  }

  @Override
  @Nullable
  public String getName() {
    return SbTextPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return SbTextPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return SbTextPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return SbTextPsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public Color getColor() {
    return SbTextPsiImplUtil.getColor(this);
  }

  @Override
  public void setColor(@NotNull Color color) {
    SbTextPsiImplUtil.setColor(this, color);
  }

}
