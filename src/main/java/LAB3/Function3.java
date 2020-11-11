package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

public class Function3 extends AbstractFunction {
    private final Matrix xMin;
    private final int fMin;
    public Function3() {
        this.xMin=new Matrix(2.0,-3.0);
        this.fMin=0;
    }
    @Override
    public double getFunctionValue(Matrix m) {

        double x1 = m.getElement(0,0);
        double x2 = m.getElement(0,1);
        double result = Math.pow(x1 - 2, 2) + Math.pow(x2 + 3, 2);
        return result;
    }

    @Override
    public Matrix getGradientValue(Matrix m) {
        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);

        double x1Partial=2*(x1-2);
        double x2Partial=2*(x2+3);

        Matrix gradientMatrix=new Matrix(x1Partial,x2Partial);

//        gradientMatrix.setElement(0,0,x1Partial);
//        gradientMatrix.setElement(0,1,x2Partial);
        return gradientMatrix;
    }

    @Override
    public Matrix getHessianValue(Matrix m) {
        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);

        double x1x1Partial=2;
        double x1x2Partial=0;
        double x2x1Partial=0;
        double x2x2Partial=2;

        Matrix hessianMatrix=new Matrix(2,2);
        hessianMatrix.setElement(0,0,x1x1Partial);
        hessianMatrix.setElement(0,1,x1x2Partial);
        hessianMatrix.setElement(1,0,x2x1Partial);
        hessianMatrix.setElement(1,1,x2x2Partial);
        return hessianMatrix;
    }

    @Override
    public String toString() {
        return "F3";
    }
}
