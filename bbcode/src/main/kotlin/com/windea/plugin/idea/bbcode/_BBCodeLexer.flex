package com.windea.plugin.idea.bbcode.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.bbcode.psi.BBCodeTypes.*;

%%

%{
  public _BBCodeLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _BBCodeLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state WAITING_TAG

EOL=\R
WHITE_SPACE=\s+

BLANK=[ \t\n\x0B\f\r]+
TAG_NAME_TOKEN=[a-zA-Z*\-_]+
ATTRIBUTE_NAME_TOKEN=[a-zA-Z*\-_]+
ATTRIBUTE_VALUE_TOKEN=[^\[\]\s]+
TEXT_TOKEN=[^\[\]]+

%%
<YYINITIAL> {
  {WHITE_SPACE}                { return WHITE_SPACE; }

  "="                          { return EQUAL_SIGN; }
  "["                          { return TAG_PREFIX_START; }
  "]"                          { return TAG_PREFIX_END; }
  "[/"                         { return TAG_SUFFIX_START; }
  "]"                          { return TAG_SUFFIX_END; }

  {BLANK}                      { return BLANK; }
  {TAG_NAME_TOKEN}             { return TAG_NAME_TOKEN; }
  {ATTRIBUTE_NAME_TOKEN}       { return ATTRIBUTE_NAME_TOKEN; }
  {ATTRIBUTE_VALUE_TOKEN}      { return ATTRIBUTE_VALUE_TOKEN; }
  {TEXT_TOKEN}                 { return TEXT_TOKEN; }

}

[^] { return BAD_CHARACTER; }
