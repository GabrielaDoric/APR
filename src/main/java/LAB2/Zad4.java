package LAB2;

import LAB1.Matrix;

public class Zad4 {

    public static void main(String[] args) {

        Function1 f1 = new Function1();

        System.out.println("x0=(0.5,0.5)");
        f1.setxMin(new Matrix(0.0, 0.0));
        f1.setX0(new Matrix(0.5, 0.5));
        int[] steps={1,10,20};
        for (int i=0;i<steps.length;i++){
            Simplex.step=steps[i];
            Evaluate.evaluateSimplex(f1);
        }

        f1.setX0(new Matrix(20.0, 20.0));
        System.out.println("x0=(20.0,20.0)");
        for (int i=0;i<steps.length;i++){
            Simplex.step=steps[i];
            Evaluate.evaluateSimplex(f1);
        }




//        System.out.println("x0=(20.0,20.0)");
//        f1.setxMin(new Matrix(0.0, 0.0));
//        f1.setX0(new Matrix(20.0, 20.0));
//
//
//        Evaluate.evaluateSimplex(f1);
//        Evaluate.evaluateHookeJeeves(f1);


    }
}
