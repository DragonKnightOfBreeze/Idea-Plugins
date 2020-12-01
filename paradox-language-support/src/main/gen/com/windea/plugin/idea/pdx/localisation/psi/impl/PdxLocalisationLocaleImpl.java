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
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.pdx.PdxLocale;
import javax.swing.Icon;

public class PdxLocalisationLocaleImpl extends PdxLocalisationNamedElementImpl implements PdxLocalisationLocale {

  public PdxLocalisationLocaleImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PdxLocalisationVisitor visitor) {
    visitor.visitLocale(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof PdxLocalisationVisitor) accept((PdxLocalisationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getLocaleId() {
    return notNullChild(findChildByType(LOCALE_ID));
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
  @NotNull
  public PsiElement getNameIdentifier() {
    return PdxLocalisationPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public Icon getIcon(@IconFlags int flags) {
    return PdxLocalisationPsiImplUtil.getIcon(this, flags);
  }

  @Override
  @Nullable
  public PdxLocale getPdxLocale() {
    return PdxLocalisationPsiImplUtil.getPdxLocale(this);
  }

}
