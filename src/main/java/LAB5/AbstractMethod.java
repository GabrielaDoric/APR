package LAB5;

import LAB1.Matrix;

public abstract class AbstractMethod {

    public abstract Matrix integrate(Matrix A, Matrix B, Matrix x0, Matrix r, double T, double interval, boolean rIsConstant, boolean calculateError, String task);


    public Matrix derivation(Matrix A, Matrix B, Matrix xk, Matrix r) {
        Matrix m = A.multiply(xk).add(B.multiply(r));
        return m;
    }


    public Matrix calculateRealValues(Matrix x0, double T, int step, int integrationSteps){
        Matrix realValuesMatrix = new Matrix(2, integrationSteps);

        double x1 = x0.getElement(0, 0) * Math.cos(T * step) + x0.getElement(1, 0) * Math.sin(T * step);
        double x2 = x0.getElement(1, 0) * Math.cos(T * step) - x0.getElement(0, 0) * Math.sin(T * step);

        realValuesMatrix.setElement(0, step - 1, x1);
        realValuesMatrix.setElement(1, step - 1, x2);

        return realValuesMatrix;
    }

//    public abstract Matrix calculateImplicit(Matrix xk, )
}

