# Generated from Calc.g4 by ANTLR 4.13.0
from antlr4 import *

if "." in __name__:
    from .CalcParser import CalcParser
else:
    from CalcParser import CalcParser


# This class defines a complete generic visitor for a parse tree produced by CalcParser.

class CalcVisitor(ParseTreeVisitor):
    def __init__(self):
        self.memory = {}

    # Visit a parse tree produced by CalcParser#start.
    def visitStart(self, ctx: CalcParser.StartContext):
        answ = []
        for i in range(0, ctx.getChildCount()):
            answ.append(self.visit(ctx.getChild(i)))
        return answ

    # Visit a parse tree produced by CalcParser#expr.
    def visitExpr(self, ctx: CalcParser.ExprContext):
        return ctx.getText()[:-1] + '=' + str(self.visit(ctx.e())) + ';'

    # Visit a parse tree produced by CalcParser#assign.
    def visitAssign(self, ctx: CalcParser.AssignContext):
        if '*' in ctx.getText()[:-1] or '+' in ctx.getText()[:-1] or '(' in ctx.getText()[:-1]:
            return ctx.getText()[:-1] + '=' + str(self.visit(ctx.a())) + ';'
        self.visit(ctx.a())
        return ctx.getText()[:-1]

    # Visit a parse tree produced by CalcParser#add.
    def visitAdd(self, ctx: CalcParser.AddContext):
        expr0 = self.visit(ctx.e(0))
        expr1 = self.visit(ctx.e(1))
        return expr0 + expr1

    # Visit a parse tree produced by CalcParser#parens.
    def visitParens(self, ctx: CalcParser.ParensContext):
        return self.visit(ctx.e())

    # Visit a parse tree produced by CalcParser#mul.
    def visitMul(self, ctx: CalcParser.MulContext):
        expr0 = self.visit(ctx.e(0))
        expr1 = self.visit(ctx.e(1))
        return expr0 * expr1

    # Visit a parse tree produced by CalcParser#id.
    def visitId(self, ctx: CalcParser.IdContext):
        name = ctx.ID().getText()
        if name in self.memory:
            return self.memory[name]
        return None

    # Visit a parse tree produced by CalcParser#int.
    def visitInt(self, ctx: CalcParser.IntContext):
        return int(ctx.INT().getText())

    # Visit a parse tree produced by CalcParser#a.
    def visitA(self, ctx: CalcParser.AContext):
        name = str(ctx.ID().getText())
        expr = self.visit(ctx.e())
        self.memory[name] = expr
        return expr


del CalcParser
