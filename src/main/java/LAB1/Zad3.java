package LAB1;

public class Zad3 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 3:");
        Matrix A=null;
        Matrix b=null;

        String filename1="./src/main/java/LAB1/A3.txt";
        String filename2="./src/main/java/LAB1/b3.txt";

        A=Matrix.loadMatrix(filename1);
        b=Matrix.loadMatrix(filename2);
        Matrix pomocna=A.clone();

        A.LU();
        System.out.println("LU:");
        A.printMatrix();

        System.out.println("LUP:");
        pomocna.LUP();
        pomocna.printMatrix();


        MatrixSolver systemSolver=new MatrixSolver();
        systemSolver.solve(A,b);


    }
}
