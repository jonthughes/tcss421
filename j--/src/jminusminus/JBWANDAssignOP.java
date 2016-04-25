package jminusminus;

public class JBWANDAssignOP
  extends JAssignment
{
  public JBWANDAssignOP(int line, JExpression lhs, JExpression rhs)
  {
    super(line, "&=", lhs, rhs);
  }
  
  public JExpression analyze(Context context)
  {
    if (!(this.lhs instanceof JLhs))
    {
      JAST.compilationUnit.reportSemanticError(line(), "Illegal lhs for assignment", new Object[0]);
      
      return this;
    }
    this.lhs = ((JLhs)this.lhs).analyzeLhs(context);
    
    this.rhs = this.rhs.analyze(context);
    if (this.lhs.type().equals(Type.INT))
    {
      this.rhs.type().mustMatchExpected(line(), Type.INT);
      this.type = Type.INT;
    }
    else if (this.lhs.type().equals(Type.STRING))
    {
      this.rhs = new JStringConcatenationOp(this.line, this.lhs, this.rhs).analyze(context);
      this.type = Type.STRING;
    }
    else
    {
      JAST.compilationUnit.reportSemanticError(line(), "Invalid lhs type for &=: " + this.lhs
        .type(), new Object[0]);
    }
    return this;
  }
  
  public void codegen(CLEmitter output)
  {
      //TODO this
    ((JLhs)this.lhs).codegenLoadLhsLvalue(output);
    if (this.lhs.type().equals(Type.STRING))
    {
      this.rhs.codegen(output);
    }
    else
    {
      ((JLhs)this.lhs).codegenLoadLhsRvalue(output);
      this.rhs.codegen(output);
      output.addNoArgInstruction(96);
    }
    if (!this.isStatementExpression) {
      ((JLhs)this.lhs).codegenDuplicateRvalue(output);
    }
    ((JLhs)this.lhs).codegenStore(output);
  }
}
