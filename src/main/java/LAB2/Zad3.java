package LAB2;

import LAB1.Matrix;

public class Zad3 {
    public static void main(String[] args) {

        Function4 f4 = new Function4();
        f4.setxMin(new Matrix(0.0, 0.0));
        f4.setX0(new Matrix(5.0, 5.0));

        Evaluate.evaluateSimplex(f4);
        Evaluate.evaluateHookeJeeves(f4);
    }
}
