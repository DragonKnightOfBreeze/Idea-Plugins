// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface StellarisScriptProperty extends StellarisScriptNamedElement {

  @NotNull
  StellarisScriptPropertyKey getPropertyKey();

  @NotNull
  StellarisScriptPropertySeparator getPropertySeparator();

  @NotNull
  StellarisScriptPropertyValue getPropertyValue();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  Icon getIcon(@IconFlags int flags);

}
