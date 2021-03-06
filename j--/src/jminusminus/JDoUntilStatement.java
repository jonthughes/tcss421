// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas
/**
 * Modified by 
 * @author Jonathan Hughes
 * @date 26 April 2016
 */
package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a do-until-statement.
 */

class JDoUntilStatement extends JStatement {

    JStatement do_statement;
    JExpression until_expression;


    public JDoUntilStatement(int line, JStatement do_statement, 
            JExpression until_expression) {
        super(line);
        this.do_statement = do_statement;
        this.until_expression = until_expression;        
    }


    public JStatement analyze(Context context) {
//        condition = (JExpression) condition.analyze(context);
//        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
//        thenPart = (JStatement) thenPart.analyze(context);
//        if (elsePart != null) {
//            elsePart = (JStatement) elsePart.analyze(context);
//        }
        return this;
    }

    public void codegen(CLEmitter output) {
//        String elseLabel = output.createLabel();
//        String endLabel = output.createLabel();
//        condition.codegen(output, elseLabel, false);
//        thenPart.codegen(output);
//        if (elsePart != null) {
//            output.addBranchInstruction(GOTO, endLabel);
//        }
//        output.addLabel(elseLabel);
//        if (elsePart != null) {
//            elsePart.codegen(output);
//            output.addLabel(endLabel);
//        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
//        p.printf("<JIfStatement line=\"%d\">\n", line());
//        p.indentRight();
//        p.printf("<TestExpression>\n");
//        p.indentRight();
//        condition.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</TestExpression>\n");
//        p.printf("<ThenClause>\n");
//        p.indentRight();
//        thenPart.writeToStdOut(p);
//        p.indentLeft();
//        p.printf("</ThenClause>\n");
//        if (elsePart != null) {
//            p.printf("<ElseClause>\n");
//            p.indentRight();
//            elsePart.writeToStdOut(p);
//            p.indentLeft();
//            p.printf("</ElseClause>\n");
//        }
//        p.indentLeft();
//        p.printf("</JIfStatement>\n");
    }

}
