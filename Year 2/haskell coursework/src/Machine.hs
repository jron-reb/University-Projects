{-# OPTIONS_GHC -Wno-incomplete-patterns #-}
module Machine
(
        Vname,
        Val,
        State,
        Instr (..),
        Stack,
        Config,
        iexec,
        exec
) where

import Data.Map

--TODO Task 1.1
type Vname = String
--TODO Task 1.2
type Val = Int
--TODO Task 1.3
type State = Map Vname Val

--TODO Task 1.4
data Instr =
        LOADI Val | LOAD Vname | ADD | STORE Vname | JMP Int | JMPLESS Int | JMPGE Int
        deriving (Eq, Read, Show)

--TODO Task 1.5
type Stack = [Val]
--data Queue a = Empty | Value a (Queue a) deriving (Show, Eq, Read)

--TODO Task 1.6
type Config = (Int, State, Stack)

--TODO Task 1.7
iexec :: Instr -> Config -> Config

iexec (LOADI x) (pc, state, stack) = (pc+1, state, x:stack)
iexec (LOAD v) (pc, state, stack) = (pc+1, state, (state ! v):stack)
iexec ADD (pc, state, x:y:stack) = (pc+1, state, x+y:stack)
--removes the first 2 elements from the list and replaces it with their sum
iexec (STORE v) (pc, state, x:stack) = (pc+1, insert v x state, stack)
--removes the 1st element from the list
iexec (JMP i) (pc, state, stack) = (pc+i+1, state, stack)
iexec (JMPLESS i) (pc, state, x:y:stack) = (if y < x then pc+i+1 else pc+1, state, stack)
--removes the 2 compared elements from the list
iexec (JMPGE i) (pc, state, x:y:stack) = (if y >= x then pc+i+1 else pc+1, state, stack)
--removes the 2 compared elements from the list

--TODO Task 1.8
exec :: [Instr] -> Config -> Config
--recursive function
exec [] (pc, state, stack) = (pc, state, stack)
--base case, when the instruction ist is empty, then return the config
exec (i:is) (pc, state, stack) = exec is (iexec i (pc, state, stack)) 
--if the instruction list is not empty, then call exec with the instruction list, minus the top instruction and the config that is returned by iexec i (pc, state, stack)
--iexec i (pc, state, stack) calls the function iexec, with an instruction and a config and returns a config