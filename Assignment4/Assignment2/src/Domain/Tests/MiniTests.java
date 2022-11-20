package Domain.Tests;

import Domain.Exceptions.LiteralException;
import Domain.FiniteAutomata.FA;
import Core.MyScanner;
import Core.Pair;
import Core.HashTable;

public class MiniTests {
    public void testGetStringConstant(){
        MyScanner ms = new MyScanner("", "", "");
        try{
            ms.getStringConstant("\"", 0);
        }
        catch (LiteralException e){
            assert e.getMessage().equals("\" expected in file  in  line 0, at position 0.");
        }

        try{
            ms.getStringConstant("\"constant", 0);
        }
        catch (LiteralException e){
            assert e.getMessage().equals("\" expected in file  in  line 0, at position 0.");
        }

        try{
            assert (ms.getStringConstant("\"constant\"", 0).equals("\"constant\""));
            assert (ms.getStringConstant("\"constant\"    t", 0).equals("\"constant\""));
            assert (ms.getStringConstant("\"constant  constant\"", 0).equals("\"constant  constant\""));
            assert (ms.getStringConstant("\"\"", 0).equals("\"\""));
        }
        catch (LiteralException e){
            System.out.println(e.getMessage());
        }
    }

    public void testGetCharConstant(){
    try{
        MyScanner ms = new MyScanner("", "", "");
        assert (ms.getCharConstant("'c'", 0).equals("'c'"));
        assert (ms.getCharConstant("'c'   ", 0).equals("'c'"));
        assert (ms.getCharConstant("''", 0).equals("''"));
        ms.getCharConstant("'", 0);
        ms.getCharConstant("'aaaaa'", 0);
    }
    catch (LiteralException e){
        assert e.getMessage().equals("' expected in file  in  line 0, at position 0.");
        }
    }

    public void testGetIdentifier(){
        MyScanner ms = new MyScanner("", "", "");
//        System.out.println("aicea");
//        System.out.println(ms.getIdentifier("(", 0));
//        System.out.println("aicea");
//        System.out.println(MiniLanguage.isSymbol(")"));
//        System.out.println(MiniLanguage.isReservedWord("INTEGER"));
//        ms.tokenize("DECLARE a INTEGER");
       // System.out.println(ms.getIdentifier("DECLARE", 0));
        Pair p1 = new Pair("a", false);
        Pair p2 = new Pair("a", false);
        HashTable st = new HashTable(16);
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
        //assert (ms.getIdentifier("andrea = 100", 0).equals(""));
    }

    public void testGetInteger(){
        MyScanner ms = new MyScanner("", "", "");
        //System.out.println("+23".matches(Constants.getNumericPattern()));
        try {
            ms.getInteger("23", 0);
            ms.getInteger("23=4", 0);
            ms.getInteger("23 = 4", 0);
            ms.getInteger("23 < 4", 0);
            System.out.println(ms.getIdentifier("DO:", 0));
            //System.out.println(ms.getInteger("\tsum = sum + a[i]", 13));
            //System.out.println("IDENTIFIER");
            //System.out.println(ms.getIdentifiers("\tsum = sum + a[i]", 13));
            //ms.getInteger("23a43 ", 0);
        } catch (LiteralException e) {
            throw new RuntimeException(e);
        }

    }

    public void testFA(){
        FA fa = new FA("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\FA.in");
        System.out.println(fa);
    }

    public void testAll(){
//        testGetStringConstant();
//        testGetCharConstant();
////        testGetNegativeInt();
////        testGetPositiveInt();
////        testGetUnsignedInt();
////        testGetIdentifier();
//        testGetInteger();
        testFA();
    }
}
