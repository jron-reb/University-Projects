1.
a.
It has the wrong type. So it is meant to return the last element in a list and therefore the type should be [a] -> a. It should also have a relevant base case. Right now it does not have that. It needs to have a base case last [x] -> x. As right now it will just return the empty list

b.


c.
mystery :: [a] -> a -> [a]

takes a list
if the first element is less than the input e. Then append x with the list obtained by evaluating mystery with the rest of the list xs and the same input e. If the first element is not less than the input e then prepend e to the front of the input list. If the list is empty then return the singleton list containing e.

Efficiency can be increased by putting the base case [] e before the other two statements. 

d.
i.
The most general unifier is a value that makes two literals equal. The most general one is the one that can be applied in the most # of cases. Where the most general part is that it is the smallest substitution

ii.
[X/John, Y/mother(john)]

iii.
It allows the query to be executed by prolog and return all the values where this literal is valid.

e.
The difference in imperative programming is that it is executed using exact mathematical equations where the output is predictable. Logic programming uses the set of mathematical logic evaluate query's. Declarative programing apparently needs to be reviewed heavily.

f.
i.
f([],0).

f([X|Y],R):-
        f(Y,R1),
        R is X + R1.

[1,2,3],X -> f([2,3],R1), X is 1 + R1
f([2,3],R1) -> f([3],R2), R1 is 2 + R2
f([3],R3) -> f([],0) R3 is 0+3
3+2+1

the query is 
f([1,2,3],X)

f gives the sum of the values in the list [X|Y]. The query returns 6

ii.
    f([1,2,3],1)

iii.
No because R1 has not been instantiated yet so it cannot be used.

g.
i. accepted: a, baa
rejected: null, b, bab, ab

ii. (ba)*a

iii.
It is not a subset of the language above. This is because the null string is a valid string for (baa)* which is not part of the infinite automata shown.

iv.
It is a valid subset of the language above. This is because (baba)+ is a subset of of (ba)*. And the last part of the language is the same ( a = a). It can also be seen on the infinite automata. Where ba can be done an infinite# of times which is a superset of (baba)+. and then needs an a at the end like the regex (baba)+a

2.
a.
Rose1 'b' [Rose1 'c' []]

For it to be polymorphic it would have to change the Char into the polymorphic type a. It needs to derive show in order to be printed.

b.

containsRose :: Char -> Rose -> Bool
containsRose char Rose(r c l)
            | char = c
            | otherwise = containsRose char l


remove 14 marks from the question

3.
a.

outputmult(X,Z) :- print(X,Z).
multTable(X,0) :- outputmult(X,0).
multTable(X,Y) :- Y is Y-1, multTable(X), outputmult(X,Y). 

b.
i.
Is the language made up of 0s and 1s where 001 is a subset of the string created.

ii.
accepted: 001, 0001
notaccepted: 2, 3
