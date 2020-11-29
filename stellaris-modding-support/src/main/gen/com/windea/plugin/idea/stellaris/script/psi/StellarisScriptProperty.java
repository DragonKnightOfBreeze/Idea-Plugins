// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface StellarisScriptProperty extends StellarisScriptNamedElement, StubBasedPsiElement<StellarisScriptPropertyStub> {

  @NotNull
  StellarisScriptPropertyKey getPropertyKey();

  @Nullable
  StellarisScriptPropertySeparator getPropertySeparator();

  @Nullable
  StellarisScriptPropertyValue getPropertyValue();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

}
