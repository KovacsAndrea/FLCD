package Domain.Specification.LexicalPart;

public class Constants {
    private static final String _numericPattern = "^0|[+|-][ ][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
    private static final String _charPattern = "^'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]*'";
    private static final String _stringPattern = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]*\"";

    public static String getNumericPattern(){
        return _numericPattern;
    }

    public static String getCharPattern(){
        return _charPattern;
    }

    public static String getStringPattern(){
        return _stringPattern;
    }
}
