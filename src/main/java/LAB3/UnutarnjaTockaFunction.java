package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

import java.util.List;

public class UnutarnjaTockaFunction extends AbstractFunction {
    List<AImplicitConstraint> implicitConstraints;
    public UnutarnjaTockaFunction(List<AImplicitConstraint> implicitConstraints) {
        this.implicitConstraints=implicitConstraints;
    }

    @Override
    public double getFunctionValue(Matrix m) {
        double suma=0;
        double solution;

        for (AImplicitConstraint implicitConstraint:implicitConstraints){
            solution=implicitConstraint.calculate(m);
            if (solution<0) {
                suma -= solution;
            }

        }
        return suma;
    }

    @Override
    public Matrix getGradientValue(Matrix m) {
        return null;
    }

    @Override
    public Matrix getHessianValue(Matrix m) {
        return null;
    }
}
