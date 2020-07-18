// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import com.intellij.psi.PsiReference;
import javax.swing.Icon;

public interface StellarisScriptVariableReference extends StellarisScriptNamedElement {

  @NotNull
  PsiElement getVariableReferenceToken();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  @Nullable
  Icon getIcon(@IconFlags int flags);

  @NotNull
  PsiReference getReference();

}
