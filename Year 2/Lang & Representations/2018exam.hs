1.

f :: [a] -> a
f [a] = head a
f (a:as) = f as 
4/4

2.
Lazy evaluation is a mixture of outermost and innermost evaluation where outermost evaluation is done first. Then shared arguments are combined using pointers and are evaluated at the same time as the outermost evaluation. If termination is possible it always terminates and using at most the same number of evaluations as innermost evaluation

square (1+2)            
square (X)          [X = (1+2)]
square X*X          [X = (3)]
square 3*3
square 9

2/4

3.
data Tree = Leaf a | Branch Tree Tree
-- need to add a function to print the tree, done in workshop
-- try first using notebook
** deriving show **
3/4

4. 
g :: [a] -> [a]
g (w:ws) = g ws ++ [ w ]
g [] = []

- g is a recursive function taking and returning a list. It reverses a list. For example [1,2,3] -> g [2,3] ++ [1] -> (g [3] ++ [2]) ++ [1] -> 
    
((g[] ++ [3]) ++ [2]) ++ [1] -> ((([]) ++ [3]) ++ [2]) ++ [1] -> [3,2,1]
-- may not terminate as the base case is after the list case, should check this in the markscheme
4/4

5.
Logic programming is programming that uses mathematical logic and deduction to define and evaluate rules. The idea behind it is that similar to haskell every evaluation or query leads to the same result every time.

Prolog is a logic programming language because it follows these rules. It is made up of rules and predicates, where the result is only true if the rule(s) of each predicate are true
    1/4

6.
=
- equality without evaluation. Where the right and left side are checked for equality with no evaluation. 2 = 1+1 will yield false, 2 = 2 is true
X and Y can be unified syntacticlaly
X+4 = 4+Y
X=3 and Y=4

is
- assignment with evaluation. Where the left side is assigned the right hand side value after evaluating the left hand side. Say that X must be a variable

=!=
- equality with evaluation. Where both sides are evaluated before being compared.

== If X and Y are syntactically equal so X+4=Y+4

2/4
-- need to review the 4 equalities

7. 
The cut predicate is responsible for stopping backtracking, a technique used to resolve queries in prolog. When prolog reads a cut the program cannot backtrack beyond the most recent cut (symbol = !). This is done for efficiency and correctness reasons, where when it is known that evaluating this again leads to false answers a cut can improve the efficiency of the program. It can also be used for correctness for example when for the first intance of a rule is false then it must be one after it, a cut symbol can be used to make sure there is no more backtracking than necessary.

3/4

8.
i. In a non-deterministic FA, a transition function can go from q0 to multiple other states with the same input. For example from q0 given a it is possible to go to q1 or q2. In determinisitc Fas this is not allowed. In deterministic Fas it is possible to go to mixed/half states so from q0 input a to go to (q1,q2). This is not posible in non-deterministic Fas

2/2

ii.
The acceptance conditions are that they end up in an accepting state. Deterministic requires that for 1 input there is only 1 output state. For non-deterministic this is not necessary.
-- not entirely sure, or for ii either tbh. Would put in the 2nd sentence of this above
** So for deterministic machines if the sequence terminates in an accepting state after processing the language
    for non deterministic machines it is if after processing the transitions generated by the language there is at least one accepting state of the automata
0/2 

iii. non-deterministics FAs are equally as powerful than deterministic FAs. This is because they can recognise the same type of languages. This is because every non-deterministic FA can be converted into a deterministic FA and the opposite can also be done. Therefore they are equally powerful.
2/2

9.
i. cda, abcda
2/2
ii. 
aUc ((bUd)(aUc))*
0/2

iii.

L1 is a subset of the language. This is because it is accepted by the automata. It is also because it is a subset of the language.

L2 is not because it is not accepted by the automata. For example abdc would be invalid.
1/2




2.
10.

keep :: Int -> [a] -> [a]
keep 0 _ = []
keep _ [] = []
keep n (x:xs) = x:keep n-1 xs

lose :: Int -> [a] -> [a]
lose 0 xs = xs
lose _ [] = []
lose n (x:xs) = x:lose n-1 xs

6/8

maxList :: [Int] -> Int
maxList [] = 0
maxList [x] = x
maxList xs
        | maxList(keep half xs) > maxList (lose half xs) = maxList(keep half xs)
        | otherwise = maxList (lose half xs)
        where half = (length xs)/2
6/6

take away 12marks from full paper

11.(e)
A higher order function is a function that takes another function and then returns a result. A way to do this might be to apply the max function when comparing the two lists, so instead of > and otherwise just have 
maxList xs = max (maxList(keep half xs) maxList (lose half xs))

maxList2 :: [Int] -> Int
maxList2 (x:xs) = foldr max x xs
0/4

12.
List are represented like [X|Y] where the first element is X and the list is Y. The last element is the list and the preceding elements are the first elements.
Lists with 1 element [X|[]], [X]
List with 3 elemtns [X,Y,Z,A], [X|Y|[Z|A]]
2/4


13.
append(L1,L2,L) :- L is [L1|L2]
0/2

--ALSO NEED TO REVISE, TRY FIRST FIGURING OUT HOW TO DEFINE LISTS IN PROLOG

ii.
append([1],[2],[1,2]) = true
append([1],[2],[]) = false
2/2

14.
member (X,[X|_]) :- !.
member (X,[Y|L]) :- member (X,L).

4/4

15.
isSet([]).
isSet([X|L]) :- /+member(X,L), isSet(L).
2/4

test that every member is not the same as the first member. Then use isset on every member that comes after it. The first one comes because the list has to be split up

16.

intersection([],S2,[]) :- S2.
intersection([X1|S1], S2, [X1|S]) :- member (X1, S), !. intersection(S1,S2,S).
intersection([X1|S1],S2,S) :- intersection(S1,S2,S).


17.
union([],S2,S) :- S
union([X1|S1],S2,S) :- /+member(X1, S), !., union(S1,S2,S).
union([X1|S1],S2,[X1|S]) :- union (S1,S2,S).

2/12

4.
a.
0 -> 1, 1->1
. 0 not accepting. 1 is accepting. 0->1 is by 0. 1 -> 1 is by 1.

0 -> 1, 1->0
0 is accepting. 1 is not accepting. 0->1 is by 0. 1->0 is by 1.

6/6

b. NEED TO LEARN INTERSECTION

c.
i. 
L is the language that accepts string consisting of either ab or aba. The empty string belongs to L.
1/2

ii. 
accepting: null string, "ab" 
not accepting: 'd', 'q'

2/2




