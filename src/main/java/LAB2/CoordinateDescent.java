package LAB2;

import LAB1.Matrix;

import java.util.Arrays;

public class CoordinateDescent {
    public static double EPSILON = 10e-6;

    String path = "C:/Users/Gabriela/Desktop/APR/src/main/java/LAB2/x0.txt";
    Matrix a = null;
    Matrix x0 = a.loadMatrix(path);

    public static Matrix coordinateDescent(AbstractFunction f, Matrix x0, double epsilon, Matrix xMin) {

        Matrix x = new Matrix(x0);
        double[] ei1 = new double[x0.getColumnLength()];
        Arrays.fill(ei1, EPSILON);
        Matrix ei = new Matrix(ei1);

        Matrix xs = null;
        do {
            xs = new Matrix(x);
            f.count();
            for (int i = 0; i < x0.getColumnLength(); i++) {
                double lambda = findLambda(f, xs, x, i);
                x.setElement(0, i, lambda);
            }
        } while (epsilonComparison(x, xs) == true);

        xMin = x;
        return xMin;
    }

    private static double findLambda(AbstractFunction f, Matrix xs, Matrix x, int dim) {
        Interval interval1 = GoldenSectionSearch.goldenRatio(f, xs, 1, x, dim);
        double value = (interval1.getA().add(interval1.getB())).devideR(2);
        return value;
    }


    private static boolean epsilonComparison(Matrix x, Matrix xs) {
        for (int i = 0; i < x.getColumnLength(); i++) {
            double x1 = x.getElement(0, i);
            double xs1 = xs.getElement(0, i);

            if (Math.abs(x1 - xs1) > EPSILON) {
                continue;
            }
            return false;
        }
        return true;
    }

}
