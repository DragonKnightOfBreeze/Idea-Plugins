/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package com.windea.plugin.idea.stellaris.script.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.script.psi.StellarisScriptTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>StellarisScriptLexer.flex</tt>
 */
public class StellarisScriptLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int WAITING_VARIABLE_EQUAL_SIGN = 2;
  public static final int WAITING_VARIABLE_VALUE = 4;
  public static final int WAITING_VARIABLE_EOL = 6;
  public static final int WAITING_PROPERTY = 8;
  public static final int WAITING_PROPERTY_KEY = 10;
  public static final int WATIING_PROPERTY_SEPARATOR = 12;
  public static final int WAITING_PROPERTY_VALUE = 14;
  public static final int WAITING_PROPERTY_EOL = 16;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7, 
     8, 8
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [7, 7, 7]
   * Total runtime size is 1928 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>14]|((ch>>7)&0x7f)]<<7)|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 68 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\103\200");

  /* The ZZ_CMAP_Y table has 256 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\53\2\1\3\22\2\1\4\37\2\1\3\237\2");

  /* The ZZ_CMAP_A table has 640 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\4\1\3\2\2\1\3\22\0\1\4\1\0\1\11\1\5\4\0\1\12\4\0\1\21\1\23\1\0\12"+
    "\22\1\10\1\0\1\37\1\36\1\35\1\0\1\6\32\7\1\24\1\13\1\24\1\0\1\7\1\0\1\30\1"+
    "\27\2\7\1\15\1\7\1\26\1\31\3\7\1\32\1\7\1\17\1\20\2\7\1\25\1\16\2\7\1\32\2"+
    "\7\1\14\1\7\1\33\1\0\1\34\7\0\1\2\32\0\1\1\337\0\1\1\177\0\13\1\35\0\2\2\5"+
    "\0\1\1\57\0\1\1\40\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\11\0\1\1\1\2\1\3\1\4\1\1\1\4\3\1"+
    "\1\5\1\4\2\1\1\6\1\7\1\2\1\10\1\11"+
    "\1\12\1\1\1\4\3\1\1\13\1\4\1\10\1\2"+
    "\1\14\1\4\1\2\1\15\1\16\1\17\1\20\1\4"+
    "\1\15\1\2\1\4\1\1\1\5\2\1\2\21\1\4"+
    "\3\21\1\22\2\21\1\23\1\0\2\24\1\0\1\25"+
    "\1\0\1\1\1\26\3\1\1\0\1\25\1\0\1\1"+
    "\1\26\1\1\2\0\1\27\1\0\1\30\1\31\1\0"+
    "\1\32\3\1\1\24\1\0\1\33\1\0\1\21\1\34"+
    "\3\21\1\5\2\1\1\13\1\5\2\1\1\22\2\21"+
    "\3\0\1\35";

  private static int [] zzUnpackAction() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\40\0\100\0\140\0\200\0\240\0\300\0\340"+
    "\0\u0100\0\u0120\0\u0140\0\u0160\0\u0180\0\u01a0\0\u01c0\0\u01e0"+
    "\0\u0200\0\u0220\0\u0240\0\u0260\0\u0280\0\u02a0\0\u0260\0\u0260"+
    "\0\u02c0\0\u02c0\0\u02e0\0\u0260\0\u0300\0\u0320\0\u0340\0\u0360"+
    "\0\u0380\0\u03a0\0\u03c0\0\u03c0\0\u03e0\0\u0400\0\u0420\0\u0440"+
    "\0\u0440\0\u0460\0\u0260\0\u0480\0\u04a0\0\u04a0\0\u04c0\0\u04e0"+
    "\0\u0500\0\u0520\0\u0540\0\u0560\0\u0580\0\u05a0\0\u05c0\0\u05e0"+
    "\0\u0600\0\u0620\0\u0640\0\u0660\0\u0680\0\u0180\0\u06a0\0\u0120"+
    "\0\u0260\0\u01c0\0\u06a0\0\u06c0\0\u06e0\0\u01a0\0\u0700\0\u0720"+
    "\0\u0740\0\u0320\0\u0260\0\u0760\0\u0780\0\u0120\0\u07a0\0\u03c0"+
    "\0\u0420\0\u0260\0\u07c0\0\u0260\0\u0260\0\u04a0\0\u04e0\0\u07e0"+
    "\0\u0800\0\u0820\0\u0580\0\u05c0\0\u06a0\0\u0840\0\u0860\0\u05a0"+
    "\0\u0880\0\u08a0\0\u08c0\0\u0700\0\u08e0\0\u0900\0\u07a0\0\u07e0"+
    "\0\u0920\0\u0940\0\u0880\0\u0960\0\u0980\0\u09a0\0\u09c0\0\u09e0"+
    "\0\u09c0";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\12\4\13\1\14\1\15\2\16\1\17\2\12\1\20"+
    "\2\16\1\21\1\16\1\22\1\23\1\16\1\24\1\25"+
    "\3\16\1\26\1\16\1\27\1\30\1\12\1\24\1\12"+
    "\1\24\1\31\2\32\1\31\1\33\25\24\1\27\1\30"+
    "\1\24\1\34\1\24\1\12\1\31\2\32\1\31\1\35"+
    "\1\24\2\12\1\36\2\12\1\37\2\12\1\40\1\12"+
    "\1\41\1\42\1\12\1\24\6\12\1\27\1\30\1\12"+
    "\1\24\1\12\1\24\1\43\2\44\1\45\1\33\25\24"+
    "\1\27\1\30\4\24\4\13\1\14\1\24\2\46\1\47"+
    "\2\24\10\46\1\24\6\46\1\27\1\30\3\24\1\12"+
    "\4\13\1\14\1\24\2\16\1\17\2\12\1\20\2\16"+
    "\1\21\1\16\1\22\1\23\1\16\1\24\1\25\3\16"+
    "\1\26\1\16\1\27\1\30\1\12\1\24\1\12\1\24"+
    "\1\50\2\51\1\50\1\33\25\24\1\27\1\30\1\52"+
    "\1\53\1\54\1\12\1\55\2\56\1\57\1\33\1\60"+
    "\2\12\1\36\2\12\1\37\2\12\1\40\1\12\1\61"+
    "\1\62\1\12\1\24\1\63\3\12\1\64\1\12\1\27"+
    "\1\30\1\12\1\24\1\12\1\65\1\55\2\56\1\57"+
    "\1\33\1\24\2\66\1\67\2\65\1\70\2\66\1\71"+
    "\1\66\1\72\1\73\1\66\1\24\1\74\3\66\1\75"+
    "\1\66\1\27\1\30\1\65\1\24\1\65\1\12\4\0"+
    "\4\12\1\0\12\12\1\0\6\12\2\0\1\12\1\0"+
    "\1\12\1\0\4\13\33\0\3\14\1\0\34\14\7\0"+
    "\1\76\4\0\7\76\2\0\6\76\5\0\1\12\3\0"+
    "\1\77\2\12\2\16\1\0\2\12\10\16\1\0\6\16"+
    "\2\0\1\100\1\101\1\100\3\102\1\0\5\102\1\103"+
    "\1\0\1\104\24\102\1\12\3\0\1\77\2\12\2\16"+
    "\1\0\2\12\1\16\1\105\6\16\1\0\6\16\2\0"+
    "\1\100\1\101\1\100\1\12\3\0\1\77\2\12\2\16"+
    "\1\0\2\12\4\16\1\106\3\16\1\0\6\16\2\0"+
    "\1\100\1\101\1\100\1\12\3\0\1\77\2\12\2\16"+
    "\1\0\2\12\6\16\1\23\1\16\1\0\6\16\2\0"+
    "\1\100\1\101\1\100\1\12\3\0\1\77\2\12\2\16"+
    "\1\0\2\12\6\16\1\23\1\107\1\0\6\16\2\0"+
    "\1\100\1\101\1\100\40\0\1\12\3\0\1\77\2\12"+
    "\2\16\1\0\2\12\10\16\1\0\1\16\1\110\4\16"+
    "\2\0\1\100\1\101\1\100\1\12\3\0\1\77\2\12"+
    "\2\16\1\0\2\12\2\16\1\111\5\16\1\0\6\16"+
    "\2\0\1\100\1\101\1\100\1\0\1\31\2\32\1\31"+
    "\33\0\3\33\1\0\34\33\1\35\2\33\1\0\1\33"+
    "\4\35\1\33\12\35\1\33\6\35\2\33\1\35\1\33"+
    "\1\35\3\112\1\0\5\112\1\113\1\0\1\114\24\112"+
    "\1\12\4\0\4\12\1\0\3\12\1\115\6\12\1\0"+
    "\6\12\2\0\1\12\1\0\2\12\4\0\4\12\1\0"+
    "\6\12\1\116\3\12\1\0\6\12\2\0\1\12\1\0"+
    "\2\12\4\0\4\12\1\0\10\12\1\42\1\12\1\0"+
    "\6\12\2\0\1\12\1\0\2\12\4\0\4\12\1\0"+
    "\10\12\1\42\1\117\1\0\6\12\2\0\1\12\1\0"+
    "\1\12\1\0\1\120\2\44\1\120\34\0\1\120\2\44"+
    "\1\45\42\0\2\46\3\0\10\46\1\0\6\46\5\0"+
    "\3\121\1\0\5\121\1\122\1\0\1\123\24\121\1\0"+
    "\1\50\2\51\1\50\71\0\1\124\37\0\1\125\2\0"+
    "\1\126\2\56\1\126\34\0\1\126\2\56\1\57\42\0"+
    "\1\127\4\0\7\127\2\0\6\127\5\0\1\12\4\0"+
    "\4\12\1\0\10\12\1\62\1\12\1\0\6\12\2\0"+
    "\1\12\1\0\2\12\4\0\4\12\1\0\10\12\1\62"+
    "\1\130\1\0\6\12\2\0\1\12\1\0\2\12\4\0"+
    "\4\12\1\0\12\12\1\0\1\12\1\131\4\12\2\0"+
    "\1\12\1\0\2\12\4\0\4\12\1\0\4\12\1\132"+
    "\5\12\1\0\6\12\2\0\1\12\1\0\1\12\1\65"+
    "\4\0\4\65\1\0\12\65\1\0\6\65\2\0\1\65"+
    "\1\0\2\65\3\0\1\77\2\65\2\66\1\0\2\65"+
    "\10\66\1\0\6\66\2\0\1\133\1\101\1\133\3\134"+
    "\1\0\5\134\1\135\1\0\1\136\24\134\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\1\66\1\137\6\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\4\66\1\140\3\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\6\66\1\73\1\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\6\66\1\73\1\141"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\10\66\1\0\1\66"+
    "\1\142\4\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\2\66\1\143\5\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\4\0\1\77"+
    "\30\0\3\101\2\102\2\0\34\102\1\12\3\0\1\77"+
    "\2\12\2\16\1\0\2\12\2\16\1\106\5\16\1\0"+
    "\6\16\2\0\1\100\1\101\1\100\1\12\3\0\1\77"+
    "\2\12\2\16\1\0\2\12\6\16\1\144\1\16\1\0"+
    "\6\16\2\0\1\100\1\101\1\100\1\12\3\0\1\77"+
    "\2\12\2\16\1\0\2\12\10\16\1\0\2\16\1\145"+
    "\3\16\2\0\1\100\1\101\1\100\1\12\3\0\1\77"+
    "\2\12\2\16\1\0\2\12\10\16\1\0\2\16\1\146"+
    "\2\16\1\146\2\0\1\100\1\101\1\100\2\112\2\0"+
    "\34\112\1\12\4\0\4\12\1\0\4\12\1\116\5\12"+
    "\1\0\6\12\2\0\1\12\1\0\2\12\4\0\4\12"+
    "\1\0\10\12\1\147\1\12\1\0\6\12\2\0\1\12"+
    "\1\0\1\12\2\121\2\0\34\121\1\12\4\0\4\12"+
    "\1\0\10\12\1\150\1\12\1\0\6\12\2\0\1\12"+
    "\1\0\2\12\4\0\4\12\1\0\12\12\1\0\2\12"+
    "\1\151\3\12\2\0\1\12\1\0\2\12\4\0\4\12"+
    "\1\0\12\12\1\0\2\12\1\152\2\12\1\152\2\0"+
    "\1\12\1\0\1\12\2\134\2\0\34\134\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\2\66\1\140\5\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\6\66\1\153\1\66"+
    "\1\0\6\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\10\66\1\0\2\66"+
    "\1\154\3\66\2\0\1\133\1\101\1\133\1\65\3\0"+
    "\1\77\2\65\2\66\1\0\2\65\10\66\1\0\2\66"+
    "\1\155\2\66\1\155\2\0\1\133\1\101\1\133\1\12"+
    "\3\0\1\156\2\12\2\16\1\0\2\12\10\16\1\0"+
    "\3\16\1\146\2\16\1\157\1\0\1\100\1\101\1\100"+
    "\1\12\3\0\1\156\2\12\2\16\1\0\2\12\10\16"+
    "\1\0\6\16\1\157\1\0\1\100\1\101\1\100\1\12"+
    "\3\0\1\160\4\12\1\0\12\12\1\0\3\12\1\152"+
    "\2\12\1\157\1\0\1\12\1\0\2\12\3\0\1\160"+
    "\4\12\1\0\12\12\1\0\6\12\1\157\1\0\1\12"+
    "\1\0\1\12\1\65\3\0\1\156\2\65\2\66\1\0"+
    "\2\65\10\66\1\0\3\66\1\155\2\66\1\157\1\0"+
    "\1\133\1\101\1\133\1\65\3\0\1\156\2\65\2\66"+
    "\1\0\2\65\10\66\1\0\6\66\1\157\1\0\1\133"+
    "\1\101\1\133\4\0\1\156\26\0\1\157\1\0\3\101"+
    "\3\157\1\0\30\157\1\161\3\157\4\0\1\160\26\0"+
    "\1\157\4\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2560];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\11\0\12\1\1\11\2\1\2\11\3\1\1\11\16\1"+
    "\1\11\23\1\1\0\1\1\1\11\1\0\1\1\1\0"+
    "\5\1\1\0\1\11\1\0\3\1\2\0\1\11\1\0"+
    "\2\11\1\0\5\1\1\0\1\1\1\0\17\1\3\0"+
    "\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[113];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  int depth = 0;

  public int nextState(){
  	if(depth <= 0)
  		return YYINITIAL;
  	else
  		return WAITING_PROPERTY_KEY;
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public StellarisScriptLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { yybegin(WAITING_PROPERTY_EOL); return STRING_TOKEN;
            } 
            // fall through
          case 30: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 31: break;
          case 3: 
            { return COMMENT;
            } 
            // fall through
          case 32: break;
          case 4: 
            { return BAD_CHARACTER;
            } 
            // fall through
          case 33: break;
          case 5: 
            { yybegin(WAITING_PROPERTY_EOL); return NUMBER_TOKEN;
            } 
            // fall through
          case 34: break;
          case 6: 
            { depth++;  yybegin(nextState()); return LEFT_BRACE;
            } 
            // fall through
          case 35: break;
          case 7: 
            { depth--;  yybegin(nextState()); return RIGHT_BRACE;
            } 
            // fall through
          case 36: break;
          case 8: 
            { yybegin(YYINITIAL); return WHITE_SPACE;
            } 
            // fall through
          case 37: break;
          case 9: 
            { return END_OF_LINE_COMMENT;
            } 
            // fall through
          case 38: break;
          case 10: 
            { yybegin(WAITING_VARIABLE_VALUE); return EQUAL_SIGN;
            } 
            // fall through
          case 39: break;
          case 11: 
            { yybegin(WAITING_VARIABLE_EOL); return NUMBER_TOKEN;
            } 
            // fall through
          case 40: break;
          case 12: 
            { yybegin(WATIING_PROPERTY_SEPARATOR); return PROPERTY_KEY_ID;
            } 
            // fall through
          case 41: break;
          case 13: 
            { yybegin(nextState()); return WHITE_SPACE;
            } 
            // fall through
          case 42: break;
          case 14: 
            { yybegin(WAITING_PROPERTY_VALUE); return GT_SIGN;
            } 
            // fall through
          case 43: break;
          case 15: 
            { yybegin(WAITING_PROPERTY_VALUE); return EQUAL_SIGN;
            } 
            // fall through
          case 44: break;
          case 16: 
            { yybegin(WAITING_PROPERTY_VALUE); return LT_SIGN;
            } 
            // fall through
          case 45: break;
          case 17: 
            { return STRING_TOKEN;
            } 
            // fall through
          case 46: break;
          case 18: 
            { return NUMBER_TOKEN;
            } 
            // fall through
          case 47: break;
          case 19: 
            { yybegin(WAITING_VARIABLE_EQUAL_SIGN); return VARIABLE_NAME_ID;
            } 
            // fall through
          case 48: break;
          case 20: 
            { yypushback(yylength()); yybegin(WAITING_PROPERTY);
            } 
            // fall through
          case 49: break;
          case 21: 
            { yybegin(WAITING_PROPERTY_EOL); return QUOTED_STRING_TOKEN;
            } 
            // fall through
          case 50: break;
          case 22: 
            { yybegin(WAITING_PROPERTY_EOL); return BOOLEAN_TOKEN;
            } 
            // fall through
          case 51: break;
          case 23: 
            { yybegin(WATIING_PROPERTY_SEPARATOR); return QUOTED_PROPERTY_KEY_ID;
            } 
            // fall through
          case 52: break;
          case 24: 
            { yybegin(WAITING_PROPERTY_VALUE); return GE_SIGN;
            } 
            // fall through
          case 53: break;
          case 25: 
            { yybegin(WAITING_PROPERTY_VALUE); return LE_SIGN;
            } 
            // fall through
          case 54: break;
          case 26: 
            { yybegin(WAITING_PROPERTY_EOL); return VARIABLE_REFERENCE_ID;
            } 
            // fall through
          case 55: break;
          case 27: 
            { return QUOTED_STRING_TOKEN;
            } 
            // fall through
          case 56: break;
          case 28: 
            { return BOOLEAN_TOKEN;
            } 
            // fall through
          case 57: break;
          case 29: 
            { yybegin(WAITING_PROPERTY_EOL); return COLOR_TOKEN;
            } 
            // fall through
          case 58: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
