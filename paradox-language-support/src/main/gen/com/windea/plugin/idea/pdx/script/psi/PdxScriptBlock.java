// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;

public interface PdxScriptBlock extends PdxScriptValue, PsiListLikeElement {

  @NotNull
  List<PdxScriptProperty> getPropertyList();

  @NotNull
  List<PdxScriptValue> getValueList();

  boolean isEmpty();

  boolean isNotEmpty();

  boolean isObject();

  boolean isArray();

  @NotNull
  List<PsiElement> getComponents();

}
