package Runner;

import Core.MyScanner;
import Domain.Settings.SETTINGS;
import Domain.Specification.Specifications;

import javax.swing.plaf.PanelUI;
import java.util.Set;

public class StandardRunner {
    public static SETTINGS.PROBLEMS PROBLEM_CHOICE = SETTINGS.PROBLEMS.ERROR;
    public static void run(){
        Specifications.createCodification();
        switch (PROBLEM_CHOICE){
            case P1 -> {
                MyScanner s1 = new MyScanner(SETTINGS.P1.PROGRAM.PATH, SETTINGS.P1.PIF.PATH, SETTINGS.P1.ST.PATH);
                s1.scan();
            }
            case P2 -> {
                MyScanner s2 = new MyScanner(SETTINGS.P2.PROGRAM.PATH, SETTINGS.P2.PIF.PATH, SETTINGS.P2.ST.PATH);
                s2.scan();
            }
            case P3 -> {
                MyScanner s3 = new MyScanner(SETTINGS.P3.PROGRAM.PATH, SETTINGS.P3.PIF.PATH, SETTINGS.P3.ST.PATH);
                s3.scan();
            }
            case ERROR -> {
                MyScanner s4 = new MyScanner(SETTINGS.P4.PROGRAM.PATH, SETTINGS.P4.PIF.PATH, SETTINGS.P4.ST.PATH);
                s4.scan();
            }
        }
    }
}
