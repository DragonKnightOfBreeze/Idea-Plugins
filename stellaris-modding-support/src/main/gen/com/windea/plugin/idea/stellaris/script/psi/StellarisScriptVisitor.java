// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiListLikeElement;

public class StellarisScriptVisitor extends PsiElementVisitor {

  public void visitBlock(@NotNull StellarisScriptBlock o) {
    visitPsiListLikeElement(o);
  }

  public void visitBoolean(@NotNull StellarisScriptBoolean o) {
    visitPsiLiteralValue(o);
  }

  public void visitItem(@NotNull StellarisScriptItem o) {
    visitPsiLiteralValue(o);
  }

  public void visitNumber(@NotNull StellarisScriptNumber o) {
    visitPsiLiteralValue(o);
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

  public void visitString(@NotNull StellarisScriptString o) {
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
    visitPsiElement(o);
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
