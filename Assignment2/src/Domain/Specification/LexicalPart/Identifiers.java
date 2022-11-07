package Domain.Specification.LexicalPart;

public class Identifiers {

    private static final String _identifierPattern = "^[a-zA-Z]([a-z|A-Z|0-9])*$";
    public static String getIdentifierPattern() {
        return  _identifierPattern;
    }
}
