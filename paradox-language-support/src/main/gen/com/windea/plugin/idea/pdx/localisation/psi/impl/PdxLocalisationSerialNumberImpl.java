// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.pdx.localisation.psi.PdxLocalisationTypes.*;
import com.windea.plugin.idea.pdx.localisation.psi.*;
import com.windea.plugin.idea.pdx.PdxSerialNumber;

public class PdxLocalisationSerialNumberImpl extends PdxLocalisationNamedElementImpl implements PdxLocalisationSerialNumber {

  public PdxLocalisationSerialNumberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxLocalisationVisitor visitor) {
    visitor.visitSerialNumber(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxLocalisationVisitor) accept((PdxLocalisationVisitor)visitor);
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
    return PdxLocalisationPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String name) {
    return PdxLocalisationPsiImplUtil.setName(this, name);
  }

  @Override
  @Nullable
  public PsiElement getNameIdentifier() {
    return PdxLocalisationPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return PdxLocalisationPsiImplUtil.getTextOffset(this);
  }

  @Override
  @Nullable
  public PdxSerialNumber getPdxSerialNumber() {
    return PdxLocalisationPsiImplUtil.getPdxSerialNumber(this);
  }

}
