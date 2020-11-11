package LAB3;

import LAB1.Matrix;

public class ImplicitConstraint4 extends AImplicitConstraint {

    public double calculate(Matrix m) {
        double x1 = m.getElement(0, 0);
        double x2 = m.getElement(0, 1);
        return ( x2-x1);
    }

}