// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import com.intellij.openapi.util.Iconable.IconFlags;
import com.windea.plugin.idea.stellaris.StellarisLocale;
import javax.swing.Icon;

public interface StellarisLocalizationProperty extends StellarisLocalizationNamedElement, StubBasedPsiElement<StellarisLocalizationPropertyStub> {

  @NotNull
  StellarisLocalizationPropertyKey getPropertyKey();

  @Nullable
  StellarisLocalizationPropertyValue getPropertyValue();

  @Nullable
  PsiElement getNumber();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  String getValue();

  @Nullable
  StellarisLocale getStellarisLocale();

}
