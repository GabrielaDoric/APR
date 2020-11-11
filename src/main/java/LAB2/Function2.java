package LAB2;

import LAB1.Matrix;

public class Function2 extends AbstractFunction {

    public Function2() {
        this.xMin=new Matrix(4.0,2.0);
        this.fMin=0;
    }
    @Override
    public double getFunctionValue(Matrix m) {

        double x1 = m.getElement(0,0);
        double x2 = m.getElement(0,1);
        double result = Math.pow(x1 - 4, 2) + 4 * Math.pow(x2 - 2, 2);
        return result;
    }

    @Override
    public Matrix getGradientValue(Matrix x0) {
        return null;
    }

    @Override
    public Matrix getHessianValue(Matrix m) {
        return null;
    }

    @Override
    public String toString() {
        return "F2";
    }
}
