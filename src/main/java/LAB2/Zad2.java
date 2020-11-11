package LAB2;

import LAB1.Matrix;


import java.util.List;
import java.util.stream.Stream;


import static java.util.stream.Collectors.toList;

public class Zad2 {
    public static void main(String[] args) {
        Function1 f1 = new Function1();
        Function2 f2 = new Function2();
        Function3 f3 = new Function3(5, new int[]{1, 2, 3, 4, 5});
        Function4 f4 = new Function4();

        f1.setxMin(new Matrix(1.0, 1.0));
        f1.setX0(new Matrix(-1.9, 2.0));

        f2.setxMin(new Matrix(4.0, 2.0));
        f2.setX0(new Matrix(0.1, 0.3));

        f3.setxMin(new Matrix(1, 2, 3, 4, 5));
        f3.setX0(new Matrix(0.0, 0.0, 0.0, 0.0, 0.0));

        f4.setxMin(new Matrix(0.0, 0.0));
        f4.setX0(new Matrix(5.1, 1.1));

        List<AbstractFunction> functions = Stream.of(f1, f2, f3, f4).collect(toList());
        for (AbstractFunction f : functions) {
            Evaluate.evaluateCoordinateDescent(f);
            System.out.println(f.toString());
            Evaluate.evaluateSimplex(f);
            if (f == f4) {
                HookeJeeves.dx = 3;
            }
            Evaluate.evaluateHookeJeeves(f);
        }
    }
}
