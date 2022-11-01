package Domain.Specification;

import Domain.Specification.LexicalPart.*;

public class MiniLanguage {
    public static boolean isSymbol(String s) {
        return isOperator(s) || isReservedWord(s) || isSeparator(s);
    }

    public static boolean isOperator(String s){
        return Operators.isOperator(s);
    }

    public static boolean isPartOfOperator(String s){
        return Operators.isPartOfOperator(s);
    }

    public static boolean isReservedWord(String s){
        return ReservedWords.isReservedWord(s);
    }

    public static boolean isSeparator(String s){
        return Separators.isSeparator(s);
    }

    public static boolean isConstant(String s){
        return Constants.isConstant(s);
    }

    public static boolean isIdentifier(String s){
        return Identifiers.isIdentifier(s);
    }


}
