// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class StellarisLocalizationParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return root(b, l + 1);
  }

  /* ********************************************************** */
  // CODE_START CODE_TEXT CODE_END
  public static boolean code(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "code")) return false;
    if (!nextTokenIs(b, CODE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, CODE_START, CODE_TEXT, CODE_END);
    exit_section_(b, m, CODE, r);
    return r;
  }

  /* ********************************************************** */
  // COLORFUL_TEXT_START COLORFUL_TEXT_CODE rich_text* COLORFUL_TEXT_END
  public static boolean colorful_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "colorful_text")) return false;
    if (!nextTokenIs(b, COLORFUL_TEXT_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COLORFUL_TEXT_START, COLORFUL_TEXT_CODE);
    r = r && colorful_text_2(b, l + 1);
    r = r && consumeToken(b, COLORFUL_TEXT_END);
    exit_section_(b, m, COLORFUL_TEXT, r);
    return r;
  }

  // rich_text*
  private static boolean colorful_text_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "colorful_text_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!rich_text(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "colorful_text_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ICON_START ICON_TEXT ICON_END
  public static boolean icon(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "icon")) return false;
    if (!nextTokenIs(b, ICON_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ICON_START, ICON_TEXT, ICON_END);
    exit_section_(b, m, ICON, r);
    return r;
  }

  /* ********************************************************** */
  // VALUE_TOKEN
  public static boolean plain_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plain_text")) return false;
    if (!nextTokenIs(b, VALUE_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, VALUE_TOKEN);
    exit_section_(b, m, PLAIN_TEXT, r);
    return r;
  }

  /* ********************************************************** */
  // property_key ":" NUMBER property_value
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY, null);
    r = property_key(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeTokens(b, -1, COLON, NUMBER));
    r = p && property_value(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // HEADER_TOKEN ":"
  public static boolean property_header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_header")) return false;
    if (!nextTokenIs(b, HEADER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HEADER_TOKEN, COLON);
    exit_section_(b, m, PROPERTY_HEADER, r);
    return r;
  }

  /* ********************************************************** */
  // KEY_TOKEN
  public static boolean property_key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_key")) return false;
    if (!nextTokenIs(b, KEY_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY_TOKEN);
    exit_section_(b, m, PROPERTY_KEY, r);
    return r;
  }

  /* ********************************************************** */
  // COMMENT | property
  static boolean property_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_list")) return false;
    if (!nextTokenIs(b, "", COMMENT, KEY_TOKEN)) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = property(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // PROPERTY_REFERENCE_START KEY_TOKEN PROPERTY_REFERENCE_END
  public static boolean property_reference(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_reference")) return false;
    if (!nextTokenIs(b, PROPERTY_REFERENCE_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PROPERTY_REFERENCE_START, KEY_TOKEN, PROPERTY_REFERENCE_END);
    exit_section_(b, m, PROPERTY_REFERENCE, r);
    return r;
  }

  /* ********************************************************** */
  // LEFT_QUOTE rich_text * RIGHT_QUOTE
  public static boolean property_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_value")) return false;
    if (!nextTokenIs(b, LEFT_QUOTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_QUOTE);
    r = r && property_value_1(b, l + 1);
    r = r && consumeToken(b, RIGHT_QUOTE);
    exit_section_(b, m, PROPERTY_VALUE, r);
    return r;
  }

  // rich_text *
  private static boolean property_value_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_value_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!rich_text(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "property_value_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // plain_text | property_reference | code | icon | serial_number | colorful_text
  public static boolean rich_text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "rich_text")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RICH_TEXT, "<rich text>");
    r = plain_text(b, l + 1);
    if (!r) r = property_reference(b, l + 1);
    if (!r) r = code(b, l + 1);
    if (!r) r = icon(b, l + 1);
    if (!r) r = serial_number(b, l + 1);
    if (!r) r = colorful_text(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ROOT_COMMENT * [root_item]
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = root_0(b, l + 1);
    r = r && root_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // ROOT_COMMENT *
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, ROOT_COMMENT)) break;
      if (!empty_element_parsed_guard_(b, "root_0", c)) break;
    }
    return true;
  }

  // [root_item]
  private static boolean root_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_1")) return false;
    root_item(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // property_header property_list *
  static boolean root_item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_item")) return false;
    if (!nextTokenIs(b, HEADER_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_header(b, l + 1);
    r = r && root_item_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // property_list *
  private static boolean root_item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_item_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property_list(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root_item_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SERIAL_NUMBER_START SERIAL_NUMBER_CODE SERIAL_NUMBER_END
  public static boolean serial_number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "serial_number")) return false;
    if (!nextTokenIs(b, SERIAL_NUMBER_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SERIAL_NUMBER_START, SERIAL_NUMBER_CODE, SERIAL_NUMBER_END);
    exit_section_(b, m, SERIAL_NUMBER, r);
    return r;
  }

}
