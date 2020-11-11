package LAB2;


import LAB1.Matrix;

import java.util.Arrays;

public class GoldenSectionSearch {

    public static final double epsilon = 10e-6;
    public static final double K = 0.5 * (Math.sqrt(5) - 1);


    public static Interval unimodalInterval(AbstractFunction f, double h, Matrix x0) {

        double[] hs = new double[x0.getColumnLength()];
        Arrays.fill(hs, h);
        Matrix mh = new Matrix(hs);
        Matrix l = x0.substract(mh);
        Matrix r = x0.add(mh);
        Matrix m = x0;

        double fl, fm, fr;
        int step = 1;

        fm = f.getFunctionValue(x0);
        fl = f.getFunctionValue(l);
        fr = f.getFunctionValue(r);

        if (fm < fr && fm < fl) {
            return new Interval(l, r);
        } else if (fm > fr) {
            do {
                l = m;
                m = r;
                fm = fr;
                r = x0.addScalar(h * (step *= 2));
                fr = f.getFunctionValue(r);
            } while (fm > fr);
        } else {
            do {
                r = m;
                m = l;
                fm = fl;
                l = x0.addScalar(-h * (step *= 2));
                fl = f.getFunctionValue(l);
            } while (fm > fl);
        }
        return new Interval(l, r);
    }

    public static Interval unimodalInterval(AbstractFunction f, Matrix x0, double h, Matrix x, int dim) {
        Matrix newX = new Matrix(x);
        Matrix l = new Matrix(0.0);
        l.setElement(0, 0, newX.getElement(0, dim) - h);
        Matrix r = new Matrix(0.0);
        r.setElement(0, 0, newX.getElement(0, dim) + h);
        Matrix m = new Matrix(0.0);
        m.setElement(0, 0, newX.getElement(0, dim));

        double fl, fm, fr;
        int step = 1;

        newX.setElement(0, dim, m.getElement(0, 0));
        fm = f.getFunctionValue(newX);
        newX.setElement(0, dim, l.getElement(0, 0));
        fl = f.getFunctionValue(newX);
        newX.setElement(0, dim, r.getElement(0, 0));
        fr = f.getFunctionValue(newX);

        if (fm < fr && fm < fl) {
            return new Interval(l, r);
        } else if (fm > fr) {
            do {
                l.setElement(0, 0, m.getElement(0, 0));
                m.setElement(0, 0, r.getElement(0, 0));
                fm = fr;
                double add = x0.getElement(0, dim) + h * (step *= 2);
                r.setElement(0, 0, add);
                newX.setElement(0, 0, r.getElement(0, 0));
                fr = f.getFunctionValue(newX);
            } while (fm > fr);
        } else {
            do {
                r.setElement(0, 0, m.getElement(0, 0));
                m.setElement(0, 0, l.getElement(0, 0));
                fm = fl;
                double add = x0.getElement(0, dim) - h * (step *= 2);
                l.setElement(0, 0, add);
                newX.setElement(0, 0, l.getElement(0, 0));
                fl = f.getFunctionValue(newX);
            } while (fm > fl);
        }
        return new Interval(l, r);
    }

    public static Interval goldenRatio(AbstractFunction f, Interval I, double e) {
        Matrix a = I.a;
        Matrix b = I.b;
        Matrix c = b.substract(b.substract(a).multiply(K));
        Matrix d = a.add(b.substract(a).multiply(K));

        double fc = f.getFunctionValue(c);
        double fd = f.getFunctionValue(d);

        while (precision(a, b, e) == true) {
            f.count();
            if (fc < fd) {
                b = d;
                d = c;
                //c = b - K * (b - a);
                c = b.substract(b.substract(a).multiply(K));
                fd = fc;
                fc = f.getFunctionValue(c);
            } else {
                a = c;
                c = d;
                //d = a + K * (b - a);
                d = a.add(b.substract(a).multiply(K));
                fc = fd;
                fd = f.getFunctionValue(d);
            }
        }

        return new Interval(a, b);
    }

    public static Interval goldenRatio(AbstractFunction f, Interval I, double e, Matrix x, int dim) {
        Matrix a = I.a;
        Matrix b = I.b;
        Matrix c = b.substract(b.substract(a).multiply(K));
        Matrix d = a.add(b.substract(a).multiply(K));

        x.setElement(0, dim, c.getElement(0,0));
        double fc = f.getFunctionValue(x);
        x.setElement(0, dim, d.getElement(0,0));
        double fd = f.getFunctionValue(x);

        while (precision(a, b, e) == true) {
            f.count();
            if (fc < fd) {
                b = d;
                d = c;
                //c = b - K * (b - a);
                c = b.substract(b.substract(a).multiply(K));
                fd = fc;
                x.setElement(0, dim, c.getElement(0,0));
                fc = f.getFunctionValue(x);
            } else {
                a = c;
                c = d;
                //d = a + K * (b - a);
                d = a.add(b.substract(a).multiply(K));
                fc = fd;
                x.setElement(0, dim, d.getElement(0,0));
                fd = f.getFunctionValue(x);
            }
        }

        return new Interval(a, b);
    }


    public static Interval goldenRatio(AbstractFunction f, Interval I) {
        return goldenRatio(f, I, epsilon);
    }

    public static Interval goldenRatio(AbstractFunction f, Interval I, Matrix x, int dim) {
        return goldenRatio(f, I, epsilon, x, dim);
    }

    public static Interval goldenRatio(AbstractFunction f, double h, Matrix x0) {
        Interval unimodalInterval = unimodalInterval(f, h, x0);
        return goldenRatio(f, unimodalInterval);
    }

    public static Interval goldenRatio(AbstractFunction f, Matrix x0, double h, Matrix x, int dim) {
        Interval unimodalInterval = unimodalInterval(f, x0, h, x, dim);
        return goldenRatio(f, unimodalInterval, x, dim);
    }

    public static double goldenRatioValue(AbstractFunction f, double h, Matrix x0) {
        Interval i = goldenRatio(f, h, x0);
        Matrix temp = (i.a).add(i.b);
        return temp.devideR(2);
    }




    private static boolean precision(Matrix a, Matrix b, double e) {
        if ((b.getElement(0, 0) - a.getElement(0, 0)) > e) {
            return true;
        }
        return false;

    }


}
