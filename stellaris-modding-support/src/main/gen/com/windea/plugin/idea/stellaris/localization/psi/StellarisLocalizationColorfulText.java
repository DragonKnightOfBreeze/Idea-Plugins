// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.stellaris.enums.StellarisColor;
import com.windea.plugin.idea.stellaris.localization.reference.StellarisLocalizationColorfulTextPsiReference;

public interface StellarisLocalizationColorfulText extends StellarisLocalizationNamedElement {

  @NotNull
  List<StellarisLocalizationRichText> getRichTextList();

  @Nullable
  PsiElement getColorCode();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  StellarisColor getColor();

  @Nullable
  StellarisLocalizationColorfulTextPsiReference getReference();

}
