// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;

public class StellarisLocalizationVisitor extends PsiElementVisitor {

  public void visitProperty(@NotNull StellarisLocalizationProperty o) {
    visitNamedElement(o);
  }

  public void visitPropertyHeader(@NotNull StellarisLocalizationPropertyHeader o) {
    visitNamedElement(o);
  }

  public void visitPropertyKey(@NotNull StellarisLocalizationPropertyKey o) {
    visitPsiElement(o);
  }

  public void visitPropertyValue(@NotNull StellarisLocalizationPropertyValue o) {
    visitPsiLiteralValue(o);
  }

  public void visitPsiLiteralValue(@NotNull PsiLiteralValue o) {
    visitElement(o);
  }

  public void visitNamedElement(@NotNull StellarisLocalizationNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
