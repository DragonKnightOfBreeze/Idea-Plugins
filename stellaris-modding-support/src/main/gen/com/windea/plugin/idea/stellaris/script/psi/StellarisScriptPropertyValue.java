// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralValue;

public interface StellarisScriptPropertyValue extends PsiLiteralValue {

  @Nullable
  StellarisScriptBooleanLiteral getBooleanLiteral();

  @Nullable
  StellarisScriptList getList();

  @Nullable
  StellarisScriptNumberLiteral getNumberLiteral();

  @Nullable
  StellarisScriptStringLiteral getStringLiteral();

  @Nullable
  StellarisScriptVariableReference getVariableReference();

  @Nullable
  String getValue();

}
