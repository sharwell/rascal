@license{
  Copyright (c) 2009-2013 CWI
  All rights reserved. This program and the accompanying materials
  are made available under the terms of the Eclipse Public License v1.0
  which accompanies this distribution, and is available at
  http://www.eclipse.org/legal/epl-v10.html
}
@contributor{Jurgen J. Vinju - Jurgen.Vinju@cwi.nl - CWI}
module lang::sdf2::util::SDF2Grammar
       
// Convert SDF2 grammars to an (unnormalized) Rascal internal grammar representation (Grammar)
   
// Todo List:
// - Some tests are marked with @ignore (and commented out) since they trigger a Rascal bug:
//   . The expression: `(Group) `A -\> B <1>`; triggers a bug in AST construction
//   . The test (Class) `[]` == \char-class([]);  // gives unsupported operation
                    
import IO;
import String;
import util::Math;
import ParseTree;
import Grammar;
import lang::rascal::grammar::definition::Names;
import lang::rascal::grammar::definition::Characters; 
import lang::sdf2::util::Load;
import lang::sdf2::\syntax::Sdf2;   

public Symbol label(str s, conditional(Symbol t, set[Condition] cs)) = conditional(label(s, t), cs);
public Symbol conditional(conditional(Symbol s, set[Condition] c1), set[Condition] c2) = conditional(s, c1 + c2);

