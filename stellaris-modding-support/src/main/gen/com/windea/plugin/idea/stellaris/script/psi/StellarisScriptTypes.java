// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.script.psi.impl.*;

public interface StellarisScriptTypes {

  IElementType BLOCK = new StellarisScriptElementType("BLOCK");
  IElementType BOOLEAN = new StellarisScriptElementType("BOOLEAN");
  IElementType CODE = new StellarisScriptElementType("CODE");
  IElementType COLOR = new StellarisScriptElementType("COLOR");
  IElementType NUMBER = new StellarisScriptElementType("NUMBER");
  IElementType PROPERTY = new StellarisScriptElementType("PROPERTY");
  IElementType PROPERTY_KEY = new StellarisScriptElementType("PROPERTY_KEY");
  IElementType PROPERTY_SEPARATOR = new StellarisScriptElementType("PROPERTY_SEPARATOR");
  IElementType PROPERTY_VALUE = new StellarisScriptElementType("PROPERTY_VALUE");
  IElementType ROOT_BLOCK = new StellarisScriptElementType("ROOT_BLOCK");
  IElementType STRING = new StellarisScriptElementType("STRING");
  IElementType STRING_VALUE = new StellarisScriptElementType("STRING_VALUE");
  IElementType VALUE = new StellarisScriptElementType("VALUE");
  IElementType VARIABLE = new StellarisScriptElementType("VARIABLE");
  IElementType VARIABLE_NAME = new StellarisScriptElementType("VARIABLE_NAME");
  IElementType VARIABLE_REFERENCE = new StellarisScriptElementType("VARIABLE_REFERENCE");
  IElementType VARIABLE_SEPARATOR = new StellarisScriptElementType("VARIABLE_SEPARATOR");
  IElementType VARIABLE_VALUE = new StellarisScriptElementType("VARIABLE_VALUE");

  IElementType BOOLEAN_TOKEN = new StellarisScriptTokenType("BOOLEAN_TOKEN");
  IElementType CODE_END = new StellarisScriptTokenType("]");
  IElementType CODE_START = new StellarisScriptTokenType("@\\[");
  IElementType CODE_TEXT_TOKEN = new StellarisScriptTokenType("CODE_TEXT_TOKEN");
  IElementType COLOR_TOKEN = new StellarisScriptTokenType("COLOR_TOKEN");
  IElementType COMMENT = new StellarisScriptTokenType("COMMENT");
  IElementType END_OF_LINE_COMMENT = new StellarisScriptTokenType("END_OF_LINE_COMMENT");
  IElementType EQUAL_SIGN = new StellarisScriptTokenType("=");
  IElementType GE_SIGN = new StellarisScriptTokenType(">=");
  IElementType GT_SIGN = new StellarisScriptTokenType(">");
  IElementType LEFT_BRACE = new StellarisScriptTokenType("{");
  IElementType LE_SIGN = new StellarisScriptTokenType("<=");
  IElementType LT_SIGN = new StellarisScriptTokenType("<");
  IElementType NUMBER_TOKEN = new StellarisScriptTokenType("NUMBER_TOKEN");
  IElementType PROPERTY_KEY_ID = new StellarisScriptTokenType("PROPERTY_KEY_ID");
  IElementType QUOTED_PROPERTY_KEY_ID = new StellarisScriptTokenType("QUOTED_PROPERTY_KEY_ID");
  IElementType QUOTED_STRING_TOKEN = new StellarisScriptTokenType("QUOTED_STRING_TOKEN");
  IElementType RIGHT_BRACE = new StellarisScriptTokenType("}");
  IElementType RIGHT_QUOTE = new StellarisScriptTokenType("\"");
  IElementType STRING_TOKEN = new StellarisScriptTokenType("STRING_TOKEN");
  IElementType VARIABLE_NAME_ID = new StellarisScriptTokenType("VARIABLE_NAME_ID");
  IElementType VARIABLE_REFERENCE_ID = new StellarisScriptTokenType("VARIABLE_REFERENCE_ID");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BLOCK) {
        return new StellarisScriptBlockImpl(node);
      }
      else if (type == BOOLEAN) {
        return new StellarisScriptBooleanImpl(node);
      }
      else if (type == CODE) {
        return new StellarisScriptCodeImpl(node);
      }
      else if (type == COLOR) {
        return new StellarisScriptColorImpl(node);
      }
      else if (type == NUMBER) {
        return new StellarisScriptNumberImpl(node);
      }
      else if (type == PROPERTY) {
        return new StellarisScriptPropertyImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new StellarisScriptPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_SEPARATOR) {
        return new StellarisScriptPropertySeparatorImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new StellarisScriptPropertyValueImpl(node);
      }
      else if (type == ROOT_BLOCK) {
        return new StellarisScriptRootBlockImpl(node);
      }
      else if (type == STRING) {
        return new StellarisScriptStringImpl(node);
      }
      else if (type == VARIABLE) {
        return new StellarisScriptVariableImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new StellarisScriptVariableNameImpl(node);
      }
      else if (type == VARIABLE_REFERENCE) {
        return new StellarisScriptVariableReferenceImpl(node);
      }
      else if (type == VARIABLE_SEPARATOR) {
        return new StellarisScriptVariableSeparatorImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new StellarisScriptVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
