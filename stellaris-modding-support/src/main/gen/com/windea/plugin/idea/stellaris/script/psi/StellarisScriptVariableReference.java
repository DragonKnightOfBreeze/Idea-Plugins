// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.script.reference.StellarisScriptVariablePsiReference;

public interface StellarisScriptVariableReference extends StellarisScriptValue {

  @NotNull
  PsiElement getVariableReferenceId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  StellarisScriptVariablePsiReference getReference();

}
