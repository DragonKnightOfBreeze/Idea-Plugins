// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.windea.plugin.idea.stellaris.script.psi.impl.*;

public interface StellarisScriptTypes {

  IElementType BLOCK = new StellarisScriptElementType("BLOCK");
  IElementType BOOLEAN = new StellarisScriptElementType("BOOLEAN");
  IElementType COLOR = new StellarisScriptElementType("COLOR");
  IElementType ITEM = new StellarisScriptElementType("ITEM");
  IElementType NUMBER = new StellarisScriptElementType("NUMBER");
  IElementType PROPERTY = new StellarisScriptElementType("PROPERTY");
  IElementType PROPERTY_KEY = new StellarisScriptElementType("PROPERTY_KEY");
  IElementType PROPERTY_SEPARATOR = new StellarisScriptElementType("PROPERTY_SEPARATOR");
  IElementType PROPERTY_VALUE = new StellarisScriptElementType("PROPERTY_VALUE");
  IElementType STRING = new StellarisScriptElementType("STRING");
  IElementType VARIABLE_DEFINITION = new StellarisScriptElementType("VARIABLE_DEFINITION");
  IElementType VARIABLE_DEFINITION_SEPARATOR = new StellarisScriptElementType("VARIABLE_DEFINITION_SEPARATOR");
  IElementType VARIABLE_NAME = new StellarisScriptElementType("VARIABLE_NAME");
  IElementType VARIABLE_REFERENCE = new StellarisScriptElementType("VARIABLE_REFERENCE");
  IElementType VARIABLE_VALUE = new StellarisScriptElementType("VARIABLE_VALUE");

  IElementType BOOLEAN_TOKEN = new StellarisScriptTokenType("BOOLEAN_TOKEN");
  IElementType COLOR_PARAMETER = new StellarisScriptTokenType("COLOR_PARAMETER");
  IElementType COLOR_TYPE = new StellarisScriptTokenType("COLOR_TYPE");
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
  IElementType RIGHT_BRACE = new StellarisScriptTokenType("}");
  IElementType STRING_TOKEN = new StellarisScriptTokenType("STRING_TOKEN");
  IElementType UNQUOTED_STRING_TOKEN = new StellarisScriptTokenType("UNQUOTED_STRING_TOKEN");
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
      else if (type == COLOR) {
        return new StellarisScriptColorImpl(node);
      }
      else if (type == ITEM) {
        return new StellarisScriptItemImpl(node);
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
      else if (type == STRING) {
        return new StellarisScriptStringImpl(node);
      }
      else if (type == VARIABLE_DEFINITION) {
        return new StellarisScriptVariableDefinitionImpl(node);
      }
      else if (type == VARIABLE_DEFINITION_SEPARATOR) {
        return new StellarisScriptVariableDefinitionSeparatorImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new StellarisScriptVariableNameImpl(node);
      }
      else if (type == VARIABLE_REFERENCE) {
        return new StellarisScriptVariableReferenceImpl(node);
      }
      else if (type == VARIABLE_VALUE) {
        return new StellarisScriptVariableValueImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
