package com.windea.plugin.idea.stellaris.script;

import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;

%%

%{
  public _StellarisScriptLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _StellarisScriptLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

EOL=\R
BLANK=[ \t\n\x0B\f\r]+
COMMENT=#[^\r\n]*
END_OF_LINE_COMMENT=#[^\r\n]*
BOOLEAN=(yes)|(no)
NUMBER=[0-9]+(\.[0-9]+)?
VARIABLE_NAME_TOKEN=@[a-zA-Z0-9_]+
VARIABLE_REFERENCE_TOKEN=@[a-zA-Z0-9_]+
STRING=\"([^\"(\r\n\\]|\\.)*?\"
UNQUOTED_STRING=[a-zA-Z0-9_.]+
KEY_TOKEN=[a-z0-9_]+

%%
<YYINITIAL> {
  {WHITE_SPACE}                   { return WHITE_SPACE; }

  "="                             { return EQUAL_SIGN; }
  "<"                             { return LT_SIGN; }
  ">"                             { return GT_SIGN; }
  "<="                            { return LE_SIGN; }
  ">="                            { return GE_SIGN; }
  "{"                             { return LEFT_BRACE; }
  "}"                             { return RIGHT_BRACE; }

  {EOL}                           { return EOL; }
  {BLANK}                         { return BLANK; }
  {COMMENT}                       { return COMMENT; }
  {END_OF_LINE_COMMENT}           { return END_OF_LINE_COMMENT; }
  {BOOLEAN}                       { return BOOLEAN_TOKEN; }
  {NUMBER}                        { return NUMBER_TOKEN; }
  {VARIABLE_NAME_TOKEN}           { return VARIABLE_NAME_ID; }
  {VARIABLE_REFERENCE_TOKEN}      { return VARIABLE_REFERENCE_ID; }
  {STRING}                        { return QUOTED_STRING_TOKEN; }
  {UNQUOTED_STRING}               { return STRING_TOKEN; }
  {KEY_TOKEN}                     { return PROPERTY_KEY_ID; }

}

[^] { return BAD_CHARACTER; }
