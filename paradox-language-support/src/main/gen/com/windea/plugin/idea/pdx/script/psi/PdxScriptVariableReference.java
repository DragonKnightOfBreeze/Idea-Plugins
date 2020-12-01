// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.pdx.script.reference.PdxScriptVariablePsiReference;

public interface PdxScriptVariableReference extends PdxScriptValue {

  @NotNull
  PsiElement getVariableReferenceId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  PdxScriptVariablePsiReference getReference();

}
