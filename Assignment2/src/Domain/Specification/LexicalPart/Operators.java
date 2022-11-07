package Domain.Specification.LexicalPart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Operators {
    private static final String _assignOperator = "=";
    private static final List<String> _arithmeticOperators = Arrays.asList("+", "-", "/", "*","%");
    private static final List<String> _relationalOperators = Arrays.asList("<", "<=", "==", "!=", "=>", ">");
    private static final String _logicalOperator = ",";
    private static final String _incrementOperator = "++";
    private static final String _decrementOperator = "--";

    private static final List<String> _allOperators = initializeOperators();

    private static List<String> initializeOperators(){
        List<String> allOperators = new ArrayList<>();
        allOperators.add(_assignOperator);
        allOperators.addAll(_arithmeticOperators);
        allOperators.addAll(_relationalOperators);
        allOperators.add(_logicalOperator);
        allOperators.add(_incrementOperator);
        allOperators.add(_decrementOperator);
        return allOperators;
    }

    public static String assignOperator(){
        return _assignOperator;
    }

    public static List<String> arithmeticOperators(){
        return _arithmeticOperators;
    }

    public static List<String> relationalOperators(){
        return _relationalOperators;
    }

    public static String logicalOperator(){
        return _logicalOperator;
    }

    public static String incrementOperator(){
        return _incrementOperator;
    }

    public static String decrementOperator(){
        return _decrementOperator;
    }

    public static boolean isOperator(String s){
        return getAll().contains(s);
    }

    public static boolean isPartOfOperator(String s){
        return s.equals("!") || s.equals("=") || s.equals("<") || s.equals("+") || s.equals("-");
    }

    public static List<String> getAll(){
        return _allOperators;
    }


}
