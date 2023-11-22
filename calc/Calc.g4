grammar Calc;

start : s+;

s     : e ';'       # expr
      | a ';'       # assign
      ;

e     : e '*' e     # mul
      | e '+' e     # add
      | INT         # int
      | ID          # id
      | '(' e ')'   # parens
      ;

a     : ID '=' e
      ;

INT   : [0-9]+ ;
ID    : [a-z]+ ;
WS    : [ \t\r\n]+ -> skip ;