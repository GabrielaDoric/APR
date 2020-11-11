package LAB1;

public class Zad1 {


    public static void main(String[] args) {
        System.out.println("ZADATAK 1:");


        String filename1="./src/main/java/LAB1/A2.txt";
        Matrix a=null;
        Matrix m1=a.loadMatrix(filename1);
        Matrix m2=a.loadMatrix(filename1);
        System.out.println(m1);


        if (m1.equals(m2)==true){
            System.out.println("Matrice su jednake");
        }

        m2.multiply(1/0.7);
        m2.multiply(0.7);
        System.out.println(m2.toString());
        if (m1.equals(m2)==true){
            System.out.println("Matrice su jednake");
        }else{
            System.out.println("Matrice nisu jednake");
        }


    String path="C:/Users/Gabriela/Desktop/APR/src/main/java/LAB1/proba.txt";
    Matrix.saveMatrix(path,m2);
    }
}