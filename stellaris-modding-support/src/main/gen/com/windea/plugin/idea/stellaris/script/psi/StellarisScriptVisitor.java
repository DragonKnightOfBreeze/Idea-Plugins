// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;
import com.intellij.psi.PsiLiteralValue;

public class StellarisScriptVisitor extends PsiElementVisitor {

  public void visitArray(@NotNull StellarisScriptArray o) {
    visitPsiListLikeElement(o);
  }

  public void visitBoolean(@NotNull StellarisScriptBoolean o) {
    visitPsiElement(o);
  }

  public void visitNumber(@NotNull StellarisScriptNumber o) {
    visitPsiElement(o);
  }

  public void visitObject(@NotNull StellarisScriptObject o) {
    visitPsiListLikeElement(o);
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
