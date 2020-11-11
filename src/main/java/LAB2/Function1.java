package LAB2;

import LAB1.Matrix;

public class Function1 extends AbstractFunction {

    public Function1() {
//        this.x0=new Matrix(-1.9,2.0);
        this.xMin=new Matrix(1.0,0.0);
        this.fMin=0;
    }


    @Override
    public double getFunctionValue(Matrix m) {

        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);

        double result = 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
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
        return "F1";
    }
}
