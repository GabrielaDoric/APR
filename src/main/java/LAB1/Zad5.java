package LAB1;

public class Zad5 {
    public static void  main(String[] args){
        System.out.println("ZADATAK 5:");

        Matrix A=null;
        Matrix b=null;

        String filename1="./src/main/java/LAB1/A5.txt";
        String filename2="./src/main/java/LAB1/b5.txt";

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
