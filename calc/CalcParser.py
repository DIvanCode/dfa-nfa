# Generated from Calc.g4 by ANTLR 4.13.0
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO

def serializedATN():
    return [
        4,1,9,46,2,0,7,0,2,1,7,1,2,2,7,2,2,3,7,3,1,0,4,0,10,8,0,11,0,12,
        0,11,1,1,1,1,1,1,1,1,1,1,1,1,3,1,20,8,1,1,2,1,2,1,2,1,2,1,2,1,2,
        1,2,3,2,29,8,2,1,2,1,2,1,2,1,2,1,2,1,2,5,2,37,8,2,10,2,12,2,40,9,
        2,1,3,1,3,1,3,1,3,1,3,0,1,4,4,0,2,4,6,0,0,47,0,9,1,0,0,0,2,19,1,
        0,0,0,4,28,1,0,0,0,6,41,1,0,0,0,8,10,3,2,1,0,9,8,1,0,0,0,10,11,1,
        0,0,0,11,9,1,0,0,0,11,12,1,0,0,0,12,1,1,0,0,0,13,14,3,4,2,0,14,15,
        5,1,0,0,15,20,1,0,0,0,16,17,3,6,3,0,17,18,5,1,0,0,18,20,1,0,0,0,
        19,13,1,0,0,0,19,16,1,0,0,0,20,3,1,0,0,0,21,22,6,2,-1,0,22,29,5,
        7,0,0,23,29,5,8,0,0,24,25,5,4,0,0,25,26,3,4,2,0,26,27,5,5,0,0,27,
        29,1,0,0,0,28,21,1,0,0,0,28,23,1,0,0,0,28,24,1,0,0,0,29,38,1,0,0,
        0,30,31,10,5,0,0,31,32,5,2,0,0,32,37,3,4,2,6,33,34,10,4,0,0,34,35,
        5,3,0,0,35,37,3,4,2,5,36,30,1,0,0,0,36,33,1,0,0,0,37,40,1,0,0,0,
        38,36,1,0,0,0,38,39,1,0,0,0,39,5,1,0,0,0,40,38,1,0,0,0,41,42,5,8,
        0,0,42,43,5,6,0,0,43,44,3,4,2,0,44,7,1,0,0,0,5,11,19,28,36,38
    ]

