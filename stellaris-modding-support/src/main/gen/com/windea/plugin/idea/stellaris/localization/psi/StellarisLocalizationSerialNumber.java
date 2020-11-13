// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.StellarisSerialNumber;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationSerialNumberPsiReference;

public interface StellarisLocalizationSerialNumber extends StellarisLocalizationRichText, StellarisLocalizationNamedElement {

  @Nullable
  PsiElement getSerialNumberId();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  StellarisSerialNumber getStellarisSerialNumber();

  @Nullable
  StellarisLocalizationSerialNumberPsiReference getReference();

}
