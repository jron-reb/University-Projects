%Second exercise family

parent(david,jeremy).
parent(david,peter).
parent(david,simon).
parent(pat,jeremy).
parent(pat,peter).
parent(pat,simon).
parent(jeremy,william).
parent(jeremy,tim).
parent(jeremy,josephine).
parent(sandra,william).
parent(sandra,tim).
parent(sandra,josephine).
parent(peter,rosemary).
parent(peter,jonathan).
parent(carol,rosemary).
parent(carol,jonathan).
parent(simon,miranda).
parent(simon,emily).
parent(clare,miranda).
parent(clare,emily).


male(david).
male(jeremy).
male(peter).
male(simon).
male(william).
male(tim).
male(jonathan).

female(pat).
female(sandra).
female(carol).
female(clare).
female(rosemary).
female(josephine).
female(miranda).
female(emily).

father(X,Y) :- male(X), parent(X,Y).
mother(X,Y) :- female(X), parent(X,Y).
grandparent(X,Y) :- parent(X,Z), parent(Z,Y).
son(X,Y) :- male(X), parent(Y,X).
daughter(X,Y) :- female(X), parent(Y,X).
grandfather(X,Y) :- father(X,Z), parent(Z,Y).
grandmother(X,Y) :- mother(X,Z), parent(Z,Y).
grandson(X,Y) :- male(X), parent(Z,X), parent(Y,Z).
%two alternative ways of accessing grandparent
granddaughter(X,Y) :- female(X), grandparent(Y,X).
sibling(X,Y) :- father(Z,X), mother(W,X), father(Z,Y), mother(W,Y), X\==Y.
brother(X,Y) :- male(X), sibling(X,Y).
%alternate way of writing it
%male(X), parent(Z,X), parent(Z,Y), \+sameperson(X,Y).
sister(X,Y) :- female(X), sibling(X,Y).
uncle(X,Y) :- male(X), brother(X,Z), parent(Z,Y).
aunt(X,Y) :- female(X), sister(X,Z), parent(Z,Y).
cousin(X,Y) :- parent(W,X),sibling(W,Z),parent(Z,Y).
sameperson(X,X).

%to find the mother of tim: mother(X,tim)
%to find the children of jeremy: father(jeremy,X) (run multiple times)
%to find the kiddos with the same father as emily: father(X,Y),father(X,emily)
%to find pat's grandchildren: father(X,Y),mother(pat,X)

quadrilateral(abcd).
quadrilateral(efgh).
quadrilateral(ijkl).

equiangular(abcd).
equiangular(efgh).

equilateral(abcd).
equilateral(ijkl).
%third set of exercises dealing with shapes
rectangle(X):- equiangular(X), quadrilateral(X).
rhombus(X):- equilateral(X), quadrilateral(X).
square(X):- rectangle(X), rhombus(X).
% if it is a rectangle and/or a rhombus then it is a quadrilateral
oblong(X):- rectangle(X), \+square(X).
diamond(X):- rhombus(X), \+ square(X).
%abcd is a rectangle,a rhombus therefore also a square
%efgh is a rectangle, and not a sqaure therefore it is oblong
%ijkl is a rhombus, and not a square and is therefore a diamond

%exercise about height
%check the answers on this one
height(john,180).
height(mary,169).
height(tim,158).
height(susan,176).

tall(X) :- height(X,Y) , Y>=175.

/** <examples>
 * the two queries wont run as expected
 * (tall(X)) and \+tall(X))
 * what happens is \+ that it looks if tall(X) is true
 * If tall is true then \+tall(X) is false
 * If tall is false then \+tall(X) is true
 * can almost be imagined to be is there anyone who is tall
 * every single person has to be NOT tall if \+tall(X) is true
*/

%exercise about marks

mark(joe_smith,ecm2414,38).
mark(mary_briggs,ecm2414,75).
mark(jane_doe,ecm2414,64).
mark(edward_elgar,ecm2414,68).
mark(rashmi_sharma,ecm2414,43).
mark(daisy_ho,ecm2414,77).
mark(harith_karimi,ecm2414,56).
mark(jonathan_edwards,ecm2414,35).
mark(ursula_dent,ecm2414,49).
mark(joe_smith,ecm2418,44).
mark(mary_briggs,ecm2418,70).
mark(jane_doe,ecm2418,61).
mark(edward_elgar,ecm2418,57).
mark(rashmi_sharma,ecm2418,51).
mark(daisy_ho,ecm2418,80).
mark(harith_karimi,ecm2418,37).
mark(jonathan_edwards,ecm2418,39).
mark(ursula_dent,ecm2418,52).
mark(joe_smith,ecm2419,56).
mark(mary_briggs,ecm2419,58).
mark(jane_doe,ecm2419,59).
mark(edward_elgar,ecm2419,37).
mark(rashmi_sharma,ecm2419,31).
mark(daisy_ho,ecm2419,72).
mark(harith_karimi,ecm2419,41).
mark(jonathan_edwards,ecm2419,36).
mark(ursula_dent,ecm2419,65).
mark(joe_smith,ecm2420,27).
mark(mary_briggs,ecm2420,61).
mark(jane_doe,ecm2420,44).
mark(edward_elgar,ecm2420,53).
mark(rashmi_sharma,ecm2420,47).
mark(daisy_ho,ecm2420,67).
mark(harith_karimi,ecm2420,59).
mark(jonathan_edwards,ecm2420,32).
mark(ursula_dent,ecm2420,39).

mitigation(joe_smith,ecm2414).
mitigation(joe_smith,ecm2418).
mitigation(ursula_dent,ecm2419).
mitigation(ursula_dent,ecm2420).
mitigation(jonathan_edwards,ecm2418).

pass(X,Y) :- mark(X,Y,Z), Z>= 40.
fail(X,Y) :- mark(X,Y,Z), Z<40.
referred(X,Y) :- fail(X,Y), \+mitigation(X,Y).
deferred(X,Y) :- fail(X,Y), mitigation(X,Y).
%pass is at least 40%
%to be referred rather than deferred they need to have a mitigation
%harith_karimi and jonathan_edwards failed ecm2418
%joe failed ecm2414 and ecm2420
%joe smith, jonathan edwards and ursula dent each have 1 deferral
%jonathan edwards failed them all
