package com.windea.plugin.idea.sbtext;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static com.windea.plugin.idea.sbtext.psi.SbTextTypes.*;

%%

%{
  public _SbTextLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class _SbTextLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R
WHITE_SPACE=\s+

COLOR_CODE=[\w#]+
VALID_ESCAPE_TOKEN=\\['\"rnt]
INVALID_ESCAPE_TOKEN=\\.
TEXT_TOKEN=[^\^\\]+

%%
<YYINITIAL> {
  {WHITE_SPACE}               { return WHITE_SPACE; }

  "^"                         { return COLOR_MARKER_START; }
  "reset"                     { return COLOR_RESET; }
  ";"                         { return COLOR_MARKER_END; }

  {COLOR_CODE}                { return COLOR_CODE; }
  {VALID_ESCAPE_TOKEN}        { return VALID_ESCAPE_TOKEN; }
  {INVALID_ESCAPE_TOKEN}      { return INVALID_ESCAPE_TOKEN; }
  {TEXT_TOKEN}                { return TEXT_TOKEN; }

}

[^] { return BAD_CHARACTER; }
