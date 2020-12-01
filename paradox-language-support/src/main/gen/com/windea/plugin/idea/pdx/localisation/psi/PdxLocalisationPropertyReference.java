// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.localisation.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.pdx.PdxColor;
import com.windea.plugin.idea.pdx.localisation.reference.PdxLocalisationPropertyPsiReference;

public interface PdxLocalisationPropertyReference extends PdxLocalisationRichText, PdxLocalisationNamedElement {

  @Nullable
  PdxLocalisationCode getCode();

  @Nullable
  PsiElement getPropertyReferenceId();

  @Nullable
  PsiElement getPropertyReferenceParameter();

  @Nullable
  String getName();

  @NotNull
  PsiElement setName(@NotNull String name);

  @Nullable
  PsiElement getNameIdentifier();

  int getTextOffset();

  @Nullable
  PdxLocalisationPropertyPsiReference getReference();

  @Nullable
  PdxColor getPdxColor();

}
