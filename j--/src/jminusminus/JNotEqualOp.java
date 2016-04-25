package jminusminus;

/**
 * Modified code from JGreaterThanOp
 * 
 * Exercise 3.23
 * 
 * @author Jonathan Hughes
 * @date 23 April 2016
 */
public class JNotEqualOp extends JComparison {
    
    public JNotEqualOp(int line, JExpression lhs, JExpression rhs) {
        super(line, "!=", lhs, rhs);
    }

    public void codegen(CLEmitter output, String targetLabel, boolean onTrue) {
        this.lhs.codegen(output);
        this.rhs.codegen(output);
        output.addBranchInstruction(onTrue ? 163 : 164, targetLabel);
        /** @TODO not sure if this needs more */ 
    }
}