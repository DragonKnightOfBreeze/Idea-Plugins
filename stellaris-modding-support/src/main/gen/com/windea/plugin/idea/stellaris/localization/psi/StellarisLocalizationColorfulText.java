// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface StellarisLocalizationColorfulText extends StellarisLocalizationNamedElement {

  @NotNull
  List<StellarisLocalizationRichText> getRichTextList();

  @NotNull
  PsiElement getColorfulTextCode();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

}
