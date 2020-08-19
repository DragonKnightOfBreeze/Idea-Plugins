package com.windea.plugin.idea.bbcode.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;

%%

%{
  public BBCodeLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class BBCodeLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state WAITING_TAG_PREFIX
%state WAITING_TAG_PREFIX_END
%state WAITING_ATTRIBUTES
%state WAITING_ATTRIBUTE_NAME
%state WAITING_EQUAL_SIGN
%state WAITING_ATTRIBUTE_VALUE
%state WAITING_SINGLE_ATTRIBUTE_VALUE
%state WAITING_TAG_BODY
%state WAITING_TAG_SUFFIX

%{
  int depth = 0;

  public int nextState(){
  	if(depth <= 0) return YYINITIAL;
  	else return WAITING_TAG_BODY;
  }
%}

EOL=\R
WHITE_SPACE=\s+

TAG_NAME=[a-zA-Z*\-_]+
ATTRIBUTE_NAME=[a-zA-Z*\-_]+
ATTRIBUTE_VALUE=[^\[\]\s]+
TEXT_TOKEN=[^\[\]]+

%%
<YYINITIAL> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  "[" { depth++; yybegin(WAITING_TAG_PREFIX); return TAG_PREFIX_START; }
  {TEXT_TOKEN} { return TEXT_TOKEN; }
}
<WAITING_TAG_PREFIX> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  {TAG_NAME} { yybegin(WAITING_ATTRIBUTES); return TAG_NAME; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_ATTRIBUTES> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  "=" { yybegin(WAITING_SINGLE_ATTRIBUTE_VALUE); return EQUAL_SIGN; }
  {ATTRIBUTE_NAME} { yybegin(WAITING_EQUAL_SIGN); return ATTRIBUTE_NAME; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_ATTRIBUTE_NAME> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  {ATTRIBUTE_NAME} { yybegin(WAITING_EQUAL_SIGN); return ATTRIBUTE_NAME; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_SINGLE_ATTRIBUTE_VALUE> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  {ATTRIBUTE_VALUE} { yybegin(WAITING_TAG_PREFIX_END); return ATTRIBUTE_VALUE; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_EQUAL_SIGN> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  "=" { yybegin(WAITING_ATTRIBUTE_VALUE); return EQUAL_SIGN; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_ATTRIBUTE_VALUE> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  {ATTRIBUTE_VALUE} { yybegin(WAITING_ATTRIBUTE_NAME); return ATTRIBUTE_VALUE; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}
<WAITING_TAG_PREFIX_END> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  "]" { yybegin(WAITING_TAG_BODY); return TAG_PREFIX_END; }
}

<WAITING_TAG_BODY> {
  "[/" { yybegin(WAITING_TAG_SUFFIX); return TAG_SUFFIX_START; }
  //这是缺失标签后缀的情况，不需要depth--？
  "[" {  yybegin(WAITING_TAG_PREFIX); return TAG_PREFIX_START; }
  {TEXT_TOKEN} { return TEXT_TOKEN; }
}
<WAITING_TAG_SUFFIX> {
  {WHITE_SPACE} { return WHITE_SPACE; }
  {TAG_NAME} { return TAG_NAME; }
  "]" { depth--; yybegin(nextState()); return TAG_PREFIX_END; }
}

[^] { return BAD_CHARACTER; }
