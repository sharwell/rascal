package org.meta_environment.rascal.ast;
import org.eclipse.imp.pdb.facts.ITree;
public abstract class Statement extends AbstractAST
{
  public java.util.List < org.meta_environment.rascal.ast.Declarator >
    getDeclarations ()
  {
    throw new UnsupportedOperationException ();
  }
  public org.meta_environment.rascal.ast.Statement getBody ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Solve extends Statement
  {
/* "with" declarations:{Declarator ";"}+ ";" "solve" body:Statement -> Statement {cons("Solve")} */
    private Solve ()
    {
    }
    /*package */ Solve (ITree tree,
			java.util.List <
			org.meta_environment.rascal.ast.Declarator >
			declarations,
			org.meta_environment.rascal.ast.Statement body)
    {
      this.tree = tree;
      this.declarations = declarations;
      this.body = body;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementSolve (this);
    }
    private java.util.List < org.meta_environment.rascal.ast.Declarator >
      declarations;
    public java.util.List < org.meta_environment.rascal.ast.Declarator >
      getDeclarations ()
    {
      return declarations;
    }
    private void $setDeclarations (java.util.List <
				   org.meta_environment.rascal.ast.
				   Declarator > x)
    {
      this.declarations = x;
    }
    public Solve setDeclarations (java.util.List <
				  org.meta_environment.rascal.ast.Declarator >
				  x)
    {
      Solve z = new Solve ();
      z.$setDeclarations (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public Solve setBody (org.meta_environment.rascal.ast.Statement x)
    {
      Solve z = new Solve ();
      z.$setBody (x);
      return z;
    }
  }
  static public class Ambiguity extends Statement
  {
    private final java.util.List < org.meta_environment.rascal.ast.Statement >
      alternatives;
    public Ambiguity (java.util.List <
		      org.meta_environment.rascal.ast.Statement >
		      alternatives)
    {
      this.alternatives =
	java.util.Collections.unmodifiableList (alternatives);
    }
    public java.util.List < org.meta_environment.rascal.ast.Statement >
      getAlternatives ()
    {
      return alternatives;
    }
  }
  public org.meta_environment.rascal.ast.Label getLabel ()
  {
    throw new UnsupportedOperationException ();
  }
  public java.util.List < org.meta_environment.rascal.ast.Generator >
    getGenerators ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class For extends Statement
  {
/* label:Label "for" "(" generators:{Generator ","}+ ")" body:Statement -> Statement {cons("For")} */
    private For ()
    {
    }
    /*package */ For (ITree tree, org.meta_environment.rascal.ast.Label label,
		      java.util.List <
		      org.meta_environment.rascal.ast.Generator > generators,
		      org.meta_environment.rascal.ast.Statement body)
    {
      this.tree = tree;
      this.label = label;
      this.generators = generators;
      this.body = body;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementFor (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public For setLabel (org.meta_environment.rascal.ast.Label x)
    {
      For z = new For ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Generator >
      generators;
    public java.util.List < org.meta_environment.rascal.ast.Generator >
      getGenerators ()
    {
      return generators;
    }
    private void $setGenerators (java.util.List <
				 org.meta_environment.rascal.ast.Generator >
				 x)
    {
      this.generators = x;
    }
    public For setGenerators (java.util.List <
			      org.meta_environment.rascal.ast.Generator > x)
    {
      For z = new For ();
      z.$setGenerators (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public For setBody (org.meta_environment.rascal.ast.Statement x)
    {
      For z = new For ();
      z.$setBody (x);
      return z;
    }
  }
  static public class All extends Statement
  {
/* label:Label "all" "(" generators:{Generator ","}+ ")" body:Statement -> Statement {cons("All")} */
    private All ()
    {
    }
    /*package */ All (ITree tree, org.meta_environment.rascal.ast.Label label,
		      java.util.List <
		      org.meta_environment.rascal.ast.Generator > generators,
		      org.meta_environment.rascal.ast.Statement body)
    {
      this.tree = tree;
      this.label = label;
      this.generators = generators;
      this.body = body;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementAll (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public All setLabel (org.meta_environment.rascal.ast.Label x)
    {
      All z = new All ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Generator >
      generators;
    public java.util.List < org.meta_environment.rascal.ast.Generator >
      getGenerators ()
    {
      return generators;
    }
    private void $setGenerators (java.util.List <
				 org.meta_environment.rascal.ast.Generator >
				 x)
    {
      this.generators = x;
    }
    public All setGenerators (java.util.List <
			      org.meta_environment.rascal.ast.Generator > x)
    {
      All z = new All ();
      z.$setGenerators (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public All setBody (org.meta_environment.rascal.ast.Statement x)
    {
      All z = new All ();
      z.$setBody (x);
      return z;
    }
  }
  static public class First extends Statement
  {
/* label:Label "first" "(" generators:{Generator ","}+ ")" body:Statement -> Statement {cons("First")} */
    private First ()
    {
    }
    /*package */ First (ITree tree,
			org.meta_environment.rascal.ast.Label label,
			java.util.List <
			org.meta_environment.rascal.ast.Generator >
			generators,
			org.meta_environment.rascal.ast.Statement body)
    {
      this.tree = tree;
      this.label = label;
      this.generators = generators;
      this.body = body;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementFirst (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public First setLabel (org.meta_environment.rascal.ast.Label x)
    {
      First z = new First ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Generator >
      generators;
    public java.util.List < org.meta_environment.rascal.ast.Generator >
      getGenerators ()
    {
      return generators;
    }
    private void $setGenerators (java.util.List <
				 org.meta_environment.rascal.ast.Generator >
				 x)
    {
      this.generators = x;
    }
    public First setGenerators (java.util.List <
				org.meta_environment.rascal.ast.Generator > x)
    {
      First z = new First ();
      z.$setGenerators (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public First setBody (org.meta_environment.rascal.ast.Statement x)
    {
      First z = new First ();
      z.$setBody (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Expression getCondition ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class While extends Statement
  {
/* label:Label "while" "(" condition:Expression ")" body:Statement -> Statement {cons("While")} */
    private While ()
    {
    }
    /*package */ While (ITree tree,
			org.meta_environment.rascal.ast.Label label,
			org.meta_environment.rascal.ast.Expression condition,
			org.meta_environment.rascal.ast.Statement body)
    {
      this.tree = tree;
      this.label = label;
      this.condition = condition;
      this.body = body;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementWhile (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public While setLabel (org.meta_environment.rascal.ast.Label x)
    {
      While z = new While ();
      z.$setLabel (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Expression condition;
    public org.meta_environment.rascal.ast.Expression getCondition ()
    {
      return condition;
    }
    private void $setCondition (org.meta_environment.rascal.ast.Expression x)
    {
      this.condition = x;
    }
    public While setCondition (org.meta_environment.rascal.ast.Expression x)
    {
      While z = new While ();
      z.$setCondition (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public While setBody (org.meta_environment.rascal.ast.Statement x)
    {
      While z = new While ();
      z.$setBody (x);
      return z;
    }
  }
  static public class DoWhile extends Statement
  {
/* label:Label "do" body:Statement "while" "(" condition:Expression ")" ";" -> Statement {cons("DoWhile")} */
    private DoWhile ()
    {
    }
    /*package */ DoWhile (ITree tree,
			  org.meta_environment.rascal.ast.Label label,
			  org.meta_environment.rascal.ast.Statement body,
			  org.meta_environment.rascal.ast.
			  Expression condition)
    {
      this.tree = tree;
      this.label = label;
      this.body = body;
      this.condition = condition;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementDoWhile (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public DoWhile setLabel (org.meta_environment.rascal.ast.Label x)
    {
      DoWhile z = new DoWhile ();
      z.$setLabel (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public DoWhile setBody (org.meta_environment.rascal.ast.Statement x)
    {
      DoWhile z = new DoWhile ();
      z.$setBody (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Expression condition;
    public org.meta_environment.rascal.ast.Expression getCondition ()
    {
      return condition;
    }
    private void $setCondition (org.meta_environment.rascal.ast.Expression x)
    {
      this.condition = x;
    }
    public DoWhile setCondition (org.meta_environment.rascal.ast.Expression x)
    {
      DoWhile z = new DoWhile ();
      z.$setCondition (x);
      return z;
    }
  }
  public java.util.List < org.meta_environment.rascal.ast.Expression >
    getConditions ()
  {
    throw new UnsupportedOperationException ();
  }
  public org.meta_environment.rascal.ast.Statement getThenStatement ()
  {
    throw new UnsupportedOperationException ();
  }
  public org.meta_environment.rascal.ast.Statement getElseStatement ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class IfThenElse extends Statement
  {
/* label:Label "if" "(" conditions:{Expression ","}+ ")" thenStatement:Statement "else" elseStatement:Statement -> Statement {cons("IfThenElse")} */
    private IfThenElse ()
    {
    }
    /*package */ IfThenElse (ITree tree,
			     org.meta_environment.rascal.ast.Label label,
			     java.util.List <
			     org.meta_environment.rascal.ast.Expression >
			     conditions,
			     org.meta_environment.rascal.ast.
			     Statement thenStatement,
			     org.meta_environment.rascal.ast.
			     Statement elseStatement)
    {
      this.tree = tree;
      this.label = label;
      this.conditions = conditions;
      this.thenStatement = thenStatement;
      this.elseStatement = elseStatement;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementIfThenElse (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public IfThenElse setLabel (org.meta_environment.rascal.ast.Label x)
    {
      IfThenElse z = new IfThenElse ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Expression >
      conditions;
    public java.util.List < org.meta_environment.rascal.ast.Expression >
      getConditions ()
    {
      return conditions;
    }
    private void $setConditions (java.util.List <
				 org.meta_environment.rascal.ast.Expression >
				 x)
    {
      this.conditions = x;
    }
    public IfThenElse setConditions (java.util.List <
				     org.meta_environment.rascal.ast.
				     Expression > x)
    {
      IfThenElse z = new IfThenElse ();
      z.$setConditions (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement thenStatement;
    public org.meta_environment.rascal.ast.Statement getThenStatement ()
    {
      return thenStatement;
    }
    private void $setThenStatement (org.meta_environment.rascal.ast.
				    Statement x)
    {
      this.thenStatement = x;
    }
    public IfThenElse setThenStatement (org.meta_environment.rascal.ast.
					Statement x)
    {
      IfThenElse z = new IfThenElse ();
      z.$setThenStatement (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement elseStatement;
    public org.meta_environment.rascal.ast.Statement getElseStatement ()
    {
      return elseStatement;
    }
    private void $setElseStatement (org.meta_environment.rascal.ast.
				    Statement x)
    {
      this.elseStatement = x;
    }
    public IfThenElse setElseStatement (org.meta_environment.rascal.ast.
					Statement x)
    {
      IfThenElse z = new IfThenElse ();
      z.$setElseStatement (x);
      return z;
    }
  }
  static public class IfThen extends Statement
  {
/* label:Label "if" "(" conditions:{Expression ","}+ ")" thenStatement:Statement NoElseMayFollow -> Statement {cons("IfThen")} */
    private IfThen ()
    {
    }
    /*package */ IfThen (ITree tree,
			 org.meta_environment.rascal.ast.Label label,
			 java.util.List <
			 org.meta_environment.rascal.ast.Expression >
			 conditions,
			 org.meta_environment.rascal.ast.
			 Statement thenStatement)
    {
      this.tree = tree;
      this.label = label;
      this.conditions = conditions;
      this.thenStatement = thenStatement;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementIfThen (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public IfThen setLabel (org.meta_environment.rascal.ast.Label x)
    {
      IfThen z = new IfThen ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Expression >
      conditions;
    public java.util.List < org.meta_environment.rascal.ast.Expression >
      getConditions ()
    {
      return conditions;
    }
    private void $setConditions (java.util.List <
				 org.meta_environment.rascal.ast.Expression >
				 x)
    {
      this.conditions = x;
    }
    public IfThen setConditions (java.util.List <
				 org.meta_environment.rascal.ast.Expression >
				 x)
    {
      IfThen z = new IfThen ();
      z.$setConditions (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement thenStatement;
    public org.meta_environment.rascal.ast.Statement getThenStatement ()
    {
      return thenStatement;
    }
    private void $setThenStatement (org.meta_environment.rascal.ast.
				    Statement x)
    {
      this.thenStatement = x;
    }
    public IfThen setThenStatement (org.meta_environment.rascal.ast.
				    Statement x)
    {
      IfThen z = new IfThen ();
      z.$setThenStatement (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Expression getExpression ()
  {
    throw new UnsupportedOperationException ();
  }
  public java.util.List < org.meta_environment.rascal.ast.Case > getCases ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Switch extends Statement
  {
/* label:Label "switch" "(" expression:Expression ")" "{" cases:Case+ "}" -> Statement {cons("Switch")} */
    private Switch ()
    {
    }
    /*package */ Switch (ITree tree,
			 org.meta_environment.rascal.ast.Label label,
			 org.meta_environment.rascal.ast.
			 Expression expression,
			 java.util.List <
			 org.meta_environment.rascal.ast.Case > cases)
    {
      this.tree = tree;
      this.label = label;
      this.expression = expression;
      this.cases = cases;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementSwitch (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public Switch setLabel (org.meta_environment.rascal.ast.Label x)
    {
      Switch z = new Switch ();
      z.$setLabel (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Expression expression;
    public org.meta_environment.rascal.ast.Expression getExpression ()
    {
      return expression;
    }
    private void $setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      this.expression = x;
    }
    public Switch setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      Switch z = new Switch ();
      z.$setExpression (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Case > cases;
    public java.util.List < org.meta_environment.rascal.ast.Case > getCases ()
    {
      return cases;
    }
    private void $setCases (java.util.List <
			    org.meta_environment.rascal.ast.Case > x)
    {
      this.cases = x;
    }
    public Switch setCases (java.util.List <
			    org.meta_environment.rascal.ast.Case > x)
    {
      Switch z = new Switch ();
      z.$setCases (x);
      return z;
    }
  }
  static public class Expression extends Statement
  {
/* expression:Expression ";" -> Statement {cons("Expression")} */
    private Expression ()
    {
    }
    /*package */ Expression (ITree tree,
			     org.meta_environment.rascal.ast.
			     Expression expression)
    {
      this.tree = tree;
      this.expression = expression;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementExpression (this);
    }
    private org.meta_environment.rascal.ast.Expression expression;
    public org.meta_environment.rascal.ast.Expression getExpression ()
    {
      return expression;
    }
    private void $setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      this.expression = x;
    }
    public Expression setExpression (org.meta_environment.rascal.ast.
				     Expression x)
    {
      Expression z = new Expression ();
      z.$setExpression (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Visit getVisit ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Visit extends Statement
  {
/* visit:Visit -> Statement {cons("Visit")} */
    private Visit ()
    {
    }
    /*package */ Visit (ITree tree,
			org.meta_environment.rascal.ast.Visit visit)
    {
      this.tree = tree;
      this.visit = visit;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementVisit (this);
    }
    private org.meta_environment.rascal.ast.Visit visit;
    public org.meta_environment.rascal.ast.Visit getVisit ()
    {
      return visit;
    }
    private void $setVisit (org.meta_environment.rascal.ast.Visit x)
    {
      this.visit = x;
    }
    public Visit setVisit (org.meta_environment.rascal.ast.Visit x)
    {
      Visit z = new Visit ();
      z.$setVisit (x);
      return z;
    }
  }
  public java.util.List < org.meta_environment.rascal.ast.Assignable >
    getAssignables ()
  {
    throw new UnsupportedOperationException ();
  }
  public org.meta_environment.rascal.ast.Assignment getOperator ()
  {
    throw new UnsupportedOperationException ();
  }
  public java.util.List < org.meta_environment.rascal.ast.Expression >
    getExpressions ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Assignment extends Statement
  {
/* assignables:{Assignable ","}+ operator:Assignment expressions:{Expression ","}+ ";" -> Statement {cons("Assignment")} */
    private Assignment ()
    {
    }
    /*package */ Assignment (ITree tree,
			     java.util.List <
			     org.meta_environment.rascal.ast.Assignable >
			     assignables,
			     org.meta_environment.rascal.ast.
			     Assignment operator,
			     java.util.List <
			     org.meta_environment.rascal.ast.Expression >
			     expressions)
    {
      this.tree = tree;
      this.assignables = assignables;
      this.operator = operator;
      this.expressions = expressions;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementAssignment (this);
    }
    private java.util.List < org.meta_environment.rascal.ast.Assignable >
      assignables;
    public java.util.List < org.meta_environment.rascal.ast.Assignable >
      getAssignables ()
    {
      return assignables;
    }
    private void $setAssignables (java.util.List <
				  org.meta_environment.rascal.ast.Assignable >
				  x)
    {
      this.assignables = x;
    }
    public Assignment setAssignables (java.util.List <
				      org.meta_environment.rascal.ast.
				      Assignable > x)
    {
      Assignment z = new Assignment ();
      z.$setAssignables (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Assignment operator;
    public org.meta_environment.rascal.ast.Assignment getOperator ()
    {
      return operator;
    }
    private void $setOperator (org.meta_environment.rascal.ast.Assignment x)
    {
      this.operator = x;
    }
    public Assignment setOperator (org.meta_environment.rascal.ast.
				   Assignment x)
    {
      Assignment z = new Assignment ();
      z.$setOperator (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Expression >
      expressions;
    public java.util.List < org.meta_environment.rascal.ast.Expression >
      getExpressions ()
    {
      return expressions;
    }
    private void $setExpressions (java.util.List <
				  org.meta_environment.rascal.ast.Expression >
				  x)
    {
      this.expressions = x;
    }
    public Assignment setExpressions (java.util.List <
				      org.meta_environment.rascal.ast.
				      Expression > x)
    {
      Assignment z = new Assignment ();
      z.$setExpressions (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Break getBrk ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Break extends Statement
  {
/* brk:Break -> Statement {cons("Break")} */
    private Break ()
    {
    }
    /*package */ Break (ITree tree, org.meta_environment.rascal.ast.Break brk)
    {
      this.tree = tree;
      this.brk = brk;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementBreak (this);
    }
    private org.meta_environment.rascal.ast.Break brk;
    public org.meta_environment.rascal.ast.Break getBrk ()
    {
      return brk;
    }
    private void $setBrk (org.meta_environment.rascal.ast.Break x)
    {
      this.brk = x;
    }
    public Break setBrk (org.meta_environment.rascal.ast.Break x)
    {
      Break z = new Break ();
      z.$setBrk (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Fail getFail ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Fail extends Statement
  {
/* fail:Fail -> Statement {cons("Fail")} */
    private Fail ()
    {
    }
    /*package */ Fail (ITree tree, org.meta_environment.rascal.ast.Fail fail)
    {
      this.tree = tree;
      this.fail = fail;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementFail (this);
    }
    private org.meta_environment.rascal.ast.Fail fail;
    public org.meta_environment.rascal.ast.Fail getFail ()
    {
      return fail;
    }
    private void $setFail (org.meta_environment.rascal.ast.Fail x)
    {
      this.fail = x;
    }
    public Fail setFail (org.meta_environment.rascal.ast.Fail x)
    {
      Fail z = new Fail ();
      z.$setFail (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Return getRet ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Return extends Statement
  {
/* ret:Return -> Statement {cons("Return")} */
    private Return ()
    {
    }
    /*package */ Return (ITree tree,
			 org.meta_environment.rascal.ast.Return ret)
    {
      this.tree = tree;
      this.ret = ret;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementReturn (this);
    }
    private org.meta_environment.rascal.ast.Return ret;
    public org.meta_environment.rascal.ast.Return getRet ()
    {
      return ret;
    }
    private void $setRet (org.meta_environment.rascal.ast.Return x)
    {
      this.ret = x;
    }
    public Return setRet (org.meta_environment.rascal.ast.Return x)
    {
      Return z = new Return ();
      z.$setRet (x);
      return z;
    }
  }
  static public class Continue extends Statement
  {
/* "continue" ";" -> Statement {cons("Continue")} */
    private Continue ()
    {
    }
    /*package */ Continue (ITree tree)
    {
      this.tree = tree;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementContinue (this);
    }
  }
  public org.meta_environment.rascal.ast.StringLiteral getLabel ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Assert extends Statement
  {
/* "assert" label:StringLiteral ":" expression:Expression ";" -> Statement {cons("Assert")} */
    private Assert ()
    {
    }
    /*package */ Assert (ITree tree,
			 org.meta_environment.rascal.ast.StringLiteral label,
			 org.meta_environment.rascal.ast.
			 Expression expression)
    {
      this.tree = tree;
      this.label = label;
      this.expression = expression;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementAssert (this);
    }
    private org.meta_environment.rascal.ast.StringLiteral label;
    public org.meta_environment.rascal.ast.StringLiteral getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.StringLiteral x)
    {
      this.label = x;
    }
    public Assert setLabel (org.meta_environment.rascal.ast.StringLiteral x)
    {
      Assert z = new Assert ();
      z.$setLabel (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Expression expression;
    public org.meta_environment.rascal.ast.Expression getExpression ()
    {
      return expression;
    }
    private void $setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      this.expression = x;
    }
    public Assert setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      Assert z = new Assert ();
      z.$setExpression (x);
      return z;
    }
  }
  static public class Insert extends Statement
  {
/* "insert" expression:Expression ";" -> Statement {cons("Insert")} */
    private Insert ()
    {
    }
    /*package */ Insert (ITree tree,
			 org.meta_environment.rascal.ast.
			 Expression expression)
    {
      this.tree = tree;
      this.expression = expression;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementInsert (this);
    }
    private org.meta_environment.rascal.ast.Expression expression;
    public org.meta_environment.rascal.ast.Expression getExpression ()
    {
      return expression;
    }
    private void $setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      this.expression = x;
    }
    public Insert setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      Insert z = new Insert ();
      z.$setExpression (x);
      return z;
    }
  }
  static public class Throw extends Statement
  {
/* "throw" expression:Expression ";" -> Statement {cons("Throw")} */
    private Throw ()
    {
    }
    /*package */ Throw (ITree tree,
			org.meta_environment.rascal.ast.Expression expression)
    {
      this.tree = tree;
      this.expression = expression;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementThrow (this);
    }
    private org.meta_environment.rascal.ast.Expression expression;
    public org.meta_environment.rascal.ast.Expression getExpression ()
    {
      return expression;
    }
    private void $setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      this.expression = x;
    }
    public Throw setExpression (org.meta_environment.rascal.ast.Expression x)
    {
      Throw z = new Throw ();
      z.$setExpression (x);
      return z;
    }
  }
  public java.util.List < org.meta_environment.rascal.ast.Catch >
    getHandlers ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Try extends Statement
  {
/* "try" body:Statement handlers:Catch+ -> Statement {non-assoc, cons("Try")} */
    private Try ()
    {
    }
    /*package */ Try (ITree tree,
		      org.meta_environment.rascal.ast.Statement body,
		      java.util.List < org.meta_environment.rascal.ast.Catch >
		      handlers)
    {
      this.tree = tree;
      this.body = body;
      this.handlers = handlers;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementTry (this);
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public Try setBody (org.meta_environment.rascal.ast.Statement x)
    {
      Try z = new Try ();
      z.$setBody (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Catch > handlers;
    public java.util.List < org.meta_environment.rascal.ast.Catch >
      getHandlers ()
    {
      return handlers;
    }
    private void $setHandlers (java.util.List <
			       org.meta_environment.rascal.ast.Catch > x)
    {
      this.handlers = x;
    }
    public Try setHandlers (java.util.List <
			    org.meta_environment.rascal.ast.Catch > x)
    {
      Try z = new Try ();
      z.$setHandlers (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Statement getFinallyBody ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class TryFinally extends Statement
  {
/* "try" body:Statement handlers:Catch+ "finally" finallyBody:Statement -> Statement {cons("TryFinally")} */
    private TryFinally ()
    {
    }
    /*package */ TryFinally (ITree tree,
			     org.meta_environment.rascal.ast.Statement body,
			     java.util.List <
			     org.meta_environment.rascal.ast.Catch > handlers,
			     org.meta_environment.rascal.ast.
			     Statement finallyBody)
    {
      this.tree = tree;
      this.body = body;
      this.handlers = handlers;
      this.finallyBody = finallyBody;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementTryFinally (this);
    }
    private org.meta_environment.rascal.ast.Statement body;
    public org.meta_environment.rascal.ast.Statement getBody ()
    {
      return body;
    }
    private void $setBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.body = x;
    }
    public TryFinally setBody (org.meta_environment.rascal.ast.Statement x)
    {
      TryFinally z = new TryFinally ();
      z.$setBody (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Catch > handlers;
    public java.util.List < org.meta_environment.rascal.ast.Catch >
      getHandlers ()
    {
      return handlers;
    }
    private void $setHandlers (java.util.List <
			       org.meta_environment.rascal.ast.Catch > x)
    {
      this.handlers = x;
    }
    public TryFinally setHandlers (java.util.List <
				   org.meta_environment.rascal.ast.Catch > x)
    {
      TryFinally z = new TryFinally ();
      z.$setHandlers (x);
      return z;
    }
    private org.meta_environment.rascal.ast.Statement finallyBody;
    public org.meta_environment.rascal.ast.Statement getFinallyBody ()
    {
      return finallyBody;
    }
    private void $setFinallyBody (org.meta_environment.rascal.ast.Statement x)
    {
      this.finallyBody = x;
    }
    public TryFinally setFinallyBody (org.meta_environment.rascal.ast.
				      Statement x)
    {
      TryFinally z = new TryFinally ();
      z.$setFinallyBody (x);
      return z;
    }
  }
  public java.util.List < org.meta_environment.rascal.ast.Statement >
    getStatements ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class Block extends Statement
  {
/* label:Label "{" statements:Statement* "}" -> Statement {cons("Block")} */
    private Block ()
    {
    }
    /*package */ Block (ITree tree,
			org.meta_environment.rascal.ast.Label label,
			java.util.List <
			org.meta_environment.rascal.ast.Statement >
			statements)
    {
      this.tree = tree;
      this.label = label;
      this.statements = statements;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementBlock (this);
    }
    private org.meta_environment.rascal.ast.Label label;
    public org.meta_environment.rascal.ast.Label getLabel ()
    {
      return label;
    }
    private void $setLabel (org.meta_environment.rascal.ast.Label x)
    {
      this.label = x;
    }
    public Block setLabel (org.meta_environment.rascal.ast.Label x)
    {
      Block z = new Block ();
      z.$setLabel (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.Statement >
      statements;
    public java.util.List < org.meta_environment.rascal.ast.Statement >
      getStatements ()
    {
      return statements;
    }
    private void $setStatements (java.util.List <
				 org.meta_environment.rascal.ast.Statement >
				 x)
    {
      this.statements = x;
    }
    public Block setStatements (java.util.List <
				org.meta_environment.rascal.ast.Statement > x)
    {
      Block z = new Block ();
      z.$setStatements (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.
    FunctionDeclaration getFunctionDeclaration ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class FunctionDeclaration extends Statement
  {
/* functionDeclaration:FunctionDeclaration -> Statement {cons("FunctionDeclaration")} */
    private FunctionDeclaration ()
    {
    }
    /*package */ FunctionDeclaration (ITree tree,
				      org.meta_environment.rascal.ast.
				      FunctionDeclaration functionDeclaration)
    {
      this.tree = tree;
      this.functionDeclaration = functionDeclaration;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementFunctionDeclaration (this);
    }
    private org.meta_environment.rascal.ast.
      FunctionDeclaration functionDeclaration;
    public org.meta_environment.rascal.ast.
      FunctionDeclaration getFunctionDeclaration ()
    {
      return functionDeclaration;
    }
    private void $setFunctionDeclaration (org.meta_environment.rascal.ast.
					  FunctionDeclaration x)
    {
      this.functionDeclaration = x;
    }
    public FunctionDeclaration setFunctionDeclaration (org.meta_environment.
						       rascal.ast.
						       FunctionDeclaration x)
    {
      FunctionDeclaration z = new FunctionDeclaration ();
      z.$setFunctionDeclaration (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.
    LocalVariableDeclaration getDeclaration ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class VariableDeclaration extends Statement
  {
/* declaration:LocalVariableDeclaration ";" -> Statement {cons("VariableDeclaration")} */
    private VariableDeclaration ()
    {
    }
    /*package */ VariableDeclaration (ITree tree,
				      org.meta_environment.rascal.ast.
				      LocalVariableDeclaration declaration)
    {
      this.tree = tree;
      this.declaration = declaration;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementVariableDeclaration (this);
    }
    private org.meta_environment.rascal.ast.
      LocalVariableDeclaration declaration;
    public org.meta_environment.rascal.ast.
      LocalVariableDeclaration getDeclaration ()
    {
      return declaration;
    }
    private void $setDeclaration (org.meta_environment.rascal.ast.
				  LocalVariableDeclaration x)
    {
      this.declaration = x;
    }
    public VariableDeclaration setDeclaration (org.meta_environment.rascal.
					       ast.LocalVariableDeclaration x)
    {
      VariableDeclaration z = new VariableDeclaration ();
      z.$setDeclaration (x);
      return z;
    }
  }
  public org.meta_environment.rascal.ast.Type getType ()
  {
    throw new UnsupportedOperationException ();
  }
  public java.util.List < org.meta_environment.rascal.ast.QualifiedName >
    getNames ()
  {
    throw new UnsupportedOperationException ();
  }
  static public class GlobalDirective extends Statement
  {
/* "global" type:Type names:{QualifiedName ","}+ ";" -> Statement {cons("GlobalDirective")} */
    private GlobalDirective ()
    {
    }
    /*package */ GlobalDirective (ITree tree,
				  org.meta_environment.rascal.ast.Type type,
				  java.util.List <
				  org.meta_environment.rascal.ast.
				  QualifiedName > names)
    {
      this.tree = tree;
      this.type = type;
      this.names = names;
    }
    public IVisitable accept (IASTVisitor visitor)
    {
      return visitor.visitStatementGlobalDirective (this);
    }
    private org.meta_environment.rascal.ast.Type type;
    public org.meta_environment.rascal.ast.Type getType ()
    {
      return type;
    }
    private void $setType (org.meta_environment.rascal.ast.Type x)
    {
      this.type = x;
    }
    public GlobalDirective setType (org.meta_environment.rascal.ast.Type x)
    {
      GlobalDirective z = new GlobalDirective ();
      z.$setType (x);
      return z;
    }
    private java.util.List < org.meta_environment.rascal.ast.QualifiedName >
      names;
    public java.util.List < org.meta_environment.rascal.ast.QualifiedName >
      getNames ()
    {
      return names;
    }
    private void $setNames (java.util.List <
			    org.meta_environment.rascal.ast.QualifiedName > x)
    {
      this.names = x;
    }
    public GlobalDirective setNames (java.util.List <
				     org.meta_environment.rascal.ast.
				     QualifiedName > x)
    {
      GlobalDirective z = new GlobalDirective ();
      z.$setNames (x);
      return z;
    }
  }
}
