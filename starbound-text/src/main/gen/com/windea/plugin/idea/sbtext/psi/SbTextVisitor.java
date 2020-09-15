// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.sbtext.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SbTextVisitor extends PsiElementVisitor {

  public void visitColorMarker(@NotNull SbTextColorMarker o) {
    visitPsiElement(o);
  }

  public void visitColorResetMarker(@NotNull SbTextColorResetMarker o) {
    visitPsiElement(o);
  }

  public void visitColorfulText(@NotNull SbTextColorfulText o) {
    visitNamedElement(o);
  }

  public void visitEscape(@NotNull SbTextEscape o) {
    visitPsiElement(o);
  }

  public void visitRichText(@NotNull SbTextRichText o) {
    visitPsiElement(o);
  }

  public void visitString(@NotNull SbTextString o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull SbTextNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
