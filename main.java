import java.util.Scanner;

public class main {
    public static void main(String[] args){

       ModelCheck mc = new ModelCheck();
       mc.excuteModelChecking();

       SatisfiabilityTest st = new SatisfiabilityTest();
       st.executeSatisfiabilityTesting();

    }
}
