package Domain.Settings;

public class SETTINGS {
    public enum RUNNER{
        STANDARD,
        FINITE_AUTOMATA
    }

    public enum ACTIONS{
        TEST_FA,
        TEST_G,
        RUN_PROGRAMS,

        TEST_FIRST_FOLLOW,
    }

    public enum FA_TESTS{
        DFA_TEST("src/Domain/Files/FA_Files/DFA.in"),
        NFA_TEST("src/Domain/Files/FA_Files/NFA.in");
        public final String PATH;
        private FA_TESTS(String path) {
            this.PATH = path;
        }
    }

    public enum GRAMMAR{
        G1("src/Domain/Files/G_Files/g1.txt"),
        G2("src/Domain/Files/G_Files/g2.txt"),

        G3("src/Domain/Files/G_Files/g3.txt");
        public final String PATH;
        private GRAMMAR(String path) {
            this.PATH = path;
        }
    }

    public enum DFA_FILES{
        IDENTIFIER_DFA("src/Domain/Files/FA_Files/DFAIdentifier.in"),
        STRING_DFA("src/Domain/Files/FA_Files/DFAString.in"),
        CHAR_DFA("src/Domain/Files/FA_Files/DFAChar.in"),
        INTEGER_DFA("src/Domain/Files/FA_Files/DFAInteger.in");
        public final String PATH;
        private DFA_FILES(String path) {
            this.PATH = path;
        }
    }

    public enum PROBLEMS{
        P1,
        P2,
        P3,
        ERROR
    }

    public enum P1{
        PROGRAM("src/Domain/Files/P_Files/P1.txt"),
        PIF("src/Domain/Files/PIF_Files/P1PIF.txt"),
        ST("src/Domain/Files/ST_Files/P1ST.txt");
        public final String PATH;
        private P1(String path) {
            this.PATH = path;
        }
    }

    public enum P2{
        PROGRAM("src/Domain/Files/P_Files/P2.txt"),
        PIF("src/Domain/Files/PIF_Files/P2PIF.txt"),
        ST("src/Domain/Files/ST_Files/P2ST.txt");
        public final String PATH;
        private P2(String path) {
            this.PATH = path;
        }
    }

    public enum P3{
        PROGRAM("src/Domain/Files/P_Files/P3.txt"),
        PIF("src/Domain/Files/PIF_Files/P3PIF.txt"),
        ST("src/Domain/Files/P_Files/P3ST.txt");
        public final String PATH;
        private P3(String path) {
            this.PATH = path;
        }
    }

    public enum P4{
        PROGRAM("src/Domain/Files/P_Files/P4.txt"),
        PIF("src/Domain/Files/PIF_Files/P4PIF.txt"),
        ST("src/Domain/Files/ST_Files/P4ST.txt");
        public final String PATH;
        private P4(String path) {
            this.PATH = path;
        }
    }
}
