package LAB3;

import LAB1.Matrix;
import LAB2.AbstractFunction;
import LAB1.MatrixSolver;
import LAB2.GoldenSectionSearch;

public class NewtonRaphson {

    public static double epsilon = 10e-6;
    public static int maxNumOfIter = 50; //max useless iteration
    static Matrix gradient;
    static Matrix v;
    static int numOfIter=0;
    static double lambda;

    public static double euclideanNorm(Matrix gradient){
        double sum=0;
        for (int i=0;i<gradient.getColumnLength();i++){
            sum+=Math.pow(gradient.getElement(0,i),2);
        }
        double norm=Math.sqrt(sum);
        return norm;
    }

    public static Matrix newtonRaphson(AbstractFunction f, Matrix x0, boolean useGoldenRatio){
        Matrix x=x0;
        Matrix deltaX;
        double lastValue = 0.0;
        lambdaFunction lambdaFunction=new lambdaFunction();
        lambdaFunction.function=f;


        do{
            f.counter++;
            Matrix gradientMatrix=f.getGradientValue(x);
            Matrix hessianMatrix=f.getHessianValue(x);

            //Ax=b, tj hessianMatrix * delta=-gradientMatrix

            // sustav  A * x = g  =>  nablaH * deltaX = -nablaF
            Matrix b=gradientMatrix.multiply(-1);
            deltaX=MatrixSolver.solveLUP(hessianMatrix,b.transpose());

            if (useGoldenRatio==true){
                //find lambda tj minimum na pravcu
                lambdaFunction.x0=x;
                lambdaFunction.v0=deltaX;
                lambda= GoldenSectionSearch.goldenRatioValue(lambdaFunction,1,x);
                deltaX=deltaX.multiply(lambda);
                x=x.add(deltaX);
            }
            else{
                x=x.add(deltaX);
            }

            numOfIter+=1;
            if (lastValue != f.getFunctionValue(x)) {
                numOfIter = 0;
                lastValue = f.getFunctionValue(x);
            }
        }while (numOfIter<maxNumOfIter && euclideanNorm(deltaX)>epsilon);
        return x;
    }
}
