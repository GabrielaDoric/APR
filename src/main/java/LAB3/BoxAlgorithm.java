package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class ImplicitConstraint {
    public static boolean constraintSatisfied(Matrix m) {
        double x1 = m.getElement(0, 0);
        double x2 = m.getElement(0, 1);
        if ((x2 - x1 >= 0) && (2 - x1 >= 0)) {
            return true;
        }
        return false;
    }
}

class ExplicitContraint {
    public static boolean constraintSatisfied(Matrix m) {
        for (int i = 0; i < m.getColumnLength(); i++) {
            if (m.getElement(0, i) <= 100 && m.getElement(0, i) >= -100) {
                return true;
            }
        }
        return false;
    }
}

public class BoxAlgorithm {
    public double epsilon = 10e-6;
    public double alfa = 1.3;

    public BoxAlgorithm() {

    }

    public BoxAlgorithm(double epsilon, double reflectionFactor) {
        this.epsilon = epsilon;
        this.alfa = reflectionFactor;
    }

    public Matrix findSolution(AbstractFunction f, Matrix x0) {
        Matrix xr;
        int k = 0;
        int h, numOfIter = 0;
        List<Matrix> points = new ArrayList<>();

        //check constraints
        if (!ExplicitContraint.constraintSatisfied(x0)) {
            System.out.println("Nije zadovoljeno ekspl");
            return null;
        } else if (!ImplicitConstraint.constraintSatisfied(x0)) {
            System.out.println("Nije zadovoljeno impl");
            return null;
        }
        Random random = new Random();
        random.setSeed(1996);
        Matrix xc = x0;
        //create simplex
        List<Matrix> badPoints = new ArrayList<>();
        points.add(x0);
        for (int i = 1; i < 2 * x0.getColumnLength(); i++) {
            Matrix pom = new Matrix(x0.getRowLength(), x0.getColumnLength());
            for (int j = 0; j < pom.getColumnLength(); j++) {
                double value = -100 + random.nextDouble() * (100 - (-100));
                pom.setElement(0, j, value);
            }
            if (!ImplicitConstraint.constraintSatisfied(pom)) {
                badPoints.add(pom);
            }else {
                points.add(pom);
            }
        }

        xc = calculateCentroid(points);
        for (Matrix badPoint: badPoints){
            while(!ImplicitConstraint.constraintSatisfied(badPoint)){
                badPoint = badPoint.add(xc).multiply(0.5);
            }
            Matrix goodPoint=badPoint;
            points.add(goodPoint);
        }
//        System.out.println("Tocke simpleksa:"+points);


        do {

            h = getMaxIndex(f, points);
            xc = calculateCentroid(points, h);

            //find reflection point
            xr = xc.multiply(1 + alfa).substract(points.get(h).multiply(alfa));

            //check explicit constraints
            for (int i = 0; i < xr.getColumnLength(); i++) {
                if (xr.getElement(0, i) < -100) {
                    xr.setElement(0, i, -100);
                }
                if (xr.getElement(0, i) > 100) {
                    xr.setElement(0, i, 100);
                }
            }

            //check implicit constraints
            while (!ImplicitConstraint.constraintSatisfied(xr)) {
                Matrix pom = xr.add(xc).multiply(0.5);
                xr = pom;
            }

            //ako je i dalje to najlosija tocka
            if (f.getFunctionValue(xr) > f.getFunctionValue(points.get(h))) {
                Matrix pom = xr.add(xc).multiply(0.5);
                xr = pom;
            }

//            System.out.println(numOfIter+"iteracija\nNajgora tocka"+points.get(h));
            points.set(h, xr);
            numOfIter++;
            f.counter++;
//            System.out.println(numOfIter+"iteracija\nNajgora tocka"+points.get(h));
            System.out.println("Centroid: " + xc);
//            System.out.println("Reflektirana tocka: " + xr);
//            System.out.println("POINTS"+points.get(0));
//            System.out.println("xccc"+xc);
            k++;
        } while (exitCriteria(f, points, xc));
//        }while(k<400);
        k = 0;
        return xc;
    }

    public static boolean exitCriteria(AbstractFunction f, List<Matrix> points, Matrix xc) {
        double suma = 0;
        for (int i = 0; i < points.size(); i++) {
            Matrix point = points.get(i);
            double funcDiff = f.getFunctionValue(point) - f.getFunctionValue(xc);
            suma += funcDiff * funcDiff;
        }
        suma /= points.size();
        suma = Math.sqrt(suma);
//        System.out.println("Sumaa"+suma);
        if (suma>10e-6){
            return true;
        }
        return false;
    }

    public static double euclideanNorm(Matrix gradient) {
        double sum = 0;
        for (int i = 0; i < gradient.getColumnLength(); i++) {
            sum += Math.pow(gradient.getElement(0, i), 2);
        }
        double norm = Math.sqrt(sum);
        return norm;
    }

    private Matrix calculateCentroid(List<Matrix> points, int h) {
        Matrix xc = new Matrix(1, points.get(0).getColumnLength());
        xc.setToZero();
        for (Matrix point : points) {
            if (point == points.get(h)) {
                continue;
            }
            for (int i = 0; i < point.getColumnLength(); i++) {
                xc.addToElement(0, i, point.getElement(0, i));
            }
        }
        xc.devide(points.size() - 1);
        return xc;

    }

    private int getMaxIndex(AbstractFunction f, List<Matrix> points) {
        int index = 0;
        for (int i = 0; i < points.size(); i++) {
            if (f.getFunctionValue(points.get(i)) > f.getFunctionValue(points.get(index))) {
                index = i;
            }
        }
        return index;
    }


    private Matrix calculateCentroid(List<Matrix> points) {
        Matrix xc = new Matrix(1, points.get(0).getColumnLength());
        xc.setToZero();
        for (Matrix point : points) {
            for (int i = 0; i < point.getColumnLength(); i++) {
                xc.addToElement(0, i, point.getElement(0, i));
            }
        }
        xc.devide(points.size());
        return xc;
    }
}
