package LAB2;

import LAB1.Matrix;

public class Zad1 {
    public static void main(String[] args) {
        int[] coefs={3};
        AbstractFunction f3 = new Function3(1,coefs);
        f3.setxMin(new Matrix(3.0));


        f3.setX0(new Matrix(10.0));
        System.out.println("X0="+ 10.0);
        Evaluate.evaluateGoldenRatio(f3);
        Evaluate.evaluateCoordinateDescent(f3);
        Evaluate.evaluateSimplex(f3);
        Evaluate.evaluateHookeJeeves(f3);


        f3.setX0(new Matrix(20.0));
        System.out.println("X0="+ 20.0);
        Evaluate.evaluateGoldenRatio(f3);
        Evaluate.evaluateCoordinateDescent(f3);
        Evaluate.evaluateSimplex(f3);
        Evaluate.evaluateHookeJeeves(f3);



    }
}
