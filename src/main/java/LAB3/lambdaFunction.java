package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

public class lambdaFunction extends AbstractFunction {

    AbstractFunction function;

    public AbstractFunction getFunction() {
        return function;
    }

    public void setFunction(AbstractFunction function) {
        this.function = function;
    }

    public Matrix getX0() {
        return x0;
    }

    public void setX0(Matrix x0) {
        this.x0 = x0;
    }


//    double getFunctionValue(Matrix m) {
//        return 0;
//    }

    @Override
    public Matrix getGradientValue(Matrix m) {
        return null;
    }

    @Override
    public Matrix getHessianValue(Matrix m) {
        return null;
    }

    public Matrix getV0() {
        return v0;
    }

    public void setV0(Matrix v0) {
        this.v0 = v0;
    }

    Matrix x0,v0;


    public lambdaFunction() {
    }
    @Override
    public double getFunctionValue(Matrix lambda) {
        double result= function.getFunctionValue(x0.add(v0.multiply(lambda.getElement(0,0))));
        return result;
    }
}
