// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.pdx.script.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.pdx.script.psi.impl.*;

public interface PdxScriptTypes {

  IElementType BLOCK = new PdxScriptElementType("BLOCK");
  IElementType BOOLEAN = new PdxScriptElementType("BOOLEAN");
  IElementType CODE = new PdxScriptElementType("CODE");
  IElementType COLOR = new PdxScriptElementType("COLOR");
  IElementType NUMBER = new PdxScriptElementType("NUMBER");
  IElementType PROPERTY = PdxScriptStubElementTypes.getPropertyType("PROPERTY");
  IElementType PROPERTY_KEY = new PdxScriptElementType("PROPERTY_KEY");
  IElementType PROPERTY_SEPARATOR = new PdxScriptElementType("PROPERTY_SEPARATOR");
  IElementType PROPERTY_VALUE = new PdxScriptElementType("PROPERTY_VALUE");
  IElementType ROOT_BLOCK = new PdxScriptElementType("ROOT_BLOCK");
  IElementType STRING = new PdxScriptElementType("STRING");
  IElementType STRING_VALUE = new PdxScriptElementType("STRING_VALUE");
  IElementType VALUE = new PdxScriptElementType("VALUE");
  IElementType VARIABLE = PdxScriptStubElementTypes.getVariableType("VARIABLE");
  IElementType VARIABLE_NAME = new PdxScriptElementType("VARIABLE_NAME");
  IElementType VARIABLE_REFERENCE = new PdxScriptElementType("VARIABLE_REFERENCE");
  IElementType VARIABLE_SEPARATOR = new PdxScriptElementType("VARIABLE_SEPARATOR");
  IElementType VARIABLE_VALUE = new PdxScriptElementType("VARIABLE_VALUE");

  IElementType BOOLEAN_TOKEN = new PdxScriptTokenType("BOOLEAN_TOKEN");
  IElementType CODE_END = new PdxScriptTokenType("]");
  IElementType CODE_START = new PdxScriptTokenType("@\\[");
  IElementType CODE_TEXT_TOKEN = new PdxScriptTokenType("CODE_TEXT_TOKEN");
  IElementType COLOR_TOKEN = new PdxScriptTokenType("COLOR_TOKEN");
  IElementType COMMENT = new PdxScriptTokenType("COMMENT");
  IElementType END_OF_LINE_COMMENT = new PdxScriptTokenType("END_OF_LINE_COMMENT");
  IElementType EQUAL_SIGN = new PdxScriptTokenType("=");
  IElementType GE_SIGN = new PdxScriptTokenType(">=");
  IElementType GT_SIGN = new PdxScriptTokenType(">");
  IElementType LEFT_BRACE = new PdxScriptTokenType("{");
  IElementType LE_SIGN = new PdxScriptTokenType("<=");
  IElementType LT_SIGN = new PdxScriptTokenType("<");
  IElementType NUMBER_TOKEN = new PdxScriptTokenType("NUMBER_TOKEN");
  IElementType PROPERTY_KEY_ID = new PdxScriptTokenType("PROPERTY_KEY_ID");
  IElementType QUOTED_PROPERTY_KEY_ID = new PdxScriptTokenType("QUOTED_PROPERTY_KEY_ID");
  IElementType QUOTED_STRING_TOKEN = new PdxScriptTokenType("QUOTED_STRING_TOKEN");
  IElementType RIGHT_BRACE = new PdxScriptTokenType("}");
  IElementType RIGHT_QUOTE = new PdxScriptTokenType("\"");
  IElementType STRING_TOKEN = new PdxScriptTokenType("STRING_TOKEN");
  IElementType VARIABLE_NAME_ID = new PdxScriptTokenType("VARIABLE_NAME_ID");
  IElementType VARIABLE_REFERENCE_ID = new PdxScriptTokenType("VARIABLE_REFERENCE_ID");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BLOCK) {
        return new PdxScriptBlockImpl(node);
      }
      else if (type == BOOLEAN) {
        return new PdxScriptBooleanImpl(node);
      }
      else if (type == CODE) {
        return new PdxScriptCodeImpl(node);
      }
      else if (type == COLOR) {
        return new PdxScriptColorImpl(node);
      }
      else if (type == NUMBER) {
        return new PdxScriptNumberImpl(node);
      }
      else if (type == PROPERTY) {
        return new PdxScriptPropertyImpl(node);
      }
      else if (type == PROPERTY_KEY) {
        return new PdxScriptPropertyKeyImpl(node);
      }
      else if (type == PROPERTY_SEPARATOR) {
        return new PdxScriptPropertySeparatorImpl(node);
      }
      else if (type == PROPERTY_VALUE) {
        return new PdxScriptPropertyValueImpl(node);
      }
      else if (type == ROOT_BLOCK) {
        return new PdxScriptRootBlockImpl(node);
      }
      else if (type == STRING) {
        return new PdxScriptStringImpl(node);
      }
      else if (type == VARIABLE) {
        return new PdxScriptVariableImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new PdxScriptVariableNameImpl(node);
      }
      else if (type == VARIABLE_REFERENCE) {
        return new PdxScriptVariableReferenceImpl(node);
      }
      else if (type == VARIABLE_SEPARATOR) {
        return new PdxScriptVariableSeparatorImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new PdxScriptVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
