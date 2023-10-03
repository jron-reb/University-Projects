module Compiler
(
    acomp,
    bcomp,
    ccomp
) where

import Machine
import Interpreter

--TODO Task 3.1
acomp :: AExp -> [Instr]
acomp (N n) = [LOADI n]
acomp (V v) = [LOAD v]
acomp (Plus a1 a2) = [head (acomp a1), head (acomp a2), ADD]

--TODO Task 3.2
bcomp :: BExp -> Bool -> Int -> [Instr]
bcomp (Bc b1) b2 x = if b1 == b2 then [JMP x] else []
bcomp (Not b1) b2 x = bcomp b1 (not b2) x
bcomp (And b1 b2) b3 x = cb1 ++ cb2
    where cb2 = bcomp b2 b3 x; test = if b3 then length cb2 else length cb2 + x; cb1 = bcomp b1 False test
bcomp (Less a1 a2) b1 x = acomp a1 ++ acomp a2 ++ (if b1 then [JMPLESS x] else [JMPGE x])

--TODO Task 3.3
ccomp :: Com -> [Instr]
ccomp (Assign v x) = acomp x ++ [STORE v]
ccomp (Seq c1 c2) = ccomp c1 ++ ccomp c2
ccomp (If b c1 c2) = cb ++ ccomp c1 ++ JMP (length (ccomp c2)):ccomp c2
    where cb = bcomp b False (length (ccomp c1) + 1)
ccomp (While b c1) = cb ++ ccomp c1 ++ [JMP (- (length cb + length (ccomp c1) + 1))]
    where cb = bcomp b False (length (ccomp c1) + 1)
ccomp SKIP = []