// This is a generated file. Not intended for manual editing.
package com.windea.plugin.idea.bbcode.psi;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class BBCodeParser implements PsiParser, LightPsiParser {

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
  // ATTRIBUTE_NAME EQUAL_SIGN ATTRIBUTE_VALUE
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, ATTRIBUTE_NAME)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ATTRIBUTE, null);
    r = consumeTokens(b, 1, ATTRIBUTE_NAME, EQUAL_SIGN, ATTRIBUTE_VALUE);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // attribute +
  static boolean attribute_group(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_group")) return false;
    if (!nextTokenIs(b, ATTRIBUTE_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!attribute(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_group", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (tag | text)*
  static boolean root(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root")) return false;
    while (true) {
      int c = current_position_(b);
      if (!root_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "root", c)) break;
    }
    return true;
  }

  // tag | text
  private static boolean root_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "root_0")) return false;
    boolean r;
    r = tag(b, l + 1);
    if (!r) r = text(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // EQUAL_SIGN ATTRIBUTE_VALUE
  static boolean simple_attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_attribute")) return false;
    if (!nextTokenIs(b, EQUAL_SIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, EQUAL_SIGN, ATTRIBUTE_VALUE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // tag_prefix  tag_body ? tag_suffix ?
  public static boolean tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag")) return false;
    if (!nextTokenIs(b, TAG_PREFIX_START)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tag_prefix(b, l + 1);
    r = r && tag_1(b, l + 1);
    r = r && tag_2(b, l + 1);
    exit_section_(b, m, TAG, r);
    return r;
  }

  // tag_body ?
  private static boolean tag_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_1")) return false;
    tag_body(b, l + 1);
    return true;
  }

  // tag_suffix ?
  private static boolean tag_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_2")) return false;
    tag_suffix(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // simple_attribute | attribute_group
  static boolean tag_attributes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_attributes")) return false;
    if (!nextTokenIs(b, "", ATTRIBUTE_NAME, EQUAL_SIGN)) return false;
    boolean r;
    r = simple_attribute(b, l + 1);
    if (!r) r = attribute_group(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (tag | text) +
  static boolean tag_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_body")) return false;
    if (!nextTokenIs(b, "", TAG_PREFIX_START, TEXT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tag_body_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!tag_body_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "tag_body", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // tag | text
  private static boolean tag_body_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_body_0")) return false;
    boolean r;
    r = tag(b, l + 1);
    if (!r) r = text(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // TAG_PREFIX_START TAG_NAME tag_attributes ? TAG_PREFIX_END
  public static boolean tag_prefix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_prefix")) return false;
    if (!nextTokenIs(b, TAG_PREFIX_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TAG_PREFIX, null);
    r = consumeTokens(b, 1, TAG_PREFIX_START, TAG_NAME);
    p = r; // pin = 1
    r = r && report_error_(b, tag_prefix_2(b, l + 1));
    r = p && consumeToken(b, TAG_PREFIX_END) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // tag_attributes ?
  private static boolean tag_prefix_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_prefix_2")) return false;
    tag_attributes(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TAG_SUFFIX_START TAG_NAME TAG_SUFFIX_END
  public static boolean tag_suffix(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag_suffix")) return false;
    if (!nextTokenIs(b, TAG_SUFFIX_START)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TAG_SUFFIX, null);
    r = consumeTokens(b, 1, TAG_SUFFIX_START, TAG_NAME, TAG_SUFFIX_END);
    p = r; // pin = 1
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TEXT_TOKEN
  public static boolean text(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text")) return false;
    if (!nextTokenIs(b, TEXT_TOKEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TEXT_TOKEN);
    exit_section_(b, m, TEXT, r);
    return r;
  }

}
