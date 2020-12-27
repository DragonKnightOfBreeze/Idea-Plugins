// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.paradox.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.paradox.model.ParadoxColor;

public interface ParadoxLocalisationColorfulText extends ParadoxLocalisationRichText, ParadoxLocalisationNamedElement {

  @NotNull
  List<ParadoxLocalisationRichText> getRichTextList();

  @Nullable
  PsiElement getColorCode();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  ParadoxColor getParadoxColor();

}
