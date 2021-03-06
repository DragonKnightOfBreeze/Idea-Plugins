// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class StellarisLocalizationVisitor extends PsiElementVisitor {

  public void visitColorfulText(@NotNull StellarisLocalizationColorfulText o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitCommand(@NotNull StellarisLocalizationCommand o) {
    visitRichText(o);
    // visitRichText(o);
  }

  public void visitEscape(@NotNull StellarisLocalizationEscape o) {
    visitRichText(o);
  }

  public void visitIcon(@NotNull StellarisLocalizationIcon o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitLocale(@NotNull StellarisLocalizationLocale o) {
    visitNamedElement(o);
  }

  public void visitProperty(@NotNull StellarisLocalizationProperty o) {
    visitNamedElement(o);
  }

  public void visitPropertyKey(@NotNull StellarisLocalizationPropertyKey o) {
    visitPsiElement(o);
  }

  public void visitPropertyReference(@NotNull StellarisLocalizationPropertyReference o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitPropertyValue(@NotNull StellarisLocalizationPropertyValue o) {
    visitPsiElement(o);
  }

  public void visitRichText(@NotNull StellarisLocalizationRichText o) {
    visitPsiElement(o);
  }

  public void visitSerialNumber(@NotNull StellarisLocalizationSerialNumber o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitString(@NotNull StellarisLocalizationString o) {
    visitRichText(o);
  }

  public void visitNamedElement(@NotNull StellarisLocalizationNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
