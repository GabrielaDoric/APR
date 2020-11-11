package LAB1;

public class Zad4 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 4:");
        Matrix.EPSILON = 0;
        Matrix A=null;
        Matrix b=null;

        String filename1="./src/main/java/LAB1/A4.txt";
        String filename2="./src/main/java/LAB1/b4.txt";

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
