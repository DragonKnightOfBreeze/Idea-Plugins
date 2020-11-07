// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;

public interface StellarisScriptBlock extends StellarisScriptValue, PsiListLikeElement {

  @NotNull
  List<StellarisScriptItem> getItemList();

  @NotNull
  List<StellarisScriptProperty> getPropertyList();

  boolean isEmpty();

  boolean isNotEmpty();

  boolean isObject();

  boolean isArray();

  @NotNull
  List<PsiElement> getComponents();

}