public GrammarDefinition sdf2grammar(loc input) {
  return sdf2grammar("Main", parse(#SDF, input)); 
}

public GrammarDefinition sdf2grammar(str main, loc input) {
  return sdf2grammar(main, parse(#SDF, input)); 
}

public GrammarDefinition sdf2grammar(loc input) {
  return sdf2grammar(parse(#SDF, input)); 
}

public GrammarDefinition sdf2module2grammar(str name, list[loc] path) {
  return sdf2grammar(name, loadSDF2Module(name, path));
}

public GrammarDefinition sdf2grammar(SDF def) {
  return sdf2grammar("Main", def);
}

public GrammarDefinition sdf2grammar(str main, SDF def) {
  if ((SDF) `definition <Module* mods>` := def) {
    ms = ();
    for (Module m <- mods) {
      gm = getModule(m);
      ms[gm.name] = gm;
    } 
    
    main = moduleName(main);
    
    if (main notin ms) 
      throw "Main module <main> not found";
    
    res = definition(main, ms);
    res = resolve(res);
    res = applyConditions(res, (s:c | c:conditional(s,_) <- getConditions(def)));
    
    return res;
  }
  
  throw "Unknown format for SDF2";
}

private GrammarModule getModule(Module m) {
  if (/(Module) `module <ModuleName mn> <ImpSection* _> <Sections _>` := m) {
    name = moduleName("<mn.id>");
    println("processing <name>");
    prods = getProductions(m);
    imps = getImports(m); 
   
    // note that imports in SDF2 have the semantics of extends in Rascal
    return \module(name, {}, imps, illegalPriorities(dup(grammar({}, prods))));
    //return \module(name, {}, imps, grammar({}, prods));
  }
  
  throw "can not find module name in <m>";
}

public str moduleName(/<pre:.*>\/<post:.*>/) = moduleName("<pre>::<post>");
public str moduleName(/languages::<rest:.*>/) = moduleName("lang::<rest>");
public default str moduleName(str i)  = i;

private set[str] getImports(Module m) {
  return { moduleName("<name.id>") | /Import i := m,  /ModuleName name := i}; 
}

public GrammarDefinition applyConditions(GrammarDefinition d,  map[Symbol from,Symbol to] conds) {
  return visit(d) {
    case prod(Symbol d, list[Symbol] ss, set[Attr] as) => prod(d, [(s in conds || (s is label && s.symbol in conds))? conds[s] : s | s <- ss], as)
  }
}

public Grammar illegalPriorities(Grammar g) {
  extracted = {};
  g = innermost visit (g) {
    case \priority(Symbol def, list[Production] ps) : 
      if ([pre*,prod(Symbol other, _, _),post*] := ps, !sameType(def, other)) {
        println("WARNING: extracting production from non-recursive priority chain");
        extracted += p[attributes = p.attributes + \tag("NotSupported"("priority with <pre> <post>"))];
        insert priority(def, pre + post);
      }
      else fail;
    case \associativity(Symbol def, Associativity a, set[Production] q) :
      if ({rest*, prod(Symbol other, _, _)} := q, !sameType(def, other)) {
        println("WARNING: extracting production from non-recursive associativity group");
        extracted += p[attributes = p.attributes + \tag("NotSupported"("<a> associativity with <other>"))];
        insert associativity(def, a, other);
      }
      else fail;
  }
  
  return compose(g, grammar({}, extracted));
}

public Grammar dup(Grammar g) {
  prods = { p | /Production p:prod(_,_,_) := g };
  
  // first we fuse the attributes (SDF2 semantics) and the cons names
  solve (prods) {
    if ({prod(l,r,a1), prod(l,r,a2), rest*} := prods) {
      prods = {prod(l,r,a1 + a2), rest};
    }
    if ({prod(label(n,l),r,a), prod(l,r,a), rest*} := prods) {
      prods = {prod(label(n,l),r,a), rest};
    }
    if ({prod(label(n,l),r,a), prod(label(m,l),r,a), rest*} := prods) {
      prods = {prod(label(n,l),r,a), rest};
    }
  } 
  
  // now we replace all uses of prods by their fused counterparts
  g = visit(g) {
    case prod(l,r,_) : 
      if ({p:prod(l,r,_), _*} := prods) 
        insert p;
      else if ({p:prod(label(n,l),r,_),_*} := prods)
        insert p;
      else fail;
    case prod(label(n,l),r,_) :
      if ({p:prod(label(m,l),r,_),_*} := prods)
        insert p;
      else fail;
  }
  
  return g;
}

test bool test1() = sdf2grammar(
        (SDF) `definition  module X exports context-free syntax    "abc" -\> ABC`).modules["X"].rules[sort("ABC")] ==
         choice(sort("ABC"), {prod(sort("ABC"),[lit("abc")],{})});

// \char-class([range(97,122),range(48,57)])
test bool test2() = rs := sdf2grammar(
		(SDF) `definition
		      'module PICOID
          'exports
          'lexical syntax
          '   [a-z] [a-z0-9]* -\> PICO-ID  
          'lexical restrictions
          '  PICO-ID -/- [a-z0-9]`).modules["PICOID"].rules 
     && prod(lex("PICO-ID"),[\char-class([range(97,122)]),\conditional(\iter-star(\char-class([range(97,122),range(48,57)])),{\not-follow(\char-class([range(97,122),range(48,57)]))})],{}) in rs[lex("PICO-ID")]          
     ;
     
test bool test3() = rs := sdf2grammar(
		(SDF) `definition
		      'module StrChar
          'exports
          ' lexical syntax
          '   ~[\\0-\\31\\n\\t\\"\\\\]          -\> StrChar {cons("normal")}`).rules
     && prod(label("normal",sort("StrChar")),[\char-class([range(26,33),range(35,91),range(93,65535)])],{}) in rs[sort("StrChar")]
     ;
       
public set[Production] getProductions(Module \mod) {
 res = {};
 visit (\mod) {
    case (Grammar) `syntax <Prod* prods>`: 
    	res += getProductions(prods, true);
    case (Grammar) `lexical syntax <Prod* prods>`: 
    	res += getProductions(prods, true); 
    case (Grammar) `context-free syntax <Prod* prods>`: 
    	res += getProductions(prods, false);
    case (Grammar) `priorities <{Priority ","}* prios>`:
    	res += getPriorities(prios,false);
    case (Grammar) `lexical priorities <{Priority ","}* prios>`: 
    	res += getPriorities(prios,true);
    case (Grammar) `context-free priorities <{Priority ","}* prios>`: 
    	res += getPriorities(prios,false);
  }; 
  
  return res;
}


    
test bool test4() = getProductions((SDF) `definition module A exports syntax A -\> B`) ==
     {prod(sort("B"),[sort("A")],{})};
     
test bool test5() = getProductions((SDF) `definition module A exports lexical syntax A -\> B`) ==
     {prod(lex("B"),[sort("A")],{})};
     
test bool test6() = getProductions((SDF) `definition module A exports lexical syntax A -\> B B -\> C`) ==
     {prod(lex("C"),[sort("B")],{}),prod(lex("B"),[sort("A")],{})};
     
test bool test7() = getProductions((SDF) `definition module A exports context-free syntax A -\> B`) ==
     {prod(sort("B"),[sort("A")],sort("B"),{})};
     
test bool test9() = getProductions((SDF) `definition module A exports priorities A -\> B \> C -\> D`) ==
     {prod(sort("B"),[sort("A")],{}),prod(sort("D"),[sort("C")],{})};

test bool test9() = getProductions((SDF) `definition module A exports priorities B "*" B -\> B \> B "+" B -\> B`) ==
     {priority(sort("B"),[prod(sort("B"),[sort("B"),lit("*"),sort("B")],{}),prod(sort("B"),[sort("B"),lit("+"),sort("B")],{})])};


public set[Production] getProductions(Prod* prods, bool isLex){
	return {*fixParameters(getProduction(prod, isLex)) | Prod prod <- prods};
}

set[Production] fixParameters(set[Production] input) {
  return innermost visit(input) {
    case prod(\parameterized-sort(str name, [pre*, sort(str x), post*]),lhs,  as) =>
         prod(\parameterized-sort(name,[pre,\parameter(x),post]),visit (lhs) { case sort(x) => \parameter(x) }, as)
  }
}

public set[Production] getProduction(Prod P, bool isLex) {
  switch (P) {
    case (Prod) `<Syms syms> -\> LAYOUT <Attrs ats>` :
        return {prod(layouts("LAYOUTLIST"),[\iter-star(sort("LAYOUT"))],{}),
                prod(sort("LAYOUT"), getSymbols(syms, isLex),getAttributes(ats))};
    case (Prod) `<Syms syms> -\> <Sym sym> {<{Attribute ","}* x>, reject, <{Attribute ","}* y> }` :
        return {prod(keywords(getSymbol(sym, isLex).name + "Keywords"), getSymbols(syms, isLex), {})};
    case (Prod) `<Syms syms> -\> <Sym sym> {<{Attribute ","}* x>, cons(<StrCon n>), <{Attribute ","}* y> }` :
        return {prod(label(unescape(n),getSymbol(sym, isLex)), getSymbols(syms, isLex), getAttributes((Attrs) `{<{Attribute ","}* x>, <{Attribute ","}* y> }`))};
    case (Prod) `<Syms syms> -\> <Sym sym> <Attrs ats>` : 
        return {prod(getSymbol(sym, isLex), getSymbols(syms, isLex),getAttributes(ats))};
    default: {
        println("WARNING: not importing <P>");
    	return {prod(sort("IGNORED"),[],{\tag("NotSupported"("<P>"))})};
    }
  }
}

test bool test10() = getProduction((Prod) `PICO-ID ":" TYPE -\> ID-TYPE`, false) == 
     prod(sort("ID-TYPE"),[sort("PICO-ID"),lit(":"),sort("TYPE")],{});
     
test bool test11() = getProduction((Prod) `PICO-ID ":" TYPE -\> ID-TYPE`, true) == 
     prod(sort("ID-TYPE"),[sort("PICO-ID"),lit(":"),sort("TYPE")],attrs([\lex()]));

test bool test12() = getProduction((Prod) `PICO-ID ":" TYPE -\> ID-TYPE {cons("decl"), left}`, false) ==
     prod(sort("ID-TYPE"),[sort("PICO-ID"), lit(":"), sort("TYPE")],{\assoc(left())});
               
test bool test13() = getProduction((Prod) `[\\ \\t\\n\\r]	-\> LAYOUT {cons("whitespace")}`, true) == 
	 prod(sort("LAYOUT"),[\char-class([range(32,32),range(9,9),range(10,10),range(13,13)])],{});
 
test bool test14() = getProduction((Prod) `{~[\\n]* [\\n]}* -\> Rest`, true) ==
     prod(sort("Rest"),[\iter-star-seps(\iter-star(\char-class([range(0,9),range(11,65535)])),[\char-class([range(10,10)])])],{});


public set[Symbol] getConditions(SDF m) {
  res = {};
  visit (m) {
    case (Grammar) `restrictions <Restriction* rests>`:
      res += getRestrictions(rests, false);
    case (Grammar) `lexical restrictions <Restriction* rests>`:
      res += getRestrictions(rests, true);
    case (Grammar) `context-free restrictions <Restriction* rests>` :
      res += getRestrictions(rests, false);
    case (Prod) `<Syms syms> -\> <Sym sym> {<{Attribute ","}* x>, reject, <{Attribute ","}* y> }` :
      res += {conditional(getSymbol(sym, false), {\delete(keywords(getSymbol(sym, false).name + "Keywords"))})
               ,conditional(getSymbol(sym, true), {\delete(keywords(getSymbol(sym, true).name + "Keywords"))})};
   }
   
   while ({conditional(s, cs1), conditional(s, cs2), rest*} := res)
       res = rest + {conditional(s, cs1 + cs2)};
   
   iprintln(res);
   return res;
}
      
    
public set[Symbol] getRestrictions(Restriction* restrictions, bool isLex) {
println("looping over < restrictions>");
  res = { *getRestriction(r, isLex) | Restriction r <- restrictions };
  println("collected: <res>");
  return res;
}

public set[Symbol] getRestriction(Restriction restriction, bool isLex) {
  println("getting rest: <restriction>");
  switch (restriction) {
    case (Restriction) `-/- <Lookaheads ls>` :
    	return {};
    
    case (Restriction) `<Sym s1> -/- <Lookaheads ls>` : 
      return {conditional(getSymbol(s1, isLex), {\not-follow(l) | l <- getLookaheads(ls) })};
  	
    case (Restriction) `<Sym s1> <Sym+ rest> -/- <Lookaheads ls>` : 
      return  getRestriction((Restriction) `<Sym s1> -/- <Lookaheads ls>`, isLex)
           + {getRestriction((Restriction) `<Sym s> -/- <Lookaheads ls>`, isLex) | Sym s <- rest};
    
    case (Restriction) `LAYOUT? -/- <Lookaheads ls>` :
      return {conditional(\iter-star(sort("LAYOUT")), {\not-follow(l) | l <- getLookaheads(ls) })};
             
       
    default: {
       println("WARNING: ignored <restriction>");
       return {};
    }
  }
}

test bool test18() = getRestriction((Restriction) `-/- [a-z]`, true) == {};

test bool test19() = getRestriction((Restriction) `ID -/- [a-z]`, true) == 
     {conditional(sort("ID"),{\not-follow(\char-class([range(97,122)]))})};
   

// ----- getLookaheads, getLookahead -----

public set[Symbol] getLookaheads(Lookaheads ls) {
   switch (ls) {
     case (Lookaheads) `<Class c>` :
     	return {getCharClass(c)};
     	  	
     case (Lookaheads) `<Lookaheads l> | <Lookaheads r>` :
     	return getLookaheads(l) + getLookaheads(r);
     	
     case (Lookaheads) `(<Lookaheads l>)` :
     	return getLookaheads(l);
     	
     default: {
        println("Warning: ignored <ls>");
        return {};
     }
   }
}
 
test bool test21() = getLookaheads((Lookaheads) `[a-z]`) == 
     {\char-class([range(97,122)])};
     
test bool test22() = getLookaheads((Lookaheads) `[a-z] . [0-9]`) ==
     {};
       
test bool test23() = getLookaheads((Lookaheads) `[a-z]  | [\\"]`) ==
     {\char-class([range(97,122)]),
      \char-class([range(34,34)])};
      
// ----- getPriorities, getPriority -----

public set[Production] getPriorities({Priority ","}* priorities, bool isLex) {
  return {getPriority(p, isLex) | Priority p <- priorities};
}

public Production getPriority(Group group, bool isLex) {
	switch (group) {
    case (Group) `<Prod p>` :   
      	return choice(definedSymbol(p, isLex), getProduction(p, isLex));
       
    case (Group) `<Group g> .` :
     	return getPriority(g, isLex); // we ignore non-transitivity here!
     	
    case (Group) `<Group g> <ArgumentIndicator i>` : 
     	return getPriority(g, isLex); // we ignore argument indicators here!
     	
    case (Group) `{<Prod* ps>}` : 
       return choice(definedSymbol(ps,isLex), {getProduction(p,isLex) | Prod p <- ps});
       
    case (Group) `{<Assoc a> : <Prod* ps>}` : 
       return \associativity(definedSymbol(ps, isLex), getAssociativity(a), {getProduction(p,isLex) | Prod p <- ps});
    
    default:
    	throw "missing case <group>";}
}
     	
test bool test24() = getPriority((Group) `A -\> B`, false) == 
     prod([sort("A")],sort("B"),{});
     
test bool test25() = getPriority((Group) `A -\> B .`, false) ==  
     prod([sort("A")],sort("B"),{});
         
test bool test26() = getPriority((Group) `A -\> B \<1\>`, false) == 
     prod(sort("B"),[sort("A")],{});
         
test bool test27() = getPriority((Group) `{A -\> B C -\> D}`, false) == 
	 choice(sort("B"),{prod([sort("C")],sort("D"),{}),prod([sort("A")],sort("B"),{})});  
	   
test bool test28() = getPriority((Group) `{left: A -\> B}`, false) == 
     \associativity(sort("B"),\left(),{prod([sort("A")],sort("B"),{})});
     
test bool test29() = getPriority((Group) `{left: A -\> B B -\> C}`, false) ==
     \associativity(sort("B"),\left(),{prod([sort("B")],sort("C"),{}),prod([sort("A")],sort("B"),{})});

public Production getPriority(Priority p, bool isLex) {
   switch (p) {
     case (Priority) `<Group g>`:
     	return getPriority(g, isLex);
         
     case (Priority) `<Group g1> <Assoc a> <Group g2>` : 
       return \associativity(definedSymbol(g1, isLex), getAssociativity(a), {getPriority((Priority) `<Group g1>`, isLex), getPriority((Priority) `<Group g2>`,isLex)});
 
     case (Priority) `<{Group "\>"}+ groups>` : 
       return priority(definedSymbol(groups,isLex), [getPriority(group ,isLex) | Group group <- groups]);
   }
}


test bool test30() = getPriority((Priority) `A -\> B`, false) == 
     priority(sort("B"),[prod(sort("B"),[sort("A")],{})]);
     
test bool test31() = getPriority((Priority) `A -\> B .`, false) == 
     priority(sort("B"),[prod([sort("A")],{})]);

test bool test32() = getPriority((Priority) `A -\> B \<1\>`, false) == 
     prod(sort("B"),[sort("A")],{});
     
test bool test33() = getPriority((Priority) `{A -\> B C -\> D}`, false) == 
     priority(sort("B"),[choice(sort("B"),{prod(sort("D"),[sort("C")],{}),prod(sort("B"),[sort("A")],{})})]);
     
test bool test34() = getPriority((Priority) `A -\> B \> C -\> D`, false) ==
     priority(sort("B"),[prod(sort("B"),[sort("A")],{}),prod(sort("D"),[sort("C")],{})]);
     
test bool test35() = getPriority((Priority) `A -\> B \> C -\> D \> E -\> F`, false) ==
     priority(sort("B"),[prod(sort("B"),[sort("A")],{}),prod(sort("D"),[sort("C")],{}),prod(sort("F"),[sort("E")],{})]);


// ----- definedSymbol -----

public Symbol definedSymbol((&T <: Tree) v, bool isLex) {
  // Note that this might not work if there are different right-hand sides in the group...
  // I don't know what to do about this yet.
  if (/(Prod) `<Sym* _> -\> <Sym s> <Attrs _>` := v) {
    return getSymbol(s, isLex);
  } else if (/(Prod) `<Sym* _> -\> LAYOUT <Attrs _>` := v) {
    return sort("LAYOUT");
  }
  throw "could not find a defined symbol in <v>";
}

// ----- getStartSymbols -----

public set[Symbol] getStartSymbols(Module \mod) {
  result = {};
  visit(\mod) {
    case (Grammar) `context-free start-symbols <Sym* syms>` :
    	result += { getSymbol(sym, false) | sym <- syms };
    case (Grammar) `lexical start-symbols <Sym* syms>`      :
    	result += { getSymbol(sym, true) | sym <- syms };
    case (Grammar) `start-symbols <Sym* syms>`              :
    	result += { getSymbol(sym, true) | sym <- syms };
  }
  return result;
}

test bool test36() = getStartSymbols((SDF) `definition module M exports context-free start-symbols A B C`) == 
     {sort("A"), sort("B"), sort("C")};
     
test bool test37() = getStartSymbols((SDF) `definition module M exports lexical start-symbols A B C`) == 
     {sort("A"), sort("B"), sort("C")};
     
test bool test38() = getStartSymbols((SDF) `definition module M exports start-symbols A B C`) == 
     {sort("A"), sort("B"), sort("C")};

public list[Symbol] getSymbols((Syms) `<Sym* ss>`, bool isLex) = [getSymbol(sym, isLex) | sym <- ss];

test bool test39() = getSymbols((Syms) `A B "ab"`, true) == [sort("A"), sort("B"), lit("ab")];
     
public Symbol getSymbol(Sym sym, bool isLex) {
  switch (sym) {
    case (Sym) `LAYOUT ?`:
        return \layouts("LAYOUTLIST");
    case (Sym) `<StrCon l> : <Sym s>`:
		return label(unescape(l), getSymbol(s,isLex));
		
    case (Sym) `<IdCon i> : <Sym s>`:
    	return label("<i>", getSymbol(s, isLex));
    	
   	case (Sym) `LAYOUT`:
    	return sort("LAYOUT"); 
    	
    case (Sym) `<StrCon l>`:
          	return lit(unescape(l));
    	
    case (Sym) `<SingleQuotedStrCon l>`:
    	return cilit(unescape(l));  
    	
    case (Sym) `<Sort n>[[<{Sym ","}+ syms>]]`:
    	return \parameterized-sort("<n>",separgs2symbols(syms,isLex));
    	
    case (Sym) `<Sym s> ?`:
    	return opt(getSymbol(s,isLex));
    	
    case (Sym) `<Class cc>`:
    	return getCharClass(cc);
    	
    case (Sym) `\< <Sym sym> -LEX \>`:
        return getSymbol(sym, true);
        
    case (Sym) `\< <Sym sym> -CF \>`:
        return getSymbol(sym, false);
       
    case (Sym) `\< <Sym sym> -VAR \>`:
        return getSymbol(sym, isLex);
        
    case (Sym) `<Sym lhs> | <Sym rhs>` : 
        return alt({getSymbol(lhs, isLex), getSymbol(rhs, isLex)});
       
  }  
  
  if (isLex) switch (sym) {
    case (Sym) `<Sort n>`:
        return lex("<n>");
        
    case (Sym) `<Sym s> *`: 
    	return \iter-star(getSymbol(s,isLex));
    	
    case (Sym) `<Sym s> +`  :
    	return \iter(getSymbol(s,isLex));
    	
    case (Sym) `<Sym s> *?` :
    	return \iter-star(getSymbol(s,isLex));
    	
    case (Sym) `<Sym s> +?` :
    	return \iter(getSymbol(s,isLex));
    	
    case (Sym) `{<Sym s> <Sym sep>} *`  :
    	return \iter-star-seps(getSymbol(s,isLex), [getSymbol(sep, isLex)]);
    	
    case (Sym) `{<Sym s> <Sym sep>} +`  :
    	return \iter-seps(getSymbol(s,isLex), [getSymbol(sep, isLex)]);
    	
    case (Sym) `<Sym s>?` :
        return \opt(getSymbol(s, isLex));
        
    case (Sym) `(<Sym first> <Sym+ rest>)` :
        return seq([getSymbol(first, isLex)] + [getSymbol(e, isLex) | e <- rest]);

    case (Sym) `(<Sym s>)` :
        return getSymbol(s, isLex);
    	
    default: throw "missed a case <sym>";
  } 
  else switch (sym) {  
    case (Sym) `<Sort n>`:
        return sort("<n>");
        
    case (Sym) `<Sym s> *`:
    	return \iter-star-seps(getSymbol(s,isLex),[\layouts("LAYOUTLIST")]);
    	
    case (Sym) `<Sym s> +` :
    	return \iter-seps(getSymbol(s,isLex),[\layouts("LAYOUTLIST")]);
    	
    case (Sym) `<Sym s> *?`:
    	return \iter-star-seps(getSymbol(s,isLex),[\layouts("LAYOUTLIST")]);
    	
    case (Sym) `<Sym s> +?`:
    	return \iter-seps(getSymbol(s,isLex),[\layouts("LAYOUTLIST")]);
    	
    case (Sym) `{<Sym s> <Sym sep>} *` :
    	return \iter-star-seps(getSymbol(s,isLex), [\layouts("LAYOUTLIST"),getSymbol(sep, isLex),\layouts("LAYOUTLIST")]);
    	
    case (Sym) `{<Sym s> <Sym sep>} +` :
    	return \iter-seps(getSymbol(s,isLex), [\layouts("LAYOUTLIST"),getSymbol(sep, isLex),\layouts("LAYOUTLIST")]);
    	
    case (Sym) `(<Sym first> <Sym+ rest>)` :
        return seq([getSymbol(first, isLex)] + [\layouts("LAYOUTLIST"), getSymbol(e, isLex) | e <- rest]);
        
    case (Sym) `(<Sym first> | <Sym second>)` :
         return alt({getSymbol(first, isLex), getSymbol(second, isLex)});
    default: throw "missed a case <sym>";  
  }
}

public Symbol alt({alt(set[Symbol] ss), *Symbol rest}) = alt(ss + rest);

test bool test40() = getSymbol((Sym) `"abc"`, false) 		== lit("abc");
test bool test41() = getSymbol((Sym) `"a\\\\c"`, false) 		== lit("a\\c");
test bool test42() = getSymbol((Sym) `"a\>c"`, false) 		== lit("a\>c");
test bool test43() = getSymbol((Sym) `ABC`, false) 			== sort("ABC");
test bool test44() = getSymbol((Sym) `'abc'`, false) 		== cilit("abc");
test bool test45() = getSymbol((Sym) `abc : ABC`, false) 	== label("abc",sort("ABC"));
test bool test46() = getSymbol((Sym) `"abc" : ABC`, false) 	== label("abc",sort("ABC"));
test bool test47() = getSymbol((Sym) `A[[B]]`, false) 		== \parameterized-sort("A", [sort("B")]);
test bool test48() = getSymbol((Sym) `A?`, false) 			== opt(sort("A"));
test bool test49() = getSymbol((Sym) `[a]`, false) 			== \char-class([range(97,97)]);
test bool test50() = getSymbol((Sym) `A*`, false) 			== \iter-star-seps(sort("A"),[\layouts("LAYOUTLIST")]);
test bool test51() = getSymbol((Sym) `A+`, false) 			== \iter-seps(sort("A"),[\layouts("LAYOUTLIST")]);
test bool test52() = getSymbol((Sym) `A*?`, false) 			== opt(\iter-star-seps(sort("A"),[\layouts("LAYOUTLIST")]));
test bool test53() = getSymbol((Sym) `A+?`, false) 			== opt(\iter-seps(sort("A"),[\layouts("LAYOUTLIST")]));
test bool test54() = getSymbol((Sym) `{A "x"}*`, false) 		== \iter-star-seps(sort("A"),[\layouts("LAYOUTLIST"),lit("x"),\layouts("LAYOUTLIST")]);
test bool test55() = getSymbol((Sym) `{A "x"}+`, false) 		== \iter-seps(sort("A"),[\layouts("LAYOUTLIST"),lit("x"),\layouts("LAYOUTLIST")]);
test bool test56() = getSymbol((Sym) `{A "x"}*?`, false) 	== opt(\iter-star-seps(sort("A"),[\layouts("LAYOUTLIST"),lit("x"),\layouts("LAYOUTLIST")]));
test bool test57() = getSymbol((Sym) `{A "x"}+?`, false) 	== opt(\iter-seps(sort("A"),[\layouts("LAYOUTLIST"),lit("x"),\layouts("LAYOUTLIST")]));
test bool test58() = getSymbol((Sym) `A*`, true) 			== \iter-star(sort("A"));
test bool test59() = getSymbol((Sym) `A+`, true) 			== \iter(sort("A"));
test bool test60() = getSymbol((Sym) `A*?`, true) 			== opt(\iter-star(sort("A")));
test bool test61() = getSymbol((Sym) `A+?`, true) 			== opt(\iter(sort("A")));
test bool test62() = getSymbol((Sym) `{A "x"}*`, true) 		== \iter-star-seps(sort("A"),[lit("x")]);
test bool test63() = getSymbol((Sym) `{A "x"}+`, true) 		== \iter-seps(sort("A"),[lit("x")]);
test bool test64() = getSymbol((Sym) `{A "x"}*?`, true) 		== opt(\iter-star-seps(sort("A"),[lit("x")]));
test bool test65() = getSymbol((Sym) `{A "x"}+?`, true) 		== opt(\iter-seps(sort("A"),[lit("x")]));

//test getSymbol((Sym) `<LAYOUT? -CF>`, true) ==
//test getSymbol((Sym) `<RegExp -LEX>`, true) ==

// ----- unescape -----
// Take a string constant and replace all escaped characters by the character itself.

// Unescape on Symbols. Note that the function below can currently coexist with the two unescape functions
// since StrCon and SingleQuotedStrCons are *not* a subtype of Symbol (which they should be).
// Do a deep match (/) to skip the chain function that syntactically includes both subtypes in Symbol.

private str unescape(Sym s) {
  if (/SingleQuotedStrCon scon := s) {
  	return unescape(scon);
  }
  if (/StrCon scon := s) {
  	return unescape(scon);
  }
  throw "unexpected string format: <s>";
}

public str unescape(StrCon s) { 
   if ([StrCon] /^\"<chars:.*>\"$/ := s)
  	return unescapeStr(chars);
   throw "unexpected string format: <s>";
}

private str unescape(SingleQuotedStrCon s) {
   if ([SingleQuotedStrCon] /^\'<chars:.*>\'$/ := s)
     return unescapeStr(chars);
   throw "unexpected string format: <s>";
}

test bool testUn1() = unescape((StrCon) `"abc"`)  	== "abc";
test bool testUn2() = unescape((StrCon) `"a\\nc"`) 	== "a\nc";
test bool testUn3() = unescape((StrCon) `"a\\"c"`) 	== "a\"c";
test bool testUn4() = unescape((StrCon) `"a\\\\c"`) 	== "a\\c";
test bool testUn5() = unescape((StrCon) `"a\\\\\\"c"`)	== "a\\\"c";

test bool testUn6() = unescape((SingleQuotedStrCon) `'abc'`)  == "abc";
test bool testUn7() = unescape((SingleQuotedStrCon) `'a\\nc'`) == "a\nc";
test bool testUn8() = unescape((SingleQuotedStrCon) `'a\\'c'`) == "a\'c";
test bool testUn9() = unescape((SingleQuotedStrCon) `'a\\\\c'`) == "a\\c";

// unescapeStr: do the actual unescaping on a string
// Also takes care of escaping of < and > characters as required for Rascal strings (TO DO/CHECK)

public str unescapeStr(str chars){
   return visit (chars) {
       case /^\\b/           => "\b"
       case /^\\t/           => "\t"
       case /^\\n/           => "\n"
       case /^\\f/           => "\f"
       case /^\\r/           => "\r"  
       case /^\\\'/          => "\'"
       case /^\\"/           => "\""
       case /^\\\\/          => "\\"
       case /^\\TOP/         => "\u00FF"
       case /^\\EOF/         => "\u00A0"
       case /^\\BOT/         => "\u0000"
       case /^\\LABEL_START/ => "\u00A1"
       case /^\</			 => "\<"
       case /^\>/			 => "\>"
     };  
}

test bool un20() =Str("abc") 	== "abc";
test bool un21() =Str("a\nbc") 	== "a\nbc";
test bool un22() =Str("a\\\nbc") == "a\\\nbc";
test bool un23() =Str("a\"bc") 	== "a\"bc";
test bool un24() =Str("a\\\"bc") == "a\"bc";
test bool un25() =Str("a\\bc") 	== "a\bc";
test bool un26() =Str("a\\\\tc") == "a\\tc";
test bool un27() =Str("a\>b")    == "a\>b";
test bool un28() =Str("a\<b")    == "a\<b";

public Symbol getCharClass(Class cc) {
   switch(cc) {
     case (Class) `[]` :
     	return \char-class([]);
     	
     case (Class) `[<Range* ranges>]` :
     		return \char-class([getCharRange(r) | /Range r := ranges]);
     	
     case (Class) `(<Class c>)`: 
     	return getCharClass(c);
     	
     case (Class) `~ <Class c>`:
     	return complement(getCharClass(c));
     	
     case (Class) `<Class l> /\\ <Class r>`:
     	return intersection(getCharClass(l),getCharClass(r));
     	
     case (Class) `<Class l> \\/ <Class r>`: 
     	return union(getCharClass(l),getCharClass(r));
     	
     case (Class) `<Class l> / <Class r>`:
     	return difference(getCharClass(l),getCharClass(r));
     	
     default: throw "missed a case <cc>";
   }
}

test bool testCC1() = ((Class) `[]`)         == \char-class([]);
test bool testCC2() = ((Class) `[a]`)        == \char-class([range(97,97)]);
test bool testCC3() = ((Class) `[a-z]`)      == \char-class([range(97,122)]);
test bool testCC4() = ((Class) `[a-z0-9]`)   == \char-class([range(97,122), range(48,57)]);
test bool testCC5() = ((Class) `([a])`)      == \char-class([range(97,97)]);
test bool testCC6() = ((Class) `~[a]`)       == complement(\char-class([range(97,97)]));
test bool testCC7() = ((Class) `[a] /\\ [b]`) == intersection(\char-class([range(97,97)]), \char-class([range(98,98)]));
test bool testCC8() = ((Class) `[a] \\/ [b]`) == union(\char-class([range(97,97)]), \char-class([range(98,98)]));
test bool testCC9() = ((Class) `[a] / [b]`)  == difference(\char-class([range(97,97)]), \char-class([range(98,98)]));
test bool testCC10() = ((Class) `[\\n]`)       == \char-class([range(10,10)]);
test bool testCC11() = ((Class) `[\\t\\n]`)     == \char-class([range(9,9), range(10,10)]);
test bool testCC12() = ((Class) `~[\\0-\\31\\n\\t\\"\\\\]`) ==
     complement(\char-class([range(0,25),range(10,10),range(9,9),range(34,34),range(92,92)]));
test bool testCC13() = ((Class) `[\\"]`)       == \char-class([range(34,34)]);

// ----- getCharRange -----
      
public CharRange getCharRange(Range r) {
  switch (r) {
    case (Range) `<Character c>` : return range(getCharacter(c),getCharacter(c));
    case (Range) `<Character l> - <Character r>`: return range(getCharacter(l),getCharacter(r));
    default: throw "missed a case <r>";
  }
}

test bool testCR1() = getCharRange((Range) `a`)   	== range(97,97);
test bool testCR2() = getCharRange((Range) `a-z`) 	== range(97,122);
test bool testCR3() = getCharRange((Range) `\\n`)  	==  range(10,10);
test bool testCR4() = getCharRange((Range) `\\1-\\31`)	==  range(1,25);

public int getCharacter(Character c) {
  switch (c) {
    case [Character] /\\<oct:[0-3][0-7][0-7]>/ : return toInt("0<oct>");
    case [Character] /\\<oct:[0-7][0-7]>/      : return toInt("0<oct>");
    case [Character] /\\<oct:[0-7]>/           : return toInt("0<oct>");
    case [Character] /\\t/                     : return 9;
    case [Character] /\\n/                     : return 10;
    case [Character] /\\r/                     : return 13;
    case [Character] /\\ /                     : return 32;
    case [Character] /\\<esc:["'\-\[\]\\ ]>/   : return charAt(esc, 0);
    case [Character] /<ch:[^"'\-\[\]\\ ]>/     : return charAt(ch, 0);
    
    default: throw "missed a case <c>";
  }
}
 
test bool testCCX1() = ((Character) `a`)    == charAt("a", 0);
test bool testCCX2() = ((Character) `\\\\`)   == charAt("\\", 0);
test bool testCCX3() = ((Character) `\\'`)   == charAt("\'", 0);
test bool testCCX4() = ((Character) `\\1`)   == toInt("01");
test bool testCCX5() = ((Character) `\\12`)  == toInt("012");
test bool testCCX6() = ((Character) `\\123`) == toInt("0123");
test bool testCCX7() = ((Character) `\\n`)   == 10; 

// ----- getAttributes, getAttribute, getAssociativity -----

public set[Attr] getAttributes(Attrs as) {
  if ((Attrs) `{ <{Attribute ","}* mods> }` := as) {
	  return {*getAttribute(m) | Attribute m <- mods};
  }
  return {};
}
   
test bool testAs() = getAttributes((Attrs) `{left, cons("decl")}`) == attrs([\assoc(\left()),term("cons"("decl"))]);

public set[Attr] getAttribute(Attribute m) {
  switch (m) {
    case (Attribute) `<Assoc as>`:
     	return {\assoc(getAssociativity(as))};
     	
    case (Attribute) `bracket`:
    	return {\bracket()};
    
    case (Attribute) `cons(<StrCon c>)` : 
        return {};
        
    case (Attribute) `memo`:
    	return {\tag("NotSupported"("memo"))};
    	
    case (Attribute) `prefer`:
        return {\tag("NotSupported"("prefer"))};
        
    case (Attribute) `avoid` :
        return {\tag("NotSupported"("avoid"))};
    	
    case (Attribute) `reject` :
        return {};
        
    case (Attribute) `category(<StrCon a>)` :
        return {\tag("category"(unescape(a)))};
        
    case (Attribute) `<IdCon c>(<StrCon a>)` : 
        return {\tag("NotSupported"("<c>"(unescape(a))))};
        
    case (Attribute) `<ATerm t>`:
        return {\tag("NotSupported"("<t>"))};
        
    default: 
        return {};
  }
}

test bool testAs2() = getAttribute((Attribute) `left`)        == \assoc(\left());
 
private Associativity getAssociativity(Assoc as){
  switch (as) {
    case (Assoc) `left`:        
    	return \left();
    case (Assoc) `right`:
    	return \right();
    case (Assoc) `non-assoc`:
    	return \non-assoc();
    case (Assoc) `assoc`:
    	return \assoc();
    default:
    	throw "missed a case <as>";
  }
}
 
test bool testAssoc() = getAssociativity((Assoc) `left`) == \left();

private list[Symbol] separgs2symbols({Sym ","}+ args, bool isLex) {
  return [ getSymbol(s, isLex) | Sym s <- args ];
}
