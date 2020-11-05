// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationIconPsiReference;

public interface StellarisLocalizationIcon extends StellarisLocalizationNamedElement {

  @Nullable
  StellarisLocalizationCode getCode();

  @Nullable
  StellarisLocalizationPropertyReference getPropertyReference();

  @Nullable
  PsiElement getIconId();

  @Nullable
  PsiElement getIconParameter();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  StellarisLocalizationIconPsiReference getReference();

}
