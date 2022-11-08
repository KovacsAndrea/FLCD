import Domain.MyScanner;
import Domain.Specification.LexicalPart.Specifications;
import Domain.Tests.MiniTests;


public class Main {
    public static void main(String[] args) {
//        MiniTests t = new MiniTests();
//        t.testAll();
        Specifications.createCodification();
//        System.out.println(Specifications.codification.toString());
        MyScanner s1 = new MyScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P1.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P1PIF.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P1ST.txt");
        s1.scan();

        MyScanner s2 = new MyScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2PIF.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2ST.txt");
        s2.scan();

        MyScanner s3 = new MyScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P3.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P3PIF.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P3ST.txt");
        s3.scan();

        MyScanner s4 = new MyScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P4.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P4PIF.txt",
                "D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P4ST.txt");
        s4.scan();
    }
}