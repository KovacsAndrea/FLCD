package Runner;

import Core.MyFAScanner;
import Domain.FiniteAutomata.DFA;
import Domain.Settings.SETTINGS;
import Domain.Specification.Specifications;

public class FARunner {
    public static SETTINGS.PROBLEMS PROBLEM_CHOICE = SETTINGS.PROBLEMS.P2;
    public static void run(){
        Specifications.createCodification();
        DFA dfa = new DFA();
        switch (PROBLEM_CHOICE){
            case P1 -> {
                MyFAScanner s1 = new MyFAScanner(SETTINGS.P1.PROGRAM.PATH, SETTINGS.P1.PIF.PATH, SETTINGS.P1.ST.PATH, dfa);
                s1.scan();
            }
            case P2 -> {
                MyFAScanner s2 = new MyFAScanner(SETTINGS.P2.PROGRAM.PATH, SETTINGS.P2.PIF.PATH, SETTINGS.P2.ST.PATH, dfa);
                s2.scan();
            }
            case P3 -> {
                MyFAScanner s3 = new MyFAScanner(SETTINGS.P3.PROGRAM.PATH, SETTINGS.P3.PIF.PATH, SETTINGS.P3.ST.PATH, dfa);
                s3.scan();
            }
            case ERROR -> {
                MyFAScanner s4 = new MyFAScanner(SETTINGS.P4.PROGRAM.PATH, SETTINGS.P4.PIF.PATH, SETTINGS.P4.ST.PATH, dfa);
                s4.scan();
            }
        }
    }
}
