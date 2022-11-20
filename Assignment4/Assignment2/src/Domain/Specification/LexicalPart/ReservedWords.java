package Domain.Specification.LexicalPart;

import java.util.Arrays;
import java.util.List;

public class ReservedWords{
    private static final List<String> _reservedWords = Arrays.asList("DECLARE", "START", "END", "READ", "WRITE", "INTEGER", "CHAR", "STRING", "BOOLEAN", "TRUE", "FALSE", "ARRAY", "OF", "DO", "DONE", "IF", "ELSE", "ELSE IF", "WHILE", "FOR", "AND");

    public static List<String> getAll(){
        return _reservedWords;
    }

    public static boolean isReservedWord(String s){
        return _reservedWords.contains(s);
    }

}
