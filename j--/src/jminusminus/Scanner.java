// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Hashtable;
import static jminusminus.TokenKind.*;

/**
 * A lexical analyzer for j--, that has no backtracking mechanism.
 * 
 * When you add a new token to the scanner, you must also add an entry in the
 * TokenKind enum in TokenInfo.java specifying the kind and image of the new
 * token.
 * 
 * @version 05 April 2016
 * @author Modified by: Jonathan Hughes 
 * @author Original: Bill Campbell, Swami Iyer and Bahar Akbal-Delibas
 */

class Scanner {

    /** End of file character. */
    public final static char EOFCH = CharReader.EOFCH;

    /** Keywords in j--. */
    private Hashtable<String, TokenKind> reserved;

    /** Source characters. */
    private CharReader input;

    /** Next unscanned character. */
    private char ch;

    /** Whether a scanner error has been found. */
    private boolean isInError;

    /** Source file name. */
    private String fileName;

    /** Line number of current token. */
    private int line;

    /**
     * Construct a Scanner object.
     * 
     * @param fileName
     *            the name of the file containing the source.
     * @exception FileNotFoundException
     *                when the named file cannot be found.
     */

    public Scanner(String fileName) throws FileNotFoundException {
        this.input = new CharReader(fileName);
        this.fileName = fileName;
        isInError = false;

        // Keywords in j--
        reserved = new Hashtable<String, TokenKind>();
        reserved.put(ABSTRACT.image(), ABSTRACT);
        reserved.put(ASSERT.image(), ASSERT); //added Exercise 2.12
        reserved.put(BOOLEAN.image(), BOOLEAN);
        reserved.put(BREAK.image(), BREAK); //added Exercise 2.12
        reserved.put(BYTE.image(), BYTE); //added Exercise 2.12
        reserved.put(CASE.image(), CASE); //added Exercise 2.12
        reserved.put(CATCH.image(), CATCH); //added Exercise 2.12
        reserved.put(CHAR.image(), CHAR); 
        reserved.put(CLASS.image(), CLASS);
        reserved.put(CONST.image(), CONST); //added Exercise 2.12
        reserved.put(CONTINUE.image(), CONTINUE); //added Exercise 2.12
        reserved.put(DEFAULT.image(), DEFAULT); //added Exercise 2.12
        reserved.put(DO.image(), DO); //added Exercise 2.12
        reserved.put(DOUBLE.image(), DOUBLE); //added Exercise 2.12
        reserved.put(ELSE.image(), ELSE);
        reserved.put(ENUM.image(), ENUM); //added Exercise 2.12
        reserved.put(EXTENDS.image(), EXTENDS);
        reserved.put(FALSE.image(), FALSE);
        reserved.put(FINAL.image(), FINAL); //added Exercise 2.12
        reserved.put(FINALLY.image(), FINALLY); //added Exercise 2.12
        reserved.put(FLOAT.image(), FLOAT); //added Exercise 2.12
        reserved.put(FOR.image(), FOR); //added Exercise 2.12
        reserved.put(GOTO.image(), GOTO); //added Exercise 2.12
        reserved.put(IF.image(), IF);
        reserved.put(IMPLEMENTS.image(), IMPLEMENTS); //added Exercise 2.12
        reserved.put(IMPORT.image(), IMPORT);
        reserved.put(INSTANCEOF.image(), INSTANCEOF);
        reserved.put(INT.image(), INT);
        reserved.put(INTERFACE.image(), INTERFACE); //added Exercise 2.12
        reserved.put(LONG.image(), LONG); //added Exercise 2.12
        reserved.put(NATIVE.image(), NATIVE); //added Exercise 2.12
        reserved.put(NEW.image(), NEW);
        reserved.put(NULL.image(), NULL);
        reserved.put(PACKAGE.image(), PACKAGE);
        reserved.put(PRIVATE.image(), PRIVATE);
        reserved.put(PROTECTED.image(), PROTECTED);
        reserved.put(PUBLIC.image(), PUBLIC);
        reserved.put(RETURN.image(), RETURN);
        reserved.put(SHORT.image(), SHORT); //added Exercise 2.12
        reserved.put(STATIC.image(), STATIC);
        reserved.put(STRICTFP.image(), STRICTFP); //added Exercise 2.12
        reserved.put(SUPER.image(), SUPER);
        reserved.put(SWITCH.image(), SWITCH); //added Exercise 2.12
        reserved.put(SYNCHRONIZED.image(), SYNCHRONIZED); //added Exercise 2.12
        reserved.put(THIS.image(), THIS);
        reserved.put(THROW.image(), THROW); //added Exercise 2.12
        reserved.put(THROWS.image(), THROWS); //added Exercise 2.12
        reserved.put(TRANSIENT.image(), TRANSIENT); //added Exercise 2.12
        reserved.put(TRUE.image(), TRUE);
        reserved.put(TRY.image(), TRY); //added Exercise 2.12
        reserved.put(VOID.image(), VOID);
        reserved.put(VOLATILE.image(), VOLATILE); //added Exercise 2.12
        reserved.put(WHILE.image(), WHILE);
        reserved.put(UNTIL.image(), UNTIL); //added Bonus Exercise 3.34
        // Prime the pump.
        nextCh();
    }

