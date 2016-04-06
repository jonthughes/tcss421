package pass;

import java.lang.System;

public class Part1Test {
    /**
     * This is a javadoc multi line comment
     * @return "Hello, World!"
     */
    public static String message() {
        return "Hello, World!";
    }

    /*
     * This is a normal multi line comment
     */
    public static void main(String[] args) {
        //test comment
        
        int i = 99;
        int j = 33;
        boolean b = true;
        j = i--;
        b = i == 4;
        b = i > 7;
//        i++;
        b = b && b;
        b = i <= 5;
//        b = null;
//        b = i < 9;
//        b = i != 4;
//        j = i / 8;
//        i /= 2;
//        i -= 2;
        i += 9;
//        i *= 2;
//        i % 10;
//        i %= 5;
//        i >> 1;
//        i >>= 1;
//        i >>> 1;
//        i >>>= 1;
//        i >= 5;
//        i << 2;
//        i <<= 2;
//        i ^ i;
//        i ^= i;
//        i | i;
//        i |= i;
//        TRUE || FALSE;
//        i & i;
//        i &= i;
        
        System.out.println(Part1Test.message());
    }

    /** Trying on one line */
}
