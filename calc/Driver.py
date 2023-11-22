import os
import sys
import time
from antlr4 import *
from CalcLexer import CalcLexer
from CalcParser import CalcParser
from CalcVisitor import CalcVisitor


test_fname = "input.tmp"


def solve(f):
    input_stream = FileStream(f)
    lexer = CalcLexer(input_stream)
    stream = CommonTokenStream(lexer)
    parser = CalcParser(stream)
    tree = parser.start()

    if parser.getNumberOfSyntaxErrors() > 0:
        return "Syntax errors"

    visitor = CalcVisitor()
    return visitor.visit(tree)


def test(lines: [str]):
    f = open(test_fname, "w")
    f.writelines(lines)
    f.close()

    return solve(test_fname)


def testing():
    print("START TESTING")

    assert test(["b=1+2+3;",
                 "a=(3);",
                 "c=a*b;",
                 "c;"]) == ["b=1+2+3=6;",
                            "a=(3)=3;",
                            "c=a*b=18;",
                            "c=18;"]

    assert test(["b=3*(1+2)*10+3*7+2*5;"]) == ["b=3*(1+2)*10+3*7+2*5=121;"]

    assert test(["b=1+2+3;",
                 "a=(3);",
                 "a=a*b;",
                 "a;"]) == ["b=1+2+3=6;",
                            "a=(3)=3;",
                            "a=a*b=18;",
                            "a=18;"]

    assert test(["b=3*;(1+2)*10+3*7+2*5;"]) == "Syntax errors"

    os.remove(test_fname)
    time.sleep(2)

    print("TESTING OK")


def main(argv):
    answ = solve(argv[1])
    if answ == "Syntax errors":
        print(answ)
    else:
        print('\n'.join(answ))


if __name__ == '__main__':
    testing()
    # main(sys.argv)
