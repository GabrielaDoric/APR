package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;
import LAB2.Simplex;
import LAB2.HookeJeeves;

import java.util.List;


//(3+1.5*x1-x2>=0) i (x2-1=0)


public class TransformationAlgorithm {


    public static Matrix TransformationAlgorithm(TransformedFunction f, Matrix x0) {
        Matrix x = null;
        double r=1;
        Matrix xOld=x0;
        int numOfiter=0;
        do {
            x = Simplex.simplex(f, xOld);
            System.out.println(x);
            if (criteria(x,xOld)){
                break;
            }
            r=r/10;
            f.r=r;
            xOld=x;
            numOfiter++;
        } while (true);

        return x;
    }

    private static boolean criteria(Matrix x, Matrix xOld) {
        for (int i=0;i<x.getColumnLength();i++){
            if (Math.abs(x.getElement(0,i)-xOld.getElement(0,i))>10e-6){
                return false;
            }
        }
        return true;
    }
}
