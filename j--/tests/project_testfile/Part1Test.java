package pass;

public class Part1Test {
       
    public static void main(String[] args) {
        /**********************************************************************
         * testing Exercise 2.10 scan (and ignore) Java multi-line comments.  *
         **********************************************************************/
        /* 
         * This is a normal multi line comment
         */
        /**
         * This is a javadoc multi line comment
         */
        /** Trying on one line */
        
        
        /**********************************************************************
         * testing Exercise 2.11 recognize and return all Java                *
         *                      operators that are not reserved words.        *
         **********************************************************************/
        = 
        > 
        < 
        ! 
        ~ 
        ? 
        : 
        ->
        == 
        >= 
        <= 
        != 
        && 
        || 
        ++ 
        --
        + 
        - 
        * 
        / 
        & 
        | 
        ^ 
        % 
        << 
        >> 
        >>>
        += 
        -= 
        *= 
        /= 
        &= 
        |= 
        ^= 
        %= 
        <<= 
        >>= 
        >>>=               
        
        
        /**********************************************************************
         * testing Exercise 2.12 recognize and return all Java reserved words.*
         **********************************************************************/
        abstract 
        continue 
        for 
        new 
        switch
        assert 
        default 
        if 
        package 
        synchronized
        boolean 
        do 
        goto 
        private 
        this
        break 
        double 
        implements 
        protected 
        throw
        byte 
        else 
        import 
        public 
        throws
        case 
        enum 
        instanceof 
        return 
        transient
        catch 
        extends 
        int 
        short 
        try
        char 
        final 
        interface 
        static 
        void
        class 
        finally 
        long 
        strictfp 
        volatile
        const 
        float 
        native 
        super 
        while
        //null, true, false - actually null/boolean literals         
        
        
        /**********************************************************************
         * testing Exercise 2.13 recognize and return Java double-precision   *
         *                      literals (returned as DOUBLE_LITERAL).        *
         **********************************************************************/
        //double literal
        1e1 
        2. 
        .3 
        0.0 
        3.14 
        1e-9d 
        1e137
        1239487274.23746
        
        
        /**********************************************************************
         * testing Exercise 2.14 recognize and return all other literals in   *
         *                 Java for example, FLOAT_LITERAL, LONG_LITERAL, etc.*
         **********************************************************************/
        //long literal
        12309830l
        3872987L 
        
        //float literal
        1e1f 
        2.f 
        .3f 
        0f 
        3.14f 
        6.022137e+23f
        
        //boolean literals          
        true 
        false 
        
        //null literal
        null
        
        /**********************************************************************
         * testing Bonus Exercise 2.15 recognize and return all other         *
         *             representations of integers (hexadecimal, octal, etc.).*
         **********************************************************************/
        //binary int
        0b10101011
        0B01111011110
        
        //octal int
        021453645
        01737323433
        
        //hex int
        0xA454E
        0XABCDEF0123456789
    }    
}
