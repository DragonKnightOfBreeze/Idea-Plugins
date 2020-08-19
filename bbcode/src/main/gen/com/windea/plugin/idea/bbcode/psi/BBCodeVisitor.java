// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;

public class BBCodeVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull BBCodeAttribute o) {
    visitNamedElement(o);
  }

  public void visitTag(@NotNull BBCodeTag o) {
    visitNamedElement(o);
  }

  public void visitTagPrefix(@NotNull BBCodeTagPrefix o) {
    visitPsiElement(o);
  }

  public void visitTagSuffix(@NotNull BBCodeTagSuffix o) {
    visitPsiElement(o);
  }

  public void visitText(@NotNull BBCodeText o) {
    visitPsiLiteralValue(o);
  }

  public void visitNamedElement(@NotNull BBCodeNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiLiteralValue(@NotNull PsiLiteralValue o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
