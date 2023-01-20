package Runner;

import Domain.Grammar.MyGrammar;
import Domain.Parser.Parser;
import Domain.Parser.ParserOutput;
import Domain.Settings.SETTINGS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;

public class ParserRunner {
    public static List<String> readText(String filename) {
        List<String> sequence = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();
            while (line != null) {
                var symbols = List.of(line.split(" "));
                sequence.addAll(symbols);
                line = reader.readLine();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return sequence;
    }

    public static List<String> readPIF(String filename){
        try {
            List<String> tokens = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null){
                List<String> tokenAndPosition = Arrays.asList(line.split(" "));
                if(!tokenAndPosition.get(3).equals("-1")) {
                    if(tokenAndPosition.get(0).contains("\"") || tokenAndPosition.get(0).contains("'") || !Pattern.matches("[a-zA-Z]+",tokenAndPosition.get(0)))
                        tokens.add("constant");
                    else
                        tokens.add("identifier");
                }
                else{
                    tokens.add(tokenAndPosition.get(0).strip());
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println(tokens);
            return tokens;
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    public static void run(){
        MyGrammar grammar = new MyGrammar(SETTINGS.GRAMMAR.G3.PATH);

        Parser parser = new Parser(grammar);
        System.out.println(parser.printFirst());
        System.out.println(parser.printFollow());
        System.out.println(parser.printParseTable());
        List<String> sequence = readText(SETTINGS.GRAMMAR.G3SEQ.PATH);
        System.out.println(parser.parseSequence(sequence));

        ParserOutput parserOutput = new ParserOutput(parser,sequence,SETTINGS.GRAMMAR.G3OUT.PATH);
        parserOutput.printTree();

//        MyGrammar grammarG2 = new MyGrammar(SETTINGS.GRAMMAR.G2.PATH);
//
//        Parser parserP1 = new Parser(grammarG2);
//        System.out.println(parserP1.printFirst());
//        System.out.println(parserP1.printFollow());
//        System.out.println(parserP1.printParseTable());
//        List<String> sequenceP1 = readPIF(SETTINGS.P1.PIF.PATH);
//        System.out.println(parserP1.parseSequence(sequenceP1));
//
//        ParserOutput parserOutputP1 = new ParserOutput(parserP1,sequenceP1,SETTINGS.GRAMMAR.G2OUT.PATH);
//        parserOutputP1.printTree();
    }
}