    /**
     * Scan the next token from input.
     * 
     * @return the the next scanned token.
     */

    public TokenInfo getNextToken() {
        StringBuffer buffer;
        boolean moreWhiteSpace = true;
        while (moreWhiteSpace) {
            while (isWhitespace(ch)) {
                nextCh();
            }
            if (ch == '/') {
                nextCh();
                if (ch == '/') {
                    // CharReader maps all new lines to '\n'
                    while (ch != '\n' && ch != EOFCH) {
                        nextCh();
                    }
                }                 
                else if (ch == '*'){ // Ignore multi-line comment Exercise 2.10
                    char prevCh = ch;
                    nextCh();
                    while (!(ch == '/' && prevCh == '*')) {
                        if (ch == EOFCH) {
                            reportScannerError("Unexpected end of line found in comment.");
                            break;
                        }
                        prevCh = ch;
                        nextCh();
                    }  
                    nextCh();
                } else if (ch == '='){ //added Exercise 2.11
                    nextCh();
                    return new TokenInfo (DIV_ASSIGN , line );                   
                } else { //added Exercise 2.11
                    return new TokenInfo (DIV , line );
                }
            } else {
                moreWhiteSpace = false;
            }
        }
        line = input.line();
        switch (ch) {
        case '(':
            nextCh();
            return new TokenInfo(LPAREN, line);
        case ')':
            nextCh();
            return new TokenInfo(RPAREN, line);
        case '{':
            nextCh();
            return new TokenInfo(LCURLY, line);
        case '}':
            nextCh();
            return new TokenInfo(RCURLY, line);
        case '[':
            nextCh();
            return new TokenInfo(LBRACK, line);
        case ']':
            nextCh();
            return new TokenInfo(RBRACK, line);
        case ';':
            nextCh();
            return new TokenInfo(SEMI, line);
        case ',':
            nextCh();
            return new TokenInfo(COMMA, line);
        case '=':
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(EQUAL, line);
            } else {
                return new TokenInfo(ASSIGN, line);
            }
        case '!':
            nextCh();
            if (ch == '=') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(NOT_EQUAL, line);
            } else {
                return new TokenInfo(LNOT, line);
            }         
        case '*':
            nextCh();
            if (ch == '=') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(STAR_ASSIGN, line);
            } else {              
                return new TokenInfo(STAR, line);
            }
        case '+':
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(PLUS_ASSIGN, line);
            } else if (ch == '+') {
                nextCh();
                return new TokenInfo(INC, line);
            } else {
                return new TokenInfo(PLUS, line);
            }
        case '-':
            nextCh();
            if (ch == '-') {
                nextCh();
                return new TokenInfo(DEC, line);
            } else if (ch == '=') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(MINUS_ASSIGN, line);
            } else if (ch == '>') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(LAMBDA, line);
            } else {
                return new TokenInfo(MINUS, line);
            }
        case '&':
            nextCh();
            if (ch == '&') {
                nextCh();
                return new TokenInfo(LAND, line);
            } else if (ch == '=') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(BWAND_ASSIGN, line);
            } else { //added Exercise 2.11
                return new TokenInfo(BWAND, line);
            }
        case '>':
            nextCh();
            if (ch == '>') { //added Exercise 2.11
                nextCh();
                if (ch == '>') { //added Exercise 2.11
                    nextCh();
                    if (ch == '=') { //added Exercise 2.11
                        nextCh();
                        return new TokenInfo(RSHIFT_ZF_ASSIGN, line);
                    } else {
                        return new TokenInfo(RSHIFT_ZF, line);
                    }
                } else if (ch == '=') { //added Exercise 2.11
                    nextCh();
                    return new TokenInfo(RSHIFT_ASSIGN, line);
                } else { //added Exercise 2.11
                    return new TokenInfo(RSHIFT, line);
                }
            } else if (ch == '=') { //added Exercise 2.11
                nextCh();
                return new TokenInfo(GE, line);
            } else {
                return new TokenInfo(GT, line);
            }
        case '<':
            nextCh();
            if (ch == '<') { //added Exercise 2.11
                nextCh();
                if (ch == '=') { //added Exercise 2.11
                    nextCh();
                    return new TokenInfo(LSHIFT_ASSIGN, line);
                } else { //added Exercise 2.11
                    return new TokenInfo(LSHIFT, line);
                }
            } else if (ch == '=') {
                nextCh();
                return new TokenInfo(LE, line);
            } else { //added Exercise 2.11
                return new TokenInfo(LT, line);
            }
        case '?': //added Exercise 2.11
            nextCh();
            return new TokenInfo(QM, line);
        case '~': //added Exercise 2.11
            nextCh();
            return new TokenInfo(BWCOMP, line);
        case ':': //added Exercise 2.11
            nextCh();
            return new TokenInfo(COLON, line);
        case '%': //added Exercise 2.11
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(MOD_ASSIGN, line);
            } else {
                return new TokenInfo(MOD, line);
            }
        case '^': //added Exercise 2.11
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(BWXOR_ASSIGN, line);
            } else {
                return new TokenInfo(BWXOR, line);
            }
        case '|': //added Exercise 2.11
            nextCh();
            if (ch == '=') {
                nextCh();
                return new TokenInfo(BWOR_ASSIGN, line);
            } else if (ch == '|') {
                nextCh();
                return new TokenInfo(LOR, line);
            } else {
                return new TokenInfo(BWOR, line);
            }
        case '\'':
            buffer = new StringBuffer();
            buffer.append('\'');
            nextCh();
            if (ch == '\\') {
                nextCh();
                buffer.append(escape());
            } else {
                buffer.append(ch);
                nextCh();
            }
            if (ch == '\'') {
                buffer.append('\'');
                nextCh();
                return new TokenInfo(CHAR_LITERAL, buffer.toString(), line);
            } else {
                // Expected a ' ; report error and try to
                // recover.
                reportScannerError(ch
                        + " found by scanner where closing ' was expected.");
                while (ch != '\'' && ch != ';' && ch != '\n') {
                    nextCh();
                }
                return new TokenInfo(CHAR_LITERAL, buffer.toString(), line);
            }
        case '"':
            buffer = new StringBuffer();
            buffer.append("\"");
            nextCh();
            while (ch != '"' && ch != '\n' && ch != EOFCH) {
                if (ch == '\\') {
                    nextCh();
                    buffer.append(escape());
                } else {
                    buffer.append(ch);
                    nextCh();
                }
            }
            if (ch == '\n') {
                reportScannerError("Unexpected end of line found in String");
            } else if (ch == EOFCH) {
                reportScannerError("Unexpected end of file found in String");
            } else {
                // Scan the closing "
                nextCh();
                buffer.append("\"");
            }
            return new TokenInfo(STRING_LITERAL, buffer.toString(), line);
        case '.':
            buffer = new StringBuffer();
            buffer.append(ch);
            nextCh();
            if (isDigit(ch)) { //float .{0 -9} [(e|E) [+| -] (0 -9) {0 -9}] [f|F]
                buffer.append(ch);
                nextCh();
                boolean atLeastOne = true;
                while (isDigit(ch)) {
                    buffer.append(ch);
                    nextCh();
                }
                if (ch == 'e' || ch == 'E') {
                    buffer.append(ch);
                    nextCh();
                    if (ch == '+' || ch == '-') {
                        buffer.append(ch);
                        nextCh();
                    }
                    atLeastOne = false;
                    while (isDigit(ch)) {
                        buffer.append(ch);
                        nextCh();
                        atLeastOne = true;
                    }
                }
                if (atLeastOne) {
                    if (ch == 'f' || ch == 'F') {
                        buffer.append(ch);
                        nextCh();
                        return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                    } else {
                        if (ch == 'd' || ch == 'D') {
                            buffer.append(ch);
                            nextCh();
                        }
                        return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                    }
                } else {
                    reportScannerError("Invalid Float or Double Literal.");
                }
            } else if (ch == '.') {
                nextCh();
                if (ch == '.') {
                    nextCh();
                    return new TokenInfo(TRIPLEDOT, line);
                } else {
                    reportScannerError("Two dots are invalid.  Should be 1 or 3.");
                }             
            } else {
                return new TokenInfo(DOT, line);
            }
        case EOFCH:
            return new TokenInfo(EOF, line);
        case '0':
            boolean atLeastOne = false; //to ensure at least 1 digit
            buffer = new StringBuffer();
            buffer.append(ch);
            nextCh();
            if (ch == 'b' || ch == 'B') { //binary int 0 (b|B) (0-1){0-1}
                buffer.append(ch);
                nextCh();
                while (ch == '0' || ch == '1') {
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                } 
                if (ch >= '2' && ch <= '9' || isIdentifierStart(ch)) {
                    reportScannerError("Invalid Binary Literal.");
                } else if (atLeastOne) {
                    return new TokenInfo(BINARY_INT_LITERAL, buffer.toString(), line);
                } else {
                    reportScannerError("Invalid Binary Literal.");
                }
            } else if (ch == 'x' || ch == 'X') { //hex int 
                          //0 (x|X) ((0 -9)|(A-F )|(a-f)) {(0 -9)|(A-F )|(a-f)}
                buffer.append(ch);
                nextCh();
                while (isDigit(ch) || (ch >= 'a' && ch <= 'f') 
                        || (ch >= 'A' && ch <= 'F')) {
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                }
                if ((ch >= 'g' && ch <= 'z') || (ch >= 'G' && ch <= 'Z')) {
                    reportScannerError("Invalid Hex Literal.");
                } else if (atLeastOne) {
                    return new TokenInfo(HEX_INT_LITERAL, buffer.toString(), line);
                } else {
                    reportScannerError("Invalid Hex Literal.");
                }
            } else if (ch >= '0' && ch <= '7') { //octal int 0(0-7){0-7}
                buffer.append(ch);
                nextCh();
                while (ch >= '0' && ch <= '7') {
                    buffer.append(ch);
                    nextCh();
                }
                return new TokenInfo(OCTAL_INT_LITERAL, buffer.toString(), line);
            } else if (ch >= '8' && ch <= '9') { 
                reportScannerError("Invalid Octal Literal.");
            } else if (ch == 'f' || ch == 'F') { //float
                buffer.append(ch);
                nextCh();
                return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
            } else if (ch == 'd' || ch == 'D') { //double
                buffer.append(ch);
                nextCh();
                return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
            } else if (ch == 'e' || ch == 'E') { //float
                buffer.append(ch);
                nextCh();
                atLeastOne = false; //to ensure at least 1 digit
                boolean isDouble = true; //if double instead of float
                if (ch == '+' || ch == '-') {
                    buffer.append(ch);
                    nextCh();
                }
                while (isDigit(ch)) {
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                }
                if (ch == 'f' || ch == 'F') {
                    buffer.append(ch);
                    nextCh();
                    isDouble = false;
                } else if (ch == 'd' || ch == 'D') { //check if double                    
                    buffer.append(ch);
                    nextCh();
                }
                if (atLeastOne) {
                    if (isDouble) {
                        return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                    } else {
                        return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                    }
                } else {
                    reportScannerError("Invalid Float Literal.");
                }
            } else if (ch == '.') { //float (0-9){0-9}.{0-9}[(e|E)[+|-](0-9){0-9}][f|F]
                buffer.append(ch);
                nextCh();
                if (isDigit(ch)) { //float .{0 -9} [(e|E) [+| -] (0 -9) {0 -9}] [f|F]
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                    while (isDigit(ch)) {
                        buffer.append(ch);
                        nextCh();
                    }
                    if (ch == 'e' || ch == 'E') {
                        buffer.append(ch);
                        nextCh();
                        if (ch == '+' || ch == '-') {
                            buffer.append(ch);
                            nextCh();
                        }
                        atLeastOne = false;
                        while (isDigit(ch)) {
                            buffer.append(ch);
                            nextCh();
                            atLeastOne = true;
                        }
                    }
                    if (atLeastOne) {
                        if (ch == 'f' || ch == 'F') {
                            buffer.append(ch);
                            nextCh();
                            return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                        } else {
                            if (ch == 'd' || ch == 'D') {
                                buffer.append(ch);
                                nextCh();
                            }
                            return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                        }
                    } else {
                        reportScannerError("Invalid Float or Double Literal.");
                    }
                } else {
                    return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                }
            } else {
                return new TokenInfo(INT_LITERAL, "0", line);
            }
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9': 
            buffer = new StringBuffer();
            while (isDigit(ch)) {
                buffer.append(ch);
                nextCh();
            }
            if (ch == 'l' || ch == 'L') { //long int
                buffer.append(ch);
                nextCh();
                return new TokenInfo(LONG_LITERAL, buffer.toString(), line);
            } else if (ch == 'f' || ch == 'F') { //float
                buffer.append(ch);
                nextCh();
                return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
            } else if (ch == 'd' || ch == 'D') { //double
                buffer.append(ch);
                nextCh();
                return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
            } else if (ch == 'e' || ch == 'E') { //float
                buffer.append(ch);
                nextCh();
                atLeastOne = false; //to ensure at least 1 digit
                boolean isDouble = true; //if double instead of float
                if (ch == '+' || ch == '-') {
                    buffer.append(ch);
                    nextCh();
                }
                while (isDigit(ch)) {
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                }
                if (ch == 'f' || ch == 'F') {
                    buffer.append(ch);
                    nextCh();
                    isDouble = false;
                } else if (ch == 'd' || ch == 'D') { //check if double                    
                    buffer.append(ch);
                    nextCh();
                }
                if (atLeastOne) {
                    if (isDouble) {
                        return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                    } else {
                        return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                    }
                } else {
                    reportScannerError("Invalid Float Literal.");
                }
            } else if (ch == '.') { 
                buffer.append(ch);
                nextCh();
                if (isDigit(ch)) { //float .{0 -9} [(e|E) [+| -] (0 -9) {0 -9}] [f|F]
                    buffer.append(ch);
                    nextCh();
                    atLeastOne = true;
                    while (isDigit(ch)) {
                        buffer.append(ch);
                        nextCh();
                    }
                    if (ch == 'e' || ch == 'E') {
                        buffer.append(ch);
                        nextCh();
                        if (ch == '+' || ch == '-') {
                            buffer.append(ch);
                            nextCh();
                        }
                        atLeastOne = false;
                        while (isDigit(ch)) {
                            buffer.append(ch);
                            nextCh();
                            atLeastOne = true;
                        }
                    }
                    if (atLeastOne) {
                        if (ch == 'f' || ch == 'F') {
                            buffer.append(ch);
                            nextCh();
                            return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                        } else {
                            if (ch == 'd' || ch == 'D') {
                                buffer.append(ch);
                                nextCh();
                            }
                            return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                        }
                    } else {
                        reportScannerError("Invalid Float or Double Literal.");
                    }
                } else {
                    if (ch == 'f' || ch == 'F') {
                        buffer.append(ch);
                        nextCh();
                        return new TokenInfo(FLOAT_LITERAL, buffer.toString(), line);
                    } else {
                        if (ch == 'd' || ch == 'D') {
                            buffer.append(ch);
                            nextCh();
                        }
                        return new TokenInfo(DOUBLE_LITERAL, buffer.toString(), line);
                    }
                }
            } else { //regular int
                return new TokenInfo(INT_LITERAL, buffer.toString(), line);
            }
        default:
            if (isIdentifierStart(ch)) {
                buffer = new StringBuffer();
                while (isIdentifierPart(ch)) {
                    buffer.append(ch);
                    nextCh();
                }
                String identifier = buffer.toString();
                if (reserved.containsKey(identifier)) { //added 2.14 boolean literal
//                    if (identifier.equals("true") || identifier.equals("false")) {
//                        return new TokenInfo(BOOLEAN_LITERAL, identifier, line);
//                    } else if (identifier.equals("null")){
//                        return new TokenInfo(NULL_LITERAL, identifier, line);
//                    } else {
//                        return new TokenInfo(reserved.get(identifier), line);
//                    }
                    return new TokenInfo(reserved.get(identifier), line);
                } else {
                    return new TokenInfo(IDENTIFIER, identifier, line);
                }
            } else {
                reportScannerError("Unidentified input token: '%c'", ch);
                nextCh();
                return getNextToken();
            }
        }
    }

    /**
     * Scan and return an escaped character.
     * 
     * @return escaped character.
     */

    private String escape() {
        switch (ch) {
        case 'b':
            nextCh();
            return "\\b";
        case 't':
            nextCh();
            return "\\t";
        case 'n':
            nextCh();
            return "\\n";
        case 'f':
            nextCh();
            return "\\f";
        case 'r':
            nextCh();
            return "\\r";
        case '"':
            nextCh();
            return "\"";
        case '\'':
            nextCh();
            return "\\'";
        case '\\':
            nextCh();
            return "\\\\";
        default:
            reportScannerError("Badly formed escape: \\%c", ch);
            nextCh();
            return "";
        }
    }

    /**
     * Advance ch to the next character from input, and update the line number.
     */

    private void nextCh() {
        line = input.line();
        try {
            ch = input.nextChar();
        } catch (Exception e) {
            reportScannerError("Unable to read characters from input");
        }
    }

    /**
     * Report a lexcial error and record the fact that an error has occured.
     * This fact can be ascertained from the Scanner by sending it an
     * errorHasOccurred() message.
     * 
     * @param message
     *            message identifying the error.
     * @param args
     *            related values.
     */

    private void reportScannerError(String message, Object... args) {
        isInError = true;
        System.err.printf("%s:%d: ", fileName, line);
        System.err.printf(message, args);
        System.err.println();
    }

    /**
     * Return true if the specified character is a digit (0-9); false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    /**
     * Return true if the specified character is a whitespace; false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isWhitespace(char c) {
        switch (c) {
        case ' ':
        case '\t':
        case '\n': // CharReader maps all new lines to '\n'
        case '\f':
            return true;
        }
        return false;
    }

    /**
     * Return true if the specified character can start an identifier name;
     * false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isIdentifierStart(char c) {
        return (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_' || c == '$');
    }

    /**
     * Return true if the specified character can be part of an identifier name;
     * false otherwise.
     * 
     * @param c
     *            character.
     * @return true or false.
     */

    private boolean isIdentifierPart(char c) {
        return (isIdentifierStart(c) || isDigit(c));
    }

    /**
     * Has an error occurred up to now in lexical analysis?
     * 
     * @return true or false.
     */

    public boolean errorHasOccurred() {
        return isInError;
    }

    /**
     * The name of the source file.
     * 
     * @return name of the source file.
     */

    public String fileName() {
        return fileName;
    }

}

