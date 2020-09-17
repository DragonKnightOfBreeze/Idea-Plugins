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

%state WAITING_COLOR_MARKER
%state WAITING_COLOR_MARKER_END

COLOR_CODE=[\w#]+
VALID_ESCAPE_TOKEN=\\['\"rnt]
INVALID_ESCAPE_TOKEN=\\.
TEXT_TOKEN=[^\^\\]+

%%

<YYINITIAL> {
  "^reset;"                   {return COLOR_RESET_MARKER_TOKEN;}
  "^"                         { yybegin(WAITING_COLOR_MARKER); return COLOR_MARKER_START; }
  {VALID_ESCAPE_TOKEN}        { return VALID_ESCAPE_TOKEN; }
  {INVALID_ESCAPE_TOKEN}      { return INVALID_ESCAPE_TOKEN; }
  {TEXT_TOKEN}                { return TEXT_TOKEN; }
}
<WAITING_COLOR_MARKER>{
  {COLOR_CODE}                { yybegin(WAITING_COLOR_MARKER_END); return COLOR_CODE; }
}
<WAITING_COLOR_MARKER_END>{
  ";" {yybegin(YYINITIAL); return COLOR_MARKER_END;}
}

[^] { return BAD_CHARACTER; }
