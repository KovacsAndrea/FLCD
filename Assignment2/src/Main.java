import Domain.SymbolTable;
import Domain.Tests.MiniTests;


public class Main {
    public static void main(String[] args) {
        MiniTests t = new MiniTests();
        t.testAll();
        SymbolTable s1 = new SymbolTable("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P1.txt");
        s1.getSymbolTable();

        SymbolTable s2 = new SymbolTable("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P2.txt");
        s2.getSymbolTable();

        SymbolTable s3 = new SymbolTable("D:\\Sem 4\\FLCD\\Lab2\\Assignment2\\src\\Files\\P3.txt");
        s3.getSymbolTable();

        String a = "abcdefghijklmnopqrstuvwxyz";
        int sum=0;
        for(int i = 0; i<20; i++){
            sum += a.charAt(i);
            System.out.println(sum);
        }
    }
}