package LAB2;

import LAB1.Matrix;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFunction {

    public Map<String, Double> valueMap;
    public int numOfIter;
    public int counter;

    public Matrix getX0() {
        return x0;
    }

    public void setX0(Matrix x0) {
        this.x0 = x0;
    }

    public Matrix getxMin() {
        return xMin;
    }

    public void setxMin(Matrix xMin) {
        this.xMin = xMin;
    }

    public double getfMin() {
        return fMin;
    }

    public void setfMin(double fMin) {
        this.fMin = fMin;
    }

    Matrix x0;
    Matrix xMin;
    double fMin;


    public void count() {
        this.counter += 1;
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public abstract double getFunctionValue(Matrix m);

    public abstract Matrix getGradientValue(Matrix m);

    public abstract Matrix getHessianValue(Matrix m);
}
