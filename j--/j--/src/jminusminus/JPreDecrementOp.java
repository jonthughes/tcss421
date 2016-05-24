/**
 * Modified code from JPreIncrementThanOp
 * 
 * Exercise 3.23
 * 
 * @author Jonathan Hughes
 * @date 23 April 2016
 */
package jminusminus;

public class JPreDecrementOp
  extends JUnaryExpression
{
  public JPreDecrementOp(int line, JExpression arg)
  {
    super(line, "--pre", arg);
  }
  
  public JExpression analyze(Context context)
  {
    if (!(this.arg instanceof JLhs))
    {
      JAST.compilationUnit.reportSemanticError(this.line, "Operand to --xpr must have an LValue.", new Object[0]);
      
      this.type = Type.ANY;
    }
    else
    {
      this.arg = this.arg.analyze(context);
      this.arg.type().mustMatchExpected(line(), Type.INT);
      this.type = Type.INT;
    }
    return this;
  }
  
  //TODO fix this
  public void codegen(CLEmitter output)
  {
    if ((this.arg instanceof JVariable))
    {
      int offset = ((LocalVariableDefn)((JVariable)this.arg).iDefn()).offset();
      output.addIINCInstruction(offset, 1);
      if (!this.isStatementExpression) {
        this.arg.codegen(output);
      }
    }
    else
    {
      ((JLhs)this.arg).codegenLoadLhsLvalue(output);
      ((JLhs)this.arg).codegenLoadLhsRvalue(output);
      output.addNoArgInstruction(4);
      output.addNoArgInstruction(96);
      if (!this.isStatementExpression) {
        ((JLhs)this.arg).codegenDuplicateRvalue(output);
      }
      ((JLhs)this.arg).codegenStore(output);
    }
  }
}