class CalcParser ( Parser ):

    grammarFileName = "Calc.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "';'", "'*'", "'+'", "'('", "')'", "'='" ]

    symbolicNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                      "<INVALID>", "<INVALID>", "<INVALID>", "INT", "ID", 
                      "WS" ]

    RULE_start = 0
    RULE_s = 1
    RULE_e = 2
    RULE_a = 3

    ruleNames =  [ "start", "s", "e", "a" ]

    EOF = Token.EOF
    T__0=1
    T__1=2
    T__2=3
    T__3=4
    T__4=5
    T__5=6
    INT=7
    ID=8
    WS=9

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.13.0")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class StartContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def s(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(CalcParser.SContext)
            else:
                return self.getTypedRuleContext(CalcParser.SContext,i)


        def getRuleIndex(self):
            return CalcParser.RULE_start

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitStart" ):
                return visitor.visitStart(self)
            else:
                return visitor.visitChildren(self)




    def start(self):

        localctx = CalcParser.StartContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_start)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 9 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 8
                self.s()
                self.state = 11 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not ((((_la) & ~0x3f) == 0 and ((1 << _la) & 400) != 0)):
                    break

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class SContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return CalcParser.RULE_s

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)



    class ExprContext(SContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.SContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def e(self):
            return self.getTypedRuleContext(CalcParser.EContext,0)


        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitExpr" ):
                return visitor.visitExpr(self)
            else:
                return visitor.visitChildren(self)


    class AssignContext(SContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.SContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def a(self):
            return self.getTypedRuleContext(CalcParser.AContext,0)


        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAssign" ):
                return visitor.visitAssign(self)
            else:
                return visitor.visitChildren(self)



    def s(self):

        localctx = CalcParser.SContext(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_s)
        try:
            self.state = 19
            self._errHandler.sync(self)
            la_ = self._interp.adaptivePredict(self._input,1,self._ctx)
            if la_ == 1:
                localctx = CalcParser.ExprContext(self, localctx)
                self.enterOuterAlt(localctx, 1)
                self.state = 13
                self.e(0)
                self.state = 14
                self.match(CalcParser.T__0)
                pass

            elif la_ == 2:
                localctx = CalcParser.AssignContext(self, localctx)
                self.enterOuterAlt(localctx, 2)
                self.state = 16
                self.a()
                self.state = 17
                self.match(CalcParser.T__0)
                pass


        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class EContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser


        def getRuleIndex(self):
            return CalcParser.RULE_e

     
        def copyFrom(self, ctx:ParserRuleContext):
            super().copyFrom(ctx)


    class AddContext(EContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.EContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def e(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(CalcParser.EContext)
            else:
                return self.getTypedRuleContext(CalcParser.EContext,i)


        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitAdd" ):
                return visitor.visitAdd(self)
            else:
                return visitor.visitChildren(self)


    class ParensContext(EContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.EContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def e(self):
            return self.getTypedRuleContext(CalcParser.EContext,0)


        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitParens" ):
                return visitor.visitParens(self)
            else:
                return visitor.visitChildren(self)


    class MulContext(EContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.EContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def e(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(CalcParser.EContext)
            else:
                return self.getTypedRuleContext(CalcParser.EContext,i)


        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitMul" ):
                return visitor.visitMul(self)
            else:
                return visitor.visitChildren(self)


    class IdContext(EContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.EContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def ID(self):
            return self.getToken(CalcParser.ID, 0)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitId" ):
                return visitor.visitId(self)
            else:
                return visitor.visitChildren(self)


    class IntContext(EContext):

        def __init__(self, parser, ctx:ParserRuleContext): # actually a CalcParser.EContext
            super().__init__(parser)
            self.copyFrom(ctx)

        def INT(self):
            return self.getToken(CalcParser.INT, 0)

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitInt" ):
                return visitor.visitInt(self)
            else:
                return visitor.visitChildren(self)



    def e(self, _p:int=0):
        _parentctx = self._ctx
        _parentState = self.state
        localctx = CalcParser.EContext(self, self._ctx, _parentState)
        _prevctx = localctx
        _startState = 4
        self.enterRecursionRule(localctx, 4, self.RULE_e, _p)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 28
            self._errHandler.sync(self)
            token = self._input.LA(1)
            if token in [7]:
                localctx = CalcParser.IntContext(self, localctx)
                self._ctx = localctx
                _prevctx = localctx

                self.state = 22
                self.match(CalcParser.INT)
                pass
            elif token in [8]:
                localctx = CalcParser.IdContext(self, localctx)
                self._ctx = localctx
                _prevctx = localctx
                self.state = 23
                self.match(CalcParser.ID)
                pass
            elif token in [4]:
                localctx = CalcParser.ParensContext(self, localctx)
                self._ctx = localctx
                _prevctx = localctx
                self.state = 24
                self.match(CalcParser.T__3)
                self.state = 25
                self.e(0)
                self.state = 26
                self.match(CalcParser.T__4)
                pass
            else:
                raise NoViableAltException(self)

            self._ctx.stop = self._input.LT(-1)
            self.state = 38
            self._errHandler.sync(self)
            _alt = self._interp.adaptivePredict(self._input,4,self._ctx)
            while _alt!=2 and _alt!=ATN.INVALID_ALT_NUMBER:
                if _alt==1:
                    if self._parseListeners is not None:
                        self.triggerExitRuleEvent()
                    _prevctx = localctx
                    self.state = 36
                    self._errHandler.sync(self)
                    la_ = self._interp.adaptivePredict(self._input,3,self._ctx)
                    if la_ == 1:
                        localctx = CalcParser.MulContext(self, CalcParser.EContext(self, _parentctx, _parentState))
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_e)
                        self.state = 30
                        if not self.precpred(self._ctx, 5):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 5)")
                        self.state = 31
                        self.match(CalcParser.T__1)
                        self.state = 32
                        self.e(6)
                        pass

                    elif la_ == 2:
                        localctx = CalcParser.AddContext(self, CalcParser.EContext(self, _parentctx, _parentState))
                        self.pushNewRecursionContext(localctx, _startState, self.RULE_e)
                        self.state = 33
                        if not self.precpred(self._ctx, 4):
                            from antlr4.error.Errors import FailedPredicateException
                            raise FailedPredicateException(self, "self.precpred(self._ctx, 4)")
                        self.state = 34
                        self.match(CalcParser.T__2)
                        self.state = 35
                        self.e(5)
                        pass

             
                self.state = 40
                self._errHandler.sync(self)
                _alt = self._interp.adaptivePredict(self._input,4,self._ctx)

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.unrollRecursionContexts(_parentctx)
        return localctx


    class AContext(ParserRuleContext):
        __slots__ = 'parser'

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def ID(self):
            return self.getToken(CalcParser.ID, 0)

        def e(self):
            return self.getTypedRuleContext(CalcParser.EContext,0)


        def getRuleIndex(self):
            return CalcParser.RULE_a

        def accept(self, visitor:ParseTreeVisitor):
            if hasattr( visitor, "visitA" ):
                return visitor.visitA(self)
            else:
                return visitor.visitChildren(self)




    def a(self):

        localctx = CalcParser.AContext(self, self._ctx, self.state)
        self.enterRule(localctx, 6, self.RULE_a)
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 41
            self.match(CalcParser.ID)
            self.state = 42
            self.match(CalcParser.T__5)
            self.state = 43
            self.e(0)
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx



    def sempred(self, localctx:RuleContext, ruleIndex:int, predIndex:int):
        if self._predicates == None:
            self._predicates = dict()
        self._predicates[2] = self.e_sempred
        pred = self._predicates.get(ruleIndex, None)
        if pred is None:
            raise Exception("No predicate with index:" + str(ruleIndex))
        else:
            return pred(localctx, predIndex)

    def e_sempred(self, localctx:EContext, predIndex:int):
            if predIndex == 0:
                return self.precpred(self._ctx, 5)
         

            if predIndex == 1:
                return self.precpred(self._ctx, 4)
         




