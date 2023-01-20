package Domain.Specification;

import Domain.Specification.LexicalPart.*;

import java.util.HashMap;

public class Specifications {

    public final static HashMap<String, Integer> codification = new HashMap<>();
    public static boolean isConstant(String s){
        return s.matches(Constants.getNumericPattern()) ||
                s.matches(Constants.getCharPattern()) ||
                s.matches(Constants.getStringPattern());
    }

    public static boolean isIdentifier(String s){
        return s.matches(Identifiers.getIdentifierPattern());
    }

    public static boolean isSymbol(String s){
        return Operators.isOperator(s) || ReservedWords.isReservedWord(s) || Separators.isSeparator(s);
    }

    public static void createCodification() {
        codification.put("identifier", 0);
        codification.put("constant", 1);

        int code = 2;

        for (String reservedWord : ReservedWords.getAll()) {
            codification.put(reservedWord, code);
            code++;
        }

        for (String operator : Operators.getAll()) {
            codification.put(operator, code);
            code++;
        }

        for (String separator : Separators.getAll()) {
            codification.put(separator, code);
            code++;
        }
    }
    public static Integer getCode(String token) {
        return codification.get(token);
    }
}
