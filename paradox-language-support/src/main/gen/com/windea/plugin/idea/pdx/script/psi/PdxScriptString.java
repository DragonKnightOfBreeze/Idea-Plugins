// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.windea.plugin.idea.pdx.script.reference.PdxScriptStringAsPropertyPsiReference;

public interface PdxScriptString extends PdxScriptStringValue {

  @Nullable
  PsiElement getQuotedStringToken();

  @Nullable
  PsiElement getStringToken();

  @NotNull
  String getValue();

  @Nullable
  PdxScriptStringAsPropertyPsiReference getReference();

}
