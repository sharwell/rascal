package org.rascalmpl.interpreter.utils;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.INode;
import org.eclipse.imp.pdb.facts.IValue;
import org.eclipse.imp.pdb.facts.exceptions.FactTypeUseException;
import org.eclipse.imp.pdb.facts.type.Type;
import org.eclipse.imp.pdb.facts.type.TypeFactory;
import org.eclipse.imp.pdb.facts.visitors.IValueVisitor;
import org.eclipse.imp.pdb.facts.visitors.VisitorException;
import org.rascalmpl.values.uptr.TreeAdapter;

public class TreeAsNode implements INode {
  private final String name;
  private final IList args;

  public TreeAsNode(IConstructor tree) {
    this.name = TreeAdapter.getConstructorName(tree);
    this.args = TreeAdapter.getASTArgs(tree);
  }
  
  @Override
  public Type getType() {
    return TypeFactory.getInstance().nodeType();
  }

  @Override
  public <T> T accept(IValueVisitor<T> v) throws VisitorException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isEqual(IValue other) {
    throw new UnsupportedOperationException();
  }

  @Override
  public IValue get(int i) throws IndexOutOfBoundsException {
    // TODO: this should deal with regular expressions in the "right" way, such as skipping 
    // over optionals and alternatives.
    return args.get(i);
  }

  @Override
  public INode set(int i, IValue newChild) throws IndexOutOfBoundsException {
    throw new UnsupportedOperationException();
  }

  @Override
  public int arity() {
    return args.length();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Iterable<IValue> getChildren() {
    return args;
  }

  @Override
  public Iterator<IValue> iterator() {
    return args.iterator();
  }

  @Override
  public IValue getAnnotation(String label) throws FactTypeUseException {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode setAnnotation(String label, IValue newValue) throws FactTypeUseException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasAnnotation(String label) throws FactTypeUseException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasAnnotations() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<String, IValue> getAnnotations() {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode setAnnotations(Map<String, IValue> annotations) {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode joinAnnotations(Map<String, IValue> annotations) {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode removeAnnotation(String key) {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode removeAnnotations() {
    throw new UnsupportedOperationException();
  }

  @Override
  public INode replace(int first, int second, int end, IList repl) throws FactTypeUseException,
      IndexOutOfBoundsException {
    throw new UnsupportedOperationException();
  }

@Override
public IValue getKeywordArgumentValue(String name) {
	throw new UnsupportedOperationException();
}

@Override
public boolean hasKeywordArguments() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public String[] getKeywordArgumentNames() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int getKeywordIndex(String name) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int positionalArity() {
	// TODO Auto-generated method stub
	return 0;
}
}