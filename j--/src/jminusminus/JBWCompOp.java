/**
 * Modified code from JLogicalNotOp
 * 
 * Exercise 3.23
 * 
 * @author Jonathan Hughes
 * @date 23 April 2016
 */
package jminusminus;

public class JBWCompOp extends JUnaryExpression {
    
    public JBWCompOp(int line, JExpression arg) {
        super(line, "~", arg);
    }

    public JExpression analyze(Context context) {
        this.arg = this.arg.analyze(context);
        this.arg.type().mustMatchExpected(line(), Type.INT);
        this.type = Type.INT;
        return this;
    }

    public void codegen(CLEmitter output) {
        //TODO probably need to change

    }  

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        //TODO probably need to change
    }
  
}
