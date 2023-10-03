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

height(john,180).
height(mary,169).
height(tim,158).
height(susan,176).

tall(X) :- height(X,Y), Y>175.
small(X) :- height(X,Y), Y<175.

/** <examples>
 * the two queries wont run as expected
 * (tall(X)) and \+tall(X))
 * what happens is \+ that it looks if tall(X) is true
 * If tall is true then \+tall(X) is false
 * If tall is false then \+tall(X) is true
 * can almost be imagined to be is there anyone who is tall
?- father(X, emily), father(X, Y)
?- parent(pat, X), parent(X, Y)
*/
