package LAB5;

import LAB1.Matrix;

public class MethodEuler extends AbstractMethod {

    int printAfter;

    public MethodEuler(int printAfter) {
        this.printAfter = printAfter;
    }

    public Matrix integrate(Matrix A, Matrix B, Matrix x0, Matrix r, double T, double interval, boolean rIsConstant, boolean calculateError, String task) {

        int integrationSteps = (int) Math.floor(interval / T);
        Matrix xk = x0;

        Matrix I = new Matrix(2, 2);
        I.identityMatrix();
        Matrix M = I.add(A.multiply(T));
        Matrix N = B.multiply(T);

        //matrice u koje cemo zapisiviati procjenu i stvarne vrijednosti (stvarne za prvi zad)
        Matrix estimationMatrix = new Matrix(2, integrationSteps);
        Matrix realValuesMatrix = new Matrix(2, integrationSteps);

        for (int step = 1; step <= integrationSteps; step++) {
            if (step % printAfter == 0) {
                System.out.println("Step: " + step + " xk: " + xk.toString());
            }

            if (!rIsConstant) {
                r.setElement(0, 0, T * step);
                r.setElement(1, 0, T * step);
            }

            xk = M.multiply(xk).add(N.multiply(r));

            //puni matricu estimacija
            estimationMatrix.setElement(0, step - 1, xk.getElement(0, 0));
            estimationMatrix.setElement(1, step - 1, xk.getElement(1, 0));

            //racunaj stvarne rez za zad1
            if (task == "ZAD1") {
                double x1 = x0.getElement(0, 0) * Math.cos(T * step) + x0.getElement(1, 0) * Math.sin(T * step);
                double x2 = x0.getElement(1, 0) * Math.cos(T * step) - x0.getElement(0, 0) * Math.sin(T * step);
                realValuesMatrix.setElement(0, step - 1, x1);
                realValuesMatrix.setElement(1, step - 1, x2);
            }

            //spremanje u file
//            String filename = "./src/main/java/LAB5/outputFiles/" + task + "Euler.txt";
//            OutputValues.outputValues(filename, T * step, estimationMatrix.getElement(0, step - 1),
//                    estimationMatrix.getElement(1, step - 1));
        }

        if (calculateError) {
            double globalError = ErrorCalculator.calculateError(estimationMatrix, realValuesMatrix);
            System.out.println("Error: \t" + globalError);
        }

        return xk;
    }


}
