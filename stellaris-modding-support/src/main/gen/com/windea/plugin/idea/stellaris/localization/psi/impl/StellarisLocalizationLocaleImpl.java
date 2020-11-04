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
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.stellaris.StellarisLocale;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationLocalePsiReference;
import javax.swing.Icon;

public class StellarisLocalizationLocaleImpl extends StellarisLocalizationNamedElementImpl implements StellarisLocalizationLocale {

  public StellarisLocalizationLocaleImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitLocale(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getLocaleId() {
    return findNotNullChildByType(LOCALE_ID);
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
  @Nullable
  public Icon getIcon(@IconFlags int flags) {
    return StellarisLocalizationPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @Nullable
  public StellarisLocale getLocale() {
    return StellarisLocalizationPsiImplUtil.getLocale(this);
  }

  @Override
  @Nullable
  public StellarisLocalizationLocalePsiReference getReference() {
    return StellarisLocalizationPsiImplUtil.getReference(this);
  }

}
