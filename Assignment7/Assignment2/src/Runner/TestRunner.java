package Runner;

import Domain.FiniteAutomata.FA;
import Domain.Settings.SETTINGS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestRunner {

    public static SETTINGS.FA_TESTS FA_CHOICE = SETTINGS.FA_TESTS.DFA_TEST;
    public static List<String> acceptedSequences = Arrays.asList("1", "11", "00", "01", "011", "001", "010101", "0011111111", "010011");
    public static List<String> badSequences = Arrays.asList("10", "100", "0110", "00111111110");
    public static void run(){
        FA fa = new FA(FA_CHOICE.PATH);
        if(fa.isDFA()){
            System.out.println("This is DFA!");
            List<String> allSequences = new ArrayList<>();
            allSequences.addAll(acceptedSequences);
            allSequences.addAll(badSequences);
            for (String s : allSequences){
                if(fa.accepts(s)){
                    System.out.println("Sequence " + s + " is accepted by the FA!");
                }
                else System.out.println("Sequence " + s + " is NOT accepted by the FA!");
            }
        }
        else System.out.println("This is NOT DFA!");
    }
}
