// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a conditional expression.
 * Modified JIfStatement
 * @author Jonathan Hughes
 * @date 23 April 16
 */
//Exercise 3.24
public class JConditionalExpression extends JExpression {

    private JExpression condition;
    private JExpression consequent;
    private JExpression alternate;
    
    public JConditionalExpression(int line, JExpression condition, JExpression consequent, JExpression alternate) {
        super(line);
        this.condition = condition;
        this.consequent = consequent;
        this.alternate = alternate;
    }

    public JExpression analyze(Context context) {
        condition = (JExpression) condition.analyze(context);
        consequent = (JExpression) consequent.analyze(context);
        alternate = (JExpression) alternate.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        consequent.type().mustMatchExpected(line(),alternate.type());
        type = consequent.type();
        return this;
    }

    @Override
    public void codegen(CLEmitter output) {
        //TODO this stuff
    }


    /**
     * @inheritDoc
     */
    public void writeToStdOut(PrettyPrinter p) {
        
    }

}
