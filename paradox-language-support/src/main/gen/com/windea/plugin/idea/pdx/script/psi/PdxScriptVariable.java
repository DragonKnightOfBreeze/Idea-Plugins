// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface PdxScriptVariable extends PdxScriptNamedElement, StubBasedPsiElement<PdxScriptVariableStub> {

  @NotNull
  PdxScriptVariableName getVariableName();

  @Nullable
  PdxScriptVariableSeparator getVariableSeparator();

  @Nullable
  PdxScriptVariableValue getVariableValue();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

}
