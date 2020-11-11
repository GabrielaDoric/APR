package LAB2;

import LAB1.Matrix;

public class Function4 extends AbstractFunction {

    public Function4(int n) {
        if (n==0){
            throw  new Error("ne moze!");
        }
        double[][] vector=null;
        for (int i=0;i<n;i++){
            vector[0][i]=n+1;
        }
        this.xMin=new Matrix(vector);
        this.fMin=0;
    }

    public Function4() {

    }

    @Override
    public String toString() {
        return "F4";
    }

    @Override
    public double getFunctionValue(Matrix m) {

        double x1 = m.getElement(0,0);
        double x2 = m.getElement(0,1);
        double result = Math.abs((x1 - x2) * (x1 + x2)) + Math.sqrt(x1 * x1 + x2 * x2);
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
}