import java.io.IOException;

/**
 * A file to test the j-- compiler for TCSS 421 Project's part 2.
 * 
 * @version 26 April 2016
 * @author Jonathan Hughes  
 */
public class Part2Test {
       
    public static void main(String[] args) {
        
        //do-until statement
        int x = 2;
        do {
            x = x * x; 
        } while (x < 1000); 
    }
        
}
