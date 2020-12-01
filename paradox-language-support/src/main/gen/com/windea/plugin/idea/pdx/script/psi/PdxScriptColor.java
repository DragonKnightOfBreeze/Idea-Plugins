// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import java.awt.Color;

public interface PdxScriptColor extends PdxScriptStringValue {

  @NotNull
  PsiElement getColorToken();

  @NotNull
  String getValue();

  @Nullable
  Color getColor();

  void setColor(@NotNull Color color);

}
