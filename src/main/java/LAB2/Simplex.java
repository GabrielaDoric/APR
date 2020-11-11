package LAB2;


import LAB1.Matrix;

import java.util.ArrayList;
import java.util.List;

public class Simplex {

    public static final double epsilon = 10e-6;
    public static double alfa = 1.3;
    public static double beta = 0.5;
    public static double gamma = 2.0;
    public static double sigma = 0.5;
    public static double step = 1;


    public static Matrix simplex(AbstractFunction f, Matrix x0) {
        List<Matrix> X = generateSimplexPoints(x0);
        Matrix xc, xr, xk, xe;
        int h, l;

        do {
            f.count();
            h = getMaxIndex(X, f); //najgora tocka
            l = getMinIndex(X, f); //najbolja tocka
            xc = getCentroid(X, X.get(h));
            xr = reflexion(xc, X.get(h));
            if (f.getFunctionValue(xr) < f.getFunctionValue(X.get(l))) {
                xe = expansion(xc, xr);
                if (f.getFunctionValue(xe) < f.getFunctionValue(X.get(l))) {
                    X.set(h, xe);
                }
                else {
                    X.set(h, xr);
                }
            }
            else {
                boolean condition = true;
                for (Matrix xj : X) {
                    if (xj.equals(X.get(h))) continue;
                    if (f.getFunctionValue(xr) < f.getFunctionValue(xj)) {
                        condition = false;
                        break;
                    }
                }
                if (condition) {
                    if (f.getFunctionValue(xr) < f.getFunctionValue(X.get(h))) {
                        X.set(h, xr);
                    }
                    xk = contraction(xc, X.get(h));
                    if (f.getFunctionValue(xk) < f.getFunctionValue(X.get(h))) {
                        X.set(h, xk);
                    }
                    else {
                        X = shift(X, X.get(l));
                    }
                }
                else {
                    X.set(h, xr);
                }
            }
            h = getMaxIndex(X, f); //najgora tocka
            xc = getCentroid(X, X.get(h));
        }
        while (!compareWithEPS(X, f, xc));
        return xc;
    }

    public static List<Matrix> generateSimplexPoints(Matrix x0) {
        List<Matrix> l = new ArrayList<>();
        l.add(x0);
        int dimensions = x0.getColumnLength();
        double a1 = (Simplex.step / (dimensions * Math.sqrt(2))) * (Math.sqrt(dimensions + 1) + dimensions - 1);
        double a2 = (Simplex.step / (dimensions * Math.sqrt(2))) * (Math.sqrt(dimensions + 1) - 1);
        for (int i = 0; i < x0.getColumnLength(); i++) {
            Matrix point = new Matrix(x0);
            for (int j = 0; j < x0.getColumnLength(); j++) {
                if (i == j) {
                    point.addToElement(0, j, a1);
                }
                else {
                    point.addToElement(0, j, a2);
                }
            }
            l.add(point);
        }
        return l;
    }

    public static Matrix getCentroid(List<Matrix> X, Matrix xh) {
        Matrix centroid=new Matrix(1,xh.getColumnLength());
        for (Matrix xi : X) {
            if (xi.equals(xh)) continue;
            for (int j = 0; j < xi.getColumnLength(); j++) {
                double value= centroid.getElement(0,j)+xi.getElement(0,j);
                centroid.setElement(0,j,value);
            }
        }
        for (int j = 0; j < centroid.getColumnLength(); j++) {
            double value=centroid.getElement(0,j)/(X.size() - 1);
            centroid.setElement(0,j,value);
        }
        return centroid;
    }

    public static int getMaxIndex(List<Matrix> X, AbstractFunction f) {
        double max = 0;
        int index = 0;
        for (int i = 0; i < X.size(); i++) {
            Matrix xi = X.get(i);
            if (i == 0) {
                max = f.getFunctionValue(xi);
                continue;
            }
            double fv = f.getFunctionValue(xi);
            if (fv > max) {
                max = fv;
                index = i;
            }
        }
        return index;
    }

    public static int getMinIndex(List<Matrix> X, AbstractFunction f) {
        double min = 0;
        int index = 0;
        for (int i = 0; i < X.size(); i++) {
            Matrix xi = X.get(i);
            if (i == 0) {
                min = f.getFunctionValue(xi);
                continue;
            }
            double fv = f.getFunctionValue(xi);
            if (fv < min) {
                min = fv;
                index = i;
            }
        }
        return index;
    }

    public static Matrix reflexion(Matrix xc, Matrix xh) {
        // xr = (1+alfa)*xc -alfa*xh
        Matrix first = xc.multiply(1+alfa);
        Matrix second = xh.multiply(alfa);
        return first.substract(second);
    }

    private static Matrix expansion(Matrix xc, Matrix xr) {
        // xe = (1-gamma)*xc + gamma*xr
        Matrix first = xc.multiply(1-gamma);
        Matrix second = xr.multiply(gamma);
        return first.add(second);
    }

    private static Matrix contraction(Matrix xc, Matrix xh) {
        // xk = (1-beta)xc + beta*xh
        Matrix first = xc.multiply(1-beta);
        Matrix second = xh.multiply(beta);
        return first.add(second);
    }

    private static List<Matrix> shift(List<Matrix> X, Matrix xl) {
        List<Matrix> shiftedX = new ArrayList<>();
        for (Matrix xi : X) {
            xi.multiply(sigma);
            xl.multiply(sigma);
            Matrix shifted = xi.add(xl);
            shiftedX.add(shifted);
        }
        return shiftedX;
    }

    public static boolean compareWithEPS(List<Matrix> X, AbstractFunction f, Matrix xc) {
        double sum = 0;
        double value = f.getFunctionValue(xc);
        for (Matrix xj : X) {
            sum += Math.pow(f.getFunctionValue(xj) - value, 2);
        }
        double result = Math.sqrt(sum / xc.getColumnLength());
        return result <= epsilon;
    }

}