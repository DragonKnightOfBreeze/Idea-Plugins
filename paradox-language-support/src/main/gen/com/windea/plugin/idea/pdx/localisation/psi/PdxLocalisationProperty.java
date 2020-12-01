// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.pdx.PdxLocale;
import javax.swing.Icon;

public interface PdxLocalisationProperty extends PdxLocalisationNamedElement, StubBasedPsiElement<PdxLocalisationPropertyStub> {

  @NotNull
  PdxLocalisationPropertyKey getPropertyKey();

  @Nullable
  PdxLocalisationPropertyValue getPropertyValue();

  @Nullable
  PsiElement getNumber();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

  @Nullable
  PdxLocale getPdxLocale();

}
