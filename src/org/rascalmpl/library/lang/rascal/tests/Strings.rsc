module lang::rascal::tests::Strings

import IO;
import String;
import List;
import util::Math;


public test bool subscription(str S){
  R = "";
  for(int i <- [0..size(S)]){
      R += S[i];
  }
  return R == S;
}

public test bool subscriptionWrapped(str S){
  for(int i <- [0 .. size(S)]){
      if(S[i] != S[i - size(S)]){
      	 return false;
      }
  }
  return true;
}

public test bool sliceFirst(str L) {
  if(isEmpty(L)) return true;
  f = arbInt(size(L));
  e = f + arbInt(size(L) - f);
  S = L[f .. e];
  return S == makeSlice(L, f, f + 1, e);
}

public test bool sliceFirst(str L) {
  if(isEmpty(L)) return true;
  f = arbInt(size(L));
  S = L[f..];
  return S == makeSlice(L, f, f + 1, size(L));
}

// In an ideal world, this should work, but we have to adapt ranges first ...

//public list[int] makeSlice(list[int] L, int b, int s, int e){
//  return
//    for(int i <- [b, s .. e])
//      append L[i];
//}

public str makeSlice(str L, int f, int s, int e){
  res = "";
  int i = f;
  int delta = s - f;
  if(delta == 0 || f == e)
     return res;
  if(f <= e){
     while(i >= 0 && i < size(L) && i < e ){
        res += L[i];
        i += delta;
     }
  } else {
    while(i >= 0 && i < size(L) && i > e){
        res += L[i];
        i += delta;
     }
  }
  return res;
}

public test bool sliceFirstSecond(str L) {
  if(isEmpty(L)) return true;
  f = arbInt(size(L));
  incr = 2;
  return L[f, f + incr..] == makeSlice(L, f, f + incr, size(L));
}


public test bool sliceEnd(str L) {
  if(isEmpty(L)) return true;
  e = arbInt(size(L));
  return L[..e] == makeSlice(L, 0, 1, e);
}

public test bool sliceSecondEnd(str L) {
  if(isEmpty(L)) return true;
  e = arbInt(size(L));
  incr = 2;
  first = incr > e ? size(L)-1 : 0;
  return L[,incr..e] == makeSlice(L, first, incr, e);
}

public tuple[int,int] arbFirstEnd(str L){
  if(isEmpty(L)) throw "No beging/end indices possible";
  if(size(L) == 1) return <0,0>;
  f = arbInt(size(L));
  e = f + arbInt(size(L) - f);
  return <f, e>;
}
public test bool sliceFirstSecondEnd(str L) {
  if(isEmpty(L)) return true;
  <f, e> = arbFirstEnd(L);
  incr = 2;
  return L[f, f + incr .. e] == makeSlice(L, f, f + incr, e);
}

public test bool sliceFirstNegative(str L) {
  if(isEmpty(L)) return true;
  f = 1;
  return L[-f..] == makeSlice(L, size(L) - f,  size(L) - f + 1, size(L));
}

public test bool sliceEndNegative(str L) {
  if(isEmpty(L)) return true;
  e = arbInt(size(L));
  return L[..-e] == makeSlice(L, 0, 1, e == 0 ? e : size(L) - e);
}

public test bool sliceFirstNegativeSecondNegative(str L) {
  if(isEmpty(L)) return true;
  f = arbInt(size(L));
  incr = 2;
  if(f == 0)
     return L[0, -incr..] == makeSlice(L, 0, size(L) - incr, size(L));
  else
     return L[-f, -(f + incr)..] == makeSlice(L, size(L) - f, size(L) - (f + incr), -1);
}

public test bool sliceSecondNegative(str L) {
  if(isEmpty(L)) return true;
  incr = 2;
  S = L[, -incr ..];
  return S == makeSlice(L, 0, size(L) - incr, size(L));
}

public test bool assignSlice() { L = "abcdefghij"; L[..] = "XY"; return L == "XYXYXYXYXY";}
public test bool assignSlice() { L = "abcdefghij"; L[2..] = "XY"; return   L == "abXYXYXYXY";}
public test bool assignSlice() { L = "abcdefghij"; L[2..6] = "XY"; return L == "abXYXYghij";}
public test bool assignSlice() { L = "abcdefghij"; L[8..3] = "XY"; return L == "abcdXYXYXj";}

public test bool assignStep() { L = "abcdefghij"; L[,2..] = "X"; return L == "XbXdXfXhXj";}
public test bool assignStep() { L = "abcdefghij"; L[,2..] = "XY"; return L == "XbYdXfYhXj";}
public test bool assignStep() { L = "abcdefghij"; L[,2..] = "X"; return L == "XbXdXfXhXj";}
public test bool assignStep() { L = "abcdefghij"; L[,2..] = "XY"; return L == "XbYdXfYhXj";}
public test bool assignStep() { L = "abcdefghij"; L[,2..] = "XYZ"; return L == "XbYdZfXhYj";}
public test bool assignStep() { L = "abcdefghij"; L[,2..] = "XYZPQRS"; return L == "XbYdZfPhQjRS";}

public test bool assignStep() { L = "abcdefghij"; L[2,4..] = "X"; return L == "abXdXfXhXj";}
public test bool assignStep() { L = "abcdefghij"; L[2,4..6] = "X"; return L == "abXdXfghij";}

public test bool assignStep() { L = "abcdefghij"; L[,6..1] = "X"; return L == "abcXefXhiX";}
public test bool assignStep() { L = "abcdefghij"; L[8,6..] = "X"; return L == "XbXdXfXhXj";}
public test bool assignStep() { L = "abcdefghij"; L[8,6..3] = "X"; return L == "abcdXfXhXj";}


