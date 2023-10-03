module Main where

import System.Environment
import Compiler
import Interpreter
import Machine

--TODO Task 3.4
compile s= show (ccomp (read s :: Com))
--want to implement acomp and bcomp but not entirely sure how to differentiate between the different inputs in haskell

removeSurroundingDoubleQuotes xs 
    | head xs == head "\"" = removeSurroundingDoubleQuotes (tail xs)
    | last xs == head "\"" = removeSurroundingDoubleQuotes (init xs)
    | otherwise = xs

removeEscapeSlash xs = [ x | x <- xs, not (x `elem` "\\") ]
--ccomp doesn't want to intake the escape character "\" or the surrounding double quotes around an input so these are removed form any user input

main = do
    inpu <- getLine
    putStrLn (compile (removeEscapeSlash (removeSurroundingDoubleQuotes inpu)))
