package Runner;

import Domain.Grammar.MyGrammar;
import Domain.Parser.Parser;
import Domain.Settings.SETTINGS;

public class FirstFollowTestRunner {
    public static SETTINGS.GRAMMAR GRAMMAR_CHOICE = SETTINGS.GRAMMAR.G3;

    public static void run(){
        MyGrammar grammar = new MyGrammar(GRAMMAR_CHOICE.PATH);

        Parser parser = new Parser(grammar);
        System.out.println(parser);
    }
}
