package LAB1;

public class Zad6 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 6:");
        Matrix.EPSILON = 10e-6;
        Matrix A=null;
        Matrix b=null;

        String filename1="./src/main/java/LAB1/A6.txt";
        String filename2="./src/main/java/LAB1/b6.txt";

        A=Matrix.loadMatrix(filename1);
        b=Matrix.loadMatrix(filename2);

        Matrix pomocnaA=A.clone();
        Matrix pomocnaB=b.clone();


        MatrixSolver systemSolverLU=new MatrixSolver();
        systemSolverLU.solve(A,b);

        MatrixSolver systemSolverLUP=new MatrixSolver();
        systemSolverLUP.solveLUP(pomocnaA,pomocnaB);


    }
}
