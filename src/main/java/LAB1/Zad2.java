package LAB1;


public class Zad2 {

    public static void  main(String[] args){
        System.out.println("ZADATAK 2:");

        Matrix A=null;
        Matrix b=null;

        String filename1="./src/main/java/LAB1/A2.txt";
        String filename2="./src/main/java/LAB1/b2.txt";

        A=Matrix.loadMatrix(filename1);
        b=Matrix.loadMatrix(filename2);

        MatrixSolver systemSolver=new MatrixSolver();
        systemSolver.solve(A,b);

    }

}
