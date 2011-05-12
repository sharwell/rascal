module lang::rascal::grammar::analyze

import Grammar;
import ParseTree;
import Graph;

@doc{
  Compute the symbol dependency graph. This graph does not report intermediate nodes
  for regular expressions.
}
@experimental
public Graph[Symbol] symbolDependencies(Grammar g) {
  return { <from,to> | /prod([_*,/Symbol to:sort(_),_*],Symbol from:sort(_),_) := g};
}