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
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationIconPsiReference;

public class StellarisLocalizationIconImpl extends StellarisLocalizationNamedElementImpl implements StellarisLocalizationIcon {

  public StellarisLocalizationIconImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitIcon(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StellarisLocalizationRichText getRichText() {
    return PsiTreeUtil.getChildOfType(this, StellarisLocalizationRichText.class);
  }

  @Override
  @Nullable
  public PsiElement getIconId() {
    return findChildByType(ICON_ID);
  }

  @Override
  @Nullable
  public PsiElement getIconParameter() {
    return findChildByType(ICON_PARAMETER);
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
  public StellarisLocalizationIconPsiReference getReference() {
    return StellarisLocalizationPsiImplUtil.getReference(this);
  }

}
