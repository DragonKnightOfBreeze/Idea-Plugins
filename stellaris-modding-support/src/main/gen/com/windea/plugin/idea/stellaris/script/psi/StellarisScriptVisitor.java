// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiListLikeElement;

public class StellarisScriptVisitor extends PsiElementVisitor {

  public void visitBooleanLiteral(@NotNull StellarisScriptBooleanLiteral o) {
    visitPsiElement(o);
  }

  public void visitList(@NotNull StellarisScriptList o) {
    visitPsiListLikeElement(o);
  }

  public void visitNumberLiteral(@NotNull StellarisScriptNumberLiteral o) {
    visitPsiElement(o);
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
    visitPsiLiteralValue(o);
  }

  public void visitStringLiteral(@NotNull StellarisScriptStringLiteral o) {
    visitPsiElement(o);
  }

  public void visitText(@NotNull StellarisScriptPlainText o) {
    visitPsiLiteralValue(o);
  }

  public void visitVariableDefinition(@NotNull StellarisScriptVariableDefinition o) {
    visitNamedElement(o);
  }

  public void visitVariableDefinitionSeparator(@NotNull StellarisScriptVariableDefinitionSeparator o) {
    visitPsiElement(o);
  }

  public void visitVariableName(@NotNull StellarisScriptVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableReference(@NotNull StellarisScriptVariableReference o) {
    visitNamedElement(o);
  }

  public void visitVariableValue(@NotNull StellarisScriptVariableValue o) {
    visitPsiLiteralValue(o);
  }

  public void visitPsiListLikeElement(@NotNull PsiListLikeElement o) {
    visitElement(o);
  }

  public void visitPsiLiteralValue(@NotNull PsiLiteralValue o) {
    visitElement(o);
  }

  public void visitNamedElement(@NotNull StellarisScriptNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
