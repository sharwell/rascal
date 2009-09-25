package org.meta_environment.rascal.ast; 
public interface IASTVisitor<T> {
public T visitFormalTypeName(Formal.TypeName x); public T visitFormalsDefault(Formals.Default x); public T visitParametersVarArgs(Parameters.VarArgs x); public T visitParametersDefault(Parameters.Default x); public T visitExpressionAnti(Expression.Anti x); public T visitExpressionGuarded(Expression.Guarded x); public T visitExpressionTypedVariableBecomes(Expression.TypedVariableBecomes x); public T visitExpressionVariableBecomes(Expression.VariableBecomes x); public T visitExpressionDescendant(Expression.Descendant x); public T visitExpressionMultiVariable(Expression.MultiVariable x); public T visitExpressionTypedVariable(Expression.TypedVariable x); public T visitExpressionMap(Expression.Map x); public T visitExpressionTuple(Expression.Tuple x); public T visitExpressionSet(Expression.Set x); public T visitExpressionList(Expression.List x); public T visitExpressionQualifiedName(Expression.QualifiedName x); public T visitExpressionLiteral(Expression.Literal x); public T visitExpressionAny(Expression.Any x); public T visitExpressionAll(Expression.All x); public T visitExpressionComprehension(Expression.Comprehension x); public T visitExpressionEnumerator(Expression.Enumerator x); public T visitExpressionNoMatch(Expression.NoMatch x); public T visitExpressionMatch(Expression.Match x); public T visitExpressionLexical(Expression.Lexical x); public T visitExpressionOr(Expression.Or x); public T visitExpressionAnd(Expression.And x); public T visitExpressionEquivalence(Expression.Equivalence x); public T visitExpressionImplication(Expression.Implication x); public T visitExpressionIfDefinedOtherwise(Expression.IfDefinedOtherwise x); public T visitExpressionIfThenElse(Expression.IfThenElse x); public T visitExpressionNonEquals(Expression.NonEquals x); public T visitExpressionEquals(Expression.Equals x); public T visitExpressionGreaterThanOrEq(Expression.GreaterThanOrEq x); public T visitExpressionGreaterThan(Expression.GreaterThan x); public T visitExpressionLessThanOrEq(Expression.LessThanOrEq x); public T visitExpressionLessThan(Expression.LessThan x); public T visitExpressionIn(Expression.In x); public T visitExpressionNotIn(Expression.NotIn x); public T visitExpressionSubtraction(Expression.Subtraction x); public T visitExpressionAddition(Expression.Addition x); public T visitExpressionIntersection(Expression.Intersection x); public T visitExpressionModulo(Expression.Modulo x); public T visitExpressionDivision(Expression.Division x); public T visitExpressionJoin(Expression.Join x); public T visitExpressionProduct(Expression.Product x); public T visitExpressionComposition(Expression.Composition x); public T visitExpressionSetAnnotation(Expression.SetAnnotation x); public T visitExpressionGetAnnotation(Expression.GetAnnotation x); public T visitExpressionTransitiveClosure(Expression.TransitiveClosure x); public T visitExpressionTransitiveReflexiveClosure(Expression.TransitiveReflexiveClosure x); public T visitExpressionNegative(Expression.Negative x); public T visitExpressionNegation(Expression.Negation x); public T visitExpressionIsDefined(Expression.IsDefined x); public T visitExpressionSubscript(Expression.Subscript x); public T visitExpressionFieldProject(Expression.FieldProject x); public T visitExpressionFieldAccess(Expression.FieldAccess x); public T visitExpressionFieldUpdate(Expression.FieldUpdate x); public T visitExpressionCallOrTree(Expression.CallOrTree x); public T visitExpressionReifiedType(Expression.ReifiedType x); public T visitExpressionReifyType(Expression.ReifyType x); public T visitExpressionStepRange(Expression.StepRange x); public T visitExpressionRange(Expression.Range x); public T visitExpressionBracket(Expression.Bracket x); public T visitExpressionVisit(Expression.Visit x); public T visitExpressionNonEmptyBlock(Expression.NonEmptyBlock x); public T visitExpressionVoidClosure(Expression.VoidClosure x); public T visitExpressionClosure(Expression.Closure x); public T visitProtocolTailPost(ProtocolTail.Post x); public T visitProtocolTailMid(ProtocolTail.Mid x); public T visitProtocolPartNonInterpolated(ProtocolPart.NonInterpolated x); public T visitProtocolPartInterpolated(ProtocolPart.Interpolated x); public T visitPathTailPost(PathTail.Post x); public T visitPathTailMid(PathTail.Mid x); public T visitPathPartNonInterpolated(PathPart.NonInterpolated x); public T visitPathPartInterpolated(PathPart.Interpolated x); public T visitStringTailPost(StringTail.Post x); public T visitStringTailMidTemplate(StringTail.MidTemplate x); public T visitStringTailMidInterpolated(StringTail.MidInterpolated x); public T visitStringLiteralNonInterpolated(StringLiteral.NonInterpolated x); public T visitStringLiteralTemplate(StringLiteral.Template x); public T visitStringLiteralInterpolated(StringLiteral.Interpolated x); public T visitStringMiddleTemplate(StringMiddle.Template x); public T visitStringMiddleInterpolated(StringMiddle.Interpolated x); public T visitStringMiddleMid(StringMiddle.Mid x); public T visitStringTemplateDoWhile(StringTemplate.DoWhile x); public T visitStringTemplateWhile(StringTemplate.While x); public T visitStringTemplateIfThenElse(StringTemplate.IfThenElse x); public T visitStringTemplateIfThen(StringTemplate.IfThen x); public T visitStringTemplateFor(StringTemplate.For x); public T visitFieldIndex(Field.Index x); public T visitFieldName(Field.Name x); public T visitMarkerLexical(Marker.Lexical x); public T visitRestLexical(Rest.Lexical x); public T visitBodyToplevels(Body.Toplevels x); public T visitBodyAnything(Body.Anything x); public T visitUnicodeEscapeLexical(UnicodeEscape.Lexical x); public T visitDecimalIntegerLiteralLexical(DecimalIntegerLiteral.Lexical x); public T visitHexIntegerLiteralLexical(HexIntegerLiteral.Lexical x); public T visitOctalIntegerLiteralLexical(OctalIntegerLiteral.Lexical x); public T visitDecimalLongLiteralLexical(DecimalLongLiteral.Lexical x); public T visitHexLongLiteralLexical(HexLongLiteral.Lexical x); public T visitOctalLongLiteralLexical(OctalLongLiteral.Lexical x); public T visitRealLiteralLexical(RealLiteral.Lexical x); public T visitBooleanLiteralLexical(BooleanLiteral.Lexical x); public T visitSingleCharacterLexical(SingleCharacter.Lexical x); public T visitCharacterLiteralLexical(CharacterLiteral.Lexical x); public T visitEscapeSequenceLexical(EscapeSequence.Lexical x); public T visitStringCharacterLexical(StringCharacter.Lexical x); public T visitStringConstantLexical(StringConstant.Lexical x); public T visitPreStringCharsLexical(PreStringChars.Lexical x); public T visitMidStringCharsLexical(MidStringChars.Lexical x); public T visitPostStringCharsLexical(PostStringChars.Lexical x); public T visitProtocolCharsLexical(ProtocolChars.Lexical x); public T visitPreProtocolCharsLexical(PreProtocolChars.Lexical x); public T visitMidProtocolCharsLexical(MidProtocolChars.Lexical x); public T visitPostProtocolCharsLexical(PostProtocolChars.Lexical x); public T visitPrePathCharsLexical(PrePathChars.Lexical x); public T visitMidPathCharsLexical(MidPathChars.Lexical x); public T visitPostPathCharsLexical(PostPathChars.Lexical x); public T visitPathCharsLexical(PathChars.Lexical x); public T visitLocationLiteralFile(LocationLiteral.File x); public T visitLocationLiteralDefault(LocationLiteral.Default x); public T visitURLCharsLexical(URLChars.Lexical x); public T visitIntegerLiteralOctalIntegerLiteral(IntegerLiteral.OctalIntegerLiteral x); public T visitIntegerLiteralHexIntegerLiteral(IntegerLiteral.HexIntegerLiteral x); public T visitIntegerLiteralDecimalIntegerLiteral(IntegerLiteral.DecimalIntegerLiteral x); public T visitLongLiteralOctalLongLiteral(LongLiteral.OctalLongLiteral x); public T visitLongLiteralHexLongLiteral(LongLiteral.HexLongLiteral x); public T visitLongLiteralDecimalLongLiteral(LongLiteral.DecimalLongLiteral x); public T visitBasicTypeReifiedReifiedType(BasicType.ReifiedReifiedType x); public T visitBasicTypeReifiedNonTerminal(BasicType.ReifiedNonTerminal x); public T visitBasicTypeReifiedFunction(BasicType.ReifiedFunction x); public T visitBasicTypeReifiedConstructor(BasicType.ReifiedConstructor x); public T visitBasicTypeReifiedAdt(BasicType.ReifiedAdt x); public T visitBasicTypeReifiedType(BasicType.ReifiedType x); public T visitBasicTypeLex(BasicType.Lex x); public T visitBasicTypeTuple(BasicType.Tuple x); public T visitBasicTypeRelation(BasicType.Relation x); public T visitBasicTypeMap(BasicType.Map x); public T visitBasicTypeBag(BasicType.Bag x); public T visitBasicTypeSet(BasicType.Set x); public T visitBasicTypeList(BasicType.List x); public T visitBasicTypeLoc(BasicType.Loc x); public T visitBasicTypeVoid(BasicType.Void x); public T visitBasicTypeNode(BasicType.Node x); public T visitBasicTypeValue(BasicType.Value x); public T visitBasicTypeString(BasicType.String x); public T visitBasicTypeReal(BasicType.Real x); public T visitBasicTypeInt(BasicType.Int x); public T visitBasicTypeBool(BasicType.Bool x); public T visitTypeArgNamed(TypeArg.Named x); public T visitTypeArgDefault(TypeArg.Default x); public T visitStructuredTypeDefault(StructuredType.Default x); public T visitFunctionTypeTypeArguments(FunctionType.TypeArguments x); public T visitTypeVarBounded(TypeVar.Bounded x); public T visitTypeVarFree(TypeVar.Free x); public T visitUserTypeParametric(UserType.Parametric x); public T visitUserTypeName(UserType.Name x); public T visitDataTypeSelectorSelector(DataTypeSelector.Selector x); public T visitTypeSymbol(Type.Symbol x); public T visitTypeBracket(Type.Bracket x); public T visitTypeSelector(Type.Selector x); public T visitTypeUser(Type.User x); public T visitTypeVariable(Type.Variable x); public T visitTypeFunction(Type.Function x); public T visitTypeStructured(Type.Structured x); public T visitTypeBasic(Type.Basic x); public T visitBoundDefault(Bound.Default x); public T visitBoundEmpty(Bound.Empty x); public T visitStatementGlobalDirective(Statement.GlobalDirective x); public T visitStatementNonEmptyBlock(Statement.NonEmptyBlock x); public T visitStatementTryFinally(Statement.TryFinally x); public T visitStatementTry(Statement.Try x); public T visitStatementContinue(Statement.Continue x); public T visitStatementFail(Statement.Fail x); public T visitStatementBreak(Statement.Break x); public T visitStatementVariableDeclaration(Statement.VariableDeclaration x); public T visitStatementFunctionDeclaration(Statement.FunctionDeclaration x); public T visitStatementAppend(Statement.Append x); public T visitStatementInsert(Statement.Insert x); public T visitStatementThrow(Statement.Throw x); public T visitStatementReturn(Statement.Return x); public T visitStatementAssertWithMessage(Statement.AssertWithMessage x); public T visitStatementAssert(Statement.Assert x); public T visitStatementAssignment(Statement.Assignment x); public T visitStatementExpression(Statement.Expression x); public T visitStatementEmptyStatement(Statement.EmptyStatement x); public T visitStatementVisit(Statement.Visit x); public T visitStatementSwitch(Statement.Switch x); public T visitStatementIfThen(Statement.IfThen x); public T visitStatementIfThenElse(Statement.IfThenElse x); public T visitStatementDoWhile(Statement.DoWhile x); public T visitStatementWhile(Statement.While x); public T visitStatementFor(Statement.For x); public T visitStatementSolve(Statement.Solve x); public T visitNoElseMayFollowDefault(NoElseMayFollow.Default x); public T visitAssignableConstructor(Assignable.Constructor x); public T visitAssignableTuple(Assignable.Tuple x); public T visitAssignableAnnotation(Assignable.Annotation x); public T visitAssignableIfDefinedOrDefault(Assignable.IfDefinedOrDefault x); public T visitAssignableFieldAccess(Assignable.FieldAccess x); public T visitAssignableSubscript(Assignable.Subscript x); public T visitAssignableVariable(Assignable.Variable x); public T visitAssignmentIfDefined(Assignment.IfDefined x); public T visitAssignmentIntersection(Assignment.Intersection x); public T visitAssignmentDivision(Assignment.Division x); public T visitAssignmentProduct(Assignment.Product x); public T visitAssignmentSubtraction(Assignment.Subtraction x); public T visitAssignmentAddition(Assignment.Addition x); public T visitAssignmentDefault(Assignment.Default x); public T visitLabelDefault(Label.Default x); public T visitLabelEmpty(Label.Empty x); public T visitDataTargetLabeled(DataTarget.Labeled x); public T visitDataTargetEmpty(DataTarget.Empty x); public T visitTargetLabeled(Target.Labeled x); public T visitTargetEmpty(Target.Empty x); public T visitCatchBinding(Catch.Binding x); public T visitCatchDefault(Catch.Default x); public T visitDeclaratorDefault(Declarator.Default x); public T visitLocalVariableDeclarationDynamic(LocalVariableDeclaration.Dynamic x); public T visitLocalVariableDeclarationDefault(LocalVariableDeclaration.Default x); public T visitMappingDefault(Mapping.Default x); public T visitStrategyInnermost(Strategy.Innermost x); public T visitStrategyOutermost(Strategy.Outermost x); public T visitStrategyBottomUpBreak(Strategy.BottomUpBreak x); public T visitStrategyBottomUp(Strategy.BottomUp x); public T visitStrategyTopDownBreak(Strategy.TopDownBreak x); public T visitStrategyTopDown(Strategy.TopDown x); public T visitComprehensionMap(Comprehension.Map x); public T visitComprehensionList(Comprehension.List x); public T visitComprehensionSet(Comprehension.Set x); public T visitReplacementConditional(Replacement.Conditional x); public T visitReplacementUnconditional(Replacement.Unconditional x); public T visitPatternWithActionArbitrary(PatternWithAction.Arbitrary x); public T visitPatternWithActionReplacing(PatternWithAction.Replacing x); public T visitCaseDefault(Case.Default x); public T visitCasePatternWithAction(Case.PatternWithAction x); public T visitVisitGivenStrategy(Visit.GivenStrategy x); public T visitVisitDefaultStrategy(Visit.DefaultStrategy x); public T visitModuleDefault(Module.Default x); public T visitModuleActualsDefault(ModuleActuals.Default x); public T visitImportedModuleDefault(ImportedModule.Default x); public T visitImportedModuleRenamings(ImportedModule.Renamings x); public T visitImportedModuleActuals(ImportedModule.Actuals x); public T visitImportedModuleActualsRenaming(ImportedModule.ActualsRenaming x); public T visitRenamingDefault(Renaming.Default x); public T visitRenamingsDefault(Renamings.Default x); public T visitImportExtend(Import.Extend x); public T visitImportDefault(Import.Default x); public T visitModuleParametersDefault(ModuleParameters.Default x); public T visitHeaderParameters(Header.Parameters x); public T visitHeaderDefault(Header.Default x); public T visitStrCharLexical(StrChar.Lexical x); public T visitStrCharnewline(StrChar.newline x); public T visitStrConLexical(StrCon.Lexical x); public T visitSingleQuotedStrCharLexical(SingleQuotedStrChar.Lexical x); public T visitSingleQuotedStrConLexical(SingleQuotedStrCon.Lexical x); public T visitSymbolSort(Symbol.Sort x); public T visitSymbolCaseInsensitiveLiteral(Symbol.CaseInsensitiveLiteral x); public T visitSymbolLiteral(Symbol.Literal x); public T visitSymbolCharacterClass(Symbol.CharacterClass x); public T visitSymbolAlternative(Symbol.Alternative x); public T visitSymbolIterStarSep(Symbol.IterStarSep x); public T visitSymbolIterSep(Symbol.IterSep x); public T visitSymbolIterStar(Symbol.IterStar x); public T visitSymbolIter(Symbol.Iter x); public T visitSymbolOptional(Symbol.Optional x); public T visitSymbolSequence(Symbol.Sequence x); public T visitSymbolEmpty(Symbol.Empty x); public T visitCharRangeRange(CharRange.Range x); public T visitCharRangeCharacter(CharRange.Character x); public T visitCharRangesBracket(CharRanges.Bracket x); public T visitCharRangesConcatenate(CharRanges.Concatenate x); public T visitCharRangesRange(CharRanges.Range x); public T visitOptCharRangesPresent(OptCharRanges.Present x); public T visitOptCharRangesAbsent(OptCharRanges.Absent x); public T visitCharClassUnion(CharClass.Union x); public T visitCharClassIntersection(CharClass.Intersection x); public T visitCharClassDifference(CharClass.Difference x); public T visitCharClassComplement(CharClass.Complement x); public T visitCharClassBracket(CharClass.Bracket x); public T visitCharClassSimpleCharclass(CharClass.SimpleCharclass x); public T visitNumCharLexical(NumChar.Lexical x); public T visitShortCharLexical(ShortChar.Lexical x); public T visitCharacterBottom(Character.Bottom x); public T visitCharacterEOF(Character.EOF x); public T visitCharacterTop(Character.Top x); public T visitCharacterShort(Character.Short x); public T visitCharacterNumeric(Character.Numeric x); public T visitNameLexical(Name.Lexical x); public T visitEscapedNameLexical(EscapedName.Lexical x); public T visitQualifiedNameDefault(QualifiedName.Default x); public T visitCommandLexical(Command.Lexical x); public T visitCommandImport(Command.Import x); public T visitCommandDeclaration(Command.Declaration x); public T visitCommandExpression(Command.Expression x); public T visitCommandStatement(Command.Statement x); public T visitCommandShell(Command.Shell x); public T visitShellCommandSetOption(ShellCommand.SetOption x); public T visitShellCommandHistory(ShellCommand.History x); public T visitShellCommandUndeclare(ShellCommand.Undeclare x); public T visitShellCommandUnimport(ShellCommand.Unimport x); public T visitShellCommandTest(ShellCommand.Test x); public T visitShellCommandListDeclarations(ShellCommand.ListDeclarations x); public T visitShellCommandListModules(ShellCommand.ListModules x); public T visitShellCommandEdit(ShellCommand.Edit x); public T visitShellCommandQuit(ShellCommand.Quit x); public T visitShellCommandHelp(ShellCommand.Help x); public T visitTagStringLexical(TagString.Lexical x); public T visitTagCharLexical(TagChar.Lexical x); public T visitTagEmpty(Tag.Empty x); public T visitTagExpression(Tag.Expression x); public T visitTagDefault(Tag.Default x); public T visitTagsDefault(Tags.Default x); public T visitRegExpLiteralLexical(RegExpLiteral.Lexical x); public T visitRegExpModifierLexical(RegExpModifier.Lexical x); public T visitBackslashLexical(Backslash.Lexical x); public T visitRegExpLexical(RegExp.Lexical x); public T visitNamedRegExpLexical(NamedRegExp.Lexical x); public T visitNamedBackslashLexical(NamedBackslash.Lexical x); public T visitVisibilityDefault(Visibility.Default x); public T visitVisibilityPrivate(Visibility.Private x); public T visitVisibilityPublic(Visibility.Public x); public T visitToplevelGivenVisibility(Toplevel.GivenVisibility x); public T visitDeclarationTag(Declaration.Tag x); public T visitDeclarationAnnotation(Declaration.Annotation x); public T visitDeclarationRule(Declaration.Rule x); public T visitDeclarationVariable(Declaration.Variable x); public T visitDeclarationFunction(Declaration.Function x); public T visitDeclarationTest(Declaration.Test x); public T visitDeclarationData(Declaration.Data x); public T visitDeclarationAlias(Declaration.Alias x); public T visitDeclarationView(Declaration.View x); public T visitAlternativeNamedType(Alternative.NamedType x); public T visitVariantNAryConstructor(Variant.NAryConstructor x); public T visitTestLabeled(Test.Labeled x); public T visitTestUnlabeled(Test.Unlabeled x); public T visitFunctionModifierJava(FunctionModifier.Java x); public T visitFunctionModifiersList(FunctionModifiers.List x); public T visitSignatureWithThrows(Signature.WithThrows x); public T visitSignatureNoThrows(Signature.NoThrows x); public T visitFunctionDeclarationAbstract(FunctionDeclaration.Abstract x); public T visitFunctionDeclarationDefault(FunctionDeclaration.Default x); public T visitFunctionBodyDefault(FunctionBody.Default x); public T visitVariableInitialized(Variable.Initialized x); public T visitVariableUnInitialized(Variable.UnInitialized x); public T visitKindAll(Kind.All x); public T visitKindTag(Kind.Tag x); public T visitKindAnno(Kind.Anno x); public T visitKindAlias(Kind.Alias x); public T visitKindView(Kind.View x); public T visitKindData(Kind.Data x); public T visitKindVariable(Kind.Variable x); public T visitKindRule(Kind.Rule x); public T visitKindFunction(Kind.Function x); public T visitKindModule(Kind.Module x); public T visitCommentLexical(Comment.Lexical x); public T visitCommentCharLexical(CommentChar.Lexical x); public T visitAsteriskLexical(Asterisk.Lexical x); public T visitLiteralLocation(Literal.Location x); public T visitLiteralString(Literal.String x); public T visitLiteralReal(Literal.Real x); public T visitLiteralInteger(Literal.Integer x); public T visitLiteralBoolean(Literal.Boolean x); public T visitLiteralRegExp(Literal.RegExp x);
public T visitFormalAmbiguity(Formal.Ambiguity x); public T visitFormalsAmbiguity(Formals.Ambiguity x); 
  public T visitParametersAmbiguity(Parameters.Ambiguity x); 
  public T visitExpressionAmbiguity(Expression.Ambiguity x); 
  public T visitProtocolTailAmbiguity(ProtocolTail.Ambiguity x); 
  public T visitProtocolPartAmbiguity(ProtocolPart.Ambiguity x); 
  public T visitPathTailAmbiguity(PathTail.Ambiguity x); 
  public T visitPathPartAmbiguity(PathPart.Ambiguity x); 
  public T visitStringTailAmbiguity(StringTail.Ambiguity x); 
  public T visitStringLiteralAmbiguity(StringLiteral.Ambiguity x); 
  public T visitStringMiddleAmbiguity(StringMiddle.Ambiguity x); 
  public T visitStringTemplateAmbiguity(StringTemplate.Ambiguity x); 
  public T visitFieldAmbiguity(Field.Ambiguity x); 
  public T visitMarkerAmbiguity(Marker.Ambiguity x); public T visitRestAmbiguity(Rest.Ambiguity x); 
  public T visitBodyAmbiguity(Body.Ambiguity x); 
  public T visitUnicodeEscapeAmbiguity(UnicodeEscape.Ambiguity x); public T visitDecimalIntegerLiteralAmbiguity(DecimalIntegerLiteral.Ambiguity x); public T visitHexIntegerLiteralAmbiguity(HexIntegerLiteral.Ambiguity x); public T visitOctalIntegerLiteralAmbiguity(OctalIntegerLiteral.Ambiguity x); public T visitDecimalLongLiteralAmbiguity(DecimalLongLiteral.Ambiguity x); public T visitHexLongLiteralAmbiguity(HexLongLiteral.Ambiguity x); public T visitOctalLongLiteralAmbiguity(OctalLongLiteral.Ambiguity x); public T visitRealLiteralAmbiguity(RealLiteral.Ambiguity x); public T visitBooleanLiteralAmbiguity(BooleanLiteral.Ambiguity x); public T visitSingleCharacterAmbiguity(SingleCharacter.Ambiguity x); public T visitCharacterLiteralAmbiguity(CharacterLiteral.Ambiguity x); public T visitEscapeSequenceAmbiguity(EscapeSequence.Ambiguity x); public T visitStringCharacterAmbiguity(StringCharacter.Ambiguity x); public T visitStringConstantAmbiguity(StringConstant.Ambiguity x); public T visitPreStringCharsAmbiguity(PreStringChars.Ambiguity x); public T visitMidStringCharsAmbiguity(MidStringChars.Ambiguity x); public T visitPostStringCharsAmbiguity(PostStringChars.Ambiguity x); public T visitProtocolCharsAmbiguity(ProtocolChars.Ambiguity x); public T visitPreProtocolCharsAmbiguity(PreProtocolChars.Ambiguity x); public T visitMidProtocolCharsAmbiguity(MidProtocolChars.Ambiguity x); public T visitPostProtocolCharsAmbiguity(PostProtocolChars.Ambiguity x); public T visitPrePathCharsAmbiguity(PrePathChars.Ambiguity x); public T visitMidPathCharsAmbiguity(MidPathChars.Ambiguity x); public T visitPostPathCharsAmbiguity(PostPathChars.Ambiguity x); public T visitPathCharsAmbiguity(PathChars.Ambiguity x); 
  public T visitLocationLiteralAmbiguity(LocationLiteral.Ambiguity x); 
  public T visitURLCharsAmbiguity(URLChars.Ambiguity x); 
  public T visitIntegerLiteralAmbiguity(IntegerLiteral.Ambiguity x); 
  public T visitLongLiteralAmbiguity(LongLiteral.Ambiguity x); 
  public T visitBasicTypeAmbiguity(BasicType.Ambiguity x); 
  public T visitTypeArgAmbiguity(TypeArg.Ambiguity x); 
  public T visitStructuredTypeAmbiguity(StructuredType.Ambiguity x); public T visitFunctionTypeAmbiguity(FunctionType.Ambiguity x); 
  public T visitTypeVarAmbiguity(TypeVar.Ambiguity x); 
  public T visitUserTypeAmbiguity(UserType.Ambiguity x); 
  public T visitDataTypeSelectorAmbiguity(DataTypeSelector.Ambiguity x); 
  public T visitTypeAmbiguity(Type.Ambiguity x); 
  public T visitBoundAmbiguity(Bound.Ambiguity x); 
  public T visitStatementAmbiguity(Statement.Ambiguity x); 
  public T visitNoElseMayFollowAmbiguity(NoElseMayFollow.Ambiguity x); 
  public T visitAssignableAmbiguity(Assignable.Ambiguity x); 
  public T visitAssignmentAmbiguity(Assignment.Ambiguity x); 
  public T visitLabelAmbiguity(Label.Ambiguity x); 
  public T visitDataTargetAmbiguity(DataTarget.Ambiguity x); 
  public T visitTargetAmbiguity(Target.Ambiguity x); 
  public T visitCatchAmbiguity(Catch.Ambiguity x); 
  public T visitDeclaratorAmbiguity(Declarator.Ambiguity x); 
  public T visitLocalVariableDeclarationAmbiguity(LocalVariableDeclaration.Ambiguity x); 
  public T visitMappingAmbiguity(Mapping.Ambiguity x); 
  public T visitStrategyAmbiguity(Strategy.Ambiguity x); 
  public T visitComprehensionAmbiguity(Comprehension.Ambiguity x); 
  public T visitReplacementAmbiguity(Replacement.Ambiguity x); 
  public T visitPatternWithActionAmbiguity(PatternWithAction.Ambiguity x); 
  public T visitCaseAmbiguity(Case.Ambiguity x); 
  public T visitVisitAmbiguity(Visit.Ambiguity x); 
  public T visitModuleAmbiguity(Module.Ambiguity x); public T visitModuleActualsAmbiguity(ModuleActuals.Ambiguity x); 
  public T visitImportedModuleAmbiguity(ImportedModule.Ambiguity x); 
  public T visitRenamingAmbiguity(Renaming.Ambiguity x); public T visitRenamingsAmbiguity(Renamings.Ambiguity x); 
  public T visitImportAmbiguity(Import.Ambiguity x); 
  public T visitModuleParametersAmbiguity(ModuleParameters.Ambiguity x); 
  public T visitHeaderAmbiguity(Header.Ambiguity x); 
  public T visitStrCharAmbiguity(StrChar.Ambiguity x); 
  public T visitStrConAmbiguity(StrCon.Ambiguity x); public T visitSingleQuotedStrCharAmbiguity(SingleQuotedStrChar.Ambiguity x); public T visitSingleQuotedStrConAmbiguity(SingleQuotedStrCon.Ambiguity x); 
  public T visitSymbolAmbiguity(Symbol.Ambiguity x); 
  public T visitCharRangeAmbiguity(CharRange.Ambiguity x); 
  public T visitCharRangesAmbiguity(CharRanges.Ambiguity x); 
  public T visitOptCharRangesAmbiguity(OptCharRanges.Ambiguity x); 
  public T visitCharClassAmbiguity(CharClass.Ambiguity x); 
  public T visitNumCharAmbiguity(NumChar.Ambiguity x); public T visitShortCharAmbiguity(ShortChar.Ambiguity x); 
  public T visitCharacterAmbiguity(Character.Ambiguity x); 
  public T visitNameAmbiguity(Name.Ambiguity x); public T visitEscapedNameAmbiguity(EscapedName.Ambiguity x); public T visitQualifiedNameAmbiguity(QualifiedName.Ambiguity x); 
  public T visitCommandAmbiguity(Command.Ambiguity x); 
  public T visitShellCommandAmbiguity(ShellCommand.Ambiguity x); 
  public T visitTagStringAmbiguity(TagString.Ambiguity x); public T visitTagCharAmbiguity(TagChar.Ambiguity x); 
  public T visitTagAmbiguity(Tag.Ambiguity x); 
  public T visitTagsAmbiguity(Tags.Ambiguity x); public T visitRegExpLiteralAmbiguity(RegExpLiteral.Ambiguity x); public T visitRegExpModifierAmbiguity(RegExpModifier.Ambiguity x); public T visitBackslashAmbiguity(Backslash.Ambiguity x); public T visitRegExpAmbiguity(RegExp.Ambiguity x); public T visitNamedRegExpAmbiguity(NamedRegExp.Ambiguity x); public T visitNamedBackslashAmbiguity(NamedBackslash.Ambiguity x); 
  public T visitVisibilityAmbiguity(Visibility.Ambiguity x); 
  public T visitToplevelAmbiguity(Toplevel.Ambiguity x); 
  public T visitDeclarationAmbiguity(Declaration.Ambiguity x); 
  public T visitAlternativeAmbiguity(Alternative.Ambiguity x); public T visitVariantAmbiguity(Variant.Ambiguity x); 
  public T visitTestAmbiguity(Test.Ambiguity x); 
  public T visitFunctionModifierAmbiguity(FunctionModifier.Ambiguity x); public T visitFunctionModifiersAmbiguity(FunctionModifiers.Ambiguity x); 
  public T visitSignatureAmbiguity(Signature.Ambiguity x); 
  public T visitFunctionDeclarationAmbiguity(FunctionDeclaration.Ambiguity x); 
  public T visitFunctionBodyAmbiguity(FunctionBody.Ambiguity x); 
  public T visitVariableAmbiguity(Variable.Ambiguity x); 
  public T visitKindAmbiguity(Kind.Ambiguity x); 
  public T visitCommentAmbiguity(Comment.Ambiguity x); public T visitCommentCharAmbiguity(CommentChar.Ambiguity x); public T visitAsteriskAmbiguity(Asterisk.Ambiguity x); 
  public T visitLiteralAmbiguity(Literal.Ambiguity x);
}