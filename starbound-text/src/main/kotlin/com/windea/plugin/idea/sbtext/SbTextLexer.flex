package com.windea.plugin.idea.sbtext.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.sbtext.psi.SbTextTypes.*;


%%

%public
%class SbTextLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

%state WAITING_COLOR_MARKER_START
%state WAITING_COLOR_CODE
%state WAITING_COLOR_MARKER_END

IS_COLOR_MARKER=\^[\w#]
COLOR_CODE=[\w#]+
VALID_ESCAPE_TOKEN=\\['\"rnt\\]
INVALID_ESCAPE_TOKEN=\\.
TEXT_TOKEN=[^\^\\]+

%%

<YYINITIAL> {
  {IS_COLOR_MARKER} {yypushback(yylength()); yybegin(WAITING_COLOR_MARKER_START);}
  {VALID_ESCAPE_TOKEN}        { return VALID_ESCAPE_TOKEN; }
  {INVALID_ESCAPE_TOKEN}      { return INVALID_ESCAPE_TOKEN; }
  {TEXT_TOKEN}                { return TEXT_TOKEN; }
  "^" {return TEXT_TOKEN;}
}
<WAITING_COLOR_MARKER_START>{
  "^reset;"                   {yybegin(YYINITIAL); return COLOR_RESET_MARKER_TOKEN;}
  "^"                         { yybegin(WAITING_COLOR_CODE); return COLOR_MARKER_START; }
}
<WAITING_COLOR_CODE>{
  {COLOR_CODE}                { yybegin(WAITING_COLOR_MARKER_END); return COLOR_CODE; }
}
<WAITING_COLOR_MARKER_END>{
  ";" {yybegin(YYINITIAL); return COLOR_MARKER_END;}
}

[^] { return BAD_CHARACTER; }
