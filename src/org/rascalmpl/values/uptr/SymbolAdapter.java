/*******************************************************************************
 * Copyright (c) 2009-2013 CWI
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:

 *   * Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI
 *   * Tijs van der Storm - Tijs.van.der.Storm@cwi.nl
 *   * Paul Klint - Paul.Klint@cwi.nl - CWI
 *   * Mark Hills - Mark.Hills@cwi.nl (CWI)
 *   * Arnold Lankamp - Arnold.Lankamp@cwi.nl
*******************************************************************************/
package org.rascalmpl.values.uptr;

import org.eclipse.imp.pdb.facts.IConstructor;
import org.eclipse.imp.pdb.facts.IList;
import org.eclipse.imp.pdb.facts.ISet;
import org.eclipse.imp.pdb.facts.IString;
import org.eclipse.imp.pdb.facts.IValue;
import org.rascalmpl.interpreter.asserts.ImplementationError;

public class SymbolAdapter {
	
	private SymbolAdapter() {
		super();
	}
	
	public static IConstructor delabel(IConstructor sym) {
		if (isLabel(sym)) {
			return (IConstructor) sym.get("symbol"); // do not use getSymbol() here!
		}
		return sym;
	}

	public static boolean isLabel(IConstructor sym) {
		return sym.getConstructorType() == Factory.Symbol_Label;
	}

	public static boolean isSort(IConstructor tree) {
		tree = delabel(tree);
		return tree.getConstructorType() == Factory.Symbol_Sort;
	}

	public static boolean isMeta(IConstructor tree) {
		tree = delabel(tree);
		return tree.getConstructorType() == Factory.Symbol_Meta;
	}
	
	public static boolean isStartSort(IConstructor tree) {
		tree = delabel(tree);
		return tree.getConstructorType() == Factory.Symbol_Start_Sort;
	}  
	
	public static boolean isStart(IConstructor tree) {
		tree = delabel(tree);
		return tree.getConstructorType() == Factory.Symbol_START;
	}
	  
	public static IConstructor getStart(IConstructor tree) {
		if (isStartSort(tree)) {
			tree = delabel(tree);
			return (IConstructor) tree.get("start");
		}
		throw new ImplementationError("Symbol does not have a child named start: " + tree);
	}

	
	public static IConstructor getLabeledSymbol(IConstructor tree) {
		return ((IConstructor) tree.get("symbol"));
	}
		
	public static IConstructor getSymbol(IConstructor tree) {
		tree = delabel(tree);
		if (isOpt(tree) || isIterPlus(tree) || isIterStar(tree)  || isIterPlusSeps(tree) || isIterStarSeps(tree) || isMeta(tree) || isConditional(tree)) {
			return ((IConstructor) tree.get("symbol"));
		}
		
		throw new ImplementationError("Symbol does not have a child named symbol: " + tree);
	}
	
	public static boolean isConditional(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Conditional;
	}

	public static String getLabelName(IConstructor tree) {
		return ((IString) tree.get("name")).getValue();
	}
	
	/**
	 * Use this to get a name of a sort or parameterized sort, not to get the name of a label.
	 * @param tree sort or parameterized sort
	 * @return the name of the sort
	 */
	public static String getName(IConstructor tree) {
		tree = delabel(tree);
		
		return ((IString) tree.get("name")).getValue();
	}
	
	public static String getLabel(IConstructor tree) {
		if (isLabel(tree)) {
			return ((IString) tree.get("name")).getValue();
		}
		
		throw new ImplementationError("Symbol does not have a child named \"label\" : " + tree);
	}

	public static boolean isParameterizedSort(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_ParameterizedSort;
	}
	
	public static boolean isParameterizedLex(IConstructor tree) {
    return tree.getConstructorType() == Factory.Symbol_ParameterizedLex;
  }
	
	public static boolean isLiteral(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_Lit;
	}

	public static boolean isCILiteral(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_CiLit;
	}

