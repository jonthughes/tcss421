package fail;

import java.lang.System;


/**
 * A file to test the j-- compiler for TCSS 421 Project's part 3.
 * 
 * @version 6 June 2016
 * @author Jonathan Hughes  
 */
public class Part3Test {
    
    public void test() {
        x = 2;
        x = 2;
        x = 2;
    }  
}

//cannot have two public classes
public class ShouldntWork {
    
}
