// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.StellarisColor;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationPropertyPsiReference;

public interface StellarisLocalizationPropertyReference extends StellarisLocalizationRichText, StellarisLocalizationNamedElement {

  @Nullable
  StellarisLocalizationCode getCode();

  @Nullable
  PsiElement getPropertyReferenceId();

  @Nullable
  PsiElement getPropertyReferenceParameter();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  int getTextOffset();

  @Nullable
  StellarisLocalizationPropertyPsiReference getReference();

  @Nullable
  StellarisColor getStellarisColor();

}
