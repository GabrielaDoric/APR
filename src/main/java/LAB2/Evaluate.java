package LAB2;

import LAB1.Matrix;

public class Evaluate {

    public static void evaluateGoldenRatio(AbstractFunction f) {
        StringBuilder sb = new StringBuilder();

        Matrix m=f.getX0();
        double value=m.getElement(0,0);
        Matrix m1=new Matrix((value));

        Interval minInterval = GoldenSectionSearch.goldenRatio(f, 1, m1);
        double minValue=GoldenSectionSearch.goldenRatioValue(f,1,m1);
        System.out.println("Zlatni rez:");
        System.out.println("\txMin="+minValue);
        System.out.println("\tMin interval="+minInterval);

        System.out.println("\tBroj evaluacija:"+f.counter);

        f.resetCounter();

        System.out.println(sb.toString());
    }


    public static void evaluateCoordinateDescent(AbstractFunction f) {
        StringBuilder sb = new StringBuilder();

        Matrix m=f.getX0();
        double value=m.getElement(0,0);
        Matrix m1=new Matrix((value));

        Matrix xMin1 = new Matrix();
        Matrix xMin=CoordinateDescent.coordinateDescent(f,m,10e-6,xMin1);

//        sb.append("Interval: ").append("Min").append(xMin).append(" ").append("\n");

        System.out.println("Pretraživanje po koordinatnim osima:");
        System.out.println("\txMin="+xMin);


        System.out.println("\tBroj evaluacija:"+f.counter);
        f.resetCounter();

        System.out.println(sb.toString());
    }


    public static Matrix evaluateSimplex(AbstractFunction f) {
        StringBuilder sb = new StringBuilder();

        Matrix m=f.getX0();
        double value=m.getElement(0,0);
        Matrix m1=new Matrix((value));

        Matrix matrix = Simplex.simplex(f,m);
        System.out.println("Simplex:");
        System.out.println("\txMin="+matrix);


        System.out.println("\tBroj evaluacija:"+f.counter);

        f.resetCounter();

        System.out.println(sb.toString());

        return matrix;
    }

    public static void evaluateHookeJeeves(AbstractFunction f) {
        StringBuilder sb = new StringBuilder();

        Matrix xB6 = HookeJeeves.hookeJeeves(f, f.getX0());
        System.out.println("Hooke-Jeeves:");
        System.out.println("\txMin="+xB6);


        System.out.println("\tBroj evaluacija:"+f.counter);
        f.resetCounter();

        System.out.println(sb.toString());
    }
}