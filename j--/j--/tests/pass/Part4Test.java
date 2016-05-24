package pass;

import java.io.IOException;
import java.lang.System;

/**
 * A file to test the j-- compiler for TCSS 421 Project's part 4.
 * 
 * @version 17 May 2016
 * @author Jonathan Hughes  
 */
public class Part4Test {
       
    public static void main(String[] args) {
        
        /**********************************************************************
         * Exercise 5.6. Add the do-while statement to j--, adding it to your *
         * compiler and testing it thoroughly.                                *
         **********************************************************************/
        //do-while statement
        int x = 2;
        do {
            x = x * x; 
        } while (x < 1000); 
               
        /**********************************************************************
         * Exercise 5.7. Add the classic for-statement to j--, adding it to   *
         * your compiler and testing it thoroughly.                           *
         **********************************************************************/
        //for statement
        for (int i = 0; i < 100; i++) {
            x += 1;
        }
            
        /**********************************************************************
         * Exercise 5.11. Add conditional expressions to j--, adding them to  *
         * your compiler and testing them thoroughly. Conditional expressions *
         * are compiled in a manner identical to if-else statements. The only * 
         * difference is that in this case, both the consequent and the       *
         * alternative are expressions.                                       *
         **********************************************************************/
        int y = 1;
        //conditional expression
        x = (x > y) ? 1 : 2;
        
        /**********************************************************************
         * Exercise 5.12. Add the conditional-or operator || to j--, adding it* 
         * to your compiler and testing it thoroughly. Make sure to avoid     *
         * unnecessary branches to branches. The conditional ||, like the     *
         * conditional &&, is short-circuited.                                *
         **********************************************************************/
        //conditional or operator ||
        boolean b = (x > y) || (x < y);
        
        /**********************************************************************
         * (Bonus) Exercise 5.15. Add the throw-statement to j--, adding it to*
         * your compiler and testing it thoroughly. The throw-statement is    *
         * straightforwardly compiled to JVM code using the athrow instruction*                           *
         **********************************************************************/
        //throw statement
        if (b) {
            try {
                throw new IOException();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                x += x;
            }
        }
        
        /**********************************************************************
         * (Bonus) Exercise 5.21. Add the primitive type long to j--, adding  *
         * it to your compiler and testing it thoroughly.                     *
         **********************************************************************/
        //long literal
        long l = 1230l;
        l = 3872987L;
//        long m = l*2L;
    }
        
}
