// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;

public interface StellarisScriptObject extends PsiListLikeElement {

  @NotNull
  List<StellarisScriptProperty> getPropertyList();

  @NotNull
  List<PsiElement> getComponents();

}
