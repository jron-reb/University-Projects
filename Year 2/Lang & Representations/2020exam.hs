1.
a.
A polymorphic type is a type that can be any haskell type. An advantage is that a specific function doesn't have to be specified for different types. It allows new data types to use these functions.

b.
conc :: [[a]] -> [a]
conc (x:xs) = x
conc [ [1,3,5], [7,8], [5,2] ]
===> [1,3,5,7,8,5,2]