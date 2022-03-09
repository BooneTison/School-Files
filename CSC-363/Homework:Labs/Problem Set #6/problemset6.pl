% Problem Set #6
% Boone Tison

% Part 1
and(A,B) :- A, B.

or(A,B) :- A,!.
or(A,B) :- B.

equal(A,B) :- A = B.

xor(A,B) :- A, not(B),!.
xor(A,B) :- not(A), B.

nor(A,B) :- not(A), not(B).

nand(A,B) :- not(A),!.
nand(A,B) :- not(B).

% Part 2
head([X | _], X).

addToFront(L1, E, L2) :- equal(L2, [E | L1]).

mylast([L | []], L).
mylast([_ | T], L) :- mylast(T,L),!.

second_to_last([X,_], X).
second_to_last([_ | T], X) :- second_to_last(T, X),!.

mylength([],0).
mylength([_ | T], X) :- mylength(T,M), X is M+1.

% Part 3
explode([H], [H,H]).
explode([H | T], [H, H | T2]) :- explode(T, T2),!.

range(D, D, [D]).
range(B, E, []) :- B > E.
range(B, E, [B | T]) :- D is B+1, range(D, E, T),!.

reverse([], Final, Final).
reverse([H | T], Final, L) :- reverse(T, Final, [H | L]).
reverse(L, Final) :- reverse(L, Final, []),!.

palindrome(L1) :- reverse(L1, X), equal(L1, X).

compress([],[]).
compress([X],[X]).
compress([E1, E1 | T1], T2) :- compress([E1 | T1], T2),!.
compress([E1, E2 | T1], [E1 | T2]) :- compress([E2 | T1], T2).

element_at(0,[H | _], H).
element_at(K, [H | T], X) :- element_at(D, T, X), K is D+1,!.
element_at(K, [H | T], X) :- D is K-1, element_at(D, T, X),!.

