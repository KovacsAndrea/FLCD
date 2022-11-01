import Domain.Specification.MiniLanguage;
import Domain.MiniScanner;
import Domain.Tests.MiniTests;


public class Main {
    public static void main(String[] args) {
        MiniTests t = new MiniTests();
        t.testAll();
        MiniScanner s1 = new MiniScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P1.txt");
        s1.scan();

        MiniScanner s2 = new MiniScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2.txt");
        s2.scan();

        MiniScanner s3 = new MiniScanner("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2.txt");
        s3.scan();
    }
}