// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface StellarisScriptVariableDefinition extends StellarisScriptNamedElement {

  @Nullable
  StellarisScriptVariableDefinitionSeparator getVariableDefinitionSeparator();

  @NotNull
  StellarisScriptVariableName getVariableName();

  @Nullable
  StellarisScriptVariableValue getVariableValue();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

}
