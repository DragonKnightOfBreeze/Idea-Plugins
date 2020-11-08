// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiListLikeElement;

public class StellarisScriptVisitor extends PsiElementVisitor {

  public void visitBlock(@NotNull StellarisScriptBlock o) {
    visitValue(o);
    // visitPsiListLikeElement(o);
  }

  public void visitBoolean(@NotNull StellarisScriptBoolean o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitCode(@NotNull StellarisScriptCode o) {
    visitStringValue(o);
  }

  public void visitColor(@NotNull StellarisScriptColor o) {
    visitStringValue(o);
  }

  public void visitNumber(@NotNull StellarisScriptNumber o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitProperty(@NotNull StellarisScriptProperty o) {
    visitNamedElement(o);
  }

  public void visitPropertyKey(@NotNull StellarisScriptPropertyKey o) {
    visitPsiElement(o);
  }

  public void visitPropertySeparator(@NotNull StellarisScriptPropertySeparator o) {
    visitPsiElement(o);
  }

  public void visitPropertyValue(@NotNull StellarisScriptPropertyValue o) {
    visitPsiElement(o);
  }

  public void visitRootBlock(@NotNull StellarisScriptRootBlock o) {
    visitBlock(o);
  }

  public void visitString(@NotNull StellarisScriptString o) {
    visitStringValue(o);
  }

  public void visitStringValue(@NotNull StellarisScriptStringValue o) {
    visitValue(o);
    // visitPsiLiteralValue(o);
  }

  public void visitValue(@NotNull StellarisScriptValue o) {
    visitPsiElement(o);
  }

  public void visitVariable(@NotNull StellarisScriptVariable o) {
    visitNamedElement(o);
  }

  public void visitVariableName(@NotNull StellarisScriptVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableReference(@NotNull StellarisScriptVariableReference o) {
    visitValue(o);
  }

  public void visitVariableSeparator(@NotNull StellarisScriptVariableSeparator o) {
    visitPsiElement(o);
  }

  public void visitVariableValue(@NotNull StellarisScriptVariableValue o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull StellarisScriptNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
