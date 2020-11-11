package LAB1;

public class Zad10 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 10:");

        String filename1="./src/main/java/LAB1/A2.txt";

        Matrix a=null;
        Matrix m=a.loadMatrix(filename1);
        System.out.println(m);

        double determinant=m.determinant();
        System.out.println("det="+determinant);
    }
}
