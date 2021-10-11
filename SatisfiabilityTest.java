import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
public class SatisfiabilityTest {
    int numOfSymbols = 0;   //# of variables
    int numOfClauses = 0;
    int maxVariable = 0;

    //
    public static void STproblem1(){
        //(x1 v x3 v -x4) ^ (X4) ^ (x2 v -X3)
        System.out.println("Problem1: (x1 v x3 v -x4) ^ (X4) ^ (x2 v -X3)");
        System.out.println("The integers that proposition symbols are stored: ");

        System.out.println("alpha: {1, 3, -4}, {4}, {2 v -3}");
        Sentence p1alpha = new Sentence(4, 3);
        p1alpha.sentence.add(new HashSet<>(Arrays.asList(1, 3, -4)));
        p1alpha.sentence.add(new HashSet<>(Arrays.asList(4)));
        p1alpha.sentence.add(new HashSet<>(Arrays.asList(2, -3)));

        Scanner scn = new Scanner(System.in);
        boolean tryAgain = false;

        do{
            System.out.println("Please enter maxFlips (the suggested maxFlips is 4)");
            int maxFlips = scn.nextInt();

            System.out.println("Please enter maxTries (the suggested maxTries is 16)");
            int maxTries = scn.nextInt();

            System.out.println(p1alpha.GSAT(p1alpha, maxFlips, maxTries));
            System.out.println("Enter y to try again, Enter other to end this problem demonstration.");
            if(scn.next().charAt(0) == 'y'){
                tryAgain = true;
            }else{
                tryAgain = false;
            }
        }while(tryAgain);
    }

    //
//    public static void readFile(File f){
//
//    }

    //
    public static void N_Queens_4(){
        System.out.println("Problem2: N-Queens_4 problem");
        Scanner scn = new Scanner(System.in);
        boolean tryAgain = false;
        do{
            Sentence NQueens = readFile("nqueens/nqueens_4.cnf");

            System.out.println("Please enter maxFlips (the suggested maxFlips is " + NQueens.numOfSymbols +")");
            int maxFlips = scn.nextInt();

            System.out.println("Please enter maxTries (the suggested maxTries is 1000 or more)");
            int maxTries = scn.nextInt();

            System.out.println(NQueens.GSAT(NQueens, maxFlips, maxTries));
            System.out.println("Enter y to try again, Enter other to end this problem demonstration.");
            if(scn.next().charAt(0) == 'y'){
                tryAgain = true;
            }else{
                tryAgain = false;
            }
        }while(tryAgain);

    }

    public static void quinn(){
        System.out.println("Problem3: quinn.cnf problem");
        System.out.println("If you don't get an assignment, you can try again by entering y");
        Scanner scn = new Scanner(System.in);
        boolean tryAgain = false;
        do{
            Sentence quinn = readFile("cnf/quinn.cnf");

            System.out.println("Please enter maxFlips (the suggested maxFlips is " + quinn.numOfSymbols +")");
            int maxFlips = scn.nextInt();

            System.out.println("Please enter maxTries (the suggested maxTries is 10000 or more)");
            int maxTries = scn.nextInt();

            System.out.println(quinn.GSAT(quinn, maxFlips, maxTries));
            System.out.println("Enter y to try again, Enter other to end this problem demonstration.");
            if(scn.next().charAt(0) == 'y'){
                tryAgain = true;
            }else{
                tryAgain = false;
            }
        }while(tryAgain);
    }

    public static void aim_50_1_6_yes1_4(){
        System.out.println("Problem3: aim-50-1_6-yes1-4.cnf problem");
        Scanner scn = new Scanner(System.in);
        boolean tryAgain = false;
        do{
            Sentence aim50 = readFile("cnf/aim-50-1_6-yes1-4.cnf");

            System.out.println("Please enter maxFlips (the suggested maxFlips is " + aim50.numOfSymbols +")");
            int maxFlips = scn.nextInt();

            System.out.println("Please enter maxTries (the suggested maxTries is 10000 or more)");
            System.out.println("This may take some time, please wait patiently.");
            int maxTries = scn.nextInt();

            System.out.println(aim50.GSAT(aim50, maxFlips, maxTries));
            System.out.println("Enter y to try again, Enter other to end this problem demonstration.");
            if(scn.next().charAt(0) == 'y'){
                tryAgain = true;
            }else{
                tryAgain = false;
            }
        }while(tryAgain);
    }

