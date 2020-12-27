// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.paradox.localisation.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.paradox.model.ParadoxLocale;
import javax.swing.Icon;

public interface ParadoxLocalisationLocale extends ParadoxLocalisationNamedElement {

  @NotNull
  PsiElement getLocaleId();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  Icon getIcon(@IconFlags int flags);

  @Nullable
  ParadoxLocale getParadoxLocale();

}
