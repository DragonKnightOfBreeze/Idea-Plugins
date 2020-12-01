// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiListLikeElement;

public class PdxScriptVisitor extends PsiElementVisitor {

  public void visitBlock(@NotNull PdxScriptBlock o) {
    visitValue(o);
    // visitPsiListLikeElement(o);
  }

  public void visitBoolean(@NotNull PdxScriptBoolean o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitCode(@NotNull PdxScriptCode o) {
    visitStringValue(o);
  }

  public void visitColor(@NotNull PdxScriptColor o) {
    visitStringValue(o);
  }

  public void visitNumber(@NotNull PdxScriptNumber o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitProperty(@NotNull PdxScriptProperty o) {
    visitNamedElement(o);
  }

  public void visitPropertyKey(@NotNull PdxScriptPropertyKey o) {
    visitPsiElement(o);
  }

  public void visitPropertySeparator(@NotNull PdxScriptPropertySeparator o) {
    visitPsiElement(o);
  }

  public void visitPropertyValue(@NotNull PdxScriptPropertyValue o) {
    visitPsiElement(o);
  }

  public void visitRootBlock(@NotNull PdxScriptRootBlock o) {
    visitBlock(o);
  }

  public void visitString(@NotNull PdxScriptString o) {
    visitStringValue(o);
  }

  public void visitStringValue(@NotNull PdxScriptStringValue o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitValue(@NotNull PdxScriptValue o) {
    visitPsiElement(o);
  }

  public void visitVariable(@NotNull PdxScriptVariable o) {
    visitNamedElement(o);
  }

  public void visitVariableName(@NotNull PdxScriptVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableReference(@NotNull PdxScriptVariableReference o) {
    visitValue(o);
  }

  public void visitVariableSeparator(@NotNull PdxScriptVariableSeparator o) {
    visitPsiElement(o);
  }

  public void visitVariableValue(@NotNull PdxScriptVariableValue o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull PdxScriptNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