    public static Sentence readFile(String path){
        int numOfSymbols = 0;   //# of variables
        int numOfClauses = 0;
        Sentence sentence = new Sentence();
        try {
            File file = new File(path);
            System.out.println("Reading file"+" "+path);
            //use readString function to
            String allCNF = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
            String[] CNFArr = allCNF.split("\\s0\\s+"); //split the content by " 0 ", apply the split as many times as possible,
            // the empty strings will be discarded
            //the first element in the array will consist of "c .....", "p cnf m n", and the first clause
            //parsing the first string in the array
            String first = CNFArr[0];
            //System.out.println("the first is \n"+first);
            String[] firstArr = first.split("\n");  //split the  first string into: comment(string starts with "c"), header line(string starts with p), and the first cnf
            //System.out.println("The file contains: ");
            //iterate through the first string array and record the key numbers
            for(String str : firstArr){
                //ignore the string starts with "c"
                if(str.charAt(0) == 'p'){
                    //System.out.println(str);                  //  arr[0][1][2][3]
                    String[] definition_Arr = str.split(" ");   //ex: p cnf 4 3
                    numOfSymbols = Integer.parseInt(definition_Arr[2]);
                    numOfClauses = Integer.parseInt(definition_Arr[3]);
                    sentence.sentence = new HashSet<>(numOfClauses);
                    sentence.numOfClauses = numOfClauses;
                    sentence.numOfSymbols = numOfSymbols;
                    //System.out.println(numOfSymbols + " Proposition Symbols\n"+ numOfClauses + " Clauses\n");
                }
                else if(str.charAt(0) != 'c'){  //the first clause
                    //System.out.println(str);
                    //System.out.println("yes");
                    String[] clause = str.split("\\s+");    //split the string with one or multiple spaces
                    // (cannot use split(" ") since it won't recognized the token with more than one space)
                    //  System.out.println(clause[0]);
                    //  System.out.println(clause[1]);
                    //  System.out.println(clause[2]);
                    Set<Integer> clauseList = new HashSet<Integer>();
                    //System.out.println("the length is "+clause.length);
                    /*for(int i = 0; i < clause.length; i++){
                        System.out.println(i+" is "+clause[i]+"\n");
                    }*/
                    for(int i = 0; i < clause.length; i++){
                        //System.out.println(clause[i]);
                        try{
                            int num = Integer.parseInt(clause[i].trim());
                            //System.out.println(num);
                            clauseList.add(num);
                        }
                        catch(NumberFormatException ex){ // handle the exception
                            System.out.println("There's an error.");
                        }
                    }
                    sentence.sentence.add(clauseList);
                }
                else{   //the case starts with "c"
                    //System.out.println(str);
                    continue;
                }
            }
            //parsing the rest CNF commands
            //System.out.println("start");
            for(int i = 1; i < CNFArr.length; i++){
                String str = CNFArr[i];
                if(str.charAt(0) == 'c'){
                    continue;       //if it is a comment line, then ignore
                }
                else{
                    String[] clause = str.split("\\s+");   //split the string with one or multiple spaces
                    // (cannot use split(" ") since it won't recognized the token with more than one space)
                    Set<Integer> clauseList = new HashSet<Integer>();
                    for(int j = 0; j < clause.length; j++){
                        try{
                            int num = Integer.parseInt(clause[j].trim());
                            clauseList.add(num);
                        }
                        catch(NumberFormatException ex){
                            System.out.println("there's an error");
                        }
                    }
                    sentence.sentence.add(clauseList);
                }

            }


        } catch (IOException e) {
            System.out.println("IOexception occurred.");
            e.printStackTrace();
        }
        return sentence;
    }

    public void executeSatisfiabilityTesting(){
        Scanner scn = new Scanner(System.in);
        System.out.println("press any character to demonstrate part3 problem1");
        scn.next();
        SatisfiabilityTest.STproblem1();
        System.out.println();

        System.out.println("press any character to demonstrate part3 N_Queens_4");
        scn.next();
        SatisfiabilityTest.N_Queens_4();
        System.out.println();

        System.out.println("press any character to demonstrate part3 quinn");
        scn.next();
        SatisfiabilityTest.quinn();
        System.out.println();

        System.out.println("press any character to demonstrate part3 aim-50-1_6-yes1-4");
        scn.next();
        SatisfiabilityTest.aim_50_1_6_yes1_4();
        System.out.println();
    }
}
