package LAB2;

import LAB1.Matrix;

public  class Interval {
    public Matrix a;

    public void setA(Matrix a) {
        this.a = a;
    }

    public void setB(Matrix b) {
        this.b = b;
    }

    public Matrix getA() {
        return a;
    }

    public Matrix getB() {
        return b;
    }

    public  Matrix b;

    public Interval(Matrix a, Matrix b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }
}
