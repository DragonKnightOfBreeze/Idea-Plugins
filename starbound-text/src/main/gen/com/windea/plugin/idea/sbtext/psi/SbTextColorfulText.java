// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.sbtext.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.awt.Color;

public interface SbTextColorfulText extends SbTextNamedElement {

  @NotNull
  SbTextColorMarker getColorMarker();

  @Nullable
  SbTextColorResetMarker getColorResetMarker();

  @Nullable
  SbTextString getString();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  Color getColor();

  void setColor(@NotNull Color color);

}
