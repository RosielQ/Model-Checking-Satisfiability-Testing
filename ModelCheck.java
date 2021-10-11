import java.util.*;

public class ModelCheck {

    public static void MCproblem1() {
        System.out.println("Mode Checking Problem1: {P, P => Q} |= Q");
        System.out.println("The integers that proposition symbols are stored: ");
        System.out.println("1   2");
        System.out.println("P   Q");
        System.out.println("KB: {1}, {-1 v 2}");
        Sentence p1KB = new Sentence(2, 2);
        p1KB.sentence.add(new HashSet<>(Arrays.asList(1)));
        p1KB.sentence.add(new HashSet<>(Arrays.asList(-1, 2)));

        System.out.println("alpha: {2}");
        Sentence p1alpha = new Sentence(1, 1);
        p1alpha.sentence.add(new HashSet<>(Arrays.asList(2)));

//        System.out.println("TEST!!!");
        if (p1KB.TT_ENTAILS(p1KB, p1alpha)) {
            System.out.println("p1: true");
        } else {
            System.out.println("p1: false");
        }

    }

    public static void MCproblem2(){
        System.out.println("Mode Checking Problem2: ");
        System.out.println("The integers that proposition symbols are stored: ");
        System.out.println(" 1   2   3   4   5   6   7   8   9");
        System.out.println("P11 P12 P21 P22 P13 P31 B11 B12 B21");
        System.out.println("R1: {-1}");
        System.out.println("R2: {-7, 2, 3}, {-2, 7}, {-3, 7}");
        System.out.println("R3: {-9, 1, 4, 6}, {-1, 9}, {-4, 9}, {-6, 9}");
        System.out.println("R7: {-8, 1, 4, 5}, {-1, 8}, {-4, 8}, {-5, 8}");
        System.out.println("R4: {-7}");
        System.out.println("R5: {9}");
        System.out.println("R6: {-8}");

        Sentence p2KB = new Sentence(9,15);
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-1)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-7, 2, 3)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-2, 7)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-3, 7)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-9, 1, 4, 6)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-1, 9)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-4, 9)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-6, 9)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-8, 1, 4, 5)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-1, 8)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-4, 8)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-5, 8)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-7)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(9)));
        p2KB.sentence.add(new HashSet<>(Arrays.asList(-8)));

        System.out.println("alpha: {-4}, {6}");
        Sentence p2alpha = new Sentence(2,2);
        p2alpha.sentence.add(new HashSet<>(Arrays.asList(-4)));
        p2alpha.sentence.add(new HashSet<>(Arrays.asList(6)));

        if (p2KB.TT_ENTAILS(p2KB, p2alpha)) {
            System.out.println("p2: true");
        } else {
            System.out.println("p2: false");
        }
    }


    public static void MCproblem3(){
        System.out.println("Mode Checking Problem3: ");
        System.out.println("The integers that proposition symbols are stored: ");
        System.out.println("    1       2       3       4       5");
        System.out.println("mythical  mortal  mammal  horned  magical");
        System.out.println("mythical => immortal:               {-1, -2}");
        System.out.println("not mythical => mortal mammal:      {1, 2}, {1, 3}");
        System.out.println("either immortal or mammal => horned: {2, 4}, {-3, 4}");
        System.out.println("horned => magical:                  {-4, 5}");

        Sentence p3KB = new Sentence(5,6);
        p3KB.sentence.add(new HashSet<>(Arrays.asList(-1, -2)));
        p3KB.sentence.add(new HashSet<>(Arrays.asList(1, 2)));
        p3KB.sentence.add(new HashSet<>(Arrays.asList(1, 3)));
        p3KB.sentence.add(new HashSet<>(Arrays.asList(2, 4)));
        p3KB.sentence.add(new HashSet<>(Arrays.asList(-3, 4)));
        p3KB.sentence.add(new HashSet<>(Arrays.asList(-4, 5)));

        System.out.println();
        System.out.println("Is unicorn mythical?: {1}");
        Sentence p3a1 = new Sentence(1,1);
        p3a1.sentence.add(new HashSet<>(Arrays.asList(1)));

        if (p3KB.TT_ENTAILS(p3KB, p3a1)) {
            System.out.println("Yes, we can prove that unicorn is mythical.");
        } else {
            System.out.println("No, we cannot prove that unicorn is mythical.");
        }

        System.out.println();
        System.out.println("Is unicorn magical?: {5}");
        Sentence p3a2 = new Sentence(1,1);
        p3a1.sentence.add(new HashSet<>(Arrays.asList(5)));

        if (p3KB.TT_ENTAILS(p3KB, p3a2)) {
            System.out.println("Yes, we can prove that unicorn is magical.");
        } else {
            System.out.println("No, we cannot prove that unicorn is mythical.");
        }

        System.out.println();
        System.out.println("Is unicorn hooned?: {4}");
        Sentence p3a3 = new Sentence(1,1);
        p3a1.sentence.add(new HashSet<>(Arrays.asList(4)));

        if (p3KB.TT_ENTAILS(p3KB, p3a3)) {
            System.out.println("Yes, we can prove that unicorn is horned.");
        } else {
            System.out.println("No, we cannot prove that unicorn is horned.");
        }



    }

    public void excuteModelChecking(){
        Scanner scn = new Scanner(System.in);
        System.out.println("press any character to demonstrate part2 problem1");
        scn.next();
        ModelCheck.MCproblem1();
        System.out.println();

        System.out.println("press any character to demonstrate part2 problem2");
        scn.next();
        ModelCheck.MCproblem2();
        System.out.println();

        System.out.println("press any character to demonstrate part2 problem3");
        scn.next();
        ModelCheck.MCproblem3();
        System.out.println();
        System.out.println("--------------------------------------------");
        System.out.println();
    }
}
