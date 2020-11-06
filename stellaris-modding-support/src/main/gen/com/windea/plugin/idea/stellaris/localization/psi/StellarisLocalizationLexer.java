/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package com.windea.plugin.idea.stellaris.localization.psi;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.*;
import static com.windea.plugin.idea.stellaris.localization.psi.StellarisLocalizationTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>StellarisLocalizationLexer.flex</tt>
 */
public class StellarisLocalizationLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int WAITING_LOCALE_COLON = 2;
  public static final int WAITING_LOCALE_EOL = 4;
  public static final int WAITING_PROPERTY_KEY = 6;
  public static final int WAITING_PROPERTY_COLON = 8;
  public static final int WAITING_PROPERTY_NUMBER = 10;
  public static final int WAITING_PROPERTY_SPACE = 12;
  public static final int WAITING_PROPERTY_VALUE = 14;
  public static final int WAITING_PROPERTY_EOL = 16;
  public static final int WAITING_RICH_TEXT = 18;
  public static final int WAITING_PROPERTY_REFERENCE = 20;
  public static final int WAITING_PROPERTY_REFERENCE_PARAMETER = 22;
  public static final int WAITING_ICON = 24;
  public static final int WAITING_ICON_PARAMETER = 26;
  public static final int WAITING_SERIAL_NUMBER = 28;
  public static final int WAITING_CODE = 30;
  public static final int WAITING_COLOR_CODE = 32;
  public static final int WAITING_COLORFUL_TEXT = 34;
  public static final int WAITING_CHECK_ICON_START = 36;
  public static final int WAITING_CHECK_SERIAL_NUMBER_START = 38;
  public static final int WAITING_CHECK_COLORFUL_TEXT_START = 40;
  public static final int WAITING_CHECK_RIGHT_QUOTE = 42;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3,  3,  4,  4,  5,  5,  6,  6,  7,  7, 
     2,  2,  8,  8,  9,  9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 
    15, 15, 16, 16, 17, 17, 18, 18, 19, 19, 20, 20
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [9, 6, 6]
   * Total runtime size is 4256 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>12]<<6)|((ch>>6)&0x3f)]<<6)|(ch&0x3f)];
  }

  /* The ZZ_CMAP_Z table has 272 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\6\4\1\5\4\4\1\6\1\7\1\10\4\4\1\11\6\4\1\12\1\13\361\4");

  /* The ZZ_CMAP_Y table has 768 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\26\3\1\4\1\3\1\5\3\3\1\6\5\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3"+
    "\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\7\1\3\1\10\1\3\1\10\1\4\4\3\1\6"+
    "\1\10\27\3\1\11\4\3\1\4\1\10\4\3\1\12\1\3\1\10\2\3\1\13\2\3\1\10\1\5\2\3\1"+
    "\13\16\3\1\14\1\15\76\3\1\11\227\3\1\4\12\3\1\10\1\6\2\3\1\16\1\3\1\10\5\3"+
    "\1\5\114\3\1\10\25\3\1\4\56\3\1\7\1\3\1\5\1\17\2\3\1\10\3\3\1\5\5\3\1\10\1"+
    "\3\1\10\5\3\1\10\1\3\1\6\1\5\6\3\1\4\15\3\1\10\67\3\1\4\3\3\1\10\61\3\1\20"+
    "\105\3\1\10\32\3");

  /* The ZZ_CMAP_A table has 1088 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\4\1\3\2\2\1\3\22\0\1\4\1\31\1\6\1\5\1\16\1\26\1\0\1\11\5\0\1\20\1"+
    "\11\1\17\12\12\1\27\6\0\32\23\1\25\1\13\1\24\1\0\1\10\1\0\15\22\1\15\3\22"+
    "\1\15\10\22\1\0\1\30\10\0\1\2\32\0\1\1\2\0\1\21\3\0\1\14\170\0\12\7\106\0"+
    "\12\7\6\0\12\7\134\0\12\7\40\0\12\7\46\0\1\1\105\0\12\7\60\0\12\7\6\0\12\7"+
    "\46\0\13\1\35\0\2\2\5\0\1\1\57\0\1\1\60\0\12\7\26\0\12\7\74\0\12\7\16\0\62"+
    "\7");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\25\0\1\1\1\2\1\3\1\4\1\1\1\5\1\6"+
    "\1\2\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\2\16\1\5\1\17\1\1\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\25\1\27\1\30\1\31\2\32"+
    "\1\5\1\33\1\34\1\35\1\36\1\37\1\40\2\41"+
    "\1\5\1\42\1\43\2\44\1\5\1\45\1\1\1\46"+
    "\1\47\1\50\1\51\1\52\1\53\1\54\1\55\1\56"+
    "\1\0\1\57\1\60\1\61\1\53\1\54\1\55\1\56"+
    "\1\54";

  private static int [] zzUnpackAction() {
    int [] result = new int[89];
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
    "\0\0\0\32\0\64\0\116\0\150\0\202\0\234\0\266"+
    "\0\320\0\352\0\u0104\0\u011e\0\u0138\0\u0152\0\u016c\0\u0186"+
    "\0\u01a0\0\u01ba\0\u01d4\0\u01ee\0\u0208\0\u0222\0\u023c\0\u0256"+
    "\0\u0270\0\u028a\0\u028a\0\u0222\0\u02a4\0\u02be\0\u02d8\0\u02f2"+
    "\0\u0222\0\u030c\0\u0326\0\u0222\0\u0340\0\u035a\0\u035a\0\u0222"+
    "\0\u0374\0\u0222\0\u0222\0\u0222\0\u0222\0\u0222\0\u038e\0\u0222"+
    "\0\u03a8\0\u0222\0\u0222\0\u0222\0\u03c2\0\u03dc\0\u03dc\0\u03f6"+
    "\0\u0410\0\u0222\0\u0222\0\u0222\0\u0222\0\u042a\0\u0444\0\u0444"+
    "\0\u0222\0\u0222\0\u045e\0\u0478\0\u0478\0\u0222\0\u0492\0\u0222"+
    "\0\u0222\0\u0492\0\u0222\0\u0222\0\u04ac\0\u04c6\0\u04e0\0\u04fa"+
    "\0\u028a\0\u0222\0\u0222\0\u0222\0\u0222\0\u0514\0\u0222\0\u0222"+
    "\0\u0222";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[89];
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
    "\1\26\4\27\1\30\2\26\1\31\4\26\1\31\4\26"+
    "\1\31\10\26\1\32\2\33\1\32\22\26\1\34\3\26"+
    "\1\32\2\33\1\35\1\36\25\26\4\27\1\37\2\26"+
    "\3\40\2\26\1\40\2\26\1\40\1\26\2\40\7\26"+
    "\1\32\2\33\1\35\22\26\1\41\3\26\1\32\2\33"+
    "\1\42\2\26\1\43\2\26\1\43\20\26\1\32\2\33"+
    "\1\42\26\26\1\32\2\33\1\32\1\26\1\44\23\26"+
    "\1\45\1\46\1\47\1\33\1\46\1\45\1\50\4\45"+
    "\1\51\1\52\1\45\1\53\2\45\1\54\3\45\1\55"+
    "\1\56\3\45\1\26\1\32\2\33\1\57\1\26\1\60"+
    "\1\26\3\61\2\26\1\61\1\62\1\26\1\61\1\26"+
    "\2\61\1\26\1\63\2\26\1\64\1\26\1\65\1\66"+
    "\1\67\1\33\1\66\1\65\1\60\7\65\1\62\13\65"+
    "\1\26\1\32\2\33\1\70\1\26\1\60\1\26\1\71"+
    "\1\26\2\71\1\26\1\71\1\72\2\71\1\73\2\71"+
    "\1\26\1\74\2\26\1\75\1\26\1\76\1\77\1\100"+
    "\1\33\1\77\1\76\1\60\12\76\1\73\10\76\1\26"+
    "\1\32\2\33\1\70\1\26\1\60\6\26\1\101\4\26"+
    "\2\101\2\26\1\102\3\26\1\103\1\104\1\105\1\33"+
    "\1\104\1\103\1\60\15\103\1\106\1\26\4\103\1\26"+
    "\1\32\2\33\1\32\1\26\1\60\5\26\1\107\1\110"+
    "\4\26\2\110\6\26\1\45\1\46\1\47\1\33\1\46"+
    "\1\45\1\111\4\45\1\51\1\112\1\45\1\53\2\45"+
    "\1\113\3\45\1\55\1\114\3\45\21\26\1\115\36\26"+
    "\1\116\17\26\1\117\23\26\1\120\23\26\33\0\4\27"+
    "\25\0\3\30\1\0\26\30\10\0\1\31\4\0\1\31"+
    "\4\0\1\31\10\0\1\121\2\33\1\121\26\0\1\121"+
    "\2\33\1\35\25\0\3\36\1\0\2\36\1\0\23\36"+
    "\3\37\1\0\26\37\10\0\3\40\2\0\1\40\2\0"+
    "\1\40\1\0\2\40\7\0\1\121\2\33\1\42\34\0"+
    "\1\43\2\0\1\43\17\0\3\45\1\0\2\45\1\0"+
    "\4\45\2\0\1\45\1\0\2\45\1\0\3\45\2\0"+
    "\4\45\1\46\1\47\1\33\1\46\1\45\1\0\4\45"+
    "\2\0\1\45\1\0\2\45\1\0\3\45\2\0\3\45"+
    "\2\122\2\0\2\122\1\123\5\122\3\123\2\122\1\123"+
    "\3\122\2\123\3\122\1\0\1\121\2\33\1\57\3\0"+
    "\3\61\2\0\1\61\2\0\1\61\1\0\2\61\12\0"+
    "\1\61\3\0\3\61\2\0\1\61\2\0\1\61\1\0"+
    "\2\61\6\0\3\65\1\0\2\65\1\0\7\65\1\0"+
    "\14\65\1\66\1\67\1\33\1\66\1\65\1\0\7\65"+
    "\1\0\13\65\1\0\1\121\2\33\1\70\35\0\1\71"+
    "\1\0\2\71\1\0\1\71\1\0\2\71\1\0\2\71"+
    "\6\0\3\76\1\0\2\76\1\0\12\76\1\0\11\76"+
    "\1\77\1\100\1\33\1\77\1\76\1\0\12\76\1\0"+
    "\10\76\3\103\1\0\2\103\1\0\15\103\2\0\5\103"+
    "\1\104\1\105\1\33\1\104\1\103\1\0\15\103\2\0"+
    "\4\103\31\0\1\124\2\125\2\0\26\125\2\126\2\0"+
    "\26\126\2\127\2\0\26\127\3\120\1\0\2\120\1\130"+
    "\23\120\2\131\2\0\26\131";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1326];
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
    "\25\0\1\11\5\1\1\11\4\1\1\11\2\1\1\11"+
    "\3\1\1\11\1\1\5\11\1\1\1\11\1\1\3\11"+
    "\5\1\4\11\3\1\2\11\3\1\1\11\1\1\2\11"+
    "\1\1\2\11\4\1\1\0\4\11\1\1\3\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[89];
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
  int codeLocation = 0;
  int propertyReferenceLocation = 0;
  boolean inIconName = false;
  boolean isColorfulText = false;

  public int nextStateForText(){
    return depth <= 0 ? WAITING_RICH_TEXT : WAITING_COLORFUL_TEXT;
  }

  public int nextStateForCheck(){
    return isColorfulText?WAITING_COLORFUL_TEXT:WAITING_RICH_TEXT;
  }

  public int nextStateForCode(){
    if(codeLocation == 0) return nextStateForText();
    else if (codeLocation == 1) return WAITING_PROPERTY_REFERENCE;
    else if (codeLocation == 2) return WAITING_ICON;
    else return nextStateForText();
  }

  public int nextStateForPropertyReference(){
    if(propertyReferenceLocation == 0) return nextStateForText();
    else if (propertyReferenceLocation == 2) return WAITING_ICON;
    else return nextStateForText();
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public StellarisLocalizationLexer(java.io.Reader in) {
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
            { return TokenType.BAD_CHARACTER;
            } 
            // fall through
          case 50: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 51: break;
          case 3: 
            { return ROOT_COMMENT;
            } 
            // fall through
          case 52: break;
          case 4: 
            { yybegin(WAITING_LOCALE_COLON); return LOCALE_ID;
            } 
            // fall through
          case 53: break;
          case 5: 
            { yybegin(WAITING_PROPERTY_KEY); return WHITE_SPACE;
            } 
            // fall through
          case 54: break;
          case 6: 
            { yybegin(WAITING_LOCALE_EOL); return COLON;
            } 
            // fall through
          case 55: break;
          case 7: 
            { return END_OF_LINE_COMMENT;
            } 
            // fall through
          case 56: break;
          case 8: 
            { return COMMENT;
            } 
            // fall through
          case 57: break;
          case 9: 
            { yybegin(WAITING_PROPERTY_COLON); return PROPERTY_KEY_ID;
            } 
            // fall through
          case 58: break;
          case 10: 
            { yybegin(WAITING_PROPERTY_NUMBER); return COLON;
            } 
            // fall through
          case 59: break;
          case 11: 
            { yybegin(WAITING_PROPERTY_VALUE); return WHITE_SPACE;
            } 
            // fall through
          case 60: break;
          case 12: 
            { yybegin(WAITING_PROPERTY_SPACE); return NUMBER;
            } 
            // fall through
          case 61: break;
          case 13: 
            { yybegin(WAITING_RICH_TEXT); return LEFT_QUOTE;
            } 
            // fall through
          case 62: break;
          case 14: 
            { return STRING_TOKEN;
            } 
            // fall through
          case 63: break;
          case 15: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);
            } 
            // fall through
          case 64: break;
          case 16: 
            { isCOlorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_COLORFUL_TEXT_START);
            } 
            // fall through
          case 65: break;
          case 17: 
            { propertyReferenceLocation=0; yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;
            } 
            // fall through
          case 66: break;
          case 18: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_ICON_START);
            } 
            // fall through
          case 67: break;
          case 19: 
            { codeLocation=0; yybegin(WAITING_CODE); return CODE_START;
            } 
            // fall through
          case 68: break;
          case 20: 
            { isColorfulText=false; yypushback(yylength()); yybegin(WAITING_CHECK_SERIAL_NUMBER_START);
            } 
            // fall through
          case 69: break;
          case 21: 
            { return PROPERTY_REFERENCE_ID;
            } 
            // fall through
          case 70: break;
          case 22: 
            { yybegin(WAITING_PROPERTY_EOL); return RIGHT_QUOTE;
            } 
            // fall through
          case 71: break;
          case 23: 
            { yybegin(nextStateForPropertyReference()); return PROPERTY_REFERENCE_END;
            } 
            // fall through
          case 72: break;
          case 24: 
            { codeLocation=1; yybegin(WAITING_CODE); return CODE_START;
            } 
            // fall through
          case 73: break;
          case 25: 
            { yybegin(WAITING_PROPERTY_REFERENCE_PARAMETER); return PARAMETER_SEPARATOR;
            } 
            // fall through
          case 74: break;
          case 26: 
            { return PROPERTY_REFERENCE_PARAMETER;
            } 
            // fall through
          case 75: break;
          case 27: 
            { yybegin(nextStateForText()); return WHITE_SPACE;
            } 
            // fall through
          case 76: break;
          case 28: 
            { return ICON_ID;
            } 
            // fall through
          case 77: break;
          case 29: 
            { propertyReferenceLocation=2; yybegin(WAITING_PROPERTY_REFERENCE); return PROPERTY_REFERENCE_START;
            } 
            // fall through
          case 78: break;
          case 30: 
            { yybegin(nextStateForText()); return ICON_END;
            } 
            // fall through
          case 79: break;
          case 31: 
            { codeLocation=2; yybegin(WAITING_CODE); return CODE_START;
            } 
            // fall through
          case 80: break;
          case 32: 
            { yybegin(WAITING_ICON_PARAMETER); return PARAMETER_SEPARATOR;
            } 
            // fall through
          case 81: break;
          case 33: 
            { return ICON_PARAMETER;
            } 
            // fall through
          case 82: break;
          case 34: 
            { return SERIAL_NUMBER_ID;
            } 
            // fall through
          case 83: break;
          case 35: 
            { yybegin(nextStateForText()); return SERIAL_NUMBER_END;
            } 
            // fall through
          case 84: break;
          case 36: 
            { return CODE_TEXT_TOKEN;
            } 
            // fall through
          case 85: break;
          case 37: 
            { yybegin(nextStateForCode()); return CODE_END;
            } 
            // fall through
          case 86: break;
          case 38: 
            { yybegin(WAITING_COLORFUL_TEXT); return COLOR_CODE;
            } 
            // fall through
          case 87: break;
          case 39: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_RIGHT_QUOTE);
            } 
            // fall through
          case 88: break;
          case 40: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_COLORFUL_TEXT_START);
            } 
            // fall through
          case 89: break;
          case 41: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_ICON_START);
            } 
            // fall through
          case 90: break;
          case 42: 
            { isColorfulText=true; yypushback(yylength()); yybegin(WAITING_CHECK_SERIAL_NUMBER_START);
            } 
            // fall through
          case 91: break;
          case 43: 
            { //特殊处理
    //如果匹配的字符串的第2个字符存在且为不为空白，则认为代表图标的开始
    //否则认为是常规字符串
    boolean isIconStart = yylength() == 2 && !Character.isWhitespace(yycharat(1));
    yypushback(yylength()-1);
    if(isIconStart){
    	  yybegin(WAITING_ICON);
    	  return ICON_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 92: break;
          case 44: 
            { //特殊处理
    //如果匹配的字符串的第3个字符存在且为百分号，则认为整个字符串代表一个编号
    //否则认为是常规字符串
    boolean isSerialNumberStart = yylength() == 3 && yycharat(2) == '%';
    yypushback(yylength()-1);
    if(isSerialNumberStart){
        yybegin(WAITING_SERIAL_NUMBER);
        return SERIAL_NUMBER_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 93: break;
          case 45: 
            { //特殊处理
    //如果匹配的字符串的第2个字符存在且为不为空白，则认为代表彩色文本的开始
    //否则认为是常规字符串
    boolean isColorfulTextStart = yylength() == 2 && !Character.isWhitespace(yycharat(1));
    yypushback(yylength()-1);
    if(isColorfulTextStart){
        yybegin(WAITING_COLOR_CODE);
        depth++;
        return COLORFUL_TEXT_START;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 94: break;
          case 46: 
            { //特殊处理
    //如果匹配到的字符串不是仅包含双引号，且最后一个字符是双引号，则表示开始的双引号不代表字符串的结束
    //否则认为是常规字符串
    boolean isRightQuote = yylength() == 1 || yycharat(yylength()-1) != '"';
    yypushback(yylength()-1);
    if(isRightQuote){
        yybegin(WAITING_PROPERTY_EOL);
        return RIGHT_QUOTE;
    }else{
        yybegin(nextStateForCheck());
        return STRING_TOKEN;
    }
            } 
            // fall through
          case 95: break;
          case 47: 
            { return INVALID_ESCAPE_TOKEN;
            } 
            // fall through
          case 96: break;
          case 48: 
            { return VALID_ESCAPE_TOKEN;
            } 
            // fall through
          case 97: break;
          case 49: 
            { depth--; yybegin(nextStateForText()); return COLORFUL_TEXT_END;
            } 
            // fall through
          case 98: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
