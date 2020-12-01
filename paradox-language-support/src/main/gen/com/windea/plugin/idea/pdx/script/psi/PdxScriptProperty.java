// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import javax.swing.Icon;

public interface PdxScriptProperty extends PdxScriptNamedElement, StubBasedPsiElement<PdxScriptPropertyStub> {

  @NotNull
  PdxScriptPropertyKey getPropertyKey();

  @Nullable
  PdxScriptPropertySeparator getPropertySeparator();

  @Nullable
  PdxScriptPropertyValue getPropertyValue();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

}
