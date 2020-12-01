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
import com.windea.plugin.idea.pdx.PdxColor;
import com.windea.plugin.idea.pdx.localisation.reference.PdxLocalisationPropertyPsiReference;

public class PdxLocalisationPropertyReferenceImpl extends PdxLocalisationNamedElementImpl implements PdxLocalisationPropertyReference {

  public PdxLocalisationPropertyReferenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxLocalisationVisitor visitor) {
    visitor.visitPropertyReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxLocalisationVisitor) accept((PdxLocalisationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PdxLocalisationCode getCode() {
    return PsiTreeUtil.getChildOfType(this, PdxLocalisationCode.class);
  }

  @Override
  @Nullable
  public PsiElement getPropertyReferenceId() {
    return findChildByType(PROPERTY_REFERENCE_ID);
  }

  @Override
  @Nullable
  public PsiElement getPropertyReferenceParameter() {
    return findChildByType(PROPERTY_REFERENCE_PARAMETER);
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
  public PdxLocalisationPropertyPsiReference getReference() {
    return PdxLocalisationPsiImplUtil.getReference(this);
  }

  @Override
  @Nullable
  public PdxColor getPdxColor() {
    return PdxLocalisationPsiImplUtil.getPdxColor(this);
  }

}
