// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiListLikeElement;

public interface StellarisScriptBlock extends PsiListLikeElement {

  @NotNull
  List<StellarisScriptItem> getItemList();

  @NotNull
  List<StellarisScriptProperty> getPropertyList();

  //WARNING: isEmpty(...) is skipped
  //matching isEmpty(StellarisScriptBlock, ...)
  //methods are not found in StellarisScriptPsiImplUtil

  //WARNING: isArray(...) is skipped
  //matching isArray(StellarisScriptBlock, ...)
  //methods are not found in StellarisScriptPsiImplUtil

  //WARNING: isObject(...) is skipped
  //matching isObject(StellarisScriptBlock, ...)
  //methods are not found in StellarisScriptPsiImplUtil

  //WARNING: getComponents(...) is skipped
  //matching getComponents(StellarisScriptBlock, ...)
  //methods are not found in StellarisScriptPsiImplUtil

}
