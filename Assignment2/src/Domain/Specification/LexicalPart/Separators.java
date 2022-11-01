package Domain.Specification.LexicalPart;

import java.util.Arrays;
import java.util.List;

public class Separators{
    private static final List<String> _separators = Arrays.asList("(", ")", "[", "]", "{", "}", ";", ":", ".", "//", "/*", "*/", "\n", "\t");

    public static List<String> getAll(){
        return _separators;
    }

    public static boolean isSeparator(String s){
        return _separators.contains(s);
    }
}
