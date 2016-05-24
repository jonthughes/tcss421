// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package junit;

import junit.framework.TestCase;
import pass.Part4Test;

public class Part4TestTest extends TestCase {

    public void testDoUntil() {
        this.assertEquals(Part4Test.doUntil(), 65536);
    }

}
