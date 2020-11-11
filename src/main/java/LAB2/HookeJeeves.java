package LAB2;

import LAB1.Matrix;


public class HookeJeeves {

    public static double epsilon = 10e-6;
    public static double dx = 0.5;


    public static Matrix hookeJeeves(AbstractFunction f, Matrix x0, double dx) {
        Matrix xB, xP, xN;
        xB = new Matrix(x0.getElements());
        xP = new Matrix(x0.getElements());
        StringBuilder sb = new StringBuilder();

        do {
            xN = localSearch(f, xP, dx);
            sb.append("xB=").append(xB);
            sb.append("xP=").append(xP);
            sb.append("xN=").append(xN).append("\n");

            if (f.getFunctionValue(xB) > f.getFunctionValue(xN)) { //bazna tocka
                xP = xN.multiply(2).substract(xB);
                xB = xN;
            } else {
                dx /= 2;
                xP = xB;
            }
            f.count();
        } while (dx >= 0.5 * epsilon);
        return xB;
    }

    public static Matrix hookeJeeves(AbstractFunction f, Matrix x0) {
        return hookeJeeves(f, x0, dx);
    }

    private static Matrix localSearch(AbstractFunction f, Matrix xP, double dx) {
        Matrix x = new Matrix(xP.getElements());
        for (int i = 0; i < x.getColumnLength(); i++) {
            double P = f.getFunctionValue(x);
            x.addToElement(0, i, dx);
            double N = f.getFunctionValue(x);
            if (N > P) {
                x.addToElement(0, i, -2 * dx);
                N = f.getFunctionValue(x);
                if (N > P) {
                    x.addToElement(0, i, dx);
                }
            }
        }
        return x;
    }

}