package LAB5;

import LAB1.Matrix;

public class ErrorCalculator {

    public static double calculateError(Matrix estimationMatrix, Matrix realValuesMatrix) {
        double x1diff = 0;
        double x2diff = 0;
        double error=0;
        double globalError=0;

        double x1Estimation=0;
        double x1Real=0;
        double x2Estimation=0;
        double x2Real=0;

        for (int i = 0; i < estimationMatrix.getColumnLength(); i++) {

            x1Estimation=estimationMatrix.getElement(0,i);
            x1Real=realValuesMatrix.getElement(0,i);

            x2Estimation=estimationMatrix.getElement(1,i);
            x2Real=realValuesMatrix.getElement(1,i);

            x1diff=Math.abs(x1Estimation-x1Real);
            x2diff=Math.abs(x2Estimation-x2Real);

            error = Math.sqrt(Math.pow(x1diff, 2) + Math.pow(x2diff, 2));
            globalError+=error;

        }

        return globalError;
    }

}
