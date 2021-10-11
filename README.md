CSC 242 
Project2: 
Model Checking and Satisfiability Testing

How to run our project:

javac *.java

java main

Note:

1.	After enter the above commands, you will be ask to enter some characters according to the print messages to see the demonstration of part 2 and part 3

2.	Our part 1 and part 2 perform very well. Part 3 demostrates satisfiability testing on set of clauses in problem 1, nqueens_4.cnf, quinn.cnf, and aim-50-1_6-yes1-4.cnf. There are some issues in part 3. It takes super long time to find a satisfiable assignment.

How we built our project:
Part 1:
I first built the class Sentence{ } with three class variables Set<Set<Integer>> sentence, which Set<Integer> is a clause, int numOfSymbols, and  int numOfClauses. 
Part 2:
I built the methods TT_ENTAILS(Sentence KB, Sentence alpha) and TT_CHECK_ALL(Sentence KB, Sentence alpha, Set<Integer> symbols, HashTable<Integer, Boolean> model) in class Sentence{} according to the pseudocode in AIMA Fig. 7.10. In class ModelCheck{}, I converted the English sentences  to conjunctive normal form by hand with instruction printed out and called TT-ENTAILS to check each query.
Part 3:
I built the method GSAT(Sentence alpha, int maxFlips, int maxTries) in class Sentence{} according to the pseudocode given in the pdf. In class SatisfiabilityTest{}, I wrote readFile(String path) to create the set of clauses from the .cnf files and called GSAT to test each problem.


The whole game will be performed in class main{}. 


