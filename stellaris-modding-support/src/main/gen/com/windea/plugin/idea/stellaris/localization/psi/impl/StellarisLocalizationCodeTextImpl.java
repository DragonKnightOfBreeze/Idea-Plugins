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

public class StellarisLocalizationCodeTextImpl extends ASTWrapperPsiElement implements StellarisLocalizationCodeText {

  public StellarisLocalizationCodeTextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull StellarisLocalizationVisitor visitor) {
    visitor.visitCodeText(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof StellarisLocalizationVisitor) accept((StellarisLocalizationVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getCodeText() {
    return findNotNullChildByType(CODE_TEXT);
  }

}
