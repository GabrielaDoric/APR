package LAB1;

public class MatrixSolver {

    public static void solve(Matrix a, Matrix b) {
        Matrix aLU = a.clone();
        try {
            aLU.LU();
            a = aLU;
        } catch (Error e) {
            System.out.println("Nije moguće napraviti LU dekompoziciju.\n");
            try {
                Matrix aLUP = a.clone();
                int[] permutationVector = aLUP.LUP();
                b.permutation(permutationVector);
                a = aLUP;
            } catch (Error e1) {
                System.out.println("Nije moguće napraviti LUP dekompoziciju.");
                return;
            }
        }

//        System.out.println("A=L|U:");
//        a.print();

        Matrix y = a.forwardSubstitution(b);

//        System.out.println("y:");
//        y.print();

        Matrix x = a.backwardSubstitution(y);

        System.out.println("Matrica x:");
        x.printMatrix();
    }


//    public static void solveLUP(Matrix a, Matrix b) {
//        Matrix aLUP = a.clone();
//        try{
//            int[] permutationVector = aLUP.LUP();
//            b.permutation(permutationVector);
//            a = aLUP;
//            } catch (Error e) {
//                System.out.println("Nije moguće napraviti LUP dekompoziciju.");
//                return;
//            }
//
//
//        Matrix y = a.forwardSubstitution(b);
////        System.out.println("y:");
////        y.print();
//
//        Matrix x = a.backwardSubstitution(y);
//
//        System.out.println("Matrica x:");
//        x.printMatrix();
//        }

    public static Matrix solveLUP(Matrix a, Matrix b) {
        Matrix aLUP = a.clone();

        try{
            int[] permutationVector = aLUP.LUP();
            b.permutation(permutationVector);
            a = aLUP;
        } catch (Error e) {
            System.out.println("Nije moguće napraviti LUP dekompoziciju.");
            Matrix x1=new Matrix();
            return x1;
        }


        Matrix y = a.forwardSubstitution(b);
//        System.out.println("y:");
//        y.print();

        Matrix x = a.backwardSubstitution(y);

//        System.out.println("Matrica x:");
//        x.printMatrix();
        return x;
    }


}