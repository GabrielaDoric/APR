package LAB4;

import LAB1.Matrix;

public class Function1 extends AbstractFunction {

    @Override
    public Matrix getxMin() {
        return xMin;
    }

    @Override
    public void setxMin(Matrix xMin) {
        this.xMin = xMin;
    }

    @Override
    public double getfMin() {
        return fMin;
    }

    public void setfMin(int fMin) {
        this.fMin = fMin;
    }

    private  Matrix xMin;
    private  int fMin;


    public Function1() {
//        this.x0=new Matrix(-1.9,2.0);
        this.xMin=new Matrix(1.0,1.0);
        this.fMin=0;
    }

    @Override
    public double getFunctionValue(double[] m) {
        double x1=m[0];
        double x2=m[1];

        double result = 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
        return result;
    }

    @Override
    public double fitness(double[] point) {
        return this.getFunctionValue(point);
    }


    @Override
    public String toString() {
        return "F1";
    }

}

