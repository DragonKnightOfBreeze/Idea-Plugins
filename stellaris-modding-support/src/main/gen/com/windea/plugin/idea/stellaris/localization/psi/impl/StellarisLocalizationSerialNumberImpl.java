// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;
import com.windea.plugin.idea.stellaris.localization.psi.*;
import com.windea.plugin.idea.stellaris.StellarisSerialNumber;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationSerialNumberPsiReference;

public class StellarisLocalizationSerialNumberImpl extends StellarisLocalizationNamedElementImpl implements StellarisLocalizationSerialNumber {

  public StellarisLocalizationSerialNumberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitSerialNumber(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getSerialNumberId() {
    return findChildByType(SERIAL_NUMBER_ID);
  }

  @Override
  @Nullable
  public String getName() {
    return StellarisLocalizationPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return StellarisLocalizationPsiImplUtil.setName(this, name);
  }

  @Override
  public int getTextOffset() {
    return StellarisLocalizationPsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public StellarisSerialNumber getStellarisSerialNumber() {
    return StellarisLocalizationPsiImplUtil.getStellarisSerialNumber(this);
  }

  @Override
  @Nullable
  public StellarisLocalizationSerialNumberPsiReference getReference() {
    return StellarisLocalizationPsiImplUtil.getReference(this);
  }

}
