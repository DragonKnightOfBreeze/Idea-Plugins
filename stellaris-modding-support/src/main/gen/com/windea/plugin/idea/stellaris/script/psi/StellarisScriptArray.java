// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;

public interface StellarisScriptArray extends PsiListLikeElement {

  @NotNull
  List<StellarisScriptText> getTextList();

  @NotNull
  List<PsiElement> getComponents();

}
