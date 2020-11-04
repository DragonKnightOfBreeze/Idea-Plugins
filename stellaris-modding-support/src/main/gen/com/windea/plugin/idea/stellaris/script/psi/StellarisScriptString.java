// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;
import com.windea.plugin.idea.stellaris.script.reference.StellarisScriptStringPsiReference;

public interface StellarisScriptString extends PsiLiteralValue {

  @Nullable
  PsiElement getQuotedStringToken();

  @Nullable
  PsiElement getStringToken();

  @NotNull
  String getValue();

  @Nullable
  StellarisScriptStringPsiReference getReference();

  boolean isValidPropertyKey();

}
