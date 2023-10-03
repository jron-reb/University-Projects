ca_weight(ecm2414,40).
ca_weight(ecm2415,100).
ca_weight(ecm2418,30).
ca_weight(ecm2419,20).
ca_weight(ecm2420,100).
ca_weight(ecm2422,50).
ca_weight(ecm2423,20).
ca_weight(ecm2433,30).

ca_mark(alan_turing,ecm2414,67).
ca_mark(alan_turing,ecm2415,73).
ca_mark(alan_turing,ecm2418,68).
ca_mark(alan_turing,ecm2419,61).
ca_mark(alan_turing,ecm2420,80).
ca_mark(alan_turing,ecm2422,79).
ca_mark(alan_turing,ecm2423,57).
ca_mark(alan_turing,ecm2433,64).

ca_mark(charles_babbage,ecm2414,70).
ca_mark(charles_babbage,ecm2415,73).
ca_mark(charles_babbage,ecm2418,64).
ca_mark(charles_babbage,ecm2419,66).
ca_mark(charles_babbage,ecm2420,71).
ca_mark(charles_babbage,ecm2422,57).
ca_mark(charles_babbage,ecm2423,73).
ca_mark(charles_babbage,ecm2433,65).

ca_mark(ada_lovelace,ecm2414,86).
ca_mark(ada_lovelace,ecm2415,65).
ca_mark(ada_lovelace,ecm2418,76).
ca_mark(ada_lovelace,ecm2419,65).
ca_mark(ada_lovelace,ecm2420,64).
ca_mark(ada_lovelace,ecm2422,71).
ca_mark(ada_lovelace,ecm2423,63).
ca_mark(ada_lovelace,ecm2433,69).

exam_mark(alan_turing,ecm2414,58).
exam_mark(alan_turing,ecm2418,65).
exam_mark(alan_turing,ecm2419,66).
exam_mark(alan_turing,ecm2422,63).
exam_mark(alan_turing,ecm2423,64).
exam_mark(alan_turing,ecm2433,68).

exam_mark(charles_babbage,ecm2414,70).
exam_mark(charles_babbage,ecm2418,76).
exam_mark(charles_babbage,ecm2419,72).
exam_mark(charles_babbage,ecm2422,48).
exam_mark(charles_babbage,ecm2423,69).
exam_mark(charles_babbage,ecm2433,71).

exam_mark(ada_lovelace,ecm2414,67).
exam_mark(ada_lovelace,ecm2418,75).
exam_mark(ada_lovelace,ecm2419,60).
exam_mark(ada_lovelace,ecm2422,69).
exam_mark(ada_lovelace,ecm2423,68).
exam_mark(ada_lovelace,ecm2433,74).

total_mark(Student,Module,Mark) :- ca_mark(Student,Module,CaMark), ca_weight(Module,CaWeight), CaWeight<100, exam_mark(Student,Module,ExamMark), Mark is (CaMark*CaWeight+ExamMark*(100-CaWeight))/100.
total_mark(Student,Module,Mark) :- ca_mark(Student,Module,Mark).

takes(Student,Module) :- ca_mark(Student,Module,_).

sum_of_total_marks(Student,[Module|Modules],Mark) :- total_mark(Student,Module,Modulemark), sum_of_total_marks(Student,Modules,Sumofmarks), Mark is Modulemark+Sumofmarks.
sum_of_total_marks(_,[],0).

overall_mark(Student, Overallmark) :- setof(Module,takes(Student,Module),Modules), length(Modules,Length), sum_of_total_marks(Student,Modules,Sum), Overallmark is Sum/Length.


%member1(X,[X|_]).
%member1(X,[Y|L]) :- member1(X,L).

%member2(X,[X|_]) :- !.
%member2(X,[Y|L]) :- member2(X,L).

class(X,Y) :- mark(X,Z), class_of(Z,Y).

class_of(Z,Y) :- Z>=69.5, !, Y=first.
class_of(Z,Y) :- Z>=59.5, !, Y=uppersecond.
class_of(Z,Y) :- Z>=49.5, !, Y=lowersecond.
class_of(Z,Y) :- Z>=39.5, !, Y=third.
class_of(Z,Y) :- Y=fail.

mark(john,45).
mark(mary,67).
mark(tom,73).
mark(bill,38).
mark(anne,47).
mark(sue,52).






