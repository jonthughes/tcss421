package jminusminus;
/**
 * Modified by 
 * @author Jonathan Hughes
 * @date 26 April 2016
 */
public class JGreaterEqualOp
  extends JComparison
{
  public JGreaterEqualOp(int line, JExpression lhs, JExpression rhs)
  {
    super(line, ">=", lhs, rhs);
  }
  
  //TODO
  public void codegen(CLEmitter output, String targetLabel, boolean onTrue)
  {
    this.lhs.codegen(output);
    this.rhs.codegen(output);
    output
      .addBranchInstruction(onTrue ? 163 : 164, targetLabel);
  }
}