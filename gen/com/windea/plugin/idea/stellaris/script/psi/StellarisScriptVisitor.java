// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class StellarisScriptVisitor extends PsiElementVisitor {

  public void visitBlock(@NotNull StellarisScriptBlock o) {
    visitPsiElement(o);
  }

  public void visitBoolean(@NotNull StellarisScriptBoolean o) {
    visitPsiElement(o);
  }

  public void visitColor(@NotNull StellarisScriptColor o) {
    visitPsiElement(o);
  }

  public void visitItem(@NotNull StellarisScriptItem o) {
    visitPsiElement(o);
  }

  public void visitNumber(@NotNull StellarisScriptNumber o) {
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
    visitPsiElement(o);
  }

  public void visitString(@NotNull StellarisScriptString o) {
    visitPsiElement(o);
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
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull StellarisScriptNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
