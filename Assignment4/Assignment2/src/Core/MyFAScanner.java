package Core;

import Domain.Exceptions.LiteralException;
import Domain.FiniteAutomata.DFA;
import Domain.Specification.*;
import Domain.Specification.LexicalPart.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyFAScanner {
    private final HashTable _symbolTable;
    private final PIF _pif;
    private final String _programFile;
    private final String _PIFFile;
    private final String _STFile;
    private int lineNr;
    private boolean _lexicalCorrect;

    private final DFA _dfa;


    public MyFAScanner(String _programFile, String _PIFFile, String _STFile, DFA _dfa){
        this._symbolTable = new HashTable(16);
        this._pif = new PIF();
        this._programFile = _programFile;
        this._PIFFile = _PIFFile;
        this._STFile = _STFile;
        this._lexicalCorrect = true;
        this._dfa = _dfa;
    }

    public void scan() {
        List<Pair<String, Integer>> tokenPairs = new ArrayList<>();
        try {
            lineNr = 1;
            File file = new File(_programFile);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                List<String> tokens = runTokens(line);
                if(tokens!=null){
                    for (String token : tokens) {
                        tokenPairs.add(new Pair<>(token, lineNr));
                    }
                }
                lineNr++;
            }
            reader.close();
            if(_lexicalCorrect){
                System.out.println(_programFile + " is lexically correct!");
            }
            buildPIF(tokenPairs);
            writeResults();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(_symbolTable);
    }

    public List<String> runTokens(String line){
        try {
            return tokenize(line);
        } catch (LiteralException e) {
            if(_lexicalCorrect){
                System.out.println(_programFile + " is lexically incorrect!");
                _lexicalCorrect = false;
            }
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> tokenize(String line) throws LiteralException {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '\"'){
                String miniString = getStringConstant(line, i);
                _symbolTable.add(miniString);
                tokens.add(miniString);
                i+= miniString.length();
            }
            else if(line.charAt(i) == '\''){
                String miniChar = getCharConstant(line, i);
                _symbolTable.add(miniChar);
                tokens.add(miniChar);
                i+= miniChar.length();
            }
            else if (line.charAt(i) == '-'){
                String miniInt = getInteger(line, i);
                if(miniInt != null){
                    if(miniInt.charAt(0) != '-'){
                        tokens.add("-");
                    }
                    tokens.add(miniInt);
                    _symbolTable.add(miniInt);
                    if(miniInt.length() == 1){
                        i+=3;
                    }
                    else {
                        i+= miniInt.length();
                    }
                }
            }
            else if (line.charAt(i) == '+'){
                String miniInt = getInteger(line, i);
                if (miniInt != null){
                    if(miniInt.charAt(0) != '+'){
                        tokens.add("+");
                    }
                    tokens.add(miniInt);
                    _symbolTable.add(miniInt);
                    if(miniInt.length() == 1){
                        i+=3;
                    }
                    else {
                        i+= miniInt.length();
                    }
                }
            }
            else if (Character.isDigit(line.charAt(i))){
                String miniInt = getInteger(line, i);
                if (miniInt !=null){
                    tokens.add(miniInt);
                    _symbolTable.add(miniInt);
                    i+= miniInt.length();
                }
            }
            else if(Character.isLetter(line.charAt(i))){
                String identifier = getIdentifier(line, i);
                if (identifier != null && !ReservedWords.isReservedWord(identifier)){
                    _symbolTable.add(identifier);
                    tokens.add(identifier);
                    i+= identifier.length()-1;

                }
                if(identifier != null&&  ReservedWords.isReservedWord(identifier)){
                    tokens.add(identifier);
                    i+= identifier.length()-1;
                }
            }
            else if(Operators.isOperator(String.valueOf(line.charAt(i))) ||
                    Separators.isSeparator(String.valueOf(line.charAt(i)))){
                tokens.add(String.valueOf(line.charAt(i)));
            }
        }
        //System.out.println(tokens);
        return tokens;
    }

    public String getStringConstant(String line, int position) throws LiteralException {
        int nextDoubleQuote = line.indexOf("\"", position + 1);
        String miniString = line.substring(position, nextDoubleQuote + 1);
        if(!_dfa._string.accepts(miniString)){
            throw new LiteralException(lineNr, position, "\" expected", _programFile);
        }
        return miniString;
    }

    public String getCharConstant(String line, int position) throws LiteralException {
        int nextSingleQuote = line.indexOf('\'', position + 1);
        String miniChar = line.substring(position, nextSingleQuote + 1);
        if(!_dfa._char.accepts(miniChar)){
            throw new LiteralException(lineNr, position, "' expected", _programFile);
        }
        return miniChar;
    }

    public String getIdentifier(String line, int position) throws LiteralException {
        ArrayList<String> symbols = new ArrayList<>();
        String identifier;
        symbols.add(" ");
        symbols.addAll(Operators.getAll());
        symbols.addAll(Separators.getAll());
        int nextSymbol = -1;
        for(String symbol : symbols){
            nextSymbol = line.indexOf(symbol, position+1);
            if(nextSymbol != -1) {
                identifier = line.substring(position, nextSymbol);
            }
            else {
                identifier = line.substring(position);
            }
            if(_dfa._identifier.accepts(identifier)){
                return identifier;
            }

        }
        throw new LiteralException(lineNr, position, "Illegal literal", _programFile);
    }

    public String getInteger(String line, int position) throws LiteralException {
        ArrayList<String> symbols = new ArrayList<>();
        symbols.add(Operators.assignOperator());
        symbols.add(" ");
        symbols.addAll(Operators.arithmeticOperators());
        symbols.addAll(Operators.relationalOperators());
        if((line.charAt(position) == '+' || line.charAt(position) == '-') && line.charAt(position + 1) == ' '){
            position+=2;
            try{
                getIdentifier(line, position);
                return null;
            }
            catch (LiteralException ignored){

            }
        }
        int nextSymbol = -1;
        for(String symbol : symbols){
            nextSymbol = line.indexOf(symbol, position+1);
            if(nextSymbol != -1){
                if(Character.isDigit(line.charAt(nextSymbol -1))){
                    break;
                }
            }
        }
        String miniInt;
        if(nextSymbol == -1){
            if(Character.isDigit(line.charAt(line.length()-1))){
                miniInt = line.substring(position);
            }
            else {
                throw new LiteralException(lineNr, position, "Illegal literal", _programFile);
            }
        }
        else {
            miniInt = line.substring(position, nextSymbol);
        }
        if(!_dfa._integer.accepts(miniInt)){
            throw new LiteralException(lineNr, position, "Illegal literal", _programFile);
        }
        return miniInt;
    }

    public void buildPIF(List<Pair<String, Integer>> tokens) {
        for (Pair<String, Integer> tokenPair : tokens) {
            String token = tokenPair.getKey();

            if (Specifications.isSymbol(token)) {
                int code = Specifications.getCode(token);
                _pif.add(code, new Pair<>(-1, -1));
            } else if (Specifications.isIdentifier(token)) {
                _symbolTable.add(token);
                Pair<Integer, Integer> position = _symbolTable.getPosition(token);
                _pif.add(0, position);
            } else if (Specifications.isConstant(token)) {
                _symbolTable.add(token);
                Pair<Integer, Integer> position = _symbolTable.getPosition(token);
                _pif.add(1, position);
            }
        }
    }

    public void writeResults() {
        try {
            File pifFile = new File(_PIFFile);
            FileWriter pifFileWriter = new FileWriter(pifFile, false);
            BufferedWriter pifWriter = new BufferedWriter(pifFileWriter);
            pifWriter.write(_pif.toString());
            pifWriter.close();

            File symbolTableFile = new File(_STFile);
            FileWriter symbolTableFileWriter = new FileWriter(symbolTableFile, false);
            BufferedWriter symbolTableWriter = new BufferedWriter(symbolTableFileWriter);
            symbolTableWriter.write(_symbolTable.toString());
            symbolTableWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
