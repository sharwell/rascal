module Dominators1

/*
 * Example from T. Lengauer and R.E. Tarjan, A Fast Algorithm for Finding Dominators
 * in a Flowgraph, TOPLAS, Vol. 1, N0. 1, July 1979, 121-141.
 *
 *    +------------------- R ----------+
 *    |         +----- / /             |
 *    |         |       /              |
 *    |         |       v              v
 *    |         |    +--B       +----- C -----+
 *    |         |    |  |       |             | 
 *    |         v    |  |       |             |       
 *    |         A <--+  |       |             |
 *    |         |       |       |             |
 *    |         v       v       v             v       
 *    |         D    +--E       F      +----- G
 *    |         |    |  ^       |      |      |
 *    |         v    |  |       |      |      |
 *    |         L    |  |       |      |      |
 *    |         |    v  |       |      v      v
 *    |         +->  H -+       +----> I <--- J
 *    |              +--------+      ^ |
 *    |                       |     /  |
 *    |                       v   /    |
 *    +---------------------> K / <----+
 */

import Set;
import Relation;
import Graph;

str ROOT = "R";

rel[str,str] PRED ={
<"R", "A">,<"R", "B">, <"R", "C">,
<"A", "D">,
<"B", "A">, <"B", "D">, <"B", "E">,
<"C", "F">, <"C", "G">,
<"D", "L">,
<"E", "H">,
<"F", "I">,
<"G", "I">, <"G", "J">,
<"H", "K">, <"H", "E">,
<"I", "K">, 
<"K", "I">, <"K", "R">,
<"L", "H">
};

set[str] VERTICES = Relation::carrier(PRED);

rel[str,set[str]] DOMINATES =
   { <V,  (VERTICES - {V, ROOT}) - range(reachX({ROOT}, {V}, PRED))> |  str V : VERTICES};

public bool checkResult(){
	return Dominators1::DOMINATES ==
 	{
	<"R", {"A", "B", "C", "D", "E", "F", "G", "L", "H", "I", "J", "K"}>, 
	<"A", {}>, 
	<"B", {}>, 
	<"C", {"F", "G", "J"}>, 
	<"D", {"L"}>, 
	<"E", {}>, 
	<"F", {}>, 
	<"G", {"J"}>, 
	<"L", {}>, 
	<"H", {}>, 
	<"I", {}>, 
	<"J", {}>, 
	<"K", {}>
	};
}