public test bool assignStep() { L = "abcdefghij"; L[-1,-2..] = "XYZPQ"; return L == "QPZYXQPZYX";}
public test bool assignStep() { L = "abcdefghij"; L[-1,-3..] = "XYZPQ"; return L == "aQcPeZgYiX";}

// Library functions

public test bool tstCenter1(str S) { c = center(S, size(S) + 5); return contains(c, S) && startsWith(c, " ") && endsWith(c, " "); }
public test bool tstCenter2(str S) { c = center(S, size(S) + 5, "x"); return contains(c, S) && startsWith(c, "x") && endsWith(c, "x"); }

public test bool  tstCharAt(str S) {  
  for(i <- [0 .. size(S)])
      if(charAt(S, i) != chars(S[i])[0]) return false;
  return true;
}

public test bool tstChars(str S) = S == stringChars(chars(S));

public test bool tstContains(str S1, str S2, str S3) = contains(S1+S2+S3, S1) && contains(S1+S2+S3, S2) && contains(S1+S2+S3, S3);

public test bool tstEndsWith(str S1, str S2) = endsWith(S1+S2, S2);

public test bool tstEscape(str S, str K1, str R1, str K2, str R2){
  if(isEmpty(K1) || isEmpty(K2) || K1[0] == K2[0]) return true;
  T = K1[0] + S + K2[0] + S + K2[0] + S + K1[0];
  return escape(T, (K1[0] : R1, K2[0] : R2)) == R1 + S + R2 + S + R2 + S + R1;
}

public test bool tstFindAll(str S1, str S2){
  S = S2 + S1 + S2 + S1 + S2;
  for(i <- findAll(S, S2))
      if(!startsWith((i < size(S) ? S[i..] : ""), S2)) return false;
  return true;
}

public test bool tstFindFirst(str S1, str S2){
  S = S1 + S2 + S1 + S2;
  i = findFirst(S, S2);
  return i >= 0 && startsWith((i < size(S) ? S[i..] : ""), S2);
}

public test bool tstFindLast(str S1, str S2){
  S = S1 + S2 + S1 + S2 + S1;
  i = findLast(S, S2);
  return i >= 0 && startsWith((i < size(S) ? S[i..] : ""), S2);
}

public test bool tstIsEmpty(str S) = isEmpty(S) ==> size(S) == 0;

public test bool tstStringChar(str S) {
  for(i <- [0 .. size(S)])
     if(stringChar(chars(S)[i]) != S[i]) return false;
  return true;
}

public test bool tstIsValidCharacter(str S) = isEmpty(S) || all(i <- [0 .. size(S)], isValidCharacter(chars(S[i])[0]));

public test bool tstLeft1(str S) { l = left(S, size(S) + 5); return startsWith(l, S) && endsWith(l, " "); }
public test bool tstLeft2(str S) { l = left(S, size(S) + 5, "x"); return startsWith(l, S) && endsWith(l, "x"); }


public test bool tstReplaceAll(str S1, str S2, str S3) {
  if(contains(S1, S2)) return true;
  S = S1 + S2 + S1 + S2 + S1;
  return replaceAll(S, S2, S3) == S1 + S3 + S1 + S3 + S1;
}

public test bool tstReplaceFirst(str S1, str S2, str S3) {
  if(contains(S1, S2)) return true;
  S = S1 + S2 + S1 + S2 + S1;
  return replaceFirst(S, S2, S3) == S1 + S3 + S1 + S2 + S1;
}

public test bool tstReplaceLast(str S1, str S2, str S3) {
  if(contains(S1, S2)) return true;
  S = S1 + S2 + S1 + S2 + S1;
  return replaceLast(S, S2, S3) == S1 + S2 + S1 + S3 + S1;
}

public test bool tstReverse(str S) = S == reverse(reverse(S));

// rexpMatch

public test bool tstRight1(str S) { r = right(S, size(S) + 5); return endsWith(r, S) && startsWith(r, " "); }
public test bool tstRight2(str S) { r = right(S, size(S) + 5, "x"); return endsWith(r, S) && startsWith(r, "x"); }

public test bool tstSize(str S) = size(S) == size(chars(S));

public test bool tstSplit(str S1, str S2) = isEmpty(S1) || isEmpty(S2) || contains(S2, S1) || split(S1, S2 + S1 + S2 + S1) == [S2, S2];

// squeeze

public test bool tstStartsWith(str S1, str S2) = startsWith(S1+S2, S1);

public test bool tstSubstring1(str S){
  for(i <- [0 .. size(S)])
      if(substring(S, i) != (i < size(S) ? S[i..] : "")) return false;
  return true;
}

public test bool tstSubstring2(str S){
  for(i <- [0 .. size(S)])
      for(j <- [i .. size(S)])
          if(substring(S, i, j) != (i < size(S) ? S[i..j] : "")) return false;
  return true;
}

public test bool toInt(int N) = N == toInt("<N>");

public test bool tstToLowerCase(str S) = /[A-Z]/ !:= toLowerCase(S);

public test bool toReal(real R) = R == toReal("<R>");

public test bool tstToUpperCase(str S) = /[a-z]/ !:= toUpperCase(S);

public test bool tstTrim(str S) = trim(S) == trim(" \t\n" + S + "\r\b\t ");

public test bool tstWrap(str S1 , str S2) {
  if(contains(S1, "\n") || contains(S2, "\n")) return true;
  S = S1 + " " + S2 + " " + S1 + " " + S2;
  n = max(size(S1), size(S2)) + 2;
  return trim(S) == trim(replaceAll(wrap(S, n), "\n", " "));
}