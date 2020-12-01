// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.paradox.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.paradox.ParadoxSerialNumber;

public interface ParadoxLocalisationSerialNumber extends ParadoxLocalisationRichText, ParadoxLocalisationNamedElement {

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
  ParadoxSerialNumber getParadoxSerialNumber();

  //WARNING: getReference(...) is skipped
  //matching getReference(ParadoxLocalisationSerialNumber, ...)
  //methods are not found in ParadoxLocalisationPsiImplUtil

}
