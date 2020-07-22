// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.intellij.psi.PsiReference;

public interface StellarisScriptStringLiteral extends PsiLiteralValue {

  @Nullable
  PsiElement getString();

  @Nullable
  PsiElement getUnquotedString();

  @NotNull
  String getValue();

  @Nullable
  PsiReference getReference();

  boolean isValidPropertyKey();

}
