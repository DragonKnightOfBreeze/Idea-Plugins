// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class PdxLocalisationVisitor extends PsiElementVisitor {

  public void visitCode(@NotNull PdxLocalisationCode o) {
    visitRichText(o);
    // visitRichText(o);
  }

  public void visitColorfulText(@NotNull PdxLocalisationColorfulText o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitEscape(@NotNull PdxLocalisationEscape o) {
    visitRichText(o);
  }

  public void visitIcon(@NotNull PdxLocalisationIcon o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitLocale(@NotNull PdxLocalisationLocale o) {
    visitNamedElement(o);
  }

  public void visitProperty(@NotNull PdxLocalisationProperty o) {
    visitNamedElement(o);
  }

  public void visitPropertyKey(@NotNull PdxLocalisationPropertyKey o) {
    visitPsiElement(o);
  }

  public void visitPropertyReference(@NotNull PdxLocalisationPropertyReference o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitPropertyValue(@NotNull PdxLocalisationPropertyValue o) {
    visitPsiElement(o);
  }

  public void visitRichText(@NotNull PdxLocalisationRichText o) {
    visitPsiElement(o);
  }

  public void visitSerialNumber(@NotNull PdxLocalisationSerialNumber o) {
    visitRichText(o);
    // visitNamedElement(o);
  }

  public void visitString(@NotNull PdxLocalisationString o) {
    visitRichText(o);
  }

  public void visitNamedElement(@NotNull PdxLocalisationNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