/**
 * A buffered character reader. Abstracts out differences between platforms,
 * mapping all new lines to '\n'. Also, keeps track of line numbers where the
 * first line is numbered 1.
 */

class CharReader {

    /** A representation of the end of file as a character. */
    public final static char EOFCH = (char) -1;

    /** The underlying reader records line numbers. */
    private LineNumberReader lineNumberReader;

    /** Name of the file that is being read. */
    private String fileName;

    /**
     * Construct a CharReader from a file name.
     * 
     * @param fileName
     *            the name of the input file.
     * @exception FileNotFoundException
     *                if the file is not found.
     */

    public CharReader(String fileName) throws FileNotFoundException {
        lineNumberReader = new LineNumberReader(new FileReader(fileName));
        this.fileName = fileName;
    }

    /**
     * Scan the next character.
     * 
     * @return the character scanned.
     * @exception IOException
     *                if an I/O error occurs.
     */

    public char nextChar() throws IOException {
        return (char) lineNumberReader.read();
    }

    /**
     * The current line number in the source file, starting at 1.
     * 
     * @return the current line number.
     */

    public int line() {
        // LineNumberReader counts lines from 0.
        return lineNumberReader.getLineNumber() + 1;
    }

    /**
     * Return the file name.
     * 
     * @return the file name.
     */

    public String fileName() {
        return fileName;
    }

    /**
     * Close the file.
     * 
     * @exception IOException
     *                if an I/O error occurs.
     */

    public void close() throws IOException {
        lineNumberReader.close();
    }

}
