package Domain;

import Domain.Specification.LexicalPart.ReservedWords;
import Domain.Specification.MiniLanguage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniScanner {
    private int _capacity;
    private SymbolTable _symbolTable;
    private String _programFile;


    public MiniScanner(String programFile){
        this._capacity = 16;
        this._symbolTable = new SymbolTable(_capacity);
        this._programFile = programFile;
    }

    public void scan() {
        try {
            File file = new File(_programFile);
            Scanner reader = new Scanner(file);

            int lineNr = 1;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                tokenize(line);
                ++lineNr;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(_symbolTable);
    }

    public void tokenize(String line) {
        ArrayList<String> tokens = new ArrayList<>();
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '\"'){
                String miniString = getStringConstant(line, i);
                _symbolTable.add(miniString);
                i+= miniString.length();
            }
            else if(line.charAt(i) == '\''){
                String miniChar = getCharConstant(line, i);
                _symbolTable.add(miniChar);
                i+= miniChar.length();
            }
            else if (line.charAt(i) == '-'){
                String miniInt = getNegativeInt(line, i);
                _symbolTable.add(miniInt);
                i+= miniInt.length();
            }
            else if (line.charAt(i) == '+'){
                String miniInt = getPositiveInt(line, i);
                if (miniInt.length()!=0){
                    _symbolTable.add(miniInt);
                }
                i+= miniInt.length();
            }
            else if (Character.isDigit(line.charAt(i))){
                String miniInt = getUnsignedInt(line, i);
                if (miniInt.length()!=0){
                    _symbolTable.add(miniInt);
                }
                i+= miniInt.length();
            }
            else if(line.charAt(i)!= ' '){
                String identifier = getIdentifier(line, i);
                if (identifier.length()!=0 && !MiniLanguage.isReservedWord(identifier)){
                    _symbolTable.add(identifier);
                }
                i+= identifier.length();
            }
        }
    }

    public String getStringConstant(String line, int position){
        StringBuilder miniString = new StringBuilder();
        if(line.length() - position >= 2) {
            position ++;
            for (int i = position; i < line.length(); i++) {
                String currentChar = String.valueOf(line.charAt(i));
                if (line.charAt(i) == '\"' && i != position)
                    break;
                if (MiniLanguage.isSeparator(currentChar) || i == line.length() - 1) {
                    break;
                }
                miniString.append(line.charAt(i));
            }
        }
        return miniString.toString();
    }

    public String getCharConstant(String line, int position){
        StringBuilder miniChar = new StringBuilder();
        position++;
        if(line.length() - position >= 2){
            if(line.charAt(position + 1) == '\''){
                miniChar.append(line.charAt(position));
            }
        }
        return miniChar.toString();
    }

    public String getNegativeInt(String line, int position){
        StringBuilder miniInt = new StringBuilder();
        if(line.length() - position >= 2){
            int i = position;
            miniInt.append(line.charAt(i));
            position ++;
            for (i = position; i < line.length(); i++) {
                if (line.charAt(i) == ' ')
                    break;
                if (line.charAt(position-1) == '-' && line.charAt(position) == '0'){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                if (!Character.isDigit(line.charAt(i))){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                miniInt.append(line.charAt(i));
            }
        }
        return miniInt.toString();
    }

    public String getPositiveInt(String line, int position){
        StringBuilder miniInt = new StringBuilder();
        if(line.length() - position >= 2){
            int i = position;
            miniInt.append(line.charAt(i));
            position ++;
            for (i = position; i < line.length(); i++) {
                if (line.charAt(position-1) == '+' && line.charAt(position) == ' '){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                if (line.charAt(i) == ' ')
                    break;
                if (line.charAt(position-1) == '+' && line.charAt(position) == '0'){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                if (!Character.isDigit(line.charAt(i))){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                miniInt.append(line.charAt(i));
            }
        }
        return miniInt.toString();
    }

    public String getUnsignedInt(String line, int position){
        StringBuilder miniInt = new StringBuilder();
        if(line.length() - position >=2 && line.charAt(position) == '0' && Character.isDigit(line.charAt(position + 1))){
            return miniInt.toString();
        }
        miniInt.append(line.charAt(position));
        position++;
        int i = position;
        while (i < line.length()){
            if(Character.isDigit(line.charAt(i))){
                miniInt.append(line.charAt(i));
                i++;
            }
            else {
                break;
            }
        }
        return miniInt.toString();
    }

    public String getIdentifier(String line, int position){
        StringBuilder miniInt = new StringBuilder();
        if(MiniLanguage.isSymbol(String.valueOf(line.charAt(position)))) {
            return miniInt.toString();
        }
        miniInt.append(line.charAt(position));
        position++;
        int i = position;
        if(line.length() - position >=1) {
            while (i < line.length()) {
                if(MiniLanguage.isSymbol(String.valueOf(line.charAt(i)))){
                    miniInt.delete(0, miniInt.length());
                    break;
                }
                if (line.charAt(i) == ' '){
                    break;
                }
                miniInt.append(line.charAt(i));
                i++;
            }
        }
        return miniInt.toString();
    }


    public SymbolTable get_symbolTable() {
        return _symbolTable;
    }


}
