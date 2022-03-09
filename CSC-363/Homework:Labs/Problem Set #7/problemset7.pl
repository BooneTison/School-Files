% Problem Set #7
% Boone Tison

% 1.
mylength([],0).
mylength([_ | T], X) :- mylength(T,M), X is M+1.

member(X, [X | _]).
member(X, [_ | Y]) :- member(X, Y).
member(X, [Y | _]) :- setEq(X,Y).

median([M],M).
median(L, M) :- member(M, L), median(L, [], [], M),!.

median([], L1, L2, M) :- mylength(L1, X), mylength(L2, X).
median([X | L], L1, L2, M) :- X < M, median(L, [X | L1], L2, M).
median([X | L], L1, L2, M) :- X > M, median(L, L1, [X | L2], M).
median([X | L], L1, L2, M) :- X = M, median(L, L1, L2, M).

% 2.
setEqHelp([],S2).

setEq(S1, S2) :- mylength(S1, X), mylength(S2, X), setEqHelp(S1,S2).
setEqHelp([H | T], S2) :- member(H, S2), setEqHelp(T,S2),!.
