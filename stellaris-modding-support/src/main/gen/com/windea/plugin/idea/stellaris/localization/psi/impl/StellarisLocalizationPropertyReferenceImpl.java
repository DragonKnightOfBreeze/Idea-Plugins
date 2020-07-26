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
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationPropertyPsiReference;

public class StellarisLocalizationPropertyReferenceImpl extends StellarisLocalizationNamedElementImpl implements StellarisLocalizationPropertyReference {

  public StellarisLocalizationPropertyReferenceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitPropertyReference(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getPropertyKeyId() {
    return findChildByType(PROPERTY_KEY_ID);
  }

  @Override
  @Nullable
  public PsiElement getPropertyReferenceParameter() {
    return findChildByType(PROPERTY_REFERENCE_PARAMETER);
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
  @Nullable
  public PsiElement getNameIdentifier() {
    return StellarisLocalizationPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public int getTextOffset() {
    return StellarisLocalizationPsiImplUtil.getTextOffset(this);
  }

  @Override
  @NotNull
  public StellarisLocalizationPropertyPsiReference getReference() {
    return StellarisLocalizationPsiImplUtil.getReference(this);
  }

}
