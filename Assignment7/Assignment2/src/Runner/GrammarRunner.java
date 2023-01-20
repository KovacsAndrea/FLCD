package Runner;

import Domain.Grammar.MyGrammar;
import Domain.Settings.SETTINGS;

public class GrammarRunner {
    public static SETTINGS.GRAMMAR GRAMMAR_CHOICE = SETTINGS.GRAMMAR.G1;
    public static String G1_NONTERMINAL = "A";
    public static String G2_NONTERMINAL = "IF_STMT";
    public static void run(){
        MyGrammar grammar = new MyGrammar(GRAMMAR_CHOICE.PATH);

        System.out.println(grammar.getNonTerminals());
        System.out.println(grammar.getTerminals());
        System.out.println(grammar.getS());
        System.out.println(grammar.getProductions());
        System.out.println(grammar.checkIfCFG());


        switch (GRAMMAR_CHOICE){
            case G1 -> System.out.println(grammar.getProductionsForNonTerminal(G1_NONTERMINAL));
            case G2 -> System.out.println(grammar.getProductionsForNonTerminal(G2_NONTERMINAL));
            default -> System.out.println("OOPSIES");
        }

    }
}
