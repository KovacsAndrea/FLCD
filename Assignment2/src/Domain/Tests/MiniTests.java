package Domain.Tests;

import Domain.MiniScanner;
import Domain.Pair;
import Domain.Specification.MiniLanguage;
import Domain.SymbolTable;

public class MiniTests {
    public void testGetStringConstant(){
        MiniScanner ms = new MiniScanner("");
        assert (ms.getStringConstant("\"constant\"", 0).equals("constant"));
        assert (ms.getStringConstant("\"constant\"    t", 0).equals("constant"));
        assert (ms.getStringConstant("\"constant  constant\"", 0).equals("constant  constant"));
       // System.out.println("constant aici:" + ms.getStringConstant("\"constant", 0));
        assert (ms.getStringConstant("\"\"", 0).equals(""));
        assert (ms.getStringConstant("\"", 0).equals(""));
    }

    public void testGetCharConstant(){
        MiniScanner ms = new MiniScanner("");
        assert (ms.getCharConstant("'c'", 0).equals("c"));
        assert (ms.getCharConstant("'c'   ", 0).equals("c"));
        assert (ms.getCharConstant("''", 0).equals(""));
        assert (ms.getCharConstant("'", 0).equals(""));
    }

    public void testGetNegativeInt(){
        MiniScanner ms = new MiniScanner("");
        assert (ms.getNegativeInt("-0345", 0).equals(""));
        assert (ms.getNegativeInt("-2345", 0).equals("-2345"));
        assert (ms.getNegativeInt("-23F45", 0).equals(""));
        assert (ms.getNegativeInt("-23(45", 0).equals(""));
        assert (ms.getNegativeInt("-0", 0).equals(""));
        assert (ms.getNegativeInt("-", 0).equals(""));
    }

    public void testGetPositiveInt(){
        MiniScanner ms = new MiniScanner("");
        assert (ms.getPositiveInt("+0345", 0).equals(""));
        assert (ms.getPositiveInt("+2345", 0).equals("+2345"));
        assert (ms.getPositiveInt("+23F45", 0).equals(""));
        assert (ms.getPositiveInt("+23(45", 0).equals(""));
        assert (ms.getPositiveInt("+0", 0).equals(""));
        assert (ms.getPositiveInt("+", 0).equals(""));
        System.out.println(ms.getPositiveInt("+ ", 0));
    }

    public void testGetUnsignedInt(){
        MiniScanner ms = new MiniScanner("");
        assert (ms.getUnsignedInt("0345", 0).equals(""));
        assert (ms.getUnsignedInt("0", 0).equals("0"));
        assert (ms.getUnsignedInt("0   ", 0).equals("0"));
    }

    public void testGetIdentifier(){
        MiniScanner ms = new MiniScanner("");
//        System.out.println("aicea");
//        System.out.println(ms.getIdentifier("(", 0));
//        System.out.println("aicea");
//        System.out.println(MiniLanguage.isSymbol(")"));
//        System.out.println(MiniLanguage.isReservedWord("INTEGER"));
//        ms.tokenize("DECLARE a INTEGER");
        System.out.println(ms.getIdentifier("DECLARE", 0));
        Pair p1 = new Pair("a", false);
        Pair p2 = new Pair("a", false);
        SymbolTable st = new SymbolTable(16);
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
    }

    public void testAll(){
        testGetStringConstant();
        testGetCharConstant();
        testGetNegativeInt();
        testGetPositiveInt();
        testGetUnsignedInt();
        testGetIdentifier();
    }
}
