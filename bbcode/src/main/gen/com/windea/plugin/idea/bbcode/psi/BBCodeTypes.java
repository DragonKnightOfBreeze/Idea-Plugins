// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.bbcode.psi.impl.*;

public interface BBCodeTypes {

  IElementType ATTRIBUTE = new BBCodeElementType("ATTRIBUTE");
  IElementType TAG = new BBCodeElementType("TAG");
  IElementType TAG_PREFIX = new BBCodeElementType("TAG_PREFIX");
  IElementType TAG_SUFFIX = new BBCodeElementType("TAG_SUFFIX");
  IElementType TEXT = new BBCodeElementType("TEXT");

  IElementType ATTRIBUTE_NAME = new BBCodeTokenType("ATTRIBUTE_NAME");
  IElementType ATTRIBUTE_VALUE = new BBCodeTokenType("ATTRIBUTE_VALUE");
  IElementType EQUAL_SIGN = new BBCodeTokenType("=");
  IElementType TAG_NAME = new BBCodeTokenType("TAG_NAME");
  IElementType TAG_PREFIX_END = new BBCodeTokenType("TAG_PREFIX_END");
  IElementType TAG_PREFIX_START = new BBCodeTokenType("[");
  IElementType TAG_SUFFIX_END = new BBCodeTokenType("]");
  IElementType TAG_SUFFIX_START = new BBCodeTokenType("[/");
  IElementType TEXT_TOKEN = new BBCodeTokenType("TEXT_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new BBCodeAttributeImpl(node);
      }
      else if (type == TAG) {
        return new BBCodeTagImpl(node);
      }
      else if (type == TAG_PREFIX) {
        return new BBCodeTagPrefixImpl(node);
      }
      else if (type == TAG_SUFFIX) {
        return new BBCodeTagSuffixImpl(node);
      }
      else if (type == TEXT) {
        return new BBCodeTextImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
