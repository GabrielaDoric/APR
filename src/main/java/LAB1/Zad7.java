package LAB1;

public class Zad7 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 7:");

        String filename1="./src/main/java/LAB1/A3.txt";

        Matrix a=null;
        Matrix m=a.loadMatrix(filename1);
        System.out.println(m);


        Matrix mInverse=m.inverse();
        System.out.println("Inverz:"+mInverse);
    }
}