	public static boolean isIterStar(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_IterStar;
	}
	
	public static boolean isIterPlus(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_IterPlus;
	}
	
	public static boolean isLayouts(IConstructor tree) {
		return tree.getConstructorType() == Factory.Symbol_LayoutX;
	}
	
	public static boolean isStarList(IConstructor tree) {
		tree = delabel(tree);
		return isIterStar(tree) || isIterStarSeps(tree) ;
	}
	
	public static boolean isPlusList(IConstructor tree) {
		tree = delabel(tree);
		return isIterPlus(tree) || isIterPlusSeps(tree);
	}
	
	public static boolean isSepList(IConstructor tree){
		tree = delabel(tree);
		return isIterPlusSeps(tree) || isIterStarSeps(tree);
	}
	
	public static boolean isAnyList(IConstructor tree) {
		tree = delabel(tree);
		return isStarList(tree) || isPlusList(tree);
	}
	
	public static boolean isOpt(IConstructor tree) {
		return delabel(tree).getConstructorType() == Factory.Symbol_Opt;
	}
	
	public static boolean isSequence(IConstructor tree){
		return delabel(tree).getConstructorType() == Factory.Symbol_Seq;
	}
	
	public static boolean isAlternative(IConstructor tree){
		return delabel(tree).getConstructorType() == Factory.Symbol_Alt;
	}

