import java.util.*;

public class Sentence {
    //m satisfies a = Sentence a is true in model m
    //M(alpha) = set of all models of alpha
    //entailment = every model in which alpha is true => beta is also true
    Set<Set<Integer>> sentence;
    int numOfSymbols;
    int numOfClauses;

    public Sentence(){
        sentence = new HashSet<>();

    }


    public Sentence(int symbolNum, int clauseNum){
        sentence = new HashSet<>();
        numOfSymbols = symbolNum;
        numOfClauses = clauseNum;
    }

    public void printSentence(){
        System.out.print("clauses of the sentence: ");
        for(Set<Integer> clause : sentence){
            System.out.print(clause + "   ");
        }
        System.out.println();
    }

    // get all symbols in the sentence and store them in a set
    public Set<Integer> getSymbolSet(){
        Set<Integer> symbolSet = new HashSet<>();
        for(Set<Integer> clause : sentence){
            for(int variable : clause) {
                symbolSet.add(Math.abs(variable));
            }
        }
        return symbolSet;
    }


    /*
   tests if an assignment satisfies a clause (makes it true) or not.
         if an assignment does not satisfy a clause (makes it false) nor not.
    */
    public static Boolean assignmentSatisfiesClause(Set<Integer> clause, Hashtable<Integer, Boolean> assignment){
        // the clause is satisfied as long as one of the literals in the clause is true
        Iterator clauseIt = clause.iterator();
        while(clauseIt.hasNext()){
            int literal = (Integer)clauseIt.next();
            boolean value;
            //TODO: consider partial assignment when literal found in assignment with value null
            if(literal > 0)
                value = assignment.get(literal);
            else//literal < 0
                value = assignment.get(Math.abs(literal)) ? false : true;
            if(value)
                return true;
        }
        return false;
    }


    //returns true if assignment satisfies a sentence
    public static Boolean assignmentSatisfiesSentence(Sentence alpha, Hashtable<Integer, Boolean> assignment){
        for(Set<Integer> clause : alpha.sentence){
            Boolean bool = assignmentSatisfiesClause(clause, assignment);
            if(bool == false)
                return false;
        }
        return true;
    }

    /*
   function TT_ENTAILS?(KB,alpha) returns true or false
       inputs: KB, alpha, query
       symbols <- a list of proposition symbols in KB and alpha
       return TT-CHECK-ALL(KB,alpha,symbols,{})
   */
    public Boolean TT_ENTAILS(Sentence KB, Sentence alpha){
        Set<Integer> symbols = new HashSet<>();
        symbols.addAll(KB.getSymbolSet());
        symbols.addAll(alpha.getSymbolSet());
        return TT_CHECK_ALL(KB, alpha, symbols, new Hashtable<Integer, Boolean>());
    }

    /*
        function TT-CHECK-ALL(KB,alpha,symbols,model) returns true or false
            if EMPTY?(symbols) then
                if PL-TRUE?(KB,model) then
                    return PL-TRUE?(alpha,model)
                else
                    return true     //when KB is flase, always return true
            else
                P <- FIRST(symbols)
                rest <- (symbols)
                return (TT-CHECK-ALL(KB,alpha,rest,model&&{P=true})
                        and
                        TT-CHECK-ALL(KB,alpha,rest,model&&{P=false}))
        */
    public Boolean TT_CHECK_ALL(Sentence KB, Sentence alpha, Set<Integer> symbols, Hashtable<Integer, Boolean> model){
        //System.out.println();
        //System.out.println("model: " + model);
        //System.out.println("symbols: " + symbols);
//        System.out.println("symbols: " + symbols);

        if(symbols.isEmpty()) {
            if (assignmentSatisfiesSentence(KB, model))
                return assignmentSatisfiesSentence(alpha, model);
            else
                return true;    //when KB is false, always return true
        }

        int firstSymbol = symbols.iterator().next();
        //System.out.println("firstSymbol: " + firstSymbol);
        Set<Integer> rest = new HashSet<>();
        rest.addAll(symbols);
        rest.remove(firstSymbol);
        //System.out.println("rest: " + rest);
        Hashtable<Integer, Boolean> copyT = new Hashtable<>();
        Hashtable<Integer, Boolean> copyF = new Hashtable<>();
        copyT.putAll(model);
        copyF.putAll(model);

        copyT.put(Math.abs(firstSymbol), true);
        //System.out.println("copyT: " + copyT);
        copyF.put(Math.abs(firstSymbol), false);
        //System.out.println("copyF: " + copyF);

        return (TT_CHECK_ALL(KB, alpha, rest, copyT)) && (TT_CHECK_ALL(KB, alpha, rest, copyF));
    }

    /*
    procedure GSAT
    Input: a set of clauses alpha, MAX-FLIPS, and MAX-TRIES
    Output: a satisfying truth assignment of alpha, if found
        for i := 1 to MAX-TRIES
            assignment := a randomly generated truth assignment
            for j := 1 to MAX-FLIPS
                if assignment satisfies alpha then return assignment
                p := a propositional variable such that a change
                in its truth assignment gives the largest increase
                in the total number of clauses of ↵ that are satisfied by T
                T := T with the truth assignment of p flipped
        return “no satisfying assignment found”
     */
    public Hashtable<Integer,Boolean> GSAT(Sentence alpha, int maxFlips, int maxTries){
        for(int i=0;i<maxTries;i++){
            Hashtable<Integer,Boolean> assignment = randomlyGenerateAssignment(alpha);
            for(int j=0;j<maxFlips;j++){
                if(Sentence.assignmentSatisfiesSentence(alpha,assignment))
                    return assignment;

                int p = 0;
                int maxClauseSatisfied = numOfClauseSatisfied(alpha, assignment);//heuristic value
                Set<Integer> symbolSet = alpha.getSymbolSet();
                for(int symbol : symbolSet){
                    Hashtable<Integer,Boolean> flippedAssignment = flip(symbol, assignment);
                    if(numOfClauseSatisfied(alpha, flippedAssignment) > maxClauseSatisfied)
                        p = symbol;
                }

                if(p != 0)
                    assignment = flip(p, assignment);
                else
                    assignment = flip(selectSymbolRandomly(symbolSet), assignment);
            }
        }
        return null;
    }

    //
    public int selectSymbolRandomly(Set<Integer> symbolsSet){
        int i = 0;
        for(int symbol : symbolsSet)
            return symbol;
        return 0;//should never reach this case
    }


    //
    public Hashtable<Integer,Boolean> flip(int symbolToBeFlipped, Hashtable<Integer,Boolean> assignment){
        if(assignment.get(symbolToBeFlipped))
            assignment.put(symbolToBeFlipped, false);
        else
            assignment.put(symbolToBeFlipped, true);
        return assignment;
    }


    //
    public int numOfClauseSatisfied(Sentence alpha, Hashtable<Integer, Boolean> assignment){
        int score = 0;
        for(Set<Integer> clause : alpha.sentence){
            if(Sentence.assignmentSatisfiesClause(clause, assignment))
                score++;
        }
        return score;
    }

    //
    public Hashtable<Integer,Boolean> randomlyGenerateAssignment(Sentence alpha){
        Hashtable<Integer,Boolean> assignment = new Hashtable<>();
        for(int symbol : alpha.getSymbolSet()){
            if(Math.random() > 0.5)
                assignment.put(symbol, true);
            else
                assignment.put(symbol, false);
        }
        return assignment;
    }



}
