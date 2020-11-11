package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

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
    public double getFunctionValue(Matrix m) {

        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);


        double result = 100 * Math.pow(x2 - Math.pow(x1, 2), 2) + Math.pow(1 - x1, 2);
        return result;
    }

    @Override
    public Matrix getGradientValue(Matrix m){
        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);

        double x1Partial=-400*x1*(x2-Math.pow(x1,2))-2*(1-x1);
        double x2Partial=200*(x2-Math.pow(x1,2));

        Matrix gradientMatrix=new Matrix(1,2);
        gradientMatrix.setElement(0,0,x1Partial);
        gradientMatrix.setElement(0,1,x2Partial);
        return gradientMatrix;
    }

    @Override
    public Matrix getHessianValue(Matrix m) {
        double x1=m.getElement(0,0);
        double x2=m.getElement(0,1);

        double x1x1Partial=-400*(x2-3*x1*x1)+2;
        double x1x2Partial=-400*x1;
        double x2x1Partial=-400*x1;
        double x2x2Partial=200;

        Matrix hessianMatrix=new Matrix(2,2);
        hessianMatrix.setElement(0,0,x1x1Partial);
        hessianMatrix.setElement(0,1,x1x2Partial);
        hessianMatrix.setElement(1,0,x2x1Partial);
        hessianMatrix.setElement(1,1,x2x2Partial);
        return hessianMatrix;
    }

    @Override
    public String toString() {
        return "F1";
    }
}

