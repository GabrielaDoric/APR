package LAB3;
import LAB2.AbstractFunction;
import LAB1.Matrix;




public class Zadaci {

    public static void main(String[] args) {
//        zad1();
//        zad2();
//          zad3();
//        zad4();
          zad5();

    }

    public static void zad1(){
        System.out.println("ZADATAK 1");
        Function3 f3 = new Function3();

        f3.setxMin(new Matrix(2.0,-3.0));
        f3.setX0(new Matrix(0.0, 0.0));

        CheckAlgorithm.checkGradientDescent(f3,false);
        CheckAlgorithm.
                checkGradientDescent(f3,true);

        System.out.println("---------------------------------------------------------------------------------------");

    }

    public static void zad2(){
        System.out.println("ZADATAK 2");
        Function1 f1 = new Function1();
        f1.setxMin(new Matrix(1.0,1.0));
        f1.setX0(new Matrix(-1.9, 2.0));

        Function2 f2 = new Function2();
        f2.setxMin(new Matrix(4.0,2.0));
        f2.setX0(new Matrix(0.1, 0.3));

        System.out.println("F1");
        CheckAlgorithm.checkGradientDescent(f1,false);
        CheckAlgorithm.checkGradientDescent(f1,true);
        CheckAlgorithm.checkNewtonRaphson(f1,false);
        CheckAlgorithm.checkNewtonRaphson(f1,true);

        System.out.println("F2");
        CheckAlgorithm.checkGradientDescent(f2,false);
        CheckAlgorithm.checkGradientDescent(f2,true);
        CheckAlgorithm.checkNewtonRaphson(f2,false);
        CheckAlgorithm.checkNewtonRaphson(f2,true);

        System.out.println("---------------------------------------------------------------------------------------");

    }

    private static void zad3() {
        System.out.println("ZADATAK 3");
        Function1 f1 = new Function1();
        f1.setxMin(new Matrix(1.0,1.0));
        f1.setX0(new Matrix(-1.9, 2.0));

        Function2 f2 = new Function2();
        f2.setxMin(new Matrix(4.0,2.0));
        f2.setX0(new Matrix(0.1, 0.3));

        System.out.println("F1");
        CheckAlgorithm.checkBoxAlgorithm(f1);

//        System.out.println("F2");
//        CheckAlgorithm.checkBoxAlgorithm(f2);

        System.out.println("---------------------------------------------------------------------------------------");

    }


    private static void zad4() {
        System.out.println("ZADATAK 4");

        Function1 f1 = new Function1();
        f1.setxMin(new Matrix(1.0,1.0));
        f1.setX0(new Matrix(-1.9, 2.0));

        Function2 f2 = new Function2();
        f2.setxMin(new Matrix(4.0,2.0));
        f2.setX0(new Matrix(0.1, 0.3));

        System.out.println("F1");
        CheckAlgorithm.checkTransformation1(f1);

        System.out.println("F2");
        CheckAlgorithm.checkTransformation1(f2);

        System.out.println("---------------------------------------------------------------------------------------");

    }

    private static void zad5() {
        System.out.println("ZADATAK 5");
        Function4 f4 = new Function4();
        f4.setxMin(new Matrix(3.0,0.0));
        f4.setX0(new Matrix(5.0, 5.0));


        System.out.println("F4");
        CheckAlgorithm.checkTransformation2(f4);

        System.out.println("---------------------------------------------------------------------------------------");

    }
}
