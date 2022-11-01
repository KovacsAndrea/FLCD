package Domain.Specification.LexicalPart;

public class Identifiers {

    public static boolean isIdentifier(String token) {
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9|_])*$";
        return token.matches(pattern);
    }
}
