import Domain.Settings.SETTINGS;
import Runner.FARunner;
import Runner.GrammarRunner;
import Runner.StandardRunner;
import Runner.TestRunner;

public class Main {
    public static SETTINGS.ACTIONS ACTION_SETTINGS = SETTINGS.ACTIONS.TEST_G;
    public static SETTINGS.RUNNER RUNNER_SETTINGS = SETTINGS.RUNNER.FINITE_AUTOMATA;

    public static void main(String[] args) {
        switch (ACTION_SETTINGS){
            case TEST_FA -> TestRunner.run();
            case TEST_G -> GrammarRunner.run();
            case RUN_PROGRAMS -> {
                switch (RUNNER_SETTINGS){
                    case STANDARD -> StandardRunner.run();
                    case FINITE_AUTOMATA -> FARunner.run();
                    default -> System.out.println("WRONG RUNNER SETTINGS");
                }
            }
            default -> System.out.println("WRONG ACTION SETTINGS");
        }

    }

}