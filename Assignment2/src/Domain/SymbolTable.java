package Domain;

import Domain.Exceptions.LiteralException;
import Domain.Specification.LexicalPart.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SymbolTable {
    private final HashTable _hashTable;
    private final String _programFile;

    private int lineNr;


    public SymbolTable(String programFile){
        this._hashTable = new HashTable(16);
        this._programFile = programFile;
    }

    public void getSymbolTable() {
        try {
            lineNr = 1;
            File file = new File(_programFile);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                runTokens(line);
                lineNr++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(_hashTable);
    }

    public void runTokens(String line){
        try {
            tokenize(line);
        } catch (LiteralException e) {
            System.out.println(e.getMessage());
        }
    }

    public void tokenize(String line) throws LiteralException {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '\"'){
                String miniString = getStringConstant(line, i);
                _hashTable.add(miniString);
                tokens.add(miniString);
                i+= miniString.length();
            }
            else if(line.charAt(i) == '\''){
                String miniChar = getCharConstant(line, i);
                _hashTable.add(miniChar);
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
                    _hashTable.add(miniInt);
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
                    _hashTable.add(miniInt);
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
                    _hashTable.add(miniInt);
                    i+= miniInt.length();
                }
            }
            else if(Character.isLetter(line.charAt(i))){
                String identifier = getIdentifier(line, i);
                if (identifier != null && !ReservedWords.isReservedWord(identifier)){
                    _hashTable.add(identifier);
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
        System.out.println(tokens);
    }

    public String getStringConstant(String line, int position) throws LiteralException {
        int nextDoubleQuote = line.indexOf("\"", position + 1);
        String miniString = line.substring(position, nextDoubleQuote + 1);
        if(!miniString.matches(Constants.getStringPattern())){
            throw new LiteralException(lineNr, position, "\" expected", _programFile);
        }
        return miniString;
    }

    public String getCharConstant(String line, int position) throws LiteralException {
        int nextSingleQuote = line.indexOf('\'', position + 1);
        String miniChar = line.substring(position, nextSingleQuote + 1);
        if(!miniChar.matches(Constants.getCharPattern())){
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
            if(identifier.matches(Identifiers.getIdentifierPattern())){
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
        if(!miniInt.matches(Constants.getNumericPattern())){
            throw new LiteralException(lineNr, position, "Illegal literal", _programFile);
        }
        return miniInt;
    }
}
