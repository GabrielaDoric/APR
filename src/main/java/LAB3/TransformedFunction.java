package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;

import java.util.List;

public class TransformedFunction extends AbstractFunction {

    AbstractFunction nonTransformedFunction;
    List<AImplicitConstraint> implicitConstraints;
    List<AImplicitConstraint> implicitConstraintsEquals;
    double r;

    public TransformedFunction(AbstractFunction f, List<AImplicitConstraint> implicitConstraints, List<AImplicitConstraint> implicitConstraintsEqauls, double r) {
        this.nonTransformedFunction=f;
        this.implicitConstraints=implicitConstraints;
        this.implicitConstraintsEquals=implicitConstraintsEqauls;
        this.r=r;
    }

    @Override
    public double getFunctionValue(Matrix m) {

        double fValue=nonTransformedFunction.getFunctionValue(m);

        double sumaImpl=0;
        for (AImplicitConstraint constraint: implicitConstraints){
            double valueOfConstraint=constraint.calculate(m);
            if (valueOfConstraint<=0){
                sumaImpl+=-Double.MAX_VALUE;
            }else{
                sumaImpl+=Math.log(valueOfConstraint);
            }
        }
        sumaImpl=r*sumaImpl;

        double sumaEqaul=0;
        for (AImplicitConstraint constraint: implicitConstraintsEquals){
            sumaEqaul+=Math.pow((constraint.calculate(m)),2);
        }
        sumaEqaul=1/r*sumaEqaul;

        double x=fValue-sumaImpl+sumaEqaul;
        return x;
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
