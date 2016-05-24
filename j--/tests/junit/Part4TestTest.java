// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import java.io.IOException;

import junit.framework.TestCase;
//import pass.Part4Test;

public class Part4TestTest extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    /**********************************************************************
     * Exercise 5.6. Add the do-while statement to j--, adding it to your *
     * compiler and testing it thoroughly.                                *
     **********************************************************************/
    public void testDoUntilStatement() {
        //this is 2^16 or 2^2^2^2^2 = 65536
        int x = 2;
        do {
            x = x * x; 
        } while (x < 1000); 
        this.assertEquals(65536, x);
        
        //should still run loop once 1+1=2, even though y > 0
        int y = 1;
        do {
            y += 1;
        } while (y < 0);
        this.assertEquals(2, y);
    }

    /**********************************************************************
     * Exercise 5.7. Add the classic for-statement to j--, adding it to   *
     * your compiler and testing it thoroughly.                           *
     **********************************************************************/
    public void testClassicForStatement() {
        //declares variable in for loop
        int x = 0;
        for (int i = 0; i < 100; i++) {
            x += 1;
        }
        this.assertEquals(100, x);
        
        //declares variable outside for loop
        int y = 0;
        int j = 0;
        for (j = 0; j < 100; j++) {
            y += 1;
        }       
        this.assertEquals(100, y);
    }
    
    /**********************************************************************
     * Exercise 5.11. Add conditional expressions to j--, adding them to  *
     * your compiler and testing them thoroughly. Conditional expressions *
     * are compiled in a manner identical to if-else statements. The only * 
     * difference is that in this case, both the consequent and the       *
     * alternative are expressions.                                       *
     **********************************************************************/
    public void testConditionalExpression() {
        int y = 1;
        int x = 0;
        //conditional expression
        x = (x > y) ? 1 : 2;
        this.assertEquals(2, x);
    }
    
    /**********************************************************************
     * Exercise 5.12. Add the conditional-or operator || to j--, adding it* 
     * to your compiler and testing it thoroughly. Make sure to avoid     *
     * unnecessary branches to branches. The conditional ||, like the     *
     * conditional &&, is short-circuited.                                *
     **********************************************************************/
    public void testConditionalOrOperator() {
        int y = 1;
        int x = 0;
        
        //conditional or operator ||
        boolean b = (x > y) || (x < y);
        this.assertEquals(true, b);
    }    
    
    /**********************************************************************
     * (Bonus) Exercise 5.15. Add the throw-statement to j--, adding it to*
     * your compiler and testing it thoroughly. The throw-statement is    *
     * straightforwardly compiled to JVM code using the athrow instruction*                           *
     **********************************************************************/
    public void testThrowStatement() {
        boolean b = true;
        int x = 1;
        boolean thrown = false;
        //throw statement
        if (b) {
            try {
                throw new IOException();
            } catch (IOException e) {
                thrown = true; //only happens if caught
            } finally {
                x += x;
            }
        }
        //only true if exception thrown and caught
        this.assertTrue(thrown); 
    }
    
    
    /**********************************************************************
     * (Bonus) Exercise 5.21. Add the primitive type long to j--, adding  *
     * it to your compiler and testing it thoroughly.                     *
     **********************************************************************/
    public void testLong() {
        //long literal
        long x = 2147483648l; 
        long y = 2147483648L;
        long z = x + y;
        this.assertEquals(4294967296l, z);
    }
}
