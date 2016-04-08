// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

/**
 * An enum of token kinds. Each entry in this enum represents the kind of a
 * token along with its image (string representation).
 * 
 * When you add a new token to the scanner, you must also add an entry to this
 * enum specifying the kind and image of the new token.
 * 
 * @version 05 April 2016
 * @author Modified by: Jonathan Hughes 
 * @author Original: Bill Campbell, Swami Iyer and Bahar Akbal-Delibas
 */

enum TokenKind {
    EOF("<EOF>"), 
            //Keywords (50 + 3)
            ABSTRACT("abstract"), ASSERT("assert"), BOOLEAN("boolean"), BREAK("break"), 
            BYTE("byte"), CASE("case"), CATCH("catch"), CHAR("char"), CLASS("class"), 
            CONST("const"), CONTINUE("continue"), DEFAULT("default"), DO("do"), DOUBLE("double"),
            ELSE("else"), ENUM("enum"), EXTENDS("extends"), FINAL("final"), 
            FINALLY("finally"), FLOAT("float"), FOR("for"), GOTO("goto"), IF("if"), 
            IMPLEMENTS("implements"), IMPORT("import"), INSTANCEOF("instanceof"), INT("int"), 
            INTERFACE("interface"), LONG("long"), NATIVE("native"), NEW("new"),  
            PACKAGE("package"), PRIVATE("private"), PROTECTED("protected"), PUBLIC("public"), 
            RETURN("return"), SHORT("short"), STATIC("static"), STRICTFP("strictfp"), 
            SUPER("super"), SWITCH("switch"), SYNCHRONIZED("synchronized"), THIS("this"), 
            THROW("throw"), THROWS("throws"), TRANSIENT("transient"),  
            TRY("try"), VOID("void"), VOLATILE("volatile"), WHILE("while"), 
            //TRUE, FALSE, and NULL - are actually boolean/null literals
            TRUE("true"), FALSE("false"), NULL("null"),
            
            //Operators (38)
            PLUS("+"), ASSIGN("="), DEC("--"), EQUAL("=="), GT(">"), INC("++"), LAND("&&"), 
            LE("<="), LT("<"), LNOT("!"), MINUS("-"), PLUS_ASSIGN("+="), STAR("*"), QM("?"),
            BWCOMP("~"), NOT_EQUAL("!="), DIV("/"), DIV_ASSIGN("/="), MINUS_ASSIGN("-="),
            STAR_ASSIGN("*="), MOD("%"), MOD_ASSIGN("%="), RSHIFT(">>"), COLON(":"),
            RSHIFT_ASSIGN(">>="), RSHIFT_ZF(">>>"), RSHIFT_ZF_ASSIGN(">>>="), GE(">="),
            LSHIFT("<<"), LSHIFT_ASSIGN("<<="), BWXOR("^"), BWXOR_ASSIGN("^="), BWOR("|"),
            BWOR_ASSIGN("|="), LOR("||"), BWAND("&"), BWAND_ASSIGN("&="), LAMBDA("->"),
            
            //Separators (9)
            LPAREN("("), RPAREN(")"), LCURLY("{"), RCURLY("}"), LBRACK("["), RBRACK("]"), 
            SEMI(";"), COMMA(","), DOT("."), 
            
            //Literals (11)
            IDENTIFIER("<IDENTIFIER>"), INT_LITERAL("<INT_LITERAL>"), 
            CHAR_LITERAL("<CHAR_LITERAL>"), STRING_LITERAL("<STRING_LITERAL>"),
            DOUBLE_LITERAL("<DOUBLE_LITERAL>"), FLOAT_LITERAL("<FLOAT_LITERAL>"),
            LONG_LITERAL("<LONG_LITERAL>"), HEX_INT_LITERAL("<HEX_INT_LITERAL>"), 
            OCTAL_INT_LITERAL("<OCTAL_INT_LITERAL>"), NULL_LITERAL("<NULL_LITERAL>"),
            BINARY_INT_LITERAL("<BINARY_INT_LITERAL>"), BOOLEAN_LITERAL("<BOOLEAN_LITERAL>");

    /** The token's string representation. */
    private String image;

    /**
     * Construct an instance TokenKind given its string representation.
     * 
     * @param image
     *            string representation of the token.
     */

    private TokenKind(String image) {
        this.image = image;
    }

    /**
     * Return the image of the token.
     * 
     * @return the token's image.
     */

    public String image() {
        return image;
    }

    /**
     * Return the string representation of the token.
     * 
     * @return the token's string representation.
     */

    public String toString() {
        return image;
    }

}

/**
 * A representation of tokens returned by the lexical analyzer method,
 * getNextToken(). A token has a kind identifying what kind of token it is, an
 * image for providing any semantic text, and the line in which it occurred in
 * the source file.
 */

class TokenInfo {

    /** Token kind. */
    private TokenKind kind;

    /**
     * Semantic text (if any). For example, the identifier name when the token
     * kind is IDENTIFIER. For tokens without a semantic text, it is simply its
     * string representation. For example, "+=" when the token kind is
     * PLUS_ASSIGN.
     */
    private String image;

    /** Line in which the token occurs in the source file. */
    private int line;

    /**
     * Construct a TokenInfo from its kind, the semantic text forming the token,
     * and its line number.
     * 
     * @param kind
     *            the token's kind.
     * @param image
     *            the semantic text comprising the token.
     * @param line
     *            the line in which the token occurs in the source file.
     */

    public TokenInfo(TokenKind kind, String image, int line) {
        this.kind = kind;
        this.image = image;
        this.line = line;
    }

    /**
     * Construct a TokenInfo from its kind, and its line number. Its image is
     * simply its string representation.
     * 
     * @param kind
     *            the token's identifying number.
     * @param line
     *            identifying the line on which the token was found.
     */

    public TokenInfo(TokenKind kind, int line) {
        this(kind, kind.toString(), line);
    }

    /**
     * Return the token's string representation.
     * 
     * @return the string representation.
     */

    public String tokenRep() {
        return kind.toString();
    }

    /**
     * Return the semantic text associated with the token.
     * 
     * @return the semantic text.
     */

    public String image() {
        return image;
    }

    /**
     * Return the line number associated with the token.
     * 
     * @return the line number.
     */

    public int line() {
        return line;
    }

    /**
     * Return the token's kind.
     * 
     * @return the kind.
     */

    public TokenKind kind() {
        return kind;
    }

    /**
     * Return the token's image.
     * 
     * @return the image.
     */

    public String toString() {
        return image;
    }

}
