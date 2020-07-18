// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.localization.psi.impl.*;

public interface StellarisLocalizationTypes {

  IElementType PROPERTY = new StellarisLocalizationElementType("PROPERTY");
  IElementType PROPERTY_HEADER = new StellarisLocalizationElementType("PROPERTY_HEADER");
  IElementType PROPERTY_KEY = new StellarisLocalizationElementType("PROPERTY_KEY");
  IElementType PROPERTY_VALUE = new StellarisLocalizationElementType("PROPERTY_VALUE");

  IElementType COLON = new StellarisLocalizationTokenType(":");
  IElementType COMMENT = new StellarisLocalizationTokenType("COMMENT");
  IElementType HEADER_TOKEN = new StellarisLocalizationTokenType("HEADER_TOKEN");
  IElementType KEY_TOKEN = new StellarisLocalizationTokenType("KEY_TOKEN");
  IElementType NUMBER = new StellarisLocalizationTokenType("NUMBER");
  IElementType ROOT_COMMENT = new StellarisLocalizationTokenType("ROOT_COMMENT");
  IElementType VALUE_TOKEN = new StellarisLocalizationTokenType("VALUE_TOKEN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == PROPERTY) {
        return new StellarisLocalizationPropertyImpl(node);
      }
      else if (type == PROPERTY_HEADER) {
        return new StellarisLocalizationPropertyHeaderImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new StellarisLocalizationPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new StellarisLocalizationPropertyValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
