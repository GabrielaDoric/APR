package LAB5;

import LAB1.Matrix;

public class MethodRungeKutta extends AbstractMethod {

    int printAfter;

    public MethodRungeKutta(int printAfter) {
        this.printAfter = printAfter;
    }

    public Matrix integrate(Matrix A, Matrix B, Matrix x0, Matrix r, double T, double interval, boolean rIsConstant, boolean calculateError, String task) {

        int integrationSteps = (int) Math.floor(interval / T);
        Matrix xk = x0;

        Matrix r1 = r.clone(); // r1=B*r
        Matrix r2 = r.clone(); // r2=B*(r+T/2)
        Matrix r3 = r.clone(); //  r3=B*(r+T)


        Matrix estimationMatrix = new Matrix(2, integrationSteps);
        Matrix realValuesMatrix = new Matrix(2, integrationSteps);
        for (int step = 1; step <= integrationSteps; step++) {
            if (step % printAfter == 0) {
                System.out.println("Step: " + step + " xk: " + xk.toString());

            }

            if (rIsConstant == false) {
                r1.setElement(0, 0, T * step);
                r1.setElement(1, 0, T * step);
                r2.setElement(0, 0, T * step + T / 2.0);
                r2.setElement(1, 0, T * step + T / 2.0);
                r3.setElement(0, 0, T * step + T);
                r3.setElement(1, 0, T * step + T);
            }


            Matrix m1 = A.multiply(xk).add(B.multiply(r1));
            Matrix m2 = A.multiply(xk.add(m1.multiply(T / 2))).add(B.multiply(r2));
            Matrix m3 = A.multiply(xk.add(m2.multiply(T / 2))).add(B.multiply(r2));
            Matrix m4 = A.multiply(xk.add(m3.multiply(T))).add(B.multiply(r3));

            Matrix m = m1.add(((m2.multiply(2)).add(m3.multiply(2)).add(m4)));
            xk = xk.add(m.multiply(T * 1 / 6));


            estimationMatrix.setElement(0, step - 1, xk.getElement(0, 0));
            estimationMatrix.setElement(1, step - 1, xk.getElement(1, 0));

            if (task=="ZAD1") {
                double x1 = x0.getElement(0, 0) * Math.cos(T * step) + x0.getElement(1, 0) * Math.sin(T * step);
                double x2 = x0.getElement(1, 0) * Math.cos(T * step) - x0.getElement(0, 0) * Math.sin(T * step);
                realValuesMatrix.setElement(0, step - 1, x1);
                realValuesMatrix.setElement(1, step - 1, x2);
            }

//            String filename = "./src/main/java/LAB5/outputFiles/" + task + "RungeKutta.txt";
//            OutputValues.outputValues(filename, T * step, estimationMatrix.getElement(0, step - 1),
//                    estimationMatrix.getElement(1, step - 1));
        }

        if (calculateError) {
            double globalError = ErrorCalculator.calculateError(estimationMatrix, realValuesMatrix);
            System.out.println("Error: \t" + globalError);
        }
        return null;
    }
}
