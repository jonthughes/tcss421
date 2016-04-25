/**
 * A file to test the j-- compiler for TCSS 421 Project's part 2.
 * 
 * @version 20 April 2016
 * @author Jonathan Hughes  
 */
public class Part2Test {
       
    public static void main(String[] args) {
        //int literal for comparison
        int i = 1234;
        i = 293;
        i = 0;
        i = 33424;
        
        /**********************************************************************
         * testing Exercise 3.21 Modify the Parser to parse and return nodes  *
         *                       for the double literal and the float literal.*
         **********************************************************************/
        //double literal
        double d = 1e1; 
        d = 2.;
        d = .3;
        d = 0.0;
        d = 3.14; 
        d = 1e-9d; 
        d = 1e-9D;
        d = 1e137;
        d = 1239487274.23746;       
      
        //float literal
        float f = 1e1f;
        f = 2.F;
        f = .3f;
        f = 0F;
        f = 3.14f; 
        f = 6.022137e+23f;
        
        /**********************************************************************
         * testing Exercise 3.22 Modify the Parser to parse and return nodes  *
         *                       for the long literal.                        *
         **********************************************************************/
        //long literal
        long l = 1230l;
        l = 3872987L;
        
        /**********************************************************************
         * testing Exercise 3.23 Modify the Parser to parse and return nodes  *
         *                       for all the additional operators that are    *
         *                       defined in Java but not yet in j--           *
         **********************************************************************/
        //Operators (38)
        /*  PLUS("+"), ASSIGN("="), DEC("--"), EQUAL("=="), GT(">"), INC("++"), LAND("&&"), 
            LE("<="), LT("<"), LNOT("!"), MINUS("-"), PLUS_ASSIGN("+="), STAR("*"), QM("?"),
            BWCOMP("~"), NOT_EQUAL("!="), DIV("/"), DIV_ASSIGN("/="), MINUS_ASSIGN("-="),
            STAR_ASSIGN("*="), MOD("%"), MOD_ASSIGN("%="), RSHIFT(">>"), COLON(":"),
            RSHIFT_ASSIGN(">>="), RSHIFT_ZF(">>>"), RSHIFT_ZF_ASSIGN(">>>="), GE(">="),
            LSHIFT("<<"), LSHIFT_ASSIGN("<<="), BWXOR("^"), BWXOR_ASSIGN("^="), BWOR("|"),
            BWOR_ASSIGN("|="), LOR("||"), BWAND("&"), BWAND_ASSIGN("&="), LAMBDA("->"), */
        
        //ASSIGN("=")
        int x = 123;
        int y = 321;
        
        //PLUS("+")
        i = x + y;
        
        //DEC("--")
        i--;
        --i;
        
        //EQUAL("==")
        boolean b = (x==y);
        
        //GT(">")
        b = x > y;
        
        //INC("++")
        i++;
        ++i;
        
        //LAND("&&")
        boolean b2 = false;
        boolean b3 = b && b2;
        
        //LE("<=")
        b = x <= y;
        
        //LT("<")
        b = x < y;
        
        //LNOT("!") 
        b = !b2;
        
        //MINUS("-") 
        i = x - y;
        
        //PLUS_ASSIGN("+=")
        i += x;
        
        //STAR("*") 
        i = x * y;
        
        //BWCOMP("~") 
        i = ~x;
        
        //NOT_EQUAL("!=") 
        b = x != y;
        
        //DIV("/") 
        i = x / y;
        
        //DIV_ASSIGN("/=") 
        i /= x;
        
        //MINUS_ASSIGN("-=")
        i -= x;
        
        //STAR_ASSIGN("*=") 
        i *= x;
        
        //MOD("%") 
        i = x % y;
        
        //MOD_ASSIGN("%=")
        i %= x;
        
        //RSHIFT(">>")
        i = x >> 2;
         
        //RSHIFT_ASSIGN(">>=")
        i >>= 2;
        
        //RSHIFT_ZF(">>>")
        i = x >>> 2;
        
        //RSHIFT_ZF_ASSIGN(">>>=")
        i >>>= 2;
        
        //GE(">=")
        b = x >= y;
        
        //LSHIFT("<<")
        i = x << 2;
        
        //LSHIFT_ASSIGN("<<=")
        i <<= 2;
        
        //BWXOR("^")
        i = x ^ y;
        
        //BWXOR_ASSIGN("^=")
        i ^= x;
        
        //BWOR("|")
        i = x | y;
        
        //BWOR_ASSIGN("|=")
        i |= x;
        
        //LOR("||") 
        b = b2 || b3;
        
        //BWAND("&")
        i = x & y;
        
        //BWAND_ASSIGN("&=") 
        i &= x;
 
//        //LAMBDA("->")
//        Runnable r = () -> System.out.println("Hello world");
        
        /**********************************************************************
         * testing Exercise 3.24 Modify the Parser to parse and return nodes  *
         *                       for conditional expressions, for example,    *
         *                       (a > b) ? a : b.                             *
         **********************************************************************/
        //QM("?")
        i = b ? (i + x) : (i + y);
        
        //COLON(":")
        i = b ? (i + x) : (i + y);
        
        /**********************************************************************
         * testing Exercise 3.25 Modify the Parser to parse and return nodes  *
         *                       for the for-statement, including both the    *
         *                       basic for-statement and the enhanced         *
         *                       for-statement.                               *
         **********************************************************************/
        //Basic for-statement
        for (int j = 0; j < 10; j++) {
            i += j;
        }
        
        //Re-use variable
        for (; j < 10; j++) {
            i += j;
        }
        
        //Enhanced for-statement
        int[] ia = { 1, 2, 3, (int) '4' };
        for (int k : ia) {
            i += k;
        }
                
        /**********************************************************************
         * testing Exercise 3.26 Modify the Parser to parse and return nodes  *
         *                       for the switch-statement.                    *
         **********************************************************************/
        //switch statement
        switch (x) {
            case 0:
                x = 0;
                break;
            default:
                x = 1;
        }
        
        /**********************************************************************
         * testing Exercise 3.27 Modify the Parser to parse and return nodes  *
         *                       for the try-catch-finally statement.         *
         **********************************************************************/
        
        /**********************************************************************
         * testing Exercise 3.28 Modify the Parser to parse and return nodes  *
         *                       for the throw-statement.                     *
         **********************************************************************/
        
        /**********************************************************************
         * testing Exercise 3.29 Modify the Parser to deal with a             *
         *                       throws-clause in method declarations.        *
         **********************************************************************/
        
        /**********************************************************************
         * Bonus
         * testing Exercise 3.30 Modify the Parser to deal with methods and   *
         *                       constructors having variable arity, that is, * 
         *                       a variable number of arguments.              *
         **********************************************************************/
        
        /**********************************************************************
         * Bonus
         * testing Exercise 3.34 Modify the Parser to parse and return nodes  *
         *                       for the do-until statement.                  *
         * Say we wish to add a do-until statement to j--. For example,       *
         *       do {                                                         *
         *       x = x * x;                                                   *
         *       } until (x > 1000);                                          *
         *   Write a grammar rule for defining the context-free syntax for a  *
         *   new do-until statement. Modify the Scanner to deal with any      *
         *   necessary new token(s).                                          *
         **********************************************************************/
        
    }    
}