	public static String toString(IConstructor symbol) {
		// TODO: this does not do the proper escaping and such!!
		
		if (isLabel(symbol)) {
			return toString((IConstructor) symbol.get("symbol")) + " " + ((IString) symbol.get("name")).getValue();
		}
		
		if (isSort(symbol) || isLex(symbol) || isKeyword(symbol)) {
			return getName(symbol);
		}
		if (isIterPlusSeps(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append('{');
			b.append(toString(getSymbol(symbol)));
			for (IValue sep : getSeparators(symbol)) {
				b.append(" ");
				b.append(toString((IConstructor) sep));
			}
			b.append('}');
			b.append('+');
			return b.toString();
			
		}
		if (isIterStarSeps(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append('{');
			b.append(toString(getSymbol(symbol)));
			for (IValue sep : getSeparators(symbol)) {
				if (!isLayouts((IConstructor) sep)) {
					b.append(" ");
					b.append(toString((IConstructor) sep));
				}
			}
			b.append('}');
			b.append('*');
			return b.toString();
		}
		if (isIterPlus(symbol)) {
			return toString(getSymbol(symbol)) + '+';
		}
		if (isIterStar(symbol)) {
			return toString(getSymbol(symbol)) + '*';
		}
		if (isOpt(symbol)) {
			return toString(getSymbol(symbol)) + '?';
		}
		if (isLayouts(symbol)) {
			return "layout[" + symbol.get("name") + "]";
		}
		if (isLiteral(symbol)) {
			return '"' + ((IString) symbol.get("string")).getValue() + '"';
		}
		if (isCILiteral(symbol)) {
			return '\'' + ((IString) symbol.get("string")).getValue() + '\'';
		}
		if (isParameterizedSort(symbol) || isParameterizedLex(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append(getName(symbol));
			IList params = (IList) symbol.get("parameters");
			b.append('[');
			b.append(toString(params));
			b.append(']');
			return b.toString();
		}
		if (isStartSort(symbol)) {
			return "start[" + toString(getStart(symbol)) + "]";
		}
		if (isParameter(symbol)) {
			return "&" + getName(symbol);
		}
		
		if (isInt(symbol) || isStr(symbol) || isReal(symbol) || isBool(symbol) || isRat(symbol)
				|| isNode(symbol) || isValue(symbol) || isVoid(symbol) || isNum(symbol) || isDatetime(symbol)) {
			return symbol.getName();
		}
		
		if (isSet(symbol) || isList(symbol) || isBag(symbol) || isReifiedType(symbol)) {
			return symbol.getName() + "[" + toString((IConstructor) symbol.get("symbol")) + "]";
		}
		
		if (isRel(symbol) || isTuple(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append(symbol.getName());
			IList symbols = (IList) symbol.get("symbols");
			b.append('[');
			b.append(toString(symbols));
			b.append(']');
			return b.toString();
		}
		
		if (isMap(symbol)) {
			return symbol.getName() + "[" + toString((IConstructor) symbol.get("from")) + "," + toString((IConstructor) symbol.get("to")) + "]";
		}
		
		
		
		if (isADT(symbol) || isAlias(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append(getName(symbol));
			IList params = (IList) symbol.get("parameters");
			
			if (!params.isEmpty()) {
				b.append('[');
				b.append(toString(params));
				b.append(']');
			}
			return b.toString();
		}
		
		if (isFunc(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append(toString((IConstructor) symbol.get("ret")));
			b.append("(");
			b.append(toString((IList) symbol.get("parameters")));
			b.append(")");
			return b.toString();
		}
		
		if (isCons(symbol)) {
			StringBuilder b = new StringBuilder();
			b.append(toString((IConstructor) symbol.get("adt")));
			b.append(" ");
			b.append(((IString) symbol.get("name")).getValue());
			b.append("(");
			b.append(toString((IList) symbol.get("parameters")));
			b.append(")");
		}
		
		// TODO: add more to cover all different symbol constructors
		return symbol.toString();
	}

	

	private static String toString(IList symbols) {
		StringBuilder b = new StringBuilder();
		
		if (symbols.length() > 0) {
			b.append(toString((IConstructor) symbols.get(0)));
			for (int i = 1; i < symbols.length(); i++) {
				b.append(',');
				b.append(toString((IConstructor) symbols.get(i)));
			}
		}
		
		return b.toString();
	}
	
	public static boolean isCons(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Cons;
	}
	
	public static boolean isFunc(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Func;
	}

	public static boolean isAlias(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Alias;
	}

	public static boolean isADT(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Adt;
	}

	public static boolean isReifiedType(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_ReifiedType;
	}

	public static boolean isBag(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Bag;
	}

	public static boolean isList(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_List;
	}

	public static boolean isSet(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Set;
	}

	public static boolean isTuple(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Tuple;
	}

	public static boolean isRel(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Rel;
	}

	public static boolean isMap(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Map;
	}

	public static boolean isDatetime(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Datetime;
	}

	public static boolean isNum(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Num;
	}

	public static boolean isVoid(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Void;
	}

	public static boolean isValue(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Value;
	}

	public static boolean isNode(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Node;
	}

	public static boolean isRat(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Rat;
	}

	public static boolean isBool(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Bool;
	}

	public static boolean isReal(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Real;
	}

	public static boolean isStr(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Str;
	}

	public static boolean isInt(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Int;
	}

	public static boolean isParameter(IConstructor symbol) {
		return symbol.getConstructorType() == Factory.Symbol_Parameter;
	}

	public static IConstructor getRhs(IConstructor symbol) {
		symbol = delabel(symbol);
		return (IConstructor) symbol.get("rhs");
	}
	
	public static boolean isIterStarSeps(IConstructor rhs) {
		rhs = delabel(rhs);
		return rhs.getConstructorType() == Factory.Symbol_IterStarSepX;
	}
	
	public static boolean isIterPlusSeps(IConstructor rhs) {
		rhs = delabel(rhs);
		return rhs.getConstructorType() == Factory.Symbol_IterSepX;
	}

	public static IList getSeparators(IConstructor rhs) {
		rhs = delabel(rhs);
		return (IList) rhs.get("separators");
	}

	public static boolean isLex(IConstructor rhs) {
		return rhs.getConstructorType() == Factory.Symbol_Lex;
	}
	
	public static boolean isKeyword(IConstructor rhs) {
		return rhs.getConstructorType() == Factory.Symbol_Keyword;
	}

	public static boolean isEmpty(IConstructor rhs) {
		return rhs.getConstructorType() == Factory.Symbol_Empty;
	}
	
	/**
	 * Computes symbol equality modulo lex/sort/layout/keyword distinction and modulo labels and conditions
	 */
	public static boolean isEqual(IConstructor l, IConstructor r) {
		while (isLabel(l)) {
			l = getLabeledSymbol(l);
		}
		
		while (isLabel(r)) {
			r = getLabeledSymbol(r);
		}
		
		while (isConditional(l)) {
			l = getSymbol(l);
		}
		
		while (isConditional(r)) {
			r = getSymbol(r);
		}
		
		if (isLayouts(l) && isLayouts(r)) {
			return true;
		}
		
		if (isSort(l) || isLex(l) || isKeyword(l) || isLayouts(l)) {
			if (isLex(r) || isSort(r) || isKeyword(r) || isLayouts(r)) {
				return getName(l).equals(getName(r));
			}
		}
		
		if (isParameterizedSort(l) && isParameterizedSort(r)) {
			return getName(l).equals(getName(r)) && isEqual(getParameters(l), getParameters(r));
		}
		
		if (isParameterizedLex(l) && isParameterizedLex(r)) {
      return getName(l).equals(getName(r)) && isEqual(getParameters(l), getParameters(r));
    }
		
		if (isParameter(l) && isParameter(r)) {
			return getName(l).equals(getName(r));
		}
		
		if ((isIterPlusSeps(l) && isIterPlusSeps(r)) || (isIterStarSeps(l) && isIterStarSeps(r))) {
			return isEqual(getSymbol(l), getSymbol(r)) && isEqual(getSeparators(l), getSeparators(r));
		}
		
		if ((isIterPlus(l) && isIterPlus(r)) || (isIterStar(l) && isIterStar(r)) || (isOpt(l) && isOpt(r))) {
			return isEqual(getSymbol(l), getSymbol(r));
		}
		
		if (isEmpty(l) && isEmpty(r)) {
			return true;
		}
		
		if (isAlt(l) && isAlt(r)) {
			return isEqual(getAlternatives(l), getAlternatives(r));
		}
		
		if (isSeq(l) && isSeq(r)) {
			return isEqual(getSequence(l), getSequence(r));
		}
		
		if ((isLiteral(l) && isLiteral(r)) || (isCILiteral(l) && isCILiteral(r)) || (isCharClass(l) && isCharClass(r))) {
			return l.isEqual(r);
		}
		
		return false;
	}

	private static IList getParameters(IConstructor l) {
		return (IList) l.get("parameters");
	}

	public static boolean isCharClass(IConstructor r) {
		return r.getConstructorType() == Factory.Symbol_CharClass;
	}

	private static IList getSequence(IConstructor r) {
		return (IList) r.get("sequence");
	}

	public static boolean isEqual(ISet l, ISet r) {
		if (l.size() != r.size()) {
			return false;
		}
		
		OUTER:for (IValue le : l) {
			for (IValue re : r) {
				if (isEqual((IConstructor) le, (IConstructor) re)) {
					continue OUTER; // found a match
				}

				return false; // no partner found
			}
		}

		return true;
	}

	private static ISet getAlternatives(IConstructor r) {
		return (ISet) r.get("alternatives");
	}

	public static boolean isAlt(IConstructor l) {
		return l.getConstructorType() == Factory.Symbol_Alt;
	}

	public static boolean isSeq(IConstructor l) {
		return l.getConstructorType() == Factory.Symbol_Seq;
	}
	
	public static boolean isEqual(IList l, IList r) {
		if (l.length() != r.length()) {
			return false;
		}
			
		for (int i = 0; i < l.length(); i++) {
			if (!isEqual((IConstructor) l.get(i), (IConstructor) r.get(i))) {
				return false;
			}
		}
		
		return true;
	}
}
