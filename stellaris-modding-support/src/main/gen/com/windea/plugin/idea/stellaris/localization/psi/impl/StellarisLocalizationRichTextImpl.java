// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.windea.plugin.idea.stellaris.localization.psi.*;

public class StellarisLocalizationRichTextImpl extends ASTWrapperPsiElement implements StellarisLocalizationRichText {

  public StellarisLocalizationRichTextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitRichText(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public StellarisLocalizationCode getCode() {
    return findChildByClass(StellarisLocalizationCode.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationColorfulText getColorfulText() {
    return findChildByClass(StellarisLocalizationColorfulText.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationEscape getEscape() {
    return findChildByClass(StellarisLocalizationEscape.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationIcon getIcon() {
    return findChildByClass(StellarisLocalizationIcon.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationPropertyReference getPropertyReference() {
    return findChildByClass(StellarisLocalizationPropertyReference.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationSerialNumber getSerialNumber() {
    return findChildByClass(StellarisLocalizationSerialNumber.class);
  }

  @Override
  @Nullable
  public StellarisLocalizationString getString() {
    return findChildByClass(StellarisLocalizationString.class);
  }

}
