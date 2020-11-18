// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface StellarisScriptVariable extends StellarisScriptNamedElement, StubBasedPsiElement<StellarisScriptVariableStub> {

  @NotNull
  StellarisScriptVariableName getVariableName();

  @Nullable
  StellarisScriptVariableSeparator getVariableSeparator();

  @Nullable
  StellarisScriptVariableValue getVariableValue();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

}
