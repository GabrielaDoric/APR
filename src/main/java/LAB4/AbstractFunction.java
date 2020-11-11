package LAB4;

import LAB1.Matrix;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractFunction {

    private double fitness;
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
    private int evaluationNum=0;


    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }


    public abstract double getFunctionValue(double[] m);

    public abstract double fitness(double[] points);

    public int getEvaluationNumber() {
        return this.evaluationNum;
    }

    public int incrementEvaluationNumber(){
        return evaluationNum++;
    }


}
