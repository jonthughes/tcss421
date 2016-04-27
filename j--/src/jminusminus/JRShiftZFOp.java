package jminusminus;
/**
 * Modified by 
 * @author Jonathan Hughes
 * @date 26 April 2016
 */
public class JRShiftZFOp
  extends JBinaryExpression
{
  public JRShiftZFOp(int line, JExpression lhs, JExpression rhs)
  {
    super(line, ">>>", lhs, rhs);
  }
  
  public JExpression analyze(Context context)
  {
    this.lhs = this.lhs.analyze(context);
    this.rhs = this.rhs.analyze(context);
    if ((this.lhs.type() == Type.STRING) || (this.rhs.type() == Type.STRING)) {
      return new JStringConcatenationOp(this.line, this.lhs, this.rhs).analyze(context);
    }
    if ((this.lhs.type() == Type.INT) && (this.rhs.type() == Type.INT))
    {
      this.type = Type.INT;
    }
    else
    {
      this.type = Type.ANY;
      JAST.compilationUnit.reportSemanticError(line(), "Invalid operand types for >>>", new Object[0]);
    }
    return this;
  }
  
  //TODO this
  public void codegen(CLEmitter output)
  {
    if (this.type == Type.INT)
    {
      this.lhs.codegen(output);
      this.rhs.codegen(output);
      output.addNoArgInstruction(96);
    }
  }
}